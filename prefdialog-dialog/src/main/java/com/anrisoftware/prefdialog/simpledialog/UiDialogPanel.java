package com.anrisoftware.prefdialog.simpledialog;

import static javax.swing.BoxLayout.LINE_AXIS;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
class UiDialogPanel extends JPanel {

	private static final String CANCEL_BUTTON_NAME = "cancelButton";
	private static final String RESET_BUTTON_NAME = "resetButton";
	private static final String APPROVE_BUTTON_NAME = "approveButton";
	private final JButton cancelButton;
	private final JButton resetButton;
	private final JButton approveButton;

	/**
	 * Create the dialog.
	 */
	public UiDialogPanel() {
		setLayout(new MigLayout("", "[grow,fill]", "[grow,fill][]"));

		JPanel buttonsPanel = new JPanel();
		add(buttonsPanel, "cell 0 1,growx,aligny top");
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, LINE_AXIS));
		buttonsPanel.add(Box.createHorizontalGlue());

		approveButton = new JButton("Approve");
		approveButton.setName(APPROVE_BUTTON_NAME);
		buttonsPanel.add(approveButton);

		buttonsPanel.add(Box.createHorizontalStrut(4));

		resetButton = new JButton("Reset");
		resetButton.setName(RESET_BUTTON_NAME);
		buttonsPanel.add(resetButton);

		buttonsPanel.add(Box.createHorizontalStrut(4));

		cancelButton = new JButton("Cancel");
		cancelButton.setName(CANCEL_BUTTON_NAME);
		buttonsPanel.add(cancelButton);
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JButton getResetButton() {
		return resetButton;
	}

	public JButton getApproveButton() {
		return approveButton;
	}

}
