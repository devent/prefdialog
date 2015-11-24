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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog;

import java.beans.PropertyVetoException;
import java.io.File;
import java.net.URI;

import javax.inject.Inject;

import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.filechoosers.FileChooserModel;
import com.anrisoftware.prefdialog.miscswing.indicestextfield.ArrayRange;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties.SpreadsheetPanelProperties;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the properties for the field.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
class PropertiesWorker {

    private static final String FILE_FIELD = "file";

    private static final String SHEET_NUMBER_FIELD = "sheetNumber";

    private static final String COLUMNS_FIELD = "columns";

    private static final String HAVE_HEADER_FIELD = "haveHeader";

    private static final String START_ROW_FIELD = "startRow";

    private static final String END_ROW_FIELD = "endRow";

    private final FieldComponent<?> field;

    private final SpreadsheetPanelProperties panelProperties;

    @Inject
    private PropertiesWorkerLogger log;

    /**
     * @see PropertiesWorkerFactory#create(FieldComponent,
     *      SpreadsheetPanelProperties)
     */
    @Inject
    PropertiesWorker(@Assisted FieldComponent<?> field,
            @Assisted SpreadsheetPanelProperties panelProperties) {
        this.field = field;
        this.panelProperties = panelProperties;
    }

    /**
     * @see SpreadsheetImportDialog#setProperties(SpreadsheetImportProperties)
     */
    @OnAwt
    public void setProperties(SpreadsheetImportProperties properties)
            throws PropertyVetoException {
        setFileProperty(properties, field);
        setSheetNumberProperty(properties, field);
        setColumnsProperty(properties, field);
        setHaveHeaderProperty(properties, field);
        setStartRowProperty(properties, field);
        setEndRowProperty(properties, field);
    }

    /**
     * @see SpreadsheetImportDialog#setProperties(SpreadsheetImportProperties)
     */
    @OnAwt
    public void setPropertiesNoChecks(SpreadsheetImportProperties properties) {
        try {
            setFileProperty(properties, field);
        } catch (PropertyVetoException e) {
        }
        try {
            setModelFileProperty(properties);
        } catch (PropertyVetoException e) {
        }
        try {
            setSheetNumberProperty(properties, field);
        } catch (PropertyVetoException e) {
            log.getLog().error(null, e);
        }
        try {
            setColumnsProperty(properties, field);
        } catch (PropertyVetoException e) {
            log.getLog().error(null, e);
        }
        try {
            setHaveHeaderProperty(properties, field);
        } catch (PropertyVetoException e) {
            log.getLog().error(null, e);
        }
        try {
            setStartRowProperty(properties, field);
        } catch (PropertyVetoException e) {
            log.getLog().error(null, e);
        }
        try {
            setEndRowProperty(properties, field);
        } catch (PropertyVetoException e) {
            log.getLog().error(null, e);
        }
    }

    private void setFileProperty(SpreadsheetImportProperties properties,
            FieldComponent<?> field) throws PropertyVetoException {
        FieldComponent<?> f = field.findField(FILE_FIELD);
        URI uri = properties.getFile();
        if (uri != null) {
            File file = new File(uri);
            f.setValue(file);
        }
    }

    private void setModelFileProperty(SpreadsheetImportProperties p)
            throws PropertyVetoException {
        FileChooserModel model = panelProperties.getFileProperties()
                .getFileModel();
        URI uri = p.getFile();
        if (uri != null) {
            File file = new File(uri);
            model.setFile(file);
        }
    }

    private void setSheetNumberProperty(SpreadsheetImportProperties p,
            FieldComponent<?> field) throws PropertyVetoException {
        FieldComponent<?> f = field.findField(SHEET_NUMBER_FIELD);
        f.setValue(p.getSheetNumber());
    }

    private void setColumnsProperty(SpreadsheetImportProperties p,
            FieldComponent<?> field) throws PropertyVetoException {
        FieldComponent<?> f = field.findField(COLUMNS_FIELD);
        f.setValue(new ArrayRange(p.getColumns()).calculateRange());
    }

    private void setHaveHeaderProperty(SpreadsheetImportProperties p,
            FieldComponent<?> field) throws PropertyVetoException {
        FieldComponent<?> f = field.findField(HAVE_HEADER_FIELD);
        f.setValue(p.isHaveHeader());
    }

    private void setStartRowProperty(SpreadsheetImportProperties p,
            FieldComponent<?> field) throws PropertyVetoException {
        FieldComponent<?> f = field.findField(START_ROW_FIELD);
        f.setValue(p.getStartRow());
    }

    private void setEndRowProperty(SpreadsheetImportProperties p,
            FieldComponent<?> field) throws PropertyVetoException {
        FieldComponent<?> f = field.findField(END_ROW_FIELD);
        f.setValue(p.getEndRow());
    }

}
