package com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog;

import java.io.IOException;

import com.anrisoftware.prefdialog.miscswing.dialogsworker.CreateDialogWorkerException;

/**
 * Thrown if there was an error saving the current dialog layout.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
@SuppressWarnings("serial")
public class SaveLayoutException extends CreateDialogWorkerException {

    public SaveLayoutException(IOException e) {
        super("Error saving current layout", e);
    }

}
