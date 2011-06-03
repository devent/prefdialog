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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child;

import info.clearthought.layout.TableLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

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
class UiChildPanel extends javax.swing.JPanel {
	private JLabel childLabel;
	private JScrollPane jScrollPane1;
	private JPanel fieldsPanel;
	private JPanel scrollPanel;
	private JSeparator jSeparator1;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new UiChildPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public UiChildPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			TableLayout thisLayout = new TableLayout(new double[][] {
					{ TableLayout.FILL },
					{ TableLayout.PREFERRED, TableLayout.FILL } });
			thisLayout.setHGap(5);
			thisLayout.setVGap(5);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(416, 300));
			this.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
			{
				childLabel = new JLabel();
				this.add(childLabel, "0, 0");
				childLabel.setText("Child Label");
			}
			{
				jSeparator1 = new JSeparator();
				this.add(jSeparator1, "0, 1");
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1, "0, 1");
				jScrollPane1.setOpaque(false);
				Border border = BorderFactory.createEmptyBorder(0, 0, 0, 0);
				jScrollPane1.setViewportBorder(border);
				jScrollPane1.setBorder(border);
				{
					scrollPanel = new JPanel();
					jScrollPane1.setViewportView(scrollPanel);
					TableLayout scrollPanelLayout = new TableLayout(
							new double[][] { { TableLayout.FILL },
									{ TableLayout.PREFERRED, TableLayout.FILL } });
					scrollPanelLayout.setHGap(5);
					scrollPanelLayout.setVGap(5);
					scrollPanel.setLayout(scrollPanelLayout);
					scrollPanel.setBorder(BorderFactory.createEmptyBorder(6, 6,
							6, 6));
					{
						fieldsPanel = new JPanel();
						TableLayout fieldsPanelLayout = new TableLayout(
								new double[][] { { TableLayout.FILL },
										{ TableLayout.FILL, TableLayout.FILL } });
						fieldsPanelLayout.setHGap(0);
						fieldsPanelLayout.setVGap(0);
						fieldsPanel.setLayout(fieldsPanelLayout);
						scrollPanel.add(getFieldsPanel(), "0, 0");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JLabel getChildLabel() {
		return childLabel;
	}

	public JPanel getScrollPanel() {
		return scrollPanel;
	}

	public JPanel getFieldsPanel() {
		return fieldsPanel;
	}

}
