/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-dialog.
 *
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.dialogaction;

import static com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status.CANCELED;
import static javax.swing.SwingUtilities.invokeAndWait;

import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.Duration;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog;

/**
 * Creates a simple dialog in a background thread, opens it and waits for the
 * dialog to be canceled or approved. The dialog is only created if all
 * dependencies are set.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public abstract class AbstractDialogAction<ValueType, DialogType extends SimpleDialog>
        implements Callable<ValueType> {

    private static final Duration WAIT_DURATION = Duration.parse("PT3S");

    private final Runnable openDialog;

    @Inject
    private AbstractDialogActionLogger log;

    private JFrame frame;

    private DialogType createdDialog;

    private SwingWorker<ValueType, Runnable> worker;

    private DialogType dialog;

    private boolean creatingDialog;

    private boolean createAutomatic;

    private Object dialogFactory;

    protected AbstractDialogAction() {
        this.creatingDialog = false;
        this.createdDialog = null;
        this.createAutomatic = true;
        this.openDialog = new Runnable() {

            @Override
            public void run() {
                openDialog();
            }

        };
    }

    /**
     * Sets to create the dialog as soon as every dependency is set.
     *
     * @param automatic
     *            set to {@code true} to create the dialog as soon as every
     *            dependency is set.
     *
     * @see #canCreateDialog()
     */
    public void setCreateAutomatic(boolean automatic) {
        this.createAutomatic = automatic;
    }

    /**
     * Returns that the dialog shoud be created as soon as every dependency is
     * set.
     *
     * @return {@code true} if the dialog should be created soon as every
     *         dependency is set.
     */
    public boolean isCreateAutomatic() {
        return createAutomatic;
    }

    /**
     * Sets the parent frame for the dialog.
     *
     * @param frame
     *            the {@link JFrame} parent.
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
        if (createAutomatic) {
            createDialog();
        }
    }

    /**
     * Returns the parent frame for the dialog.
     *
     * @return the {@link JFrame} parent.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Sets the dialog that should be created.
     *
     * @param dialog
     *            the {@link SimpleDialog}.
     *
     * @see SimpleDialog#createDialog()
     */
    public void setDialog(DialogType dialog) {
        this.dialog = dialog;
        if (createAutomatic) {
            createDialog();
        }
    }

    /**
     * Sets the dialog factory that creates the dialog.
     *
     * @param dialogFactory
     *            the {@link Object} dialog factory.
     *
     * @see SimpleDialog#createDialog()
     */
    public void setDialogFactory(Object dialogFactory) {
        this.dialogFactory = dialogFactory;
        if (createAutomatic) {
            createDialog();
        }
    }

    /**
     * Returns the dialog factory that creates the dialog.
     *
     * @return the {@link Object} dialog factory.
     */
    @SuppressWarnings("unchecked")
    public <T> T getDialogFactory() {
        return (T) dialogFactory;
    }

    /**
     * Returns the not yet created dialog.
     *
     * @return the {@link SimpleDialog}.
     */
    public DialogType getNotCreatedDialog() {
        return dialog;
    }

    /**
     * Waits for the dialog to be created and opens the dialog. Waits until the
     * user either canceled or approved the dialog. After the dialog was
     * approved a value is created and returned.
     *
     * @see #createValue(SimpleDialog)
     */
    @Override
    public ValueType call() throws Exception {
        worker = new SwingWorker<ValueType, Runnable>() {

            @Override
            protected ValueType doInBackground() throws Exception {
                publish(openDialog);
                waitForDialog();
                return createValue();
            }

            @Override
            protected void process(List<Runnable> chunks) {
                for (Runnable runnable : chunks) {
                    runnable.run();
                }
            }

        };
        worker.execute();
        return worker.get();
    }

    private void openDialog() {
        DialogType dialog = getDialog();
        dialog.getDialog().setLocationRelativeTo(frame);
        try {
            dialog.openDialog();
        } catch (PropertyVetoException e) {
            log.openDialogVetoed(e);
        }
        synchronized (worker) {
            worker.notify();
        }
    }

    private void waitForDialog() throws InterruptedException {
        synchronized (worker) {
            worker.wait();
        }
    }

    /**
     * Creates the dialog. The dialog is only created if all dependencies are
     * set.
     *
     * @see #canCreateDialog()
     */
    public final void createDialog() {
        boolean creatingDialog = this.creatingDialog;
        if (!canCreateDialog() || creatingDialog) {
            return;
        }
        this.creatingDialog = true;
        try {
            createDialog0();
        } catch (InvocationTargetException e) {
            throw log.errorCreateDialog(this, e);
        } catch (InterruptedException e) {
            throw log.errorCreateDialogInterrupted(this, e);
        }
    }

    /**
     * Returns if all dependencies are set to create the dialog.
     *
     * @return {@code true} if the dialog can be created.
     */
    protected boolean canCreateDialog() {
        return log.checkCanCreateDialog(this);
    }

    private void createDialog0() throws InterruptedException,
            InvocationTargetException {
        invokeAndWait(new Runnable() {

            @Override
            public void run() {
                DialogType d = doCreateDialog(dialog);
                dialogCreated(d);
            }
        });
    }

    /**
     * Creates the dialog in a background thread. The dialog is only created if
     * all dependencies are set.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Is called in the AWT thread.
     *
     * @param dialog
     *            the {@link SimpleDialog}.
     *
     * @return the created {@link SimpleDialog}.
     */
    @OnAwt
    protected DialogType doCreateDialog(DialogType dialog) {
        dialog.createDialog();
        dialog.getDialog().pack();
        return dialog;
    }

    private void dialogCreated(DialogType dialog) {
        this.createdDialog = dialog;
        synchronized (this) {
            notify();
        }
    }

    /**
     * Waits for the dialog to be created and returns it.
     *
     * @return the {@link SimpleDialog}.
     *
     * @see #createDialog()
     */
    public DialogType getDialog() {
        if (!createAutomatic) {
            createDialog();
        }
        while (createdDialog == null) {
            waitDialogCreated();
        }
        return createdDialog;
    }

    private void waitDialogCreated() {
        try {
            synchronized (this) {
                wait(getDialogWaitDuration().getMillis());
            }
            log.checkDialogCreated(this, createdDialog);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the time duration to wait for the dialog to be created.
     *
     * @return the time {@link Duration}.
     */
    protected Duration getDialogWaitDuration() {
        return WAIT_DURATION;
    }

    private ValueType createValue() throws Exception {
        if (createdDialog.getStatus() == CANCELED) {
            return null;
        }
        return createValue(createdDialog);
    }

    /**
     * Created the value if the dialog was approved.
     *
     * @param dialog
     *            the {@link SimpleDialog}.
     *
     * @return the value.
     */
    protected abstract ValueType createValue(DialogType dialog)
            throws Exception;

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
