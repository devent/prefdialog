/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.statusbar;

import static java.lang.Short.MAX_VALUE;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXBusyLabel;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

@SuppressWarnings("serial")
class UiStatusPanel extends JPanel {

    private final JLabel messageLabel;
    private final JXBusyLabel busyLabel;
    private final JPanel cardPanel;
    private final JPanel busyPanel;
    private final JPanel progressPanel;
    private final JProgressBar progressBar;
    private final CardLayout cardLayout;

    /**
     * Create the panel.
     */
    @OnAwt
    UiStatusPanel() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        cardPanel = new JPanel();
        add(cardPanel);
        cardLayout = new CardLayout(0, 0);
        cardPanel.setLayout(cardLayout);

        busyPanel = new JPanel();
        cardPanel.add(busyPanel, StatusBar.BUSY_PANEL_CARD);
        busyPanel.setBorder(new EmptyBorder(2, 0, 2, 2));
        busyPanel.setLayout(new BoxLayout(busyPanel, BoxLayout.X_AXIS));

        busyLabel = new JXBusyLabel(new Dimension(15, 15));
        busyLabel.setVisible(false);
        busyLabel.setName("busyLabel");
        busyPanel.add(busyLabel);

        busyPanel.add(Box.createHorizontalStrut(2));

        messageLabel = new JLabel(" ");
        messageLabel
                .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        messageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        messageLabel.setFont(messageLabel.getFont().deriveFont(
                messageLabel.getFont().getStyle() & ~Font.BOLD));
        busyPanel.add(messageLabel);
        messageLabel.setMaximumSize(new Dimension(MAX_VALUE, 15));
        messageLabel.setName("messageLabel");

        progressPanel = new JPanel();
        cardPanel.add(progressPanel, StatusBar.PROGRESS_PANEL_CARD);
        progressPanel.setLayout(new BorderLayout(0, 0));

        progressBar = new JProgressBar();
        progressPanel.add(progressBar);
        progressBar.setName(StatusBar.PROGRESS_BAR_NAME);

    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    public JXBusyLabel getBusyLabel() {
        return busyLabel;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public JPanel getBusyPanel() {
        return busyPanel;
    }

    public JPanel getProgressPanel() {
        return progressPanel;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}
