package com.anrisoftware.prefdialog.simpledialog;

import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.util.ServiceLoader.load;
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

import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelField;
import com.anrisoftware.resources.texts.api.Texts;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

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
	 * @param texts
	 *            the {@link Texts} resources.
	 * 
	 * @param parent
	 *            the parent {@link Injector}.
	 * 
	 * @see SimpleDialogFactory#create(Object, String)
	 * 
	 * @return the {@link CsvImportDialog}.
	 */
	public static SimpleDialog decorate(JDialog dialog, Object properties,
			String panelFieldName, Texts texts, Injector parent) {
		Injector injector = parent
				.createChildInjector(new SimpleDialogModule());
		SimpleDialog simpleDialog = injector.getInstance(
				SimpleDialogFactory.class).create(properties, panelFieldName);
		simpleDialog.setTexts(texts);
		simpleDialog.setParent(parent);
		simpleDialog.setDialog(dialog);
		dialog.pack();
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

	private static final String PREFERENCES_PANEL_NAME = "VerticalPreferencesPanel";

	static final String CANCEL_ACTION_NAME = "cancel_action";

	static final String APPROVE_ACTION_NAME = "approve_action";

	static final String RESTORE_ACTION_NAME = "restore_action";

	private final SimpleDialogLogger log;

	private final CancelAction cancelAction;

	private final ApproveAction approveAction;

	private final Object properties;

	private final String panelFieldName;

	private final UiDialogPanel dialogPanel;

	private final VetoableChangeSupport vetoableSupport;

	private final RestoreAction restoreAction;

	private Object parent;

	private VerticalPreferencesPanelField panel;

	private JDialog dialog;

	private Status status;

	private Texts texts;

	/**
	 * @see SimpleDialogFactory#create(Object, String)
	 */
	@Inject
	SimpleDialog(SimpleDialogLogger logger, UiDialogPanel dialogPanel,
			ApproveAction approveAction, CancelAction cancelAction,
			RestoreAction restoreAction, @Assisted Object properties,
			@Assisted String panelFieldName) {
		this.log = logger;
		this.dialogPanel = dialogPanel;
		this.properties = properties;
		this.panelFieldName = panelFieldName;
		this.approveAction = approveAction;
		this.cancelAction = cancelAction;
		this.restoreAction = restoreAction;
		this.vetoableSupport = new VetoableChangeSupport(this);
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
		if (panel != null) {
			panel.setTexts(texts);
		}
	}

	/**
	 * Sets the parent dependencies.
	 * 
	 * @param parent
	 *            the parent dependencies or {@code null}.
	 */
	public void setParent(Object parent) {
		this.parent = parent;
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
		panel = createPanel().createPanel((Injector) parent);
		dialogPanel.add(panel.getAWTComponent(), "cell 0 0");
		setupActions();
		return this;
	}

	private VerticalPreferencesPanelField createPanel() {
		return (VerticalPreferencesPanelField) findPanelFactory().create(
				properties, panelFieldName);
	}

	private FieldFactory<?> findPanelFactory() {
		for (FieldService service : load(FieldService.class)) {
			if (isPreferencesPanelService(service)) {
				return service.getFactory(parent);
			}
		}
		throw log.errorFindService(this, PREFERENCES_PANEL_NAME);
	}

	private boolean isPreferencesPanelService(FieldService service) {
		return service.getInfo().getAnnotationType().getSimpleName()
				.equals(PREFERENCES_PANEL_NAME);
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
	private void openDialog() {
		panel.requestFocus();
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
		try {
			panel.restoreInput();
		} catch (PropertyVetoException e) {
		}
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
	 * @see FieldComponent#addVetoableChangeListener(VetoableChangeListener)
	 * @see #STATUS_PROPERTY
	 */
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		panel.addVetoableChangeListener(listener);
		vetoableSupport.addVetoableChangeListener(listener);
	}

	/**
	 * @see FieldComponent#removeVetoableChangeListener(VetoableChangeListener)
	 * @see #STATUS_PROPERTY
	 */
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		panel.removeVetoableChangeListener(listener);
		vetoableSupport.removeVetoableChangeListener(listener);
	}

	/**
	 * @see FieldComponent#addVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see #STATUS_PROPERTY
	 */
	public void addVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		panel.addVetoableChangeListener(propertyName, listener);
		vetoableSupport.addVetoableChangeListener(propertyName, listener);
	}

	/**
	 * @see FieldComponent#removeVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see #STATUS_PROPERTY
	 */
	public void removeVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		panel.removeVetoableChangeListener(propertyName, listener);
		vetoableSupport.removeVetoableChangeListener(propertyName, listener);
	}

}
