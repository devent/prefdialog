package com.globalscalingsoftware.prefdialog.internal;

import info.clearthought.layout.TableLayout;

import java.awt.BorderLayout;

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
public class UiPreferencePanel extends javax.swing.JPanel {

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel topPanel;
	private JLabel childTitleLabel;
	private JButton restoreButton;
	private JButton applyButton;
	private JPanel buttonPanel;
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
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(514, 406));
			{
				buttonPanel = new JPanel();
				TableLayout buttonPanelLayout = new TableLayout(new double[][] {
						{ TableLayout.FILL, TableLayout.PREFERRED,
								TableLayout.PREFERRED, TableLayout.MINIMUM },
						{ TableLayout.MINIMUM, TableLayout.FILL,
								TableLayout.MINIMUM } });
				buttonPanelLayout.setHGap(5);
				buttonPanelLayout.setVGap(5);
				buttonPanel.setLayout(buttonPanelLayout);
				this.add(getButtonPanel(), BorderLayout.SOUTH);
				{
					applyButton = new JButton();
					buttonPanel.add(applyButton, "2, 1");
					applyButton.setText("Apply");
				}
				{
					restoreButton = new JButton();
					buttonPanel.add(restoreButton, "1, 1");
					restoreButton.setText("Restore");
				}
			}
			{
				topPanel = new JPanel();
				TableLayout topPanelLayout = new TableLayout(new double[][] {
						{ TableLayout.MINIMUM, TableLayout.FILL, 7.0 },
						{ TableLayout.FILL, TableLayout.PREFERRED,
								TableLayout.PREFERRED } });
				topPanelLayout.setHGap(5);
				topPanelLayout.setVGap(5);
				topPanel.setLayout(topPanelLayout);
				this.add(topPanel, BorderLayout.NORTH);
				{
					childTitleLabel = new JLabel();
					topPanel.add(childTitleLabel, "1, 1");
					childTitleLabel.setText("Title");
				}
				{
					jSeparator1 = new JSeparator();
					topPanel.add(jSeparator1, "1, 2");
				}
			}
			{
				bottomPanel = new JPanel();
				this.add(bottomPanel, BorderLayout.CENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JLabel getChildTitleLabel() {
		return childTitleLabel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public JButton getApplyButton() {
		return applyButton;
	}

	public JButton getRestoreButton() {
		return restoreButton;
	}

}
