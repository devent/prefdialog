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
package com.anrisoftware.prefdialog.appdialogs.confirmationdialogs;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
final class UiConfirmationPanel extends JPanel {

    private final JLabel textLabel;

    private final JLabel iconLabel;

    /**
     * Create the panel.
     */
    public UiConfirmationPanel() {
        setLayout(new MigLayout("", "[][grow]", "[grow]"));

        textLabel = new JLabel("Text");
        textLabel.setFont(textLabel.getFont().deriveFont(
                textLabel.getFont().getStyle() & ~Font.BOLD));
        add(textLabel, "cell 1 0,grow");

        iconLabel = new JLabel("Icon");
        add(iconLabel, "cell 0 0,alignx left,growy");

    }

    public JLabel getTextLabel() {
        return textLabel;
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }
}
