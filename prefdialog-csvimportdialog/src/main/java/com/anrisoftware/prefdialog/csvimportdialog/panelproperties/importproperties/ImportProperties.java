/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.importproperties;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import com.anrisoftware.prefdialog.annotations.CheckBox;
import com.anrisoftware.prefdialog.annotations.ComboBox;
import com.anrisoftware.prefdialog.annotations.FieldButton;
import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.annotations.Spinner;
import com.anrisoftware.prefdialog.fields.historycombobox.HistoryComboBox;

public class ImportProperties {

    private Charset charset;

    private final CharsetModel charsetModel;

    private final Collection<Charset> charsetDefaults;

    private final List<Charset> charsetsHistory;

    private final CharsetEditor charsetEditor;

    private Locale locale;

    private final LocaleModel localeModel;

    private int startRow;

    private final StartRowModel startRowModel;

    private boolean haveHeader;

    @Inject
    ImportProperties(CharsetModel charsetModel, CharsetEditor charsetEditor,
            @Named("charsetDefaults") Collection<Charset> charsetDefaults,
            LocaleModel localeModel, StartRowModel startRowModel) {
        this.haveHeader = false;
        this.charset = Charset.defaultCharset();
        this.charsetModel = charsetModel;
        this.charsetDefaults = charsetDefaults;
        this.charsetsHistory = new ArrayList<Charset>();
        this.charsetEditor = charsetEditor;
        this.locale = Locale.getDefault();
        this.localeModel = localeModel;
        this.startRow = (Integer) startRowModel.getValue();
        this.startRowModel = startRowModel;
    }

    public void setHaveHeader(boolean haveHeader) {
        this.haveHeader = haveHeader;
    }

    @FieldComponent(order = 0, showTitle = false)
    @FieldButton
    @CheckBox
    public boolean isHaveHeader() {
        return haveHeader;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    @FieldComponent(order = 1, invalidText = "charset_unknown")
    @HistoryComboBox(editable = true, model = "charsetModel", editor = "charsetEditor", history = "charsetsHistory", defaultItems = "charsetDefaults")
    public Charset getCharset() {
        return charset;
    }

    public CharsetModel getCharsetModel() {
        return charsetModel;
    }

    public Collection<Charset> getCharsetDefaults() {
        return charsetDefaults;
    }

    public List<Charset> getCharsetsHistory() {
        return charsetsHistory;
    }

    public CharsetEditor getCharsetEditor() {
        return charsetEditor;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @FieldComponent(order = 2)
    @ComboBox(model = "localeModel", rendererClass = LocaleRenderer.class)
    public Locale getLocale() {
        return locale;
    }

    public LocaleModel getLocaleModel() {
        return localeModel;
    }

    @FieldComponent(order = 3)
    @Spinner(model = "startRowModel")
    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public StartRowModel getStartRowModel() {
        return startRowModel;
    }

}
