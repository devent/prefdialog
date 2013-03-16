package com.anrisoftware.prefdialog.filechooser.panel.imageresources.actions.iconsize;

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.OPTIONS_BUTTON_NAME;
import static javax.swing.SwingUtilities.invokeLater;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolAction;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolButtonsModel;
import com.anrisoftware.prefdialog.filechooser.panel.core.AbstractToolAction;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.ImageResource;
import com.anrisoftware.resources.images.api.Images;

/**
 * Returns the file chooser panel and the image resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractIconSizeAction implements ActionListener {

	private final Locale defaultLocale;

	private Images images;

	private FileChooserPanel fileChooserPanel;

	protected AbstractIconSizeAction() {
		this.defaultLocale = UIManager.getDefaults().getDefaultLocale();
	}

	public void setFileChooserPanel(FileChooserPanel panel) {
		this.fileChooserPanel = panel;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				System.out.println("run()"); // TODO println
				setupOptionsButton();
				setupOptionalToolButtons();
			}
		});
	}

	private void setupOptionsButton() {
		@SuppressWarnings("unchecked")
		AbstractToolAction toolAction = (AbstractToolAction) (fileChooserPanel
				.getComponents(JButton.class).get(OPTIONS_BUTTON_NAME))
				.getAction();
		String resource = toolAction.getImageResource();
		if (resource != null) {
			IconSize size = getIconSize();
			toolAction.setLargeIcon(getIcon(resource, size));
		}
	}

	private void setupOptionalToolButtons() {
		ToolButtonsModel model = fileChooserPanel.getToolButtonsModel();
		for (int i = 0; i < model.getSize(); i++) {
			ToolAction action = model.getActionAt(i);
			if (action instanceof AbstractToolAction) {
				AbstractToolAction toolAction = (AbstractToolAction) action;
				String resource = toolAction.getImageResource();
				if (resource != null) {
					IconSize size = getIconSize();
					toolAction.setLargeIcon(getIcon(resource, size));
				}
			}
		}
	}

	private ImageIcon getIcon(String name, IconSize size) {
		ImageResource resource = images.getResource(name, defaultLocale, size);
		return new ImageIcon(resource.getImage());
	}

	protected abstract IconSize getIconSize();

}
