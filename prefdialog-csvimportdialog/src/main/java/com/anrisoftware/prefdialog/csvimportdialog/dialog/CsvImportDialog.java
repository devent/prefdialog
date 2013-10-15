/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-csvimportdialog.
 * 
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * 
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import static com.anrisoftware.prefdialog.csvimportdialog.dialog.CsvImportDialogModule.getFactory;

import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.inject.Inject;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.anrisoftware.globalpom.dataimport.CsvImportProperties;
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanel;
import com.anrisoftware.prefdialog.csvimportdialog.importpaneldock.ImportPanelDock;
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
	 * @see #decorate(JDialog, JFrame, CsvImportProperties)
	 */
	public static CsvImportDialog decorateCsvImportDialog(JDialog dialog,
			JFrame frame, CsvImportProperties properties) {
		return decorate(dialog, frame, properties);
	}

	/**
	 * Decorates the dialog with the CSV import panel and dialog actions.
	 * 
	 * @param dialog
	 *            the {@link JDialog}.
	 * 
	 * @see CsvImportDialogFactory#create(JFrame, CsvImportProperties)
	 * 
	 * @return the {@link CsvImportDialog}.
	 */
	public static CsvImportDialog decorate(JDialog dialog, JFrame frame,
			CsvImportProperties properties) {
		CsvImportDialog importDialog;
		importDialog = getFactory().create(frame, properties);
		importDialog.setDialog(dialog);
		return importDialog;
	}

	private final CsvImportProperties properties;

	private final Dock dock;

	@Inject
	private ImportPanelDock importPanelDock;

	@Inject
	private Injector parent;

	private Texts texts;

	/**
	 * @see CsvImportDialogFactory#create(JFrame, CsvImportProperties)
	 */
	@Inject
	CsvImportDialog(DockFactory dockFactory, @Assisted JFrame frame,
			@Assisted CsvImportProperties properties) {
		this.dock = dockFactory.create(frame);
		this.properties = properties;
		setupDock();
	}

	private void setupDock() {
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

	@Inject
	void setTextsFactory(TextsFactory factory) {
		this.texts = factory.create(CsvImportDialog.class.getSimpleName());
	}

	/**
	 * Sets the parent dependencies.
	 * 
	 * @param parent
	 *            the parent dependencies or {@code null}.
	 */
	public void setParent(Injector parent) {
		this.parent = parent;
	}

	@Override
	@OnAwt
	public SimpleDialog createDialog() {
		setApproveActionName(IMPORT_ACTION_NAME);
		importPanelDock.createPanel(parent, properties);
		setFieldsPanel(dock.getAWTComponent());
		dock.addEditorDock(importPanelDock);
		setTexts(texts);
		return super.createDialog();
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
