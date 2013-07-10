/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.separatorproperties;

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

public class SeparatorProperties {

	private Character separatorChar;

	private final SeparatorCharModel separatorCharModel;

	private final CharacterRenderer separatorCharRenderer;

	private Character customSeparatorChar;

	private final List<Character> customSeparatorCharsHistory;

	private final CustomCharacterEditor customSeparatorCharEditor;

	private final CustomCharacterModel customSeparatorCharModel;

	private boolean useCustomSeparator;

	private final UseCustomSeparatorAction useCustomSeparatorAction;

	@Inject
	SeparatorProperties(SeparatorCharModel separatorCharModel,
			CharacterRenderer separatorCharRenderer,
			CustomCharacterModel customSeparatorCharModel,
			CustomCharacterEditor customSeparatorCharEditor,
			UseCustomSeparatorAction useCustomSeparatorAction) {
		this.separatorChar = separatorCharModel.getElementAt(0);
		this.separatorCharModel = separatorCharModel;
		this.separatorCharRenderer = separatorCharRenderer;
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

	@FieldComponent(order = 0)
	@ComboBox(model = "separatorCharModel", renderer = "separatorCharRenderer")
	public Character getSeparatorChar() {
		return separatorChar;
	}

	public SeparatorCharModel getSeparatorCharModel() {
		return separatorCharModel;
	}

	public CharacterRenderer getSeparatorCharRenderer() {
		return separatorCharRenderer;
	}

	@FieldComponent(order = 1, showTitle = false)
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

	@FieldComponent(order = 2, showTitle = false, readOnly = true)
	@HistoryComboBox(editable = true, model = "customSeparatorCharModel", editor = "customSeparatorCharEditor", history = "customSeparatorCharsHistory")
	public Character getCustomSeparatorChar() {
		return customSeparatorChar;
	}

	public void setCustomSeparatorChar(Character customSeparatorChar) {
		this.customSeparatorChar = customSeparatorChar;
	}

	public CustomCharacterEditor getCustomSeparatorCharEditor() {
		return customSeparatorCharEditor;
	}

	public List<Character> getCustomSeparatorCharsHistory() {
		return customSeparatorCharsHistory;
	}

	public CustomCharacterModel getCustomSeparatorCharModel() {
		return customSeparatorCharModel;
	}

	public void setupProperties(CsvImportProperties properties) {
		setupSeparator(properties);
	}

	private void setupSeparator(CsvImportProperties properties) {
		SeparatorCharModel model = getSeparatorCharModel();
		char separator = properties.getSeparator();
		if (model.getIndexOf(separator) != -1) {
			setSeparatorChar(separator);
		} else {
			setUseCustomSeparator(true);
			setCustomSeparatorChar(separator);
		}
	}
}
