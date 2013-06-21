package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.annotations.Spinner;
import com.anrisoftware.prefdialog.fields.historycombobox.HistoryComboBox;

public class ImportProperties {

	private Charset charset;

	private final CharsetModel charsetModel;

	private final List<Charset> charsetsHistory;

	private final Set<Charset> charsetDefaults;

	private Locale locale;

	private final LocaleModel localeModel;

	private final List<Locale> localesHistory;

	private final Collection<Locale> localeDefaults;

	private int startRow;

	private final StartRowModel startRowModel;

	@Inject
	ImportProperties(CharsetModel charsetModel, LocaleModel localeModel,
			StartRowModel startRowModel,
			@Named("charsetDefaults") Set<Charset> charsetDefaults,
			@Named("localeDefaults") Collection<Locale> localeDefaults) {
		this.charset = Charset.defaultCharset();
		this.charsetModel = charsetModel;
		this.charsetsHistory = new ArrayList<Charset>();
		this.charsetDefaults = charsetDefaults;
		this.locale = Locale.getDefault();
		this.localeModel = localeModel;
		this.localesHistory = new ArrayList<Locale>();
		this.localeDefaults = localeDefaults;
		this.startRow = (Integer) startRowModel.getValue();
		this.startRowModel = startRowModel;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	@FieldComponent
	@HistoryComboBox(model = "charsetModel", history = "charsetsHistory", defaultItems = "charsetDefaults")
	public Charset getCharset() {
		return charset;
	}

	public CharsetModel getCharsetModel() {
		return charsetModel;
	}

	public List<Charset> getCharsetsHistory() {
		return charsetsHistory;
	}

	public Set<Charset> getCharsetDefaults() {
		return charsetDefaults;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@FieldComponent
	@HistoryComboBox(model = "localeModel", rendererClass = LocaleRenderer.class, history = "localesHistory", defaultItems = "localeDefaults")
	public Locale getLocale() {
		return locale;
	}

	public LocaleModel getLocaleModel() {
		return localeModel;
	}

	public List<Locale> getLocalesHistory() {
		return localesHistory;
	}

	public Collection<Locale> getLocaleDefaults() {
		return localeDefaults;
	}

	@FieldComponent
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
