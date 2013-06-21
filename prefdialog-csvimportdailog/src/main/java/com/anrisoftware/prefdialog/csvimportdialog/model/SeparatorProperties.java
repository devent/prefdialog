package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.fields.historycombobox.HistoryComboBox;

public class SeparatorProperties {

	private Character seperatorChar;

	private final SeperatorCharModel seperatorCharModel;

	private final List<Character> seperatorCharsHistory;

	private final Set<Character> seperatorCharDefaults;

	private final SeperatorCharRenderer seperatorCharRenderer;

	private final SeperatorCharEditor seperatorCharEditor;

	@Inject
	SeparatorProperties(
			SeperatorCharModel seperatorCharModel,
			@Named("seperatorCharDefaults") Set<Character> seperatorCharDefaults,
			SeperatorCharRenderer seperatorCharRenderer,
			SeperatorCharEditor seperatorCharEditor) {
		this.seperatorChar = first(seperatorCharDefaults);
		this.seperatorCharModel = seperatorCharModel;
		this.seperatorCharDefaults = seperatorCharDefaults;
		this.seperatorCharsHistory = new ArrayList<Character>();
		this.seperatorCharRenderer = seperatorCharRenderer;
		this.seperatorCharEditor = seperatorCharEditor;
	}

	private <T> T first(Set<T> set) {
		Iterator<T> it = set.iterator();
		if (it.hasNext()) {
			return it.next();
		}
		return null;
	}

	public void setSeperatorChar(Character seperatorChar) {
		this.seperatorChar = seperatorChar;
	}

	@FieldComponent
	@HistoryComboBox(editable = true, model = "seperatorCharModel", renderer = "seperatorCharRenderer", editor = "seperatorCharEditor", history = "seperatorCharsHistory", defaultItems = "seperatorCharDefaults")
	public Character getSeperatorChar() {
		return seperatorChar;
	}

	public SeperatorCharModel getSeperatorCharModel() {
		return seperatorCharModel;
	}

	public List<Character> getSeperatorCharsHistory() {
		return seperatorCharsHistory;
	}

	public Set<Character> getSeperatorCharDefaults() {
		return seperatorCharDefaults;
	}

	public SeperatorCharRenderer getSeperatorCharRenderer() {
		return seperatorCharRenderer;
	}

	public SeperatorCharEditor getSeperatorCharEditor() {
		return seperatorCharEditor;
	}
}
