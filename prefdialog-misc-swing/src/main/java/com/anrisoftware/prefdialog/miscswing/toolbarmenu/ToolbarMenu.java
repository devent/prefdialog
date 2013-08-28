package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

public class ToolbarMenu {

	/**
	 * Name of the icons only menu.
	 */
	public static final String ICONS_ONLY_NAME = "iconsOnlyMenu";

	/**
	 * Name of the text only menu.
	 */
	public static final String TEXT_ONLY_NAME = "textOnlyMenu";

	/**
	 * Name of the text alongside icons menu.
	 */
	public static final String TEXT_ALONGSIDE_NAME = "textAlongsideMenu";

	private final List<AbstractResourcesAction> actions;

	private final MouseAdapter mouseListener;

	private final Map<String, AbstractResourcesAction> menuActions;

	@Inject
	private UiMenu menu;

	ToolbarMenu() {
		this.menuActions = new HashMap<String, AbstractResourcesAction>();
		this.actions = new ArrayList<AbstractResourcesAction>();
		this.mouseListener = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				maybeShowPopup(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				maybeShowPopup(e);
			}

		};
	}

	@Inject
	void setIconsOnlyAction(IconsOnlyAction action) {
		menuActions.put(ICONS_ONLY_NAME, action);
		action.setToolbarMenu(this);
		menu.getIconsOnlyMenu().setName(ICONS_ONLY_NAME);
		menu.getIconsOnlyMenu().setAction(action);
	}

	@Inject
	void setTextOnlyAction(TextOnlyAction action) {
		menuActions.put(TEXT_ONLY_NAME, action);
		action.setToolbarMenu(this);
		menu.getTextOnlyMenu().setName(TEXT_ONLY_NAME);
		menu.getTextOnlyMenu().setAction(action);
	}

	@Inject
	void setTextAlongsideOnlyAction(TextAlongsideOnlyAction action) {
		menuActions.put(TEXT_ALONGSIDE_NAME, action);
		action.setToolbarMenu(this);
		menu.getTextAlongsideIconsMenu().setName(TEXT_ALONGSIDE_NAME);
		menu.getTextAlongsideIconsMenu().setAction(action);
	}

	public void setToolBar(JToolBar bar) {
		bar.addMouseListener(mouseListener);
	}

	/**
	 * Sets the locale for the resources of the actions.
	 * 
	 * @param locale
	 *            the {@link Locale}.
	 */
	public void setLocale(Locale locale) {
		for (AbstractResourcesAction action : menuActions.values()) {
			action.setLocale(locale);
		}
		for (AbstractResourcesAction action : actions) {
			action.setLocale(locale);
		}
	}

	/**
	 * Sets the texts resource for the actions.
	 * 
	 * @param texts
	 *            the {@link Texts}.
	 */
	public void setTexts(Texts texts) {
		for (AbstractResourcesAction action : menuActions.values()) {
			action.setTexts(texts);
		}
		for (AbstractResourcesAction action : actions) {
			action.setTexts(texts);
		}
	}

	/**
	 * Sets the images resource for the actions.
	 * 
	 * @param images
	 *            the {@link Images}.
	 */
	public void setImages(Images images) {
		for (AbstractResourcesAction action : menuActions.values()) {
			action.setImages(images);
		}
		for (AbstractResourcesAction action : actions) {
			action.setImages(images);
		}
	}

	public void addAction(AbstractResourcesAction action) {
		actions.add(action);
	}

	public JPopupMenu getPopupMenu() {
		return menu;
	}

	/**
	 * Sets show icons only for actions.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param b
	 *            set to {@code true} to show only icons.
	 */
	public void setIconsOnly(boolean b) {
		for (AbstractResourcesAction action : actions) {
			action.setShowText(!b);
		}
	}

	/**
	 * Sets show text only for actions.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param b
	 *            set to {@code true} to show text icons.
	 */
	public void setTextOnly(boolean b) {
		for (AbstractResourcesAction action : actions) {
			action.setShowIcon(!b);
		}
	}

	private void maybeShowPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
			menu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

}
