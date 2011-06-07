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
package com.globalscalingsoftware.prefdialog.dialog.internal;

import info.clearthought.layout.TableLayout;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

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
class UiPreferencesDialog extends javax.swing.JDialog {

	private JPanel dialogPanel;
	private JSplitPane splitPane;
	private JScrollPane jScrollPane1;
	private JButton applyButton;
	private JButton okButton;
	private JButton cancelButton;
	private JSeparator jSeparator1;
	private JTree childTree;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame();
				UiPreferencesDialog inst = new UiPreferencesDialog(frame);
				inst.setVisible(true);
			}
		});
	}

	public UiPreferencesDialog(Frame owner) {
		super(owner);
		initGUI();
	}

	private void initGUI() {
		try {
			{
			}
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			{
				dialogPanel = new JPanel();
				TableLayout dialogPanelLayout = new TableLayout(new double[][] {
						{ TableLayout.FILL, TableLayout.PREFERRED,
								TableLayout.PREFERRED, TableLayout.PREFERRED },
						{ TableLayout.FILL, TableLayout.PREFERRED,
								TableLayout.PREFERRED } });
				dialogPanelLayout.setHGap(5);
				dialogPanelLayout.setVGap(5);
				dialogPanel.setLayout(dialogPanelLayout);
				getContentPane().add(getDialogPanel(), BorderLayout.CENTER);
				dialogPanel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6,
						6));
				{
					splitPane = new JSplitPane();
					dialogPanel.add(splitPane, "0, 0, 3, 0");
					splitPane.setBorder(BorderFactory.createEmptyBorder(0, 0,
							0, 0));
					{
						jScrollPane1 = new JScrollPane();
						splitPane.add(jScrollPane1, JSplitPane.LEFT);
						jScrollPane1.setBorder(BorderFactory.createEmptyBorder(
								3, 3, 3, 3));
						{
							childTree = new JTree();
							jScrollPane1.setViewportView(getChildTree());
						}
					}
				}
				{
					jSeparator1 = new JSeparator();
					dialogPanel.add(jSeparator1, "0, 1, 2, 1");
				}
				{
					cancelButton = new JButton();
					dialogPanel.add(cancelButton, "3, 2");
					cancelButton.setName("cancel");
					cancelButton.setText("Cancel");
				}
				{
					okButton = new JButton();
					dialogPanel.add(getOkButton(), "1, 2");
					okButton.setName("ok");
					okButton.setText("Ok");
				}
				{
					applyButton = new JButton();
					dialogPanel.add(getApplyButton(), "2, 2");
					applyButton.setText("Apply");
					applyButton.setName("apply");
				}
			}
			this.setSize(642, 522);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JPanel getDialogPanel() {
		return dialogPanel;
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public JTree getChildTree() {
		return childTree;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getApplyButton() {
		return applyButton;
	}

}