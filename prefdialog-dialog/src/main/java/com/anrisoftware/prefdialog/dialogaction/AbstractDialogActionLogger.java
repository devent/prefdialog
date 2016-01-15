/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-dialog.
 *
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.dialogaction;

import static com.anrisoftware.prefdialog.dialogaction.AbstractDialogActionLogger._.can_create;
import static com.anrisoftware.prefdialog.dialogaction.AbstractDialogActionLogger._.create_dialog_interrupted;
import static com.anrisoftware.prefdialog.dialogaction.AbstractDialogActionLogger._.create_dialog_interrupted_message;
import static com.anrisoftware.prefdialog.dialogaction.AbstractDialogActionLogger._.dialog_null;
import static com.anrisoftware.prefdialog.dialogaction.AbstractDialogActionLogger._.error_create_dialog;
import static com.anrisoftware.prefdialog.dialogaction.AbstractDialogActionLogger._.error_create_dialog_message;
import static com.anrisoftware.prefdialog.dialogaction.AbstractDialogActionLogger._.no_dialog;
import static com.anrisoftware.prefdialog.dialogaction.AbstractDialogActionLogger._.no_frame;
import static org.apache.commons.lang3.Validate.notNull;

import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link AbstractDialogAction}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
class AbstractDialogActionLogger extends AbstractLogger {

    enum _ {

        can_create("Can create dialog for {}."),

        dialog_null("Dialog was not created for %s."),

        no_dialog("No dialog set for {}."),

        no_frame("No parent frame set for {}."),

        ERROR_DIALOG("Error while creating dialog: {}."),

        INTERRUPTED_DIALOG("Interrupted while creating dialog."),

        error_create_dialog("Error while creating dialog"),

        error_create_dialog_message("Error while creating dialog: {}"),

        action("dialog action"),

        create_dialog_interrupted("Creating dialog interrupted"),

        create_dialog_interrupted_message("Creating dialog interrupted: {}");

        private String name;

        private _(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Creates a logger for {@link AbstractDialogAction}.
     */
    public AbstractDialogActionLogger() {
        super(AbstractDialogAction.class);
    }

    boolean checkCanCreateDialog(AbstractDialogAction<?, ?> action) {
        if (action.getFrame() == null) {
            debug(no_frame, action);
            return false;
        }
        if (action.getDialogFactory() == null
                && action.getNotCreatedDialog() == null) {
            debug(no_dialog, action);
            return false;
        }
        debug(can_create, action);
        return true;
    }

    void checkDialogCreated(AbstractDialogAction<?, ?> action, Object dialog) {
        notNull(dialog, dialog_null.toString(), action);
    }

    DialogActionException errorCreateDialog(AbstractDialogAction<?, ?> action,
            InvocationTargetException e) {
        Throwable cause = e.getCause();
        return logException(new DialogActionException(error_create_dialog,
                cause).add(action, action), error_create_dialog_message,
                cause.getLocalizedMessage());
    }

    DialogActionException errorCreateDialogInterrupted(
            AbstractDialogAction<?, ?> action, InterruptedException e) {
        return logException(
                new DialogActionException(create_dialog_interrupted).add(
                        action, action), create_dialog_interrupted_message,
                e.getLocalizedMessage());
    }

    void openDialogVetoed(PropertyVetoException e) {
        log.error(null, e);
    }
}
