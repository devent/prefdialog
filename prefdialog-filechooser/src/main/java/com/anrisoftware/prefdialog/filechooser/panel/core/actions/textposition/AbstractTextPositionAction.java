package com.anrisoftware.prefdialog.filechooser.panel.core.actions.textposition;

import static java.util.regex.Pattern.compile;

import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import com.anrisoftware.prefdialog.filechooser.panel.core.actions.options.AbstractOptionMenuAction;

abstract class AbstractTextPositionAction extends AbstractOptionMenuAction {

	private static final Pattern TOOL_BUTTON_PATTERN = compile(".*-tool-button");

	private TextPositionActionsModel model;

	public void setModel(TextPositionActionsModel model) {
		this.model = model;
	}

	@SuppressWarnings("unchecked")
	protected Map<String, ? extends AbstractButton> getFontChooserButtons() {
		return fileChooserPanel.getComponents(TOOL_BUTTON_PATTERN,
				JButton.class, JToggleButton.class);
	}

	protected void saveIcon(String name, Icon icon) {
		model.savedIcons.put(name, icon);
	}

	protected void saveText(String name, String text) {
		model.savedText.put(name, text);
	}

	protected void restoreText(String name, AbstractButton button) {
		String old = model.savedText.get(name);
		if (old != null) {
			button.setText(old);
		}
	}

	protected void restoreIcon(String name, AbstractButton button) {
		Icon oldIcon = model.savedIcons.get(name);
		if (oldIcon != null) {
			button.setIcon(oldIcon);
		}
	}

}
