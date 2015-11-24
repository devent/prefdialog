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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.importproperties;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.annotations.CheckBox;
import com.anrisoftware.prefdialog.annotations.FieldButton;
import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.annotations.FormattedTextField;
import com.anrisoftware.prefdialog.annotations.Spinner;
import com.anrisoftware.prefdialog.miscswing.indicestextfield.ArrayRange;
import com.anrisoftware.prefdialog.miscswing.indicestextfield.IndicesTextFieldFormatterFactory;

/**
 * Shows the import properties fields.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
public class ImportProperties {

    @Inject
    private SheetNumberModel sheetNumberModel;

    @Inject
    private IndicesTextFieldFormatterFactory columnsFormatter;

    private int sheetNumber;

    private ArrayRange columns;

    private int startRow;

    private int endRow;

    private boolean haveHeader;

    @Inject
    ImportProperties() {
        this.sheetNumber = 0;
        this.columns = new ArrayRange(new int[] { 0 }, "");
        this.startRow = 0;
        this.endRow = 0;
        this.haveHeader = false;
    }

    @FieldComponent(order = 0, toolTip = "sheetNumber_short")
    @Spinner(model = "sheetNumberModel")
    public int getSheetNumber() {
        return sheetNumber;
    }

    public void setSheetNumber(int sheetNumber) {
        this.sheetNumber = sheetNumber;
    }

    public SheetNumberModel getSheetNumberModel() {
        return sheetNumberModel;
    }

    @FieldComponent(order = 1, toolTip = "columns_short")
    @FormattedTextField(editable = true, formatterFactory = "columnsFormatter")
    public ArrayRange getColumns() {
        return columns;
    }

    public void setColumns(ArrayRange columns) {
        this.columns = columns;
    }

    public IndicesTextFieldFormatterFactory getColumnsFormatter() {
        return columnsFormatter;
    }

    @FieldComponent(order = 2, toolTip = "startRow_short")
    @FormattedTextField(editable = true)
    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    @FieldComponent(order = 3, toolTip = "endRow_short")
    @FormattedTextField(editable = true)
    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public void setHaveHeader(boolean haveHeader) {
        this.haveHeader = haveHeader;
    }

    @FieldComponent(order = 4, toolTip = "haveHeader_short")
    @FieldButton
    @CheckBox
    public boolean isHaveHeader() {
        return haveHeader;
    }

}
