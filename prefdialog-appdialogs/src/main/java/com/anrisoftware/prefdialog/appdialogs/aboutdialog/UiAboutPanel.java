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
package com.anrisoftware.prefdialog.appdialogs.aboutdialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

@SuppressWarnings("serial")
class UiAboutPanel extends JPanel {

    private final JTabbedPane aboutPane;

    /**
     * Create the panel.
     */
    UiAboutPanel() {
        setLayout(new BorderLayout(0, 0));

        aboutPane = new JTabbedPane(JTabbedPane.TOP);
        aboutPane.setName(AboutDialog.ABOUT_PANE_NAME);
        add(aboutPane);

    }

    public JTabbedPane getAboutPane() {
        return aboutPane;
    }

    public Component createAboutText(String text) {
        JScrollPane textPane = new JScrollPane();
        JLabel textLabel = new JLabel(text);
        textPane.setViewportView(textLabel);
        textLabel.setVerticalAlignment(SwingConstants.TOP);
        textLabel.setOpaque(true);
        textLabel.setBackground(UIManager.getColor("TextArea.background"));
        textLabel.setFont(textLabel.getFont().deriveFont(
                textLabel.getFont().getStyle() & ~Font.BOLD));
        return textPane;
    }
}
