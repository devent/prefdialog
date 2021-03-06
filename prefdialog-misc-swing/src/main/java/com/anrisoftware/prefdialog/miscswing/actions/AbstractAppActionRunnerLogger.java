/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import static com.anrisoftware.prefdialog.miscswing.actions.AbstractAppActionLogger._.error;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.anrisoftware.prefdialog.miscswing.logwindowdock.AbstractLogWindowLogger;
import com.anrisoftware.prefdialog.miscswing.logwindowdock.ErrorCategory;
import com.anrisoftware.prefdialog.miscswing.logwindowdock.ErrorNode;
import com.anrisoftware.prefdialog.miscswing.logwindowdock.ErrorNodeFactory;

/**
 * Logging for {@link AbstractAppActionRunner}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
final class AbstractAppActionRunnerLogger extends AbstractLogWindowLogger {

    /**
     * Sets the context of the logger to {@link AbstractAppActionRunner}.
     */
    public AbstractAppActionRunnerLogger() {
        super(AbstractAppActionRunner.class);
    }

    @Inject
    private ErrorNodeFactory errorNodeFactory;

    ErrorNode errorAction(ErrorCategory category, Throwable e) {
        logException(e, error, e.getLocalizedMessage());
        ErrorNode node = errorNodeFactory.create(category);
        node.setException(e);
        return node;
    }

}
