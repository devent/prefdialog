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
package com.anrisoftware.prefdialog.miscswing.dialogsworker;

import java.beans.PropertyVetoException;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging for {@link OpenFileChooserDialogAction}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
final class OpenFileChooserDialogActionLogger extends AbstractLogger {

    /**
     * Sets the context of the logger to {@link OpenFileChooserDialogAction}.
     */
    public OpenFileChooserDialogActionLogger() {
        super(OpenFileChooserDialogAction.class);
    }

    void propertyVeto(PropertyVetoException e) {
        log.error(null, e);
    }
}
