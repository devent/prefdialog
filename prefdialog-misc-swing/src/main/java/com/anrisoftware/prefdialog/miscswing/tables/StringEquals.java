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
package com.anrisoftware.prefdialog.miscswing.tables;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;

/**
 * Check two string values of equality.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.6
 */
@Singleton
public class StringEquals {

    /**
     * Compare the new string value with the old string value.
     */
    public boolean isStringEquals(Object newValue, String old) {
        if (newValue == null && old == null) {
            return true;
        }
        String a = null;
        if (newValue != null) {
            a = newValue.toString();
        }
        if (isEmpty(a) && isEmpty(old)) {
            return true;
        }
        return StringUtils.equals(a, old);
    }

}
