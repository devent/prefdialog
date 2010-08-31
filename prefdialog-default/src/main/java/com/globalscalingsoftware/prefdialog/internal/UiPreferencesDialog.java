package com.globalscalingsoftware.prefdialog.internal;

import info.clearthought.layout.TableLayout;

import java.awt.BorderLayout;

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

	public UiPreferencesDialog(JFrame frame) {
		super(frame);
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
						{ TableLayout.FILL, 100.0, 100.0 },
						{ TableLayout.FILL, TableLayout.PREFERRED,
								TableLayout.PREFERRED } });
				dialogPanelLayout.setHGap(5);
				dialogPanelLayout.setVGap(5);
				dialogPanel.setLayout(dialogPanelLayout);
				getContentPane().add(getDialogPanel(), BorderLayout.CENTER);
				dialogPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4,
						4));
				{
					splitPane = new JSplitPane();
					dialogPanel.add(getSplitPane(), "0, 0, 2, 0");
					{
						jScrollPane1 = new JScrollPane();
						splitPane.add(jScrollPane1, JSplitPane.LEFT);
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
					dialogPanel.add(getCancelButton(), "2, 2");
					cancelButton.setText("Cancel");
				}
				{
					okButton = new JButton();
					dialogPanel.add(getOkButton(), "1, 2");
					okButton.setText("Ok");
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

}
