package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.annotations.CheckBox;
import com.anrisoftware.prefdialog.annotations.ComboBox;
import com.anrisoftware.prefdialog.annotations.FieldButton;
import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.fields.historycombobox.HistoryComboBox;

public class SeparatorProperties {

	private Character separatorChar;

	private final SeparatorCharModel separatorCharModel;

	private final SeparatorCharRenderer separatorCharRenderer;

	private final SeparatorCharEditor separatorCharEditor;

	private Character customSeparatorChar;

	private final List<Character> customSeparatorCharsHistory;

	private final SeparatorCharEditor customSeparatorCharEditor;

	private final CustomSeparatorCharModel customSeparatorCharModel;

	private boolean useCustomSeparator;

	private final UseCustomSeparatorAction useCustomSeparatorAction;

	@Inject
	SeparatorProperties(SeparatorCharModel separatorCharModel,
			SeparatorCharRenderer separatorCharRenderer,
			SeparatorCharEditor separatorCharEditor,
			SeparatorCharEditor customSeparatorCharEditor,
			CustomSeparatorCharModel customSeparatorCharModel,
			UseCustomSeparatorAction useCustomSeparatorAction) {
		this.separatorChar = separatorCharModel.getElementAt(0);
		this.separatorCharModel = separatorCharModel;
		this.separatorCharRenderer = separatorCharRenderer;
		this.separatorCharEditor = separatorCharEditor;
		this.customSeparatorChar = '\0';
		this.customSeparatorCharsHistory = new ArrayList<Character>();
		this.customSeparatorCharEditor = customSeparatorCharEditor;
		this.customSeparatorCharModel = customSeparatorCharModel;
		this.useCustomSeparator = false;
		this.useCustomSeparatorAction = useCustomSeparatorAction;
	}

	public void setSeparatorChar(Character separatorChar) {
		this.separatorChar = separatorChar;
	}

	@FieldComponent
	@ComboBox(model = "separatorCharModel", renderer = "separatorCharRenderer", editor = "separatorCharEditor")
	public Character getSeparatorChar() {
		return separatorChar;
	}

	public SeparatorCharModel getSeparatorCharModel() {
		return separatorCharModel;
	}

	public SeparatorCharRenderer getSeparatorCharRenderer() {
		return separatorCharRenderer;
	}

	public SeparatorCharEditor getSeparatorCharEditor() {
		return separatorCharEditor;
	}

	@FieldComponent(showTitle = false)
	@FieldButton(action = "useCustomSeparatorAction")
	@CheckBox
	public boolean isUseCustomSeparator() {
		return useCustomSeparator;
	}

	public void setUseCustomSeparator(boolean useCustomSeparator) {
		this.useCustomSeparator = useCustomSeparator;
	}

	public UseCustomSeparatorAction getUseCustomSeparatorAction() {
		return useCustomSeparatorAction;
	}

	@FieldComponent(showTitle = false, readOnly = true)
	@HistoryComboBox(editable = true, model = "customSeparatorCharModel", editor = "customSeparatorCharEditor", history = "customSeparatorCharsHistory")
	public Character getCustomSeparatorChar() {
		return customSeparatorChar;
	}

	public void setCustomSeparatorChar(Character customSeparatorChar) {
		this.customSeparatorChar = customSeparatorChar;
	}

	public SeparatorCharEditor getCustomSeparatorCharEditor() {
		return customSeparatorCharEditor;
	}

	public List<Character> getCustomSeparatorCharsHistory() {
		return customSeparatorCharsHistory;
	}

	public CustomSeparatorCharModel getCustomSeparatorCharModel() {
		return customSeparatorCharModel;
	}
}
