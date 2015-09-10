/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import java.beans.PropertyChangeListener;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.swing.Action;

import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Manages the actions and its resources of an application.
 * <p>
 * <h2>Example</h2>
 * <p>
 *
 * <pre>
 * public class FileMenuActions extends AbstractMenuActions {
 * 
 *     public static final String FILE_MENU_NAME = &quot;fileMenuMenu&quot;;
 *     public static final String EXIT_NAME = &quot;exitMenu&quot;;
 * 
 *     &#064;Inject
 *     private ExitAction exitAction;
 * 
 *     private Map&lt;String, Action&gt; actions;
 * 
 *     void setTexts(TextsProvider texts) {
 *         super.setTexts(texts);
 *     }
 * 
 *     &#064;Override
 *     public Map&lt;String, Action&gt; getActions() {
 *         if (actions == null) {
 *             actions = createActions();
 *         }
 *         return actions;
 *     }
 * 
 *     private Map&lt;String, Action&gt; createActions() {
 *         actions = new HashMap&lt;String, Action&gt;();
 *         actions.put(FILE_MENU_NAME, dataAction);
 *         actions.put(EXIT_NAME, originalDataAction);
 *         return actions;
 *     }
 * }
 * </pre>
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public abstract class AbstractExecuteActions {

    /**
     * Sets the texts resource for the actions.
     *
     * @see AbstractResourcesAction#setTexts(Texts)
     */
    public void setTexts(Texts texts) {
        for (Action action : getActions().values()) {
            ((AbstractResourcesAction) action).setTexts(texts);
        }
    }

    /**
     * Sets the images resource for the actions.
     *
     * @see AbstractResourcesAction#setImages(Images)
     */
    public void setImages(Images images) {
        for (Action action : getActions().values()) {
            AbstractResourcesAction a = (AbstractResourcesAction) action;
            a.setImages(images);
        }
    }

    /**
     * Sets the locale for the actions.
     */
    public void setLocale(Locale locale) {
        for (Action action : getActions().values()) {
            AbstractResourcesAction a = (AbstractResourcesAction) action;
            a.setLocale(locale);
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
     * @see ExecuteAction#addAction(Callable, PropertyChangeListener...)
     */
    public void addAction(String name, Callable<?> action,
            PropertyChangeListener... listeners) {
        ((ExecuteAction) getActions().get(name)).addAction(action, listeners);
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
     * @see ExecuteAction#addAWTAction(Runnable)
     */
    public void addAWTAction(String name, Runnable action) {
        ((ExecuteAction) getActions().get(name)).addAWTAction(action);
    }

    /**
     * Enabled or disables menu action(s).
     *
     * @param enabled
     *            set to {@code true} to enable the action(s).
     *
     * @param name
     *            the name(s) of the menu action(s).
     */
    public void setActionEnabled(final boolean enabled, final String... name) {
        for (String n : name) {
            getActions().get(n).setEnabled(enabled);
        }
    }

    /**
     * Returns the actions.
     *
     * @return the {@link Map} of the actions, where the key is the action name
     *         and the value is the {@link Action}.
     */
    public abstract Map<String, Action> getActions();

}
