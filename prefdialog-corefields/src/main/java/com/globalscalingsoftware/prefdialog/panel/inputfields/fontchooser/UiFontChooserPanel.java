package com.globalscalingsoftware.prefdialog.panel.inputfields.fontchooser;

import info.clearthought.layout.TableLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

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
class UiFontChooserPanel extends javax.swing.JPanel {

	private JButton openFileButton;

	private FontChooserComboBox fontBox;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new UiFontChooserPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	UiFontChooserPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			TableLayout thisLayout = new TableLayout(new double[][] {
					{ TableLayout.FILL, TableLayout.PREFERRED },
					{ TableLayout.FILL, TableLayout.PREFERRED } });
			thisLayout.setHGap(5);
			thisLayout.setVGap(5);
			this.setLayout(thisLayout);
			{
				fontBox = new FontChooserComboBox();
				this.add(fontBox, "0, 0");

				openFileButton = new JButton();
				this.add(getOpenFontChooserButton(), "1, 0");
				openFileButton.setText("...");
				openFileButton.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
				openFileButton.setContentAreaFilled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FontChooserComboBox getFontBox() {
		return fontBox;
	}

	public JButton getOpenFontChooserButton() {
		return openFileButton;
	}

}