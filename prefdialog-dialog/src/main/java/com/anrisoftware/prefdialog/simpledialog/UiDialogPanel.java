/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-dialog.
 *
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.simpledialog;

import static com.anrisoftware.prefdialog.simpledialog.SimpleDialog.APPROVE_BUTTON_NAME;
import static com.anrisoftware.prefdialog.simpledialog.SimpleDialog.CANCEL_BUTTON_NAME;
import static com.anrisoftware.prefdialog.simpledialog.SimpleDialog.RESTORE_BUTTON_NAME;
import static javax.swing.BoxLayout.LINE_AXIS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Simple dialog panel with approval, restore and cancel buttons.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
final class UiDialogPanel extends JPanel {

	private final JButton cancelButton;
	private final JButton restoreButton;
	private final JButton approveButton;
    private final JPanel buttonsPanel;
    private final Component approveStrut;
    private final Component restoreStrut;
    private final JPanel errorPanel;
    private final JLabel errorTextLabel;

	/**
	 * Create the dialog.
	 */
	@OnAwt
	UiDialogPanel() {
        setLayout(new MigLayout("", "[grow,fill]", "[grow,fill][][]"));

        errorPanel = new JPanel();
        add(errorPanel, "cell 0 1");
        errorPanel.setLayout(new BorderLayout(0, 0));

        errorTextLabel = new JLabel("Error");
        errorTextLabel.setName(SimpleDialog.ERROR_TEXT_NAME);
        errorTextLabel.setForeground(new Color(178, 34, 34));
        errorTextLabel
                .setFont(errorTextLabel.getFont().deriveFont(
                        errorTextLabel.getFont().getStyle() & ~Font.BOLD
                                | Font.ITALIC));
        errorTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        errorPanel.add(errorTextLabel);

        buttonsPanel = new JPanel();
        add(buttonsPanel, "cell 0 2,growx,aligny top");
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, LINE_AXIS));
		buttonsPanel.add(Box.createHorizontalGlue());

		approveButton = new JButton("Approve");
		approveButton.setName(APPROVE_BUTTON_NAME);
		buttonsPanel.add(approveButton);

        approveStrut = Box.createHorizontalStrut(4);
        buttonsPanel.add(approveStrut);

		restoreButton = new JButton("Reset");
		restoreButton.setName(RESTORE_BUTTON_NAME);
		buttonsPanel.add(restoreButton);

        restoreStrut = Box.createHorizontalStrut(4);
        buttonsPanel.add(restoreStrut);

		cancelButton = new JButton("Cancel");
		cancelButton.setName(CANCEL_BUTTON_NAME);
		buttonsPanel.add(cancelButton);
	}

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JButton getRestoreButton() {
		return restoreButton;
	}

	public JButton getApproveButton() {
		return approveButton;
	}

    public Component getApproveStrut() {
        return approveStrut;
    }

    public Component getRestoreStrut() {
        return restoreStrut;
    }

    public JLabel getErrorTextLabel() {
        return errorTextLabel;
    }

    public JPanel getErrorPanel() {
        return errorPanel;
    }
}
