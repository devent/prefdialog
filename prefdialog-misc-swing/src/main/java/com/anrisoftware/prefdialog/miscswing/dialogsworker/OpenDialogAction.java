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

import static javax.swing.SwingUtilities.invokeLater;
import static org.apache.commons.lang3.Validate.notNull;

import java.awt.Component;
import java.awt.Container;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import com.anrisoftware.resources.texts.api.Texts;

/**
 * Opens a dialog on the AWT event thread and waits for the user to close it.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public abstract class OpenDialogAction<DialogType extends Container, ResultType>
        implements Callable<ResultType> {

    private AbstractCreateDialogWorker<DialogType> dialogWorker;

    private CountDownLatch dialogLatch;

    private Component parentComponent;

    private Locale locale;

    private Texts texts;

    private String dialogTitleResourceName;

    private String dialogTitle;

    private ResultType dialogResult;

    public synchronized void setDialogWorker(
            AbstractCreateDialogWorker<DialogType> dialogWorker) {
        this.dialogWorker = dialogWorker;
    }

    public void setParentComponent(Component parentComponent) {
        this.parentComponent = parentComponent;
    }

    public Component getParent() {
        return parentComponent;
    }

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

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    /**
     * Opens the dialog on the AWT event thread and waits for the user to close
     * the dialog.
     *
     * @return the result value or {@code null}.
     */
    @Override
    public synchronized ResultType call() throws Exception {
        notNull(dialogWorker, "dialogWorker");
        DialogType dialog = createDialog(dialogWorker);
        dialogLatch = new CountDownLatch(1);
        openDialog(dialog, dialogWorker);
        dialogLatch.await();
        return dialogResult;
    }

    /**
     * Opens the dialog on the AWT event thread.
     *
     * @param dialog
     *            the dialog.
     *
     * @param dialogWorker
     *            the {@link AbstractCreateDialogWorker}.
     *
     * @return the return value.
     *
     * @since 3.5
     */
    protected abstract ResultType openDialogAWT(DialogType dialog,
            AbstractCreateDialogWorker<DialogType> dialogWorker);

    /**
     * Setups the dialog worker to create the dialog.
     *
     * @param dialogWorker
     *            the {@link AbstractCreateDialogWorker}.
     *
     * @return the created dialog.
     *
     * @throws CreateDialogWorkerException
     *             if there were any errors creating the dialog.
     *
     * @see AbstractCreateDialogWorker#getDialog()
     *
     * @since 3.5
     */
    protected DialogType createDialog(
            AbstractCreateDialogWorker<DialogType> dialogWorker)
            throws CreateDialogWorkerException {
        dialogWorker.setLocale(locale);
        dialogWorker.setTexts(texts);
        dialogWorker.setDialogTitleResourceName(dialogTitleResourceName);
        dialogWorker.setDialogTitle(dialogTitle);
        return dialogWorker.getDialog();
    }

    private void openDialog(final DialogType dialog,
            final AbstractCreateDialogWorker<DialogType> dialogWorker) {
        invokeLater(new Runnable() {

            @Override
            public void run() {
                dialogResult = openDialogAWT(dialog, dialogWorker);
                dialogLatch.countDown();
            }

        });
    }

}
