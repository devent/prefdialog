/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-spreadsheetimportdialog.
 *
 * prefdialog-spreadsheetimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-spreadsheetimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-spreadsheetimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel;

import static com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanelLogger._.error_read_columns;
import static com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanelLogger._.error_read_columns_message;

import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link SpreadsheetImportPanel}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
@Singleton
class SpreadsheetImportPanelLogger extends AbstractLogger {

    enum _ {

        error_read_columns("Error read columns"),

        error_read_columns_message("Error read columns in {}.");

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
     * Creates a logger for {@link SpreadsheetImportPanel}.
     */
    public SpreadsheetImportPanelLogger() {
        super(SpreadsheetImportPanel.class);
    }

    void errorRead(SpreadsheetImportPanel panel, IOException e) {
        logException(new PropertyVetoException(error_read_columns.toString(),
                null), error_read_columns_message, panel);
    }

}
