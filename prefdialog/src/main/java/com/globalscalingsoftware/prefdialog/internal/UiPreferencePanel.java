package com.globalscalingsoftware.prefdialog.internal;

import info.clearthought.layout.TableLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
@SuppressWarnings("serial")
class UiPreferencePanel extends javax.swing.JPanel {

	private JLabel childTitleLabel;
	private JButton restoreButton;
	private JButton applyButton;
	private JPanel bottomPanel;
	private JSeparator jSeparator1;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new UiPreferencePanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public UiPreferencePanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			TableLayout thisLayout = new TableLayout(new double[][] {
					{ TableLayout.FILL, 100.0, 100.0 },
					{ TableLayout.PREFERRED, TableLayout.PREFERRED,
							TableLayout.FILL, TableLayout.PREFERRED } });
			thisLayout.setHGap(5);
			thisLayout.setVGap(5);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(514, 406));
			this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
			{
				restoreButton = new JButton();
				this.add(restoreButton, "2, 3");
				restoreButton.setText("Restore");
			}
			{
				applyButton = new JButton();
				this.add(applyButton, "1, 3");
				applyButton.setText("Apply");
			}
			{
				jSeparator1 = new JSeparator();
				this.add(jSeparator1, "0, 1, 2, 1");
			}
			{
				bottomPanel = new JPanel();
				this.add(bottomPanel, "0, 2, 2, 2");
				bottomPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4,
						4));
			}
			{
				childTitleLabel = new JLabel();
				this.add(childTitleLabel, "0, 0, 2, 0");
				childTitleLabel.setText("Title");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JPanel getBottomPanel() {
		return bottomPanel;
	}

	public JLabel getChildTitleLabel() {
		return childTitleLabel;
	}

	public JButton getApplyButton() {
		return applyButton;
	}

	public JButton getRestoreButton() {
		return restoreButton;
	}

}
