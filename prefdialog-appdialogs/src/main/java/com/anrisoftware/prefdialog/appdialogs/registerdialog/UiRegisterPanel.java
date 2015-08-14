/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-appdialogs.
 *
 * prefdialog-appdialogs is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-appdialogs is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-appdialogs. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.appdialogs.registerdialog;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXHyperlink;

/**
 * Registration dialog content panel.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@SuppressWarnings("serial")
final class UiRegisterPanel extends JPanel {

    private static final String EMAIL_TEXT = "<html><a href=\"email:order@anr-institute.com\">order@anr-institute.com</a></html>";

    private static final String REGISTRATION_TEXT = "<html>This is a demo version of this program. You have still <em>30 days</em> left for a registration. Please send the key to the E-Mail address below. Shortly you will receive a code to register this product, Please fill the fields <em>name</em> and <em>code</em> with your name and the received code, respectively, to register.</html>";

    private final JTextField keyField;

    private final JTextField nameField;

    private final JTextField codeField;

    private final JLabel codeLabel;

    private final JLabel nameLabel;

    private final JLabel keyLabel;

    private final JXHyperlink emailLink;

    private final JLabel textLabel;

    /**
     * Create the panel.
     */
    public UiRegisterPanel() {
        setLayout(new MigLayout("", "0[grow 50][grow 50]0",
                "0[][][][10px][][]0"));

        textLabel = new JLabel();
        textLabel.setFont(textLabel.getFont().deriveFont(Font.PLAIN));
        add(textLabel, "cell 0 0 2 1,grow");
        textLabel.setText(REGISTRATION_TEXT);

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        add(panel, "cell 0 1 2 1,grow");
        panel.setLayout(new MigLayout("", "[grow,center]", "[]"));

        emailLink = new JXHyperlink();
        panel.add(emailLink, "cell 0 0");
        emailLink.setText(EMAIL_TEXT);

        keyLabel = new JLabel("Key:");
        add(keyLabel, "flowx,cell 0 2");

        keyField = new JTextField();
        keyField.setHorizontalAlignment(JTextField.CENTER);
        keyLabel.setLabelFor(keyField);
        keyField.setEditable(false);
        add(keyField, "cell 1 2,growx");
        keyField.setColumns(10);

        nameLabel = new JLabel("Name:");
        add(nameLabel, "cell 0 4");

        nameField = new JTextField();
        nameField.setName(RegisterDialog.NAME_FIELD_NAME);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameLabel.setLabelFor(nameField);
        add(nameField, "cell 1 4,growx");
        nameField.setColumns(10);

        codeLabel = new JLabel("Code:");
        add(codeLabel, "cell 0 5");

        codeField = new JTextField();
        codeField.setName(RegisterDialog.CODE_FIELD_NAME);
        codeField.setHorizontalAlignment(JTextField.CENTER);
        codeLabel.setLabelFor(codeField);
        add(codeField, "cell 1 5,growx");
        codeField.setColumns(10);

    }

    public JTextField getCodeField() {
        return codeField;
    }

    public JLabel getCodeLabel() {
        return codeLabel;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JTextField getKeyField() {
        return keyField;
    }

    public JLabel getKeyLabel() {
        return keyLabel;
    }

    public JXHyperlink getEmailLink() {
        return emailLink;
    }

    public JLabel getTextLabel() {
        return textLabel;
    }
}
