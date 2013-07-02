package com.anrisoftware.prefdialog.csvimportdialog.importpanel;

import static com.anrisoftware.prefdialog.fields.FieldComponent.VALUE_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedVetoableChangeListener.lockedVetoableChangeListener;
import static java.awt.BorderLayout.CENTER;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
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
import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedVetoableChangeListener;
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

	private final LockedVetoableChangeListener valueListener;

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
		this.valueListener = lockedVetoableChangeListener(new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				valueListener.lock();
				updateColumns();
				valueListener.unlock();
			}
		});
	}

	private void updateColumns() throws PropertyVetoException {
		File file = new File(properties.getFile());
		if (!file.isFile()) {
			numberColumnsField.setValue(0);
			return;
		}
		try {
			CsvImporter importer = importerFactory.create(properties);
			readNumberColumns(importer);
		} catch (CsvImportException e) {
			throw log.errorRead(this, e);
		}
	}

	private void readNumberColumns(CsvImporter importer)
			throws CsvImportException, PropertyVetoException {
		List<Object> values = importer.call().getValues();
		if (values != null) {
			numberColumnsField.setValue(values.size());
		}
	}

	public void createPanel(Injector injector) {
		this.propertiesPanel = (VerticalPreferencesPanelField) fieldService
				.getFactory(injector).create(properties, "importPanel");
		propertiesPanel.createPanel(injector);
		propertiesPanel
				.addVetoableChangeListener(VALUE_PROPERTY, valueListener);
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

	public CsvPanelProperties getProperties() {
		return properties;
	}
}
