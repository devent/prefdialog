/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.dialogsworker;

import static javax.swing.SwingUtilities.invokeAndWait;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import javax.swing.JComponent;

import com.anrisoftware.resources.texts.api.Texts;

/**
 * Creates a dialog on the AWT event thread.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
public abstract class AbstractCreateDialogWorker<DialogType extends JComponent> {

    private DialogType dialog;

    private Locale locale;

    private Texts texts;

    private String dialogTitleResourceName;

    private String dialogTitle;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setTexts(Texts texts) {
        this.texts = texts;
    }

    public Texts getTexts() {
        return texts;
    }

    public void setDialogTitleResourceName(String name) {
        this.dialogTitleResourceName = name;
    }

    public String getDialogTitleResourceName() {
        return dialogTitleResourceName;
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public String getDialogTitle() {
        return dialogTitle;
    }

    /**
     * Returns the dialog. If not already created, the dialog is created on the
     * AWT event thread.
     *
     * @return the dialog.
     *
     * @throws CreateDialogWorkerException
     *             if there was an error creating the dialog.
     *
     * @throws CreateDialogInterrupedException
     *             if the dialog creation on the AWT event thread was
     *             interrupted.
     */
    public synchronized DialogType getDialog()
            throws CreateDialogWorkerException, CreateDialogInterrupedException {
        if (dialog == null) {
            this.dialog = createDialog0();
        }
        return dialog;
    }

    /**
     * Creates the dialog. The method is called on the AWT event thread.
     *
     * @return the dialog.
     */
    protected abstract DialogType createDialog();

    private DialogType createDialog0() throws CreateDialogWorkerException,
            CreateDialogInterrupedException {
        try {
            createDialogAWT();
        } catch (InvocationTargetException e) {
            throw new CreateDialogWorkerException(e.getCause());
        } catch (InterruptedException e) {
            throw new CreateDialogInterrupedException(e);
        }
        return dialog;
    }

    private void createDialogAWT() throws InterruptedException,
            InvocationTargetException {
        invokeAndWait(new Runnable() {

            @Override
            public void run() {
                dialog = createDialog();
            }
        });
    }

}
