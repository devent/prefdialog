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
package com.anrisoftware.prefdialog.miscswing.indicestextfield;

import static org.apache.commons.lang3.StringUtils.join;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Range of indices.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
@SuppressWarnings("serial")
public class ArrayRange implements Serializable {

    private final int[] value;

    private String str;

    private boolean continuesRange;

    private int min;

    private int max;

    public ArrayRange(int[] value) {
        this.value = copyValue(value);
    }

    public ArrayRange(int[] value, String text) {
        this.value = copyValue(value);
        this.str = text;
    }

    /**
     * Returns the minimal value of the range.
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns the maximal value of the range.
     */
    public int getMax() {
        return max;
    }

    /**
     * Returns {@code true} if the range is continued.
     */
    public boolean isContinued() {
        return continuesRange;
    }

    /**
     * Returns the range value.
     */
    public int[] getValue() {
        return value;
    }

    /**
     * Calculate the array range.
     *
     * @return this {@link ArrayRange}.
     */
    public ArrayRange calculateRange() {
        if (value.length == 0) {
            continuesRange = false;
            return this;
        }
        int first = value[0];
        int last = value[value.length - 1];
        this.min = first;
        this.max = last;
        if (value.length == 1) {
            this.continuesRange = true;
            return this;
        }
        this.continuesRange = true;
        for (int i = 1; i < value.length; i++) {
            if (value[i] != first + i) {
                this.continuesRange = false;
                break;
            }
        }
        return this;
    }

    /**
     * Returns the string representation of this array range.
     */
    public String getStr() {
        return str;
    }

    /**
     * Prints the array range.
     */
    public String printRange() {
        StringBuilder b = new StringBuilder();
        if (continuesRange) {
            printContinuesRange(b);
        } else {
            printDiscontinuedRange(b);
        }
        String string = b.toString();
        this.str = string;
        return string;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    private void printDiscontinuedRange(StringBuilder b) {
        b.append('{');
        b.append(join(value, ','));
        b.append('}');
    }

    private void printContinuesRange(StringBuilder b) {
        b.append('[');
        b.append(min);
        b.append(',');
        b.append(max);
        b.append(']');
    }

    private int[] copyValue(int[] value) {
        int[] v = value.clone();
        Arrays.sort(v);
        return v;
    }

}
