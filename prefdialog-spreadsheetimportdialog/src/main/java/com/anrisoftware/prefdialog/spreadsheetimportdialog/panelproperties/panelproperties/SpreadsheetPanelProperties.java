/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-spreadsheetimportdialog.
 *
 * prefdialog-spreadsheetimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-spreadsheetimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-spreadsheetimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties;

import java.io.File;
import java.net.URI;

import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;
import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.fileproperties.FileProperties;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.importproperties.ImportProperties;
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanel;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Spreadsheet properties panel.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
public class SpreadsheetPanelProperties implements SpreadsheetImportProperties {

	private Object importPanel;

	private final FileProperties fileProperties;

	private final ImportProperties importProperties;

	/**
	 * @see SpreadsheetPanelPropertiesFactory#create()
	 */
	@AssistedInject
	SpreadsheetPanelProperties(FileProperties fileProperties,
            ImportProperties importProperties) {
		this.fileProperties = fileProperties;
		this.importProperties = importProperties;
	}

	/**
	 * @see SpreadsheetPanelPropertiesFactory#create(SpreadsheetImportProperties)
	 */
	@AssistedInject
	SpreadsheetPanelProperties(FileProperties fileProperties,
			ImportProperties importProperties,
			@Assisted SpreadsheetImportProperties properties) {
		this.fileProperties = fileProperties;
		this.importProperties = importProperties;
		setupProperties(properties);
	}

    private void setupProperties(SpreadsheetImportProperties p) {
        if (p == null) {
			return;
		}
        fileProperties.setFile(new File(p.getFile()));
        importProperties.setSheetNumber(p.getSheetNumber());
        importProperties.setColumns(p.getColumns());
        importProperties.setStartRow(p.getStartRow());
        importProperties.setEndRow(p.getEndRow());
        importProperties.setHaveHeader(p.isHaveHeader());
	}

	public void setImportPanel(Object importPanel) {
		this.importPanel = importPanel;
	}

	@FieldComponent
	@VerticalPreferencesPanel
	public Object getImportPanel() {
		return importPanel;
	}

	@FieldComponent(order = 0)
	@Child
	public FileProperties getFileProperties() {
		return fileProperties;
	}

	@FieldComponent(order = 1)
	@Child
	public ImportProperties getImportProperties() {
		return importProperties;
	}

	@Override
	public URI getFile() {
		File file = fileProperties.getFile();
		return file == null ? null : file.toURI();
	}

	@Override
    public int getSheetNumber() {
        return importProperties.getSheetNumber();
    }

    @Override
    public int[] getColumns() {
        return importProperties.getColumns();
	}

	@Override
	public int getStartRow() {
		return importProperties.getStartRow();
	}

	@Override
    public int getEndRow() {
        return importProperties.getEndRow();
	}

    @Override
    public boolean isHaveHeader() {
        return importProperties.isHaveHeader();
    }

}
