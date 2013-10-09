/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.simpledialog;

import static com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status.CANCELED;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.Duration;

import com.anrisoftware.globalpom.threads.api.Threads;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Creates a simple dialog in a background thread, opens it and waits for the
 * dialog to be canceled or approved. The dialog is only created if all
 * dependencies are set.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public abstract class AbstractDialogAction<Value, Dialog extends SimpleDialog>
		implements Callable<Value> {

	private static final Duration WAIT_DURATION = Duration.parse("PT1S");

	private AbstractDialogActionLogger log;

	private final Runnable openDialog;

	private JFrame frame;

	private Dialog createdDialog;

	private SwingWorker<Value, Runnable> worker;

	private Threads threads;

	private Dialog dialog;

	private boolean creatingDialog;

	protected AbstractDialogAction() {
		this.creatingDialog = false;
		this.openDialog = new Runnable() {

			@Override
			public void run() {
				openDialog();
			}

		};
	}

	/**
	 * Injects the logger.
	 * 
	 * @param logger
	 *            the {@link AbstractDialogActionLogger}.
	 */
	@Inject
	void setAbstractDialogActionLogger(AbstractDialogActionLogger logger) {
		this.log = logger;
		this.createdDialog = null;
	}

	/**
	 * Sets the threads to create the dialog in the background.
	 * 
	 * @param threads
	 *            the {@link Threads}.
	 */
	public void setThreads(Threads threads) {
		this.threads = threads;
		createDialog();
	}

	/**
	 * Returns the threads to create the dialog in the background.
	 * 
	 * @return the {@link Threads}.
	 */
	public Threads getThreads() {
		return threads;
	}

	/**
	 * Sets the parent frame for the dialog.
	 * 
	 * @param frame
	 *            the {@link JFrame} parent.
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
		createDialog();
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
	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
		createDialog();
	}

	/**
	 * Returns the not yet created dialog.
	 * 
	 * @return the {@link SimpleDialog}.
	 */
	public Dialog getNotCreatedDialog() {
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
	public Value call() throws Exception {
		worker = new SwingWorker<Value, Runnable>() {

			@Override
			protected Value doInBackground() throws Exception {
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
		Dialog dialog = getDialog();
		dialog.getDialog().pack();
		dialog.getDialog().setLocationRelativeTo(frame);
		dialog.openDialog();
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
	@OnAwt
	public final void createDialog() {
		if (!canCreateDialog() || creatingDialog) {
			return;
		}
		creatingDialog = true;
		doCreateDialog();
	}

	/**
	 * Creates the dialog in a background thread. The dialog is only created if
	 * all dependencies are set.
	 * 
	 * @see #canCreateDialog()
	 */
	@OnAwt
	protected void doCreateDialog() {
		CreateDialogAction action = new CreateDialogAction(dialog);
		threads.submit(action, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				dialogCreated(evt);
			}

		});
	}

	/**
	 * Returns if all dependencies are set to create the dialog.
	 * 
	 * @return {@code true} if the dialog can be created.
	 */
	protected boolean canCreateDialog() {
		return log.checkCanCreateDialog(this);
	}

	private void dialogCreated(PropertyChangeEvent evt) {
		Dialog dialog = log.fromFuture(this, evt);
		this.createdDialog = dialog;
		synchronized (this) {
			notify();
		}
		dialogCreated(dialog);
	}

	/**
	 * Called when the dialog is created.
	 * 
	 * @param dialog
	 *            the {@link SimpleDialog}.
	 */
	protected void dialogCreated(Dialog dialog) {
	}

	/**
	 * Waits for the dialog to be created and returns it.
	 * 
	 * @return the {@link SimpleDialog}.
	 * 
	 * @see #createDialog()
	 */
	public Dialog getDialog() {
		while (createdDialog == null) {
			try {
				synchronized (this) {
					wait(getDialogWaitDuration().getMillis());
				}
				log.checkDialogCreated(this, createdDialog);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		createdDialog = dialog;
		return createdDialog;
	}

	/**
	 * Returns the time duration to wait for the dialog to be created.
	 * 
	 * @return the time {@link Duration}.
	 */
	protected Duration getDialogWaitDuration() {
		return WAIT_DURATION;
	}

	private Value createValue() throws Exception {
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
	protected abstract Value createValue(Dialog dialog) throws Exception;

	@Override
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
