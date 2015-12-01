package com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog;

import static com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog.SpreadsheetImportDialogWorkerLogger._.error_load_layout;
import static com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog.SpreadsheetImportDialogWorkerLogger._.error_save_layout;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog;

/**
 * Logging for {@link SpreadsheetImportDialogWorker}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
final class SpreadsheetImportDialogWorkerLogger extends AbstractLogger {

    enum _ {

        error_save_layout("Error save current layout for {}: {}"),

        error_load_layout("Error load current layout for {}: {}");

        private String name;

        private _(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Sets the context of the logger to {@link SpreadsheetImportDialogWorker}.
     */
    public SpreadsheetImportDialogWorkerLogger() {
        super(SpreadsheetImportDialogWorker.class);
    }

    void errorSaveLayout(SimpleDialog dialog, Exception e) {
        error(error_save_layout, dialog, e.getLocalizedMessage());
    }

    void errorLoadLayout(SpreadsheetImportDialog dialog, Exception e) {
        error(error_load_layout, dialog, e.getLocalizedMessage());
    }
}
