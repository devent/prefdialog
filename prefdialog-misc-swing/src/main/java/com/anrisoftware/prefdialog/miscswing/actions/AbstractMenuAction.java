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

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;

/**
 * Sets the action name and mnemonic from text resource.
 * <p>
 * <h2>Example</h2>
 * <p>
 * 
 * <pre>
 * &#064;SuppressWarnings(&quot;serial&quot;)
 * class ExitAction extends AbstractMenuAction {
 * 
 *     private static final String ACTION_NAME = &quot;exit_action&quot;;
 * 
 *     ExitAction() {
 *         super(ACTION_NAME);
 *         setEnabled(true);
 *     }
 * }
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractMenuAction extends AbstractResourcesAction
        implements MenuAction {

    private Actions actions;

    private String id;

    /**
     * @see AbstractResourcesAction#AbstractResourcesAction(String)
     */
    protected AbstractMenuAction(String name) {
        super(name);
        this.id = name;
    }

    /**
     * Injects the application actions to submit actions to.
     * 
     * @param actions
     *            the {@link Actions}.
     */
    @Inject
    public void setActions(Actions actions) {
        this.actions = actions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void addAction(Callable<?> action,
            PropertyChangeListener... listeners) {
        actions.addAction(getId(), action, listeners);
    }

    @Override
    public void addAWTAction(Runnable action) {
        actions.addAWTAction(getId(), action);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actions.executeActions(getId());
    }
}
