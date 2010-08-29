package com.globalscalingsoftware.prefdialog.internal;

import info.clearthought.layout.TableLayout;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

import com.globalscalingsoftware.prefdialog.IPreferencesDialogOwner;

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
public class UiPreferencesDialog extends javax.swing.JDialog {
	private JSplitPane mainSplitPane;
	private JScrollPane jScrollPane1;
	private JSeparator jSeparator1;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel buttonsPanel;
	private JPanel childPanel;
	private JPanel jPanel1;
	private JTree preferencesTree;

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

	public UiPreferencesDialog(IPreferencesDialogOwner owner) {
		this(owner.getFrame());
	}

	public UiPreferencesDialog(JFrame owner) {
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
				mainSplitPane = new JSplitPane();
				getContentPane().add(getMainSplitPane(), BorderLayout.CENTER);
				{
					jPanel1 = new JPanel();
					mainSplitPane.add(jPanel1, JSplitPane.LEFT);
					BorderLayout jPanel1Layout = new BorderLayout();
					jPanel1.setLayout(jPanel1Layout);
					jPanel1.setPreferredSize(new java.awt.Dimension(86, 675));
					{
						jScrollPane1 = new JScrollPane();
						jPanel1.add(jScrollPane1, BorderLayout.CENTER);
						jScrollPane1.setOpaque(false);
						{
							preferencesTree = new JTree();
							jScrollPane1.setViewportView(getPreferencesTree());
						}
					}
				}
				{
					childPanel = new JPanel();
					mainSplitPane.add(getChildPanel(), JSplitPane.RIGHT);
				}
			}
			{
				buttonsPanel = new JPanel();
				TableLayout buttonsPanelLayout = new TableLayout(
						new double[][] {
								{ TableLayout.FILL, TableLayout.PREFERRED,
										TableLayout.PREFERRED,
										TableLayout.MINIMUM },
								{ TableLayout.FILL, TableLayout.PREFERRED,
										TableLayout.MINIMUM } });
				buttonsPanelLayout.setHGap(5);
				buttonsPanelLayout.setVGap(5);
				buttonsPanel.setLayout(buttonsPanelLayout);
				getContentPane().add(getButtonsPanel(), BorderLayout.SOUTH);
				{
					cancelButton = new JButton();
					buttonsPanel.add(cancelButton, "2, 1");
					cancelButton.setText("Cancel");
				}
				{
					okButton = new JButton();
					buttonsPanel.add(okButton, "1, 1");
					okButton.setText("Ok");
				}
				{
					jSeparator1 = new JSeparator();
					buttonsPanel.add(jSeparator1, "0, 0, 2, 0");
				}
			}
			this.setSize(721, 703);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSplitPane getMainSplitPane() {
		return mainSplitPane;
	}

	public JTree getPreferencesTree() {
		return preferencesTree;
	}

	public JPanel getChildPanel() {
		return childPanel;
	}

	public JPanel getButtonsPanel() {
		return buttonsPanel;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JButton getOkButton() {
		return okButton;
	}

}
