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
package com.anrisoftware.prefdialog.csvimportdialog.importpanel;

import static com.anrisoftware.prefdialog.fields.FieldComponent.VALUE_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedChangeListener.lockedChangeListener;
import static java.awt.BorderLayout.CENTER;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportException;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImporter;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImporterFactory;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties.UseCustomQuoteAction;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.separatorproperties.UseCustomSeparatorAction;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedChangeListener;
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelField;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

public class CsvImportPanel {

	private final CsvImportPanelLogger log;

	private final JPanel container;

	private final Texts texts;

	private final CsvPanelProperties properties;

	private final FieldService fieldService;

	private VerticalPreferencesPanelField propertiesPanel;

	private final LockedChangeListener valueListener;

	private final CsvImporterFactory importerFactory;

	private FieldComponent<Component> numberColumnsField;

	@Inject
	CsvImportPanel(CsvImportPanelLogger logger, TextsFactory textsFactory,
			FieldService fieldService,
			CsvPanelPropertiesFactory propertiesFactory,
			CsvImporterFactory importerFactory, @Assisted JPanel container,
			@Assisted CsvImportProperties properties) {
		this.log = logger;
		this.fieldService = fieldService;
		this.container = container;
		this.properties = propertiesFactory.create(properties);
		this.texts = textsFactory.create(CsvImportPanel.class.getSimpleName());
		this.importerFactory = importerFactory;
		this.valueListener = lockedChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				valueListener.lock();
				updateColumns();
				valueListener.unlock();
			}
		});
	}

	private void updateColumns() {
		File file = new File(properties.getFile());
		if (!file.isFile()) {
			setNumberColumns(0);
			return;
		}
		try {
			CsvImporter importer = importerFactory.create(properties);
			readNumberColumns(importer);
		} catch (CsvImportException e) {
			log.errorRead(this, e);
		}
	}

	private void readNumberColumns(CsvImporter importer)
			throws CsvImportException {
		List<Object> values = importer.call().getValues();
		if (values != null) {
			setNumberColumns(values.size());
		}
	}

	private void setNumberColumns(int size) {
		try {
			numberColumnsField.setValue(size);
		} catch (PropertyVetoException e) {
		}
	}

	public void createPanel(Injector injector) {
		this.propertiesPanel = (VerticalPreferencesPanelField) fieldService
				.getFactory(injector).create(properties, "importPanel");
		propertiesPanel.createPanel(injector);
		propertiesPanel
				.addPropertyChangeListener(VALUE_PROPERTY, valueListener);
		setupPanel();
		setupActions();
	}

	private void setupActions() {
		UseCustomSeparatorAction useCustomSeparatorAction = properties
				.getSeparatorProperties().getUseCustomSeparatorAction();
		useCustomSeparatorAction.setCustomSeparatorCharField(propertiesPanel
				.findField("customSeparatorChar"));
		useCustomSeparatorAction.setSeparatorCharField(propertiesPanel
				.findField("separatorChar"));
		UseCustomQuoteAction useCustomQuoteAction = properties
				.getAdvancedProperties().getUseCustomQuoteAction();
		useCustomQuoteAction.setQuoteField(propertiesPanel
				.findField("quoteChar"));
		useCustomQuoteAction.setCustomQuoteField(propertiesPanel
				.findField("customQuoteChar"));
	}

	private void setupPanel() {
		propertiesPanel.setTexts(texts);
		container.setLayout(new BorderLayout());
		container.add(propertiesPanel.getAWTComponent(), CENTER);
		numberColumnsField = propertiesPanel.findField("numberColumns");
	}

	public Component getAWTComponent() {
		return container;
	}

	/**
	 * Returns the CSV properties of the panel.
	 * 
	 * @return the {@link CsvImportProperties}.
	 */
	public CsvImportProperties getProperties() {
		return properties;
	}

	/**
	 * Sets the focus on the CSV import panel.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 */
	public void requestFocus() {
		FieldComponent<Component> file = propertiesPanel.findField("file");
		file.requestFocus();
	}

	/**
	 * Restores the input of the panel to default values.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @throws PropertyVetoException
	 *             if the old user input is not valid.
	 */
	public void retoreInput() throws PropertyVetoException {
		propertiesPanel.restoreInput();
	}

	/**
	 * @see FieldComponent#addVetoableChangeListener(VetoableChangeListener)
	 */
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		propertiesPanel.addVetoableChangeListener(listener);
	}

	/**
	 * @see FieldComponent#removeVetoableChangeListener(VetoableChangeListener)
	 */
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		propertiesPanel.removeVetoableChangeListener(listener);
	}

	/**
	 * @see FieldComponent#addVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 */
	public void addVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		propertiesPanel.addVetoableChangeListener(propertyName, listener);
	}

	/**
	 * @see FieldComponent#removeVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 */
	public void removeVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		propertiesPanel.removeVetoableChangeListener(propertyName, listener);
	}

}
