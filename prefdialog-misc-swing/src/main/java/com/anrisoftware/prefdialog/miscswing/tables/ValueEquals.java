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
package com.anrisoftware.prefdialog.miscswing.tables;

import javax.inject.Singleton;

/**
 * Check two generic values of equality.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.6
 */
@Singleton
public class ValueEquals {

    /**
     * Compare the new value with the old value.
     */
    public boolean isValueEquals(Object newValue, Object old) {
        if (newValue == null && old == null) {
            return true;
        }
        if (newValue == null) {
            return false;
        }
        return newValue.equals(old);
    }

}
