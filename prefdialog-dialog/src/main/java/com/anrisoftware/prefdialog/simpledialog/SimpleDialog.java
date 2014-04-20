/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-dialog.
 *
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.simpledialog;

import static com.anrisoftware.prefdialog.simpledialog.SimpleDialogModule.getSimpleDialogFactory;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import static javax.swing.KeyStroke.getKeyStroke;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRootPane;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Simple dialog with approve, restore and cancel buttons.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class SimpleDialog {

	/**
	 * Status of the dialog.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 3.0
	 */
	public enum Status {

		/**
		 * The user approved the dialog.
		 */
		APPROVED,

		/**
		 * The user canceled the dialog.
		 */
		CANCELED;
	}

	/**
	 * Decorates the dialog.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param dialog
	 *            the {@link JDialog}.
	 * 
	 * @param fieldsPanel
	 *            the fields panel {@link Component} for the dialog.
	 * 
	 * @param texts
	 *            the {@link Texts} resources.
	 * 
	 * @see SimpleDialogFactory#create()
	 * 
	 * @return the {@link SimpleDialog}.
	 */
	@OnAwt
	public static SimpleDialog decorate(JDialog dialog, Component fieldsPanel) {
		SimpleDialog simpleDialog = getSimpleDialogFactory().create();
		simpleDialog.setFieldsPanel(fieldsPanel);
		simpleDialog.setDialog(dialog);
		return simpleDialog.createDialog();
	}

	/**
	 * Status property.
	 * 
	 * @see #setStatus(Status)
	 */
	public static final String STATUS_PROPERTY = "status";

	/**
	 * Approve button name.
	 */
	public static final String APPROVE_BUTTON_NAME = "approveButton";

	/**
	 * Restore button name.
	 */
	public static final String RESTORE_BUTTON_NAME = "restoreButton";

	/**
	 * Cancel button name.
	 */
	public static final String CANCEL_BUTTON_NAME = "cancelButton";

	static final String CANCEL_ACTION_NAME = "cancel_action";

	static final String APPROVE_ACTION_NAME = "approve_action";

	static final String RESTORE_ACTION_NAME = "restore_action";

	private final VetoableChangeSupport vetoableSupport;

	@Inject
	private UiDialogPanelFactory panelFactory;

	private CancelAction cancelAction;

	private ApproveAction approveAction;

	private UiDialogPanel dialogPanel;

	private RestoreAction restoreAction;

	private Component fieldsPanel;

	private JDialog dialog;

	private Status status;

	private Texts texts;

	private Locale locale;

	private Images images;

	/**
	 * @see SimpleDialogFactory#create()
	 */
	@Inject
	protected SimpleDialog() {
		this.vetoableSupport = new VetoableChangeSupport(this);
		this.locale = Locale.getDefault();
	}

	@Inject
	void setApproveAction(ApproveAction action) {
		this.approveAction = action;
	}

	@Inject
	void setCancelAction(CancelAction action) {
		this.cancelAction = action;
	}

	@Inject
	void setRestoreAction(RestoreAction action) {
		this.restoreAction = action;
	}

	/**
	 * Sets the panel component with the fields of the dialog.
	 * 
	 * @param panel
	 *            the panel {@link Component}.
	 */
	public void setFieldsPanel(Component panel) {
		this.fieldsPanel = panel;
	}

	/**
	 * Sets the texts resources for the dialog. There should be the resources
	 * available:
	 * <ul>
	 * <li>{@value #APPROVE_ACTION_NAME}
	 * <li>{@value #CANCEL_ACTION_NAME}
	 * <li>{@value #RESTORE_ACTION_NAME}
	 * </ul>
	 * 
	 * @param texts
	 *            the {@link Texts} resources.
	 */
	public void setTexts(Texts texts) {
		this.texts = texts;
	}

	/**
	 * Returns the texts resources for the dialog.
	 * 
	 * @return the {@link Texts} resources.
	 */
	public Texts getTexts() {
		return texts;
	}

	/**
	 * Sets the images resources for the dialog.
	 * 
	 * @param images
	 *            the {@link Images} resources.
	 */
	public void setImages(Images images) {
		this.images = images;
	}

	/**
	 * Returns the images resources for the dialog.
	 * 
	 * @return the {@link Images} resources.
	 */
	public Images getImages() {
		return images;
	}

	/**
	 * Sets the locale of the components of the dialog.
	 * 
	 * @param locale
	 *            the {@link Locale}.
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Returns the locale of the components of the dialog.
	 * 
	 * @return the {@link Locale}.
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Sets the approval action name. The action name is used to look up the
	 * action resources.
	 * 
	 * @param name
	 *            the action name {@link String}.
	 */
	public void setApproveActionName(String name) {
		approveAction.setActionName(name);
	}

	/**
	 * Sets the cancel action name. The action name is used to look up the
	 * action resources.
	 * 
	 * @param name
	 *            the action name {@link String}.
	 */
	public void setCancelActionName(String name) {
		cancelAction.setActionName(name);
	}

	/**
	 * Sets the restore action name. The action name is used to look up the
	 * action resources.
	 * 
	 * @param name
	 *            the action name {@link String}.
	 */
	public void setRestoreActionName(String name) {
		restoreAction.setActionName(name);
	}

	/**
	 * Sets the dialog.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param dialog
	 *            the {@link JDialog}.
	 */
	@OnAwt
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	/**
	 * Returns the dialog.
	 * 
	 * @return the {@link JDialog}.
	 */
	public JDialog getDialog() {
		return dialog;
	}

	/**
	 * Creates the simple dialog and all child fields.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @return this {@link SimpleDialog}.
	 */
	@OnAwt
	public SimpleDialog createDialog() {
		setupPanel();
		setupActions();
		setupDialog();
		setupKeyboardActions();
		return this;
	}

	private void setupDialog() {
		dialog.add(getAWTComponent());
		dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelAction.actionPerformed(null);
			}
		});
	}

	private void setupPanel() {
		this.dialogPanel = panelFactory.create();
		dialogPanel.add(fieldsPanel, "cell 0 0");
	}

	private void setupActions() {
		approveAction.setDialog(this);
		approveAction.setTexts(texts);
		cancelAction.setDialog(this);
		cancelAction.setTexts(texts);
		restoreAction.setDialog(this);
		restoreAction.setTexts(texts);
		cancelAction.setTexts(texts);
		getCancelButton().setAction(cancelAction);
		getApprovalButton().setAction(approveAction);
		getRestoreButton().setAction(restoreAction);
	}

	private void setupKeyboardActions() {
		JRootPane rootPane = dialog.getRootPane();
		rootPane.setDefaultButton(getApprovalButton());
		rootPane.registerKeyboardAction(cancelAction,
				getKeyStroke(VK_ESCAPE, 0), WHEN_IN_FOCUSED_WINDOW);
	}

	/**
	 * Adds a new action listener to the approval dialog button.
	 * 
	 * @param action
	 *            the {@link ActionListener}.
	 */
	public void addApprovalAction(ActionListener action) {
		getApprovalButton().addActionListener(action);
	}

	/**
	 * Adds a new action listener to the cancel dialog button.
	 * 
	 * @param action
	 *            the {@link ActionListener}.
	 */
	public void addCancelAction(ActionListener action) {
		getCancelButton().addActionListener(action);
	}

	/**
	 * Adds a new action listener to the restore dialog button.
	 * 
	 * @param action
	 *            the {@link ActionListener}.
	 */
	public void addRestoreAction(ActionListener action) {
		getRestoreButton().addActionListener(action);
	}

	/**
	 * Opens or closes the dialog.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param visible
	 *            set to {@code true} to open the dialog.
	 */
	public void setVisible(boolean visible) {
		if (visible) {
			openDialog();
		} else {
			closeDialog();
		}
	}

	/**
	 * Opens the dialog.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 */
	public void openDialog() {
		dialog.setVisible(true);
	}

	/**
	 * Closes the dialog.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 */
	public void closeDialog() {
		dialog.setVisible(false);
	}

	/**
	 * Restores the input of the dialog.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 */
	public void restoreDialog() {
	}

	/**
	 * Returns the component to be added in a container. The component contains
	 * the dialog button and fields.
	 * 
	 * @return the {@link Component}.
	 */
	public Component getAWTComponent() {
		return dialogPanel;
	}

	/**
	 * Returns the approval dialog button.
	 * 
	 * @return the approval {@link JButton}.
	 */
	public JButton getApprovalButton() {
		return dialogPanel.getApproveButton();
	}

	/**
	 * Returns the reset dialog button.
	 * 
	 * @return the reset {@link JButton}.
	 */
	public JButton getRestoreButton() {
		return dialogPanel.getRestoreButton();
	}

	/**
	 * Returns the cancel dialog button.
	 * 
	 * @return the cancel {@link JButton}.
	 */
	public JButton getCancelButton() {
		return dialogPanel.getCancelButton();
	}

	/**
	 * Sets the status of the dialog. The property change listeners are informed
	 * of the status change.
	 * 
	 * @param status
	 *            the {@link Status}.
	 * 
	 * @throws PropertyVetoException
	 *             if the status was vetoed by one of the property change
	 *             listener.
	 * 
	 * @see #STATUS_PROPERTY
	 */
	public void setStatus(Status status) throws PropertyVetoException {
		Status oldValue = this.status;
		vetoableSupport.fireVetoableChange(STATUS_PROPERTY, oldValue, status);
		this.status = status;
	}

	/**
	 * Returns the status of the dialog.
	 * 
	 * @return the {@link Status} or {@code null} if the dialog was not open
	 *         yet.
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @see #STATUS_PROPERTY
	 */
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		vetoableSupport.addVetoableChangeListener(listener);
	}

	/**
	 * @see #STATUS_PROPERTY
	 */
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		vetoableSupport.removeVetoableChangeListener(listener);
	}

	/**
	 * @see #STATUS_PROPERTY
	 */
	public void addVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		vetoableSupport.addVetoableChangeListener(propertyName, listener);
	}

	/**
	 * @see #STATUS_PROPERTY
	 */
	public void removeVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		vetoableSupport.removeVetoableChangeListener(propertyName, listener);
	}

}
