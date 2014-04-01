/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.filechooser;

import static com.anrisoftware.prefdialog.fields.filechooser.FileChooserField.FILE_FIELD_NAME;
import static com.anrisoftware.prefdialog.fields.filechooser.FileChooserField.FILE_FIELD_PANEL_NAME;
import static com.anrisoftware.prefdialog.fields.filechooser.FileChooserField.OPEN_FILE_CHOOSER_NAME;
import static java.lang.String.format;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
class UiPanel extends JPanel {

    private JComponent fileField;

    private final JButton openFileChooser;

    private String name;

    /**
     * Create the panel.
     */
    UiPanel() {
        setLayout(new MigLayout("", "0[grow][]0", "0[]0"));

        fileField = new JTextField();
        fileField.setName(FileChooserField.FILE_FIELD_NAME);
        add(fileField, "cell 0 0,growx");

        openFileChooser = new JButton("...");
        openFileChooser.setName(OPEN_FILE_CHOOSER_NAME);
        add(openFileChooser, "cell 1 0");

    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        fileField.setEnabled(enabled);
        openFileChooser.setEnabled(enabled);
    }

    @Override
    public void setName(String name) {
        super.setName(format("%s-%s", name, FILE_FIELD_PANEL_NAME));
        this.name = name;
        fileField.setName(format("%s-%s", name, FILE_FIELD_NAME));
        openFileChooser.setName(format("%s-%s", name, OPEN_FILE_CHOOSER_NAME));
    }

    public void setFileField(JComponent field) {
        field.setName(format("%s-%s", name, FILE_FIELD_NAME));
        remove(fileField);
        add(field, "cell 0 0,growx");
        this.fileField = field;
    }

    public JComponent getFileField() {
        return fileField;
    }

    public JButton getOpenFileChooser() {
        return openFileChooser;
    }
}
