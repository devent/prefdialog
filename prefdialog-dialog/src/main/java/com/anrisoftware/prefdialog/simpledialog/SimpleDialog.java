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

import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRootPane;

import com.anrisoftware.resources.texts.api.Texts;
import com.google.inject.Injector;

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
	public static SimpleDialog decorate(JDialog dialog, Component fieldsPanel,
			Texts texts, Injector parent) {
		SimpleDialog simpleDialog = getSimpleDialogFactory(parent).create();
		simpleDialog.setFieldsPanel(fieldsPanel);
		simpleDialog.setTexts(texts);
		simpleDialog.setDialog(dialog);
		return simpleDialog;
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

	private CancelAction cancelAction;

	private ApproveAction approveAction;

	private UiDialogPanel dialogPanel;

	private final VetoableChangeSupport vetoableSupport;

	private RestoreAction restoreAction;

	private Component fieldsPanel;

	private JDialog dialog;

	private Status status;

	private Texts texts;

	/**
	 * @see SimpleDialogFactory#create()
	 */
	@Inject
	SimpleDialog() {
		this.vetoableSupport = new VetoableChangeSupport(this);
	}

	@Inject
	void setDialogPanel(UiDialogPanel panel) {
		this.dialogPanel = panel;
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
	 * Sets the dialog.
	 * 
	 * @param dialog
	 *            the {@link JDialog}.
	 */
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
		dialog.add(getAWTComponent());
		setupDialog();
	}

	private void setupDialog() {
		setupKeyboardActions();
		dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelAction.actionPerformed(null);
			}
		});
	}

	private void setupKeyboardActions() {
		JRootPane rootPane = dialog.getRootPane();
		rootPane.setDefaultButton(getApprovalButton());
		rootPane.registerKeyboardAction(cancelAction,
				getKeyStroke(VK_ESCAPE, 0), WHEN_IN_FOCUSED_WINDOW);
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
	public SimpleDialog createDialog() {
		dialogPanel.add(fieldsPanel, "cell 0 0");
		setupActions();
		return this;
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
