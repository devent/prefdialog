package com.anrisoftware.prefdialog.filechooser.panel.core.actions.textposition;

import static java.util.regex.Pattern.compile;

import java.awt.event.ActionListener;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;

abstract class AbstractTextPositionAction implements ActionListener {

	private static final Pattern TOOL_BUTTON_PATTERN = compile(".*-tool-button");

	private TextPositionActionsModel model;

	private FileChooserPanel fileChooserPanel;

	public void setFileChooserPanel(FileChooserPanel panel) {
		this.fileChooserPanel = panel;
	}

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

	protected void saveText(String name, AbstractButton button) {
		String text = button.getText();
		Action action = button.getAction();
		if (action != null) {
			text = action.getValue(Action.NAME).toString();
		}
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
