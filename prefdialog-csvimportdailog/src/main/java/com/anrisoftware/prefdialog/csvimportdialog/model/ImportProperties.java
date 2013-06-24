package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import com.anrisoftware.prefdialog.annotations.ComboBox;
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

	@Inject
	ImportProperties(CharsetModel charsetModel, CharsetEditor charsetEditor,
			@Named("charsetDefaults") Collection<Charset> charsetDefaults,
			LocaleModel localeModel, StartRowModel startRowModel) {
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

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	@FieldComponent(order = 0, invalidText = "charset_unknown")
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

	@FieldComponent(order = 1)
	@ComboBox(model = "localeModel", rendererClass = LocaleRenderer.class)
	public Locale getLocale() {
		return locale;
	}

	public LocaleModel getLocaleModel() {
		return localeModel;
	}

	@FieldComponent(order = 2)
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
