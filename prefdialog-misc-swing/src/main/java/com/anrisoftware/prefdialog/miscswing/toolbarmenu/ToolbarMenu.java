package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;
import com.anrisoftware.resources.texts.api.Texts;

public class ToolbarMenu {

	private final List<Action> actions;

	private final List<AbstractResourcesAction> resourcesActions;

	private final MouseAdapter mouseListener;

	@Inject
	private UiMenu menu;

	ToolbarMenu() {
		this.actions = new ArrayList<Action>();
		this.resourcesActions = new ArrayList<AbstractResourcesAction>();
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
		for (AbstractResourcesAction action : resourcesActions) {
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
		for (AbstractResourcesAction action : resourcesActions) {
			action.setTexts(texts);
		}
	}

	public void addAction(AbstractResourcesAction action) {
		resourcesActions.add(action);
	}

	public JPopupMenu getPopupMenu() {
		return menu;
	}

	/**
	 * Sets show icons only for actions.
	 * 
	 * @param b
	 *            set to {@code true} to show only icons.
	 */
	public void setIconsOnly(boolean b) {
		for (AbstractResourcesAction action : resourcesActions) {
			action.setShowText(!b);
		}
	}

	private void maybeShowPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
			menu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

}
