package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.annotations.CheckBox;
import com.anrisoftware.prefdialog.annotations.ComboBox;
import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.fields.historycombobox.HistoryComboBox;

public class SeparatorProperties {

	private Character seperatorChar;

	private final SeperatorCharModel seperatorCharModel;

	private final SeperatorCharRenderer seperatorCharRenderer;

	private final SeperatorCharEditor seperatorCharEditor;

	private Character customSeperatorChar;

	private final List<Character> customSeperatorCharsHistory;

	private final SeperatorCharEditor customSeperatorCharEditor;

	private final CustomSeperatorCharModel customSeperatorCharModel;

	private boolean useCustomSeparator;

	@Inject
	SeparatorProperties(SeperatorCharModel seperatorCharModel,
			SeperatorCharRenderer seperatorCharRenderer,
			SeperatorCharEditor seperatorCharEditor,
			SeperatorCharEditor customSeperatorCharEditor,
			CustomSeperatorCharModel customSeperatorCharModel) {
		this.seperatorChar = seperatorCharModel.getElementAt(0);
		this.seperatorCharModel = seperatorCharModel;
		this.seperatorCharRenderer = seperatorCharRenderer;
		this.seperatorCharEditor = seperatorCharEditor;
		this.customSeperatorChar = '\0';
		this.customSeperatorCharsHistory = new ArrayList<Character>();
		this.customSeperatorCharEditor = customSeperatorCharEditor;
		this.customSeperatorCharModel = customSeperatorCharModel;
		this.useCustomSeparator = false;
	}

	public void setSeperatorChar(Character seperatorChar) {
		this.seperatorChar = seperatorChar;
	}

	@FieldComponent
	@ComboBox(model = "seperatorCharModel", renderer = "seperatorCharRenderer", editor = "seperatorCharEditor")
	public Character getSeperatorChar() {
		return seperatorChar;
	}

	public SeperatorCharModel getSeperatorCharModel() {
		return seperatorCharModel;
	}

	public SeperatorCharRenderer getSeperatorCharRenderer() {
		return seperatorCharRenderer;
	}

	public SeperatorCharEditor getSeperatorCharEditor() {
		return seperatorCharEditor;
	}

	@FieldComponent(showTitle = false)
	@CheckBox
	public boolean isUseCustomSeparator() {
		return useCustomSeparator;
	}

	public void setUseCustomSeparator(boolean useCustomSeparator) {
		this.useCustomSeparator = useCustomSeparator;
	}

	@FieldComponent(showTitle = false)
	@HistoryComboBox(editable = true, model = "customSeperatorCharModel", editor = "customSeperatorCharEditor", history = "customSeperatorCharsHistory")
	public Character getCustomSeperatorChar() {
		return customSeperatorChar;
	}

	public void setCustomSeperatorChar(Character customSeperatorChar) {
		this.customSeperatorChar = customSeperatorChar;
	}

	public SeperatorCharEditor getCustomSeperatorCharEditor() {
		return customSeperatorCharEditor;
	}

	public List<Character> getCustomSeperatorCharsHistory() {
		return customSeperatorCharsHistory;
	}

	public CustomSeperatorCharModel getCustomSeperatorCharModel() {
		return customSeperatorCharModel;
	}
}
