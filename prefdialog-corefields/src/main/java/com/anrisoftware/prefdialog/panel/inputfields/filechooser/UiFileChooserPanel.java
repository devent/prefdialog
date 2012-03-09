package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import info.clearthought.layout.TableLayout;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import com.google.inject.Inject;

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
class UiFileChooserPanel extends javax.swing.JPanel {
	private JButton openFileButton;
	private final FileTextField fileTextField;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new UiFileChooserPanel(null));
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Inject
	UiFileChooserPanel(FileTextField fileTextField) {
		super();
		this.fileTextField = fileTextField;
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
				int height = fileTextField.getPreferredSize().height;
				fileTextField.setPreferredSize(new Dimension(200, height));
				this.add(fileTextField, "0, 0");
			}
			{
				openFileButton = new JButton();
				this.add(getOpenFileButton(), "1, 0");
				openFileButton.setText("...");
				openFileButton.setBorder(BorderFactory
						.createEtchedBorder(BevelBorder.LOWERED));
				openFileButton.setContentAreaFilled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FileTextField getFileTextField() {
		return fileTextField;
	}

	public JButton getOpenFileButton() {
		return openFileButton;
	}

}
