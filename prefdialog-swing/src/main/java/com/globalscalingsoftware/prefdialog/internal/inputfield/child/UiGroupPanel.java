/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import info.clearthought.layout.TableLayout;

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
class UiGroupPanel extends javax.swing.JPanel {
	private JLabel groupLabel;
	private JSeparator jSeparator1;
	private JPanel fieldsPanel;

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
					{ 3.0, TableLayout.PREFERRED, TableLayout.FILL },
					{ TableLayout.PREFERRED, TableLayout.FILL } });
			thisLayout.setHGap(5);
			thisLayout.setVGap(5);
			this.setLayout(thisLayout);
			{
				groupLabel = new JLabel();
				this.add(groupLabel, "0, 0, 1, 0");
				groupLabel.setText("Group Label");
			}
			{
				jSeparator1 = new JSeparator();
				this.add(jSeparator1, "2, 0, f, c");
			}
			{
				fieldsPanel = new JPanel();
				TableLayout fieldsPanelLayout = new TableLayout(new double[][] {
						{ TableLayout.FILL }, { TableLayout.FILL } });
				fieldsPanelLayout.setHGap(5);
				fieldsPanelLayout.setVGap(5);
				fieldsPanel.setLayout(fieldsPanelLayout);
				this.add(fieldsPanel, "1, 1, 2, 1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JLabel getGroupLabel() {
		return groupLabel;
	}

	public JPanel getFieldsPanel() {
		return fieldsPanel;
	}

}
