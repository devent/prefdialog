package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.annotations.CheckBox;
import com.anrisoftware.prefdialog.annotations.ComboBox;
import com.anrisoftware.prefdialog.annotations.FieldButton;
import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.charactermodel.CharacterRenderer;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.charactermodel.CustomCharacterEditor;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.charactermodel.CustomCharacterModel;
import com.anrisoftware.prefdialog.fields.historycombobox.HistoryComboBox;

public class AdvancedProperties {

	private Character quoteChar;

	private final QuoteCharModel quoteCharModel;

	private final CharacterRenderer quoteCharRenderer;

	private Character customQuoteChar;

	private final List<Character> customQuoteCharHistory;

	private final CustomCharacterEditor customQuoteCharEditor;

	private final CustomCharacterModel customQuoteCharModel;

	private boolean useCustomQuote;

	private final UseCustomQuoteAction useCustomQuoteAction;

	private LineEnd lineEndSymbols;

	private final LineEndModel lineEndSymbolsModel;

	private final LineEndRenderer lineEndSymbolsRenderer;

	@Inject
	AdvancedProperties(QuoteCharModel quoteCharModel,
			CharacterRenderer quoteCharRenderer,
			CustomCharacterModel customQuoteCharModel,
			CustomCharacterEditor customQuoteCharEditor,
			UseCustomQuoteAction useCustomQuoteAction,
			LineEndModel lineEndSymbolsModel,
			LineEndRenderer lineEndSymbolsRenderer) {
		this.quoteChar = quoteCharModel.getElementAt(0);
		this.quoteCharModel = quoteCharModel;
		this.quoteCharRenderer = quoteCharRenderer;
		this.customQuoteChar = '\0';
		this.customQuoteCharHistory = new ArrayList<Character>();
		this.customQuoteCharEditor = customQuoteCharEditor;
		this.customQuoteCharModel = customQuoteCharModel;
		this.useCustomQuote = false;
		this.useCustomQuoteAction = useCustomQuoteAction;
		this.lineEndSymbols = LineEnd.DEFAULT;
		this.lineEndSymbolsModel = lineEndSymbolsModel;
		this.lineEndSymbolsRenderer = lineEndSymbolsRenderer;
	}

	public void setQuoteChar(Character delimiter) {
		this.quoteChar = delimiter;
	}

	@FieldComponent(order = 0)
	@ComboBox(model = "quoteCharModel", renderer = "quoteCharRenderer")
	public Character getQuoteChar() {
		return quoteChar;
	}

	public QuoteCharModel getQuoteCharModel() {
		return quoteCharModel;
	}

	public CharacterRenderer getQuoteCharRenderer() {
		return quoteCharRenderer;
	}

	@FieldComponent(order = 1, showTitle = false)
	@FieldButton(action = "useCustomQuoteAction")
	@CheckBox
	public boolean isUseCustomQuote() {
		return useCustomQuote;
	}

	public void setUseCustomQuote(boolean use) {
		this.useCustomQuote = use;
	}

	public UseCustomQuoteAction getUseCustomQuoteAction() {
		return useCustomQuoteAction;
	}

	@FieldComponent(order = 2, showTitle = false, readOnly = true)
	@HistoryComboBox(editable = true, model = "customQuoteCharModel", editor = "customQuoteCharEditor", history = "customQuoteCharHistory")
	public Character getCustomQuoteChar() {
		return customQuoteChar;
	}

	public void setCustomQuoteChar(Character delimiter) {
		this.customQuoteChar = delimiter;
	}

	public CustomCharacterEditor getCustomQuoteCharEditor() {
		return customQuoteCharEditor;
	}

	public List<Character> getCustomQuoteCharHistory() {
		return customQuoteCharHistory;
	}

	public CustomCharacterModel getCustomQuoteCharModel() {
		return customQuoteCharModel;
	}

	public void setLineEndSymbols(LineEnd lineEndSymbols) {
		this.lineEndSymbols = lineEndSymbols;
	}

	@FieldComponent(order = 3)
	@ComboBox(editable = false, model = "lineEndSymbolsModel", renderer = "lineEndSymbolsRenderer")
	public LineEnd getLineEndSymbols() {
		return lineEndSymbols;
	}

	public LineEndModel getLineEndSymbolsModel() {
		return lineEndSymbolsModel;
	}

	public LineEndRenderer getLineEndSymbolsRenderer() {
		return lineEndSymbolsRenderer;
	}

	public void setupProperties(CsvImportProperties properties) {
		setupQuoteChar(properties);
		setupLineEndSymbols(properties.getEndOfLineSymbols());
	}

	private void setupLineEndSymbols(String symbols) {
		if (symbols.equals(System.getProperty("line.separator"))) {
			setLineEndSymbols(LineEnd.DEFAULT);
		} else {
			setLineEndSymbols(findLineEnd(symbols));
		}
	}

	private LineEnd findLineEnd(String symbols) {
		for (LineEnd value : LineEnd.values()) {
			if (value.equals(symbols)) {
				return value;
			}
		}
		return LineEnd.DEFAULT;
	}

	private void setupQuoteChar(CsvImportProperties properties) {
		QuoteCharModel model = getQuoteCharModel();
		char delimiter = properties.getQuote();
		if (model.getIndexOf(delimiter) != -1) {
			setQuoteChar(delimiter);
		} else {
			setUseCustomQuote(true);
			setCustomQuoteChar(delimiter);
		}
	}
}
