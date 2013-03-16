package com.anrisoftware.prefdialog.filechooser.panel.imageresources.actions.textposition;

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.event.ActionEvent;
import java.util.Map.Entry;

import javax.swing.AbstractButton;

class TextOnlyAction extends AbstractTextPositionAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				hideIcons();
			}
		});
	}

	private void hideIcons() {
		for (Entry<String, ? extends AbstractButton> entry : getFontChooserButtons()
				.entrySet()) {
			AbstractButton button = entry.getValue();
			saveIcon(entry.getKey(), button.getIcon());
			restoreText(entry.getKey(), button);
			button.setIcon(null);
		}
	}

}
