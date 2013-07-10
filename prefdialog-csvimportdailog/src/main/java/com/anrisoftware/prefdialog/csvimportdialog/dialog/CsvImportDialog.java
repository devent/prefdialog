package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

import javax.inject.Inject;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanel;
import com.anrisoftware.prefdialog.csvimportdialog.importpaneldock.ImportPanelDock;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;
import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockFactory;
import com.anrisoftware.prefdialog.miscswing.docks.api.FocusChangedEvent;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

/**
 * Cancel and import actions dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class CsvImportDialog {

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
	 * Status property.
	 * 
	 * @see #setStatus(Status)
	 */
	public static final String STATUS_PROPERTY = "status";

	/**
	 * Decorates the dialog with the CSV import dialog.
	 * 
	 * @param dialog
	 *            the {@link JDialog}.
	 * 
	 * @param injector
	 *            the parent {@link Injector}.
	 * 
	 * @see CsvImportDialogFactory#create(JFrame, CsvImportProperties)
	 * 
	 * @return the {@link CsvImportDialog}.
	 */
	public static CsvImportDialog decorate(JDialog dialog, JFrame frame,
			CsvImportProperties properties, Injector injector) {
		Injector dialogInjector = injector
				.createChildInjector(new CsvImportDialogModule());
		CsvImportDialog importDialog = dialogInjector.getInstance(
				CsvImportDialogFactory.class).create(frame, properties);
		importDialog.createPanel(injector);
		importDialog.setDialog(dialog);
		dialog.pack();
		return importDialog;
	}

	private final CancelAction cancelAction;

	private final ImportAction importAction;

	private final CsvImportProperties properties;

	private final Dock dock;

	private final Texts texts;

	private final ImportPanelDock importPanelDock;

	private final UiPanel dialogPanel;

	private final VetoableChangeSupport vetoableSupport;

	private JDialog dialog;

	private Status status;

	/**
	 * @see CsvImportDialogFactory#create(JFrame, CsvImportProperties)
	 */
	@Inject
	CsvImportDialog(UiPanel dialogPanel, TextsFactory textsFactory,
			CancelAction cancelAction, ImportAction importAction,
			DockFactory dockFactory, ImportPanelDock importPanelDock,
			@Assisted JFrame frame, @Assisted CsvImportProperties properties) {
		this.texts = textsFactory.create(CsvImportDialog.class.getSimpleName());
		this.dock = dockFactory.create(frame);
		this.importPanelDock = importPanelDock;
		this.dialogPanel = dialogPanel;
		this.properties = properties;
		this.cancelAction = cancelAction;
		this.importAction = importAction;
		this.status = null;
		this.vetoableSupport = new VetoableChangeSupport(this);
		setupDock();
		setupActions();
	}

	private void setupDock() {
		dialogPanel.add(dock.getAWTComponent(), BorderLayout.CENTER);
		dock.addEditorDock(importPanelDock);
	}

	private void setupActions() {
		importAction.setDialog(this);
		importAction.setTexts(texts);
		cancelAction.setTexts(texts);
		cancelAction.setDialog(this);
		dialogPanel.getCancelButton().setAction(cancelAction);
		dialogPanel.getImportButton().setAction(importAction);
	}

	public void createPanel(Injector injector) {
		importPanelDock.createPanel(injector, properties);
	}

	public Dock getDock() {
		return dock;
	}

	public CsvImportProperties getProperties() {
		return importPanelDock.getImportPanel().getProperties();
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
		dialog.add(getAWTComponent());
		setupDialog(dialog);
	}

	private void setupDialog(JDialog dialog) {
		JRootPane rootPane = dialog.getRootPane();
		rootPane.setDefaultButton(dialogPanel.getImportButton());
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		rootPane.registerKeyboardAction(cancelAction, stroke,
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cancelAction.actionPerformed(null);
			}
		});
		dock.addStateChangedListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (e instanceof FocusChangedEvent) {
					dock.requestFocus(importPanelDock);
					importPanelDock.getImportPanel().requestFocus();
				}
			}
		});
	}

	public void addCancelAction(ActionListener action) {
		dialogPanel.getCancelButton().addActionListener(action);
	}

	public void addImportAction(ActionListener action) {
		dialogPanel.getImportButton().addActionListener(action);
	}

	public void openDialog() {
		importPanelDock.getImportPanel().requestFocus();
		dialog.setVisible(true);
	}

	public void closeDialog() {
		dialog.setVisible(false);
	}

	public Component getAWTComponent() {
		return dialogPanel;
	}

	public ImportPanelDock getImportPanelDock() {
		return importPanelDock;
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
	 * @see CsvImportPanel#addVetoableChangeListener(VetoableChangeListener)
	 * @see #STATUS_PROPERTY
	 */
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		importPanelDock.addVetoableChangeListener(listener);
		vetoableSupport.addVetoableChangeListener(listener);
	}

	/**
	 * @see CsvImportPanel#removeVetoableChangeListener(VetoableChangeListener)
	 * @see #STATUS_PROPERTY
	 */
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		importPanelDock.removeVetoableChangeListener(listener);
		vetoableSupport.removeVetoableChangeListener(listener);
	}

	/**
	 * @see CsvImportPanel#addVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see #STATUS_PROPERTY
	 */
	public void addVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		importPanelDock.addVetoableChangeListener(propertyName, listener);
		vetoableSupport.addVetoableChangeListener(propertyName, listener);
	}

	/**
	 * @see CsvImportPanel#removeVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see #STATUS_PROPERTY
	 */
	public void removeVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		importPanelDock.removeVetoableChangeListener(propertyName, listener);
		vetoableSupport.removeVetoableChangeListener(propertyName, listener);
	}

}
