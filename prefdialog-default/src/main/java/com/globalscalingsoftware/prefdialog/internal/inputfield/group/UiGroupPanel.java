package com.globalscalingsoftware.prefdialog.internal.inputfield.group;

import info.clearthought.layout.TableLayout;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class UiGroupPanel extends javax.swing.JPanel {
	private JLabel groupLabel;
	private JSeparator jSeparator1;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new UiGroupPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public UiGroupPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			TableLayout thisLayout = new TableLayout(new double[][] {
					{ 3.0, TableLayout.PREFERRED, TableLayout.FILL, 3.0 },
					{ 3.0, TableLayout.PREFERRED, 3.0, TableLayout.FILL } });
			thisLayout.setHGap(5);
			thisLayout.setVGap(5);
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			{
				groupLabel = new JLabel();
				this.add(getGroupLabel(), "1, 1");
				groupLabel.setText("Group Label");
			}
			{
				jSeparator1 = new JSeparator();
				this.add(jSeparator1, "2, 1, f, c");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JLabel getGroupLabel() {
		return groupLabel;
	}

}
