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
package com.anrisoftware.prefdialog.miscswing.multichart.chart;

import static java.lang.Class.forName;

/**
 * Chart utilities.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public final class ChartUtils {

    /**
     * Name of the Java 7 <code>ForkJoinPool</code> class.
     */
    public static final String FORK_JOIN_POOL_CLASS = "java.util.concurrent.ForkJoinPool";

    /**
     * Tests if the specified class is available.
     * 
     * @param name
     *            the class {@link String} name.
     * 
     * @return {@code true} if the class is available.
     */
    public static boolean haveClass(String name) {
        try {
            return forName(name, false, ChartUtils.class.getClassLoader()) != null;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
