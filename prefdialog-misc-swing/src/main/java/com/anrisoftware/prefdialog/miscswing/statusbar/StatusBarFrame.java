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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Frame to test the status bar.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class StatusBarFrame extends JFrame {

    private final JTextField messageField;
    private final JButton startProgressButton;
    private final JSpinner maxSpinner;
    private final JButton showMessageButton;
    private final JCheckBox busyBox;
    private boolean busyEnabled;
    private int maxValue;
    private StatusBar statusBar;
    private String messageText;
    private Object[] args;

    public StatusBarFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        this.messageText = "Some message <var1> with <var2> variables.";
        this.maxValue = 100;

        JPanel buttonsPanel = new JPanel();
        getContentPane().add(buttonsPanel, BorderLayout.CENTER);
        GridBagLayout gbl_buttonsPanel = new GridBagLayout();
        gbl_buttonsPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
        gbl_buttonsPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
        gbl_buttonsPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0,
                0.0, Double.MIN_VALUE };
        gbl_buttonsPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        buttonsPanel.setLayout(gbl_buttonsPanel);

        busyBox = new JCheckBox("Busy");
        GridBagConstraints gbc_busyBox = new GridBagConstraints();
        gbc_busyBox.insets = new Insets(0, 0, 5, 5);
        gbc_busyBox.gridx = 1;
        gbc_busyBox.gridy = 1;
        buttonsPanel.add(busyBox, gbc_busyBox);
        busyBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                busyEnabled = busyBox.isSelected();
            }
        });

        showMessageButton = new JButton("Show Message");
        GridBagConstraints gbc_showMessageButton = new GridBagConstraints();
        gbc_showMessageButton.insets = new Insets(0, 0, 5, 5);
        gbc_showMessageButton.gridx = 2;
        gbc_showMessageButton.gridy = 1;
        buttonsPanel.add(showMessageButton, gbc_showMessageButton);
        showMessageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showMessage();
            }
        });

        maxSpinner = new JSpinner();
        maxSpinner.setModel(new SpinnerNumberModel(maxValue, 1, 100, 1));
        GridBagConstraints gbc_maxSpinner = new GridBagConstraints();
        gbc_maxSpinner.insets = new Insets(0, 0, 5, 5);
        gbc_maxSpinner.gridx = 1;
        gbc_maxSpinner.gridy = 2;
        buttonsPanel.add(maxSpinner, gbc_maxSpinner);
        maxSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                maxValue = (Integer) maxSpinner.getValue();
            }
        });

        startProgressButton = new JButton("Start Progress");
        startProgressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startProgress();
            }

        });
        GridBagConstraints gbc_startProgressButton = new GridBagConstraints();
        gbc_startProgressButton.insets = new Insets(0, 0, 5, 5);
        gbc_startProgressButton.gridx = 2;
        gbc_startProgressButton.gridy = 2;
        buttonsPanel.add(startProgressButton, gbc_startProgressButton);

        JLabel messageLabel = new JLabel("Message:");
        GridBagConstraints gbc_messageLabel = new GridBagConstraints();
        gbc_messageLabel.insets = new Insets(0, 0, 0, 5);
        gbc_messageLabel.anchor = GridBagConstraints.EAST;
        gbc_messageLabel.gridx = 1;
        gbc_messageLabel.gridy = 4;
        buttonsPanel.add(messageLabel, gbc_messageLabel);

        messageField = new JTextField();
        messageField.setText(messageText);
        GridBagConstraints gbc_messageField = new GridBagConstraints();
        gbc_messageField.gridwidth = 2;
        gbc_messageField.insets = new Insets(0, 0, 0, 5);
        gbc_messageField.fill = GridBagConstraints.HORIZONTAL;
        gbc_messageField.gridx = 2;
        gbc_messageField.gridy = 4;
        buttonsPanel.add(messageField, gbc_messageField);
        messageField.setColumns(10);
        messageField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                messageText = messageField.getText();
            }
        });
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public void setStatusBar(StatusBar bar) {
        this.statusBar = bar;
        getContentPane().add(BorderLayout.SOUTH, bar.getComponent());
    }

    public void openFrame() {
        pack();
        setSize(new Dimension(480, 360));
        setVisible(true);
    }

    public JButton getStartProgressButton() {
        return startProgressButton;
    }

    public JSpinner getMaxSpinner() {
        return maxSpinner;
    }

    public JButton getShowMessageButton() {
        return showMessageButton;
    }

    public JCheckBox getBusyBox() {
        return busyBox;
    }

    public JTextField getMessageField() {
        return messageField;
    }

    private void showMessage() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                statusBar.setMessage(busyEnabled, messageText, args);
            }
        });
        thread.start();
    }

    private void startProgress() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i <= maxValue; i++) {
                    statusBar.setProgress(maxValue, i, messageText, args);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        thread.start();
    }
}
