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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Preview panel.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
@SuppressWarnings("serial")
class UiPreviewPanel extends JPanel {

    final JTable dataTable;

    /**
     * Create the panel.
     */
    UiPreviewPanel() {
        setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        dataTable = new JTable();
        scrollPane.setViewportView(dataTable);

    }
}
