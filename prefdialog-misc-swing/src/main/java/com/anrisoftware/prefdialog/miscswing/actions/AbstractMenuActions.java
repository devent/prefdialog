/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.actions;

import static javax.swing.SwingUtilities.invokeLater;

import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.swing.Action;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Manages the actions of a menu.
 * <p>
 * <h2>Example</h2>
 * <p>
 * 
 * <pre>
 * &#064;Singleton
 * public class FileMenuActions extends AbstractMenuActions {
 * 
 * 	public static final String FILE_MENU_NAME = &quot;fileMenuMenu&quot;;
 * 	public static final String EXIT_NAME = &quot;exitMenu&quot;;
 * 
 * 	&#064;Inject
 * 	private ExitAction exitAction;
 * 
 * 	private Map&lt;String, MenuAction&gt; actions;
 * 
 * 	&#064;Inject
 * 	protected void setTexts(TextsProvider texts) {
 * 		super.setTexts(texts);
 * 	}
 * 
 * 	&#064;Override
 * 	public Map&lt;String, MenuAction&gt; getActions() {
 * 		if (actions == null) {
 * 			actions = createActions();
 * 		}
 * 		return actions;
 * 	}
 * 
 * 	private Map&lt;String, MenuAction&gt; createActions() {
 * 		actions = new HashMap&lt;String, MenuAction&gt;();
 * 		actions.put(FILE_MENU_NAME, dataAction);
 * 		actions.put(EXIT_NAME, originalDataAction);
 * 		return actions;
 * 	}
 * }
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public abstract class AbstractMenuActions {

	/**
	 * Sets the texts resource for the actions.
	 * 
	 * @see AbstractResourcesAction#setTexts(Texts)
	 */
	protected void setTexts(Texts texts) {
		for (MenuAction action : getActions().values()) {
			((AbstractResourcesAction) action).setTexts(texts);
		}
	}

	/**
	 * Sets the images resource for the actions.
	 * 
	 * @see AbstractResourcesAction#setImages(com.anrisoftware.resources.images.api.Images)
	 */
	protected void setImages(Images images) {
		for (MenuAction action : getActions().values()) {
			AbstractResourcesAction a = (AbstractResourcesAction) action;
			a.setImages(images);
		}
	}

	/**
	 * Adds the action to be executed for the specified menu entry.
	 * 
	 * @param name
	 *            the name of the action.
	 * 
	 * @param action
	 *            the {@link Callable} action.
	 * 
	 * @param listeners
	 *            optional the {@link PropertyChangeListener} listeners that are
	 *            called when the action has finished.
	 * 
	 * @see MenuAction#addAction(Callable, PropertyChangeListener...)
	 */
	public void addAction(String name, Callable<?> action,
			PropertyChangeListener... listeners) {
		getActions().get(name).addAction(action, listeners);
	}

	/**
	 * Adds the action to be executed on the AWT thread for the specified menu
	 * entry.
	 * 
	 * @param name
	 *            the name of the action.
	 * 
	 * @param action
	 *            the {@link Runnable} action.
	 * 
	 * @see MenuAction#addAWTAction(Runnable)
	 */
	public void addAWTAction(String name, Runnable action) {
		getActions().get(name).addAWTAction(action);
	}

	/**
	 * Enabled or disables the menu action.
	 * 
	 * @param name
	 *            the name of the menu action.
	 * 
	 * @param enabled
	 *            set to {@code true} to enable the action.
	 */
	public void setActionEnabled(final String name, final boolean enabled) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				((Action) getActions().get(name)).setEnabled(enabled);
			}
		});
	}

	/**
	 * Enabled or disables multiple menu actions.
	 * 
	 * @param names
	 *            the names of the menu actions.
	 * 
	 * @param enabled
	 *            set to {@code true} to enable the actions.
	 */
	public void setActionsEnabled(final String[] names, final boolean enabled) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				for (String name : names) {
					((Action) getActions().get(name)).setEnabled(enabled);
				}
			}
		});
	}

	/**
	 * Returns the actions.
	 * 
	 * @return the {@link Map} of the actions, where the key is the action name
	 *         and the value is the {@link MenuAction}.
	 */
	public abstract Map<String, MenuAction> getActions();

}
