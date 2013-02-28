package com.anrisoftware.prefdialog.filechooser.panel.core.actions.textposition;

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.event.ActionEvent;
import java.util.Map.Entry;

import javax.swing.AbstractButton;

class IconOnlyAction extends AbstractTextPositionAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				hideText();
			}
		});
	}

	private void hideText() {
		for (Entry<String, ? extends AbstractButton> entry : getFontChooserButtons()
				.entrySet()) {
			AbstractButton button = entry.getValue();
			restoreIcon(entry.getKey(), button);
			saveText(entry.getKey(), button.getText());
			button.setText(null);
		}
	}
}