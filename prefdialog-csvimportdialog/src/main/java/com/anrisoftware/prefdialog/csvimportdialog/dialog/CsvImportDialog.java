/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-csvimportdialog.
 *
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import static com.anrisoftware.prefdialog.csvimportdialog.dialog.CsvImportDialogModule.getFactory;

import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.inject.Inject;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.anrisoftware.globalpom.dataimport.CsvImportProperties;
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanel;
import com.anrisoftware.prefdialog.csvimportdialog.importpaneldock.ImportPanelDock;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelProperties;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockFactory;
import com.anrisoftware.prefdialog.miscswing.docks.api.FocusChangedEvent;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

/**
 * Dialog to import CSV data.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class CsvImportDialog extends SimpleDialog {

	private static final String IMPORT_ACTION_NAME = "import_action";

	/**
	 * @see #decorate(CsvImportProperties, JDialog, JFrame)
	 */
	public static CsvImportDialog decorateCsvImportDialog(
			CsvImportProperties properties, JDialog dialog, JFrame frame) {
		return decorate(properties, dialog, frame);
	}

	/**
	 * Decorates the dialog with the CSV import panel and dialog actions.
	 * 
	 * @param properties
	 *            the {@link CsvImportProperties}.
	 * 
	 * @param dialog
	 *            the {@link JDialog}.
	 * 
	 * @param frame
	 *            the {@link JFrame} owner or {@code null}.
	 * 
	 * @return the {@link CsvImportDialog}.
	 * 
	 * @see CsvImportDialogFactory#create(CsvImportProperties)
	 */
	@OnAwt
	public static CsvImportDialog decorate(CsvImportProperties properties,
			JDialog dialog, JFrame frame) {
		CsvImportDialog importDialog;
		importDialog = getFactory().create(properties);
		importDialog.setDialog(dialog);
		importDialog.createDialog(frame);
		return importDialog;
	}

	private final CsvImportProperties properties;

	private final ChangeListener requestFocusListener;

	private final Dock dock;

	@Inject
	private ImportPanelDock importPanelDock;

	@Inject
	private Injector parent;

	@Inject
	private ValidListener validListener;

	@Inject
	private PropertiesWorkerFactory propertiesWorkerFactory;

	private Texts texts;

	/**
	 * @see CsvImportDialogFactory#create(CsvImportProperties)
	 */
	@Inject
	CsvImportDialog(DockFactory dockFactory,
			@Assisted CsvImportProperties properties) {
		this.dock = dockFactory.create();
		this.properties = properties;
		this.requestFocusListener = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (e instanceof FocusChangedEvent) {
					dock.requestFocus(importPanelDock);
					importPanelDock.getImportPanel().requestFocus();
				}
			}
		};
		setupDock();
	}

	@Inject
	void setTextsFactory(TextsFactory factory) {
		this.texts = factory.create(CsvImportDialog.class.getSimpleName());
	}

	/**
	 * Sets the parent dependency modules.
	 * 
	 * @param parent
	 *            the parent {@link Inject}.
	 */
	public void setParent(Injector parent) {
		this.parent = parent;
	}

	@Override
	@OnAwt
	public CsvImportDialog createDialog() {
		return createDialog(null);
	}

	/**
	 * @see #createDialog()
	 * 
	 * @param frame
	 *            the parent {@link JFrame} owner or {@code null}.
	 */
	@OnAwt
	public CsvImportDialog createDialog(JFrame frame) {
		dock.createDock(frame);
		setApproveActionName(IMPORT_ACTION_NAME);
		importPanelDock.createPanel(parent, properties);
		setFieldsPanel(dock.getAWTComponent());
		dock.addEditorDock(importPanelDock);
		setTexts(texts);
		validListener.installDialog(this);
		return (CsvImportDialog) super.createDialog();
	}

	/**
	 * Returns the dock.
	 *
	 * @return the {@link Dock}.
	 */
	public Dock getDock() {
		return dock;
	}

	/**
	 * Sets the properties to the dialog fields.
	 * 
	 * @param properties
	 *            the {@link CsvImportProperties}.
	 * 
	 * @throws PropertyVetoException
	 *             if one of the values of the properties are vetoed.
	 */
	@OnAwt
	public void setProperties(CsvImportProperties properties)
			throws PropertyVetoException {
		FieldComponent<?> field = getImportPanelField();
		CsvPanelProperties p = (CsvPanelProperties) getProperties();
		propertiesWorkerFactory.create(field, p).setProperties(properties);
	}

	/**
	 * Sets the properties to the dialog fields. if one of the values of the
	 * properties is vetoed the value is skipped.
	 * 
	 * @param properties
	 *            the {@link CsvImportProperties}.
	 */
	@OnAwt
	public void setPropertiesNoChecks(CsvImportProperties properties) {
		FieldComponent<?> field = getImportPanelField();
		CsvPanelProperties p = (CsvPanelProperties) getProperties();
		propertiesWorkerFactory.create(field, p).setPropertiesNoChecks(
				properties);
	}

	/**
	 * Returns the CSV import properties.
	 * 
	 * @return the {@link CsvImportProperties}.
	 */
	public CsvImportProperties getProperties() {
		return importPanelDock.getImportPanel().getProperties();
	}

	/**
	 * Returns the import panel dock.
	 *
	 * @return the {@link ImportPanelDock}.
	 */
	public ImportPanelDock getImportPanelDock() {
		return importPanelDock;
	}

	/**
	 * Returns the CSV import panel field.
	 *
	 * @return the {@link FieldComponent}.
	 */
	public FieldComponent<JPanel> getImportPanelField() {
		return importPanelDock.getImportPanel().getPanel();
	}

	@Override
	public void openDialog() {
		importPanelDock.getImportPanel().requestFocus();
		super.openDialog();
	}

	@Override
	public void restoreDialog() {
		try {
			importPanelDock.restoreInput();
		} catch (PropertyVetoException e) {
		}
	}

	private void setupDock() {
		dock.addStateChangedListener(requestFocusListener);
	}

	/**
	 * @see CsvImportPanel#addVetoableChangeListener(VetoableChangeListener)
	 * @see SimpleDialog#addVetoableChangeListener(VetoableChangeListener)
	 */
	@Override
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		importPanelDock.addVetoableChangeListener(listener);
		super.addVetoableChangeListener(listener);
	}

	/**
	 * @see CsvImportPanel#removeVetoableChangeListener(VetoableChangeListener)
	 * @see SimpleDialog#removeVetoableChangeListener(VetoableChangeListener)
	 */
	@Override
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		importPanelDock.removeVetoableChangeListener(listener);
		super.removeVetoableChangeListener(listener);
	}

	/**
	 * @see CsvImportPanel#addVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see SimpleDialog#addVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 */
	@Override
	public void addVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		importPanelDock.addVetoableChangeListener(propertyName, listener);
		super.addVetoableChangeListener(propertyName, listener);
	}

	/**
	 * @see CsvImportPanel#removeVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see SimpleDialog#removeVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 */
	@Override
	public void removeVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		importPanelDock.removeVetoableChangeListener(propertyName, listener);
		super.removeVetoableChangeListener(propertyName, listener);
	}

}
