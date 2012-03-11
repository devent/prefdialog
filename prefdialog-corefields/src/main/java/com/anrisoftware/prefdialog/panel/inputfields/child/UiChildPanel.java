/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields.child;

import info.clearthought.layout.TableLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
/**
 * Contains a {@link JLabel} for the title and a {@link JScrollPane} for the
 * child fields.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
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
					{ TableLayout.PREFERRED, TableLayout.PREFERRED,
							TableLayout.FILL } });
			thisLayout.setHGap(5);
			thisLayout.setVGap(5);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(416, 300));
			this.setBorder(BorderFactory.createEmptyBorder(6, 6, 0, 6));
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
				this.add(jScrollPane1, "0, 2");
				jScrollPane1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,
						0));
				{
					scrollPanel = new JPanel();
					TableLayout scrollPanelLayout = new TableLayout(
							new double[][] { { TableLayout.FILL },
									{ TableLayout.PREFERRED, TableLayout.FILL } });
					scrollPanelLayout.setHGap(5);
					scrollPanelLayout.setVGap(5);
					scrollPanel.setLayout(scrollPanelLayout);
					jScrollPane1.setViewportView(getScrollPanel());
					scrollPanel.setBorder(BorderFactory.createEmptyBorder(0, 6,
							0, 6));
					{
						fieldsPanel = new JPanel();
						TableLayout fieldsPanelLayout = new TableLayout(
								new double[][] { { TableLayout.FILL },
										{ TableLayout.FILL } });
						fieldsPanelLayout.setHGap(5);
						fieldsPanelLayout.setVGap(5);
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
