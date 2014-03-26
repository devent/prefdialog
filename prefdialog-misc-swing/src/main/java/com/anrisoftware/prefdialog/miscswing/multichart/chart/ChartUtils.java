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
