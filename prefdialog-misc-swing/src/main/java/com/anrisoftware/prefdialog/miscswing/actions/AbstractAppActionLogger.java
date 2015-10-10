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

import static com.anrisoftware.prefdialog.miscswing.actions.AbstractAppActionLogger._.error;
import static com.anrisoftware.prefdialog.miscswing.actions.AbstractAppActionLogger._.task;
import static com.anrisoftware.prefdialog.miscswing.actions.AbstractAppActionLogger._.task_error;
import static com.anrisoftware.prefdialog.miscswing.actions.AbstractAppActionLogger._.task_error_message;
import static com.anrisoftware.prefdialog.miscswing.actions.AbstractAppActionLogger._.task_interrupted;
import static com.anrisoftware.prefdialog.miscswing.actions.AbstractAppActionLogger._.task_interrupted_message;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.anrisoftware.prefdialog.miscswing.logwindowdock.AbstractLogWindowLogger;
import com.anrisoftware.prefdialog.miscswing.logwindowdock.ErrorCategory;
import com.anrisoftware.prefdialog.miscswing.logwindowdock.ErrorNode;
import com.anrisoftware.prefdialog.miscswing.logwindowdock.ErrorNodeFactory;

/**
 * Logging messages for {@link AbstractAppActionListener}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
@Singleton
class AbstractAppActionLogger extends AbstractLogWindowLogger {

    enum _ {

        error("Error in task: {}."),

        task("task"),

        task_error_message("Task error: %s."),

        task_error("Task error"),

        task_interrupted_message("Task interrupted: %s."),

        task_interrupted("Task interrupted");

        private String name;

        private _(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Inject
    private ErrorNodeFactory errorNodeFactory;

    /**
     * Create logger for {@link AbstractAppActionListener}.
     */
    public AbstractAppActionLogger() {
        super(AbstractAppActionListener.class);
    }

    <T> T fromFuture(Future<T> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw logException(new AppActionException(task_interrupted, e).add(
                    task, future), task_interrupted_message, future);
        } catch (ExecutionException e) {
            throw logException(
                    new AppActionException(task_error, e.getCause()).add(task,
                            future), task_error_message, future);
        }
    }

    ErrorNode errorAction(ErrorCategory category, Throwable e) {
        logException(e, error, e.getLocalizedMessage());
        ErrorNode node = errorNodeFactory.create(category);
        node.setException(e);
        return node;
    }

}
