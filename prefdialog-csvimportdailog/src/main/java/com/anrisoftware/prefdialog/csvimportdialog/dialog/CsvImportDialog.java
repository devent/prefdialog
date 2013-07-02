package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.inject.Inject;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import com.anrisoftware.prefdialog.csvimportdialog.importpaneldock.ImportPanelDock;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;
import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockFactory;
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
		return properties;
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
	}

	public void addCancelAction(ActionListener action) {
		dialogPanel.getCancelButton().addActionListener(action);
	}

	public void addImportAction(ActionListener action) {
		dialogPanel.getImportButton().addActionListener(action);
	}

	public void openDialog() {
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
	 * Sets the status of the dialog.
	 * 
	 * @param status
	 *            the {@link Status}.
	 */
	public void setStatus(Status status) {
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
}
