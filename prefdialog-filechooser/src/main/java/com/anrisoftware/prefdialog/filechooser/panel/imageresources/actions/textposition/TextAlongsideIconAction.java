package com.anrisoftware.prefdialog.filechooser.panel.imageresources.actions.textposition;

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.event.ActionEvent;
import java.util.Map.Entry;

import javax.swing.AbstractButton;

class TextAlongsideIconAction extends AbstractTextPositionAction {

	@Override
	public void actionPerformed(ActionEvent e) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				showText();
			}
		});
	}

	private void showText() {
		for (Entry<String, ? extends AbstractButton> entry : getFontChooserButtons()
				.entrySet()) {
			AbstractButton button = entry.getValue();
			restoreIcon(entry.getKey(), button);
			restoreText(entry.getKey(), button);
			button.setHideActionText(false);
		}
	}

}
