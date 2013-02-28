package com.anrisoftware.prefdialog.filechooser.panel.textresources;

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.APPROVE_BUTTON_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.BACK_BUTTON_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.CANCEL_BUTTON_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.DEFAULT_ICON_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.DETAILED_TREE_VIEW_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.DETAILED_VIEW_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.FILTER_LABEL_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.FORWARD_BUTTON_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.HUGE_ICON_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.ICONS_ONLY_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.ICON_SIZE_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.LARGE_ICON_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.MEDIUM_ICON_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.NAME_LABEL_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.OPTIONS_BUTTON_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.PREVIEW_BUTTON_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.REFRESH_BUTTON_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SHORT_VIEW_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SHOW_HIDDEN_FILES_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SHOW_PREVIEW_BUTTON_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SMALL_ICON_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORTING_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORT_DATE_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORT_DESCENDING_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORT_FOLDER_FIRST_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORT_NAME_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORT_SIZE_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.SORT_TYPE_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.TEXT_ALONGSIDE_ICONS_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.TEXT_ONLY_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.TEXT_POSITION_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.TREE_VIEW_MENU_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.UP_BUTTON_NAME;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.VIEW_MENU_NAME;

import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.resources.api.LocaleListener;
import com.anrisoftware.resources.texts.central.TextsResources;
import com.google.inject.assistedinject.Assisted;

public class FileChooserTextResources implements LocaleListener {

	private final FileChooserPanel fileChooser;

	private final TextsResources texts;

	@Inject
	FileChooserTextResources(@Assisted TextsResources texts,
			@Assisted FileChooserPanel fileChooser) {
		this.texts = texts;
		this.fileChooser = fileChooser;
		texts.addTextsLocaleListeners(this);
		updateLocale(texts.getLocale());
	}

	@Override
	public void updateLocale(Locale locale) {
		setupActions();
	}

	private void setupActions() {
		setActionText(PREVIEW_BUTTON_NAME, "preview");
		setActionText(REFRESH_BUTTON_NAME, "refresh");
		setActionText(SHOW_PREVIEW_BUTTON_NAME, "show_preview");
		setActionText(UP_BUTTON_NAME, "up");
		setActionText(FORWARD_BUTTON_NAME, "forward");
		setActionText(BACK_BUTTON_NAME, "back");
		setActionText(CANCEL_BUTTON_NAME, "cancel");
		setActionText(APPROVE_BUTTON_NAME, "approve");
		setActionText(DETAILED_TREE_VIEW_MENU_NAME, "detailed_tree_view");
		setActionText(TREE_VIEW_MENU_NAME, "tree_view");
		setActionText(DETAILED_VIEW_MENU_NAME, "detailed_view");
		setActionText(SHORT_VIEW_MENU_NAME, "short_view");
		setActionText(SORT_FOLDER_FIRST_MENU_NAME, "sort_folder_first");
		setActionText(SORT_DESCENDING_MENU_NAME, "sort_descending");
		setActionText(SORT_TYPE_MENU_NAME, "sort_type");
		setActionText(SORT_DATE_MENU_NAME, "sort_date");
		setActionText(SORT_SIZE_MENU_NAME, "sort_size");
		setActionText(SORT_NAME_MENU_NAME, "sort_name");
		setActionText(SHOW_HIDDEN_FILES_MENU_NAME, "show_hidden_files");
		setActionText(VIEW_MENU_NAME, "view");
		setActionText(SORTING_MENU_NAME, "sorting");
		setActionText(OPTIONS_BUTTON_NAME, "options");
		setActionText(HUGE_ICON_MENU_NAME, "huge_icon");
		setActionText(LARGE_ICON_MENU_NAME, "large_icon");
		setActionText(MEDIUM_ICON_MENU_NAME, "medium_icon");
		setActionText(SMALL_ICON_MENU_NAME, "small_icon");
		setActionText(DEFAULT_ICON_MENU_NAME, "default_icon");
		setActionText(ICON_SIZE_MENU_NAME, "icon_size");
		setActionText(TEXT_ALONGSIDE_ICONS_MENU_NAME, "text_alongside_icons");
		setActionText(TEXT_ONLY_MENU_NAME, "text_only");
		setActionText(ICONS_ONLY_MENU_NAME, "icons_only");
		setActionText(TEXT_POSITION_MENU_NAME, "text_position");
		setLabelText(NAME_LABEL_NAME, "name");
		setLabelText(FILTER_LABEL_NAME, "filter");
	}

	private void setActionText(String name, String actionName) {
		AbstractButton button = getFileChooserButton(name);
		if (button != null) {
			button.setText(texts.getAction(actionName));
			button.setMnemonic(texts.getMnemonic(actionName));
			int index = texts.getMnemonicIndex(actionName);
			if (index != -1) {
				button.setDisplayedMnemonicIndex(index);
			}
		}
	}

	private AbstractButton getFileChooserButton(String name) {
		Map<String, JComponent> components = fileChooser.getComponents();
		return (AbstractButton) components.get(name);
	}

	private void setLabelText(String name, String actionName) {
		JLabel label = getFileChooserLabel(name);
		if (label != null) {
			label.setText(texts.getAction(actionName));
			label.setDisplayedMnemonic(texts.getMnemonic(actionName));
			int index = texts.getMnemonicIndex(actionName);
			if (index != -1) {
				label.setDisplayedMnemonicIndex(index);
			}
		}
	}

	private JLabel getFileChooserLabel(String name) {
		Map<String, JComponent> components = fileChooser.getComponents();
		return (JLabel) components.get(name);
	}
}
