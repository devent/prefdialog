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

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Parses the array range.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
class ArrayRangeParser {

    private final String text;

    public ArrayRangeParser(String text) {
        this.text = text;
    }

    public ArrayRange parseString() throws InvalidRangeException {
        String[] split = splitString(text, "[](){},");
        int[] value;
        if (split.length == 0) {
            value = new int[] {};
        } else if (isNumber(split)) {
            value = new int[] { Integer.valueOf(split[0]) };
        } else if (isClosed(split)) {
            value = parseClosed(split);
        } else if (isOpen(split)) {
            value = parseOpen(split);
        } else if (isSet(split)) {
            value = parseSet(split);
        } else {
            throw new InvalidRangeException(text);
        }
        return new ArrayRange(value, text).calculateRange();
    }

    private String[] splitString(String text, String delim) {
        StringTokenizer token = new StringTokenizer(text, delim, true);
        List<String> list = new ArrayList<String>();
        while (token.hasMoreTokens()) {
            String next = token.nextToken();
            if (next.charAt(0) != ',') {
                list.add(next);
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * Checks if the text is simple number.
     */
    private boolean isNumber(String[] split) {
        try {
            Integer.valueOf(split[0]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the text is a closed interval.
     */
    private boolean isClosed(String[] split) throws InvalidRangeException {
        if (compareStringChar(split, 0, '[')
                && compareStringChar(split, lastIndex(split), ']')) {
            if (split.length != 4) {
                throw new InvalidRangeException(text);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the text is an open interval.
     */
    private boolean isOpen(String[] split) throws InvalidRangeException {
        if (compareStringChar(split, 0, ']')
                || compareStringChar(split, 0, '(')
                || compareStringChar(split, lastIndex(split), '[')
                || compareStringChar(split, lastIndex(split), ')')) {
            if (split.length != 4) {
                throw new InvalidRangeException(text);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the text is a set.
     */
    private boolean isSet(String[] split) throws InvalidRangeException {
        if (compareStringChar(split, 0, '{')
                || compareStringChar(split, lastIndex(split), '}')) {
            if (split.length < 2) {
                throw new InvalidRangeException(text);
            }
            return true;
        } else {
            return false;
        }
    }

    private int lastIndex(String[] split) {
        return split.length - 1;
    }

    private boolean compareStringChar(String[] split, int index, char c) {
        return split[index].charAt(0) == c;
    }

    private int[] parseSet(String[] split) throws InvalidRangeException {
        int[] value = new int[split.length - 2];
        for (int i = 1; i < lastIndex(split); i++) {
            value[i - 1] = Integer.valueOf(split[i]);
        }
        return value;
    }

    private int[] parseClosed(String[] split) {
        int min = Integer.valueOf(split[1]);
        int max = Integer.valueOf(split[lastIndex(split) - 1]);
        int[] value = new int[max - min + 1];
        for (int i = 0; i < value.length; i++) {
            value[i] = min + i;
        }
        return value;
    }

    private int[] parseOpen(String[] split) {
        int min = Integer.valueOf(split[1]);
        int max = Integer.valueOf(split[lastIndex(split) - 1]);
        if (compareStringChar(split, 0, ']')
                || compareStringChar(split, 0, '(')) {
            min += 1;
        }
        if (compareStringChar(split, lastIndex(split), '[')
                || compareStringChar(split, lastIndex(split), ')')) {
            max -= 1;
        }
        int[] value = new int[max - min + 1];
        for (int i = 0; i < value.length; i++) {
            value[i] = min + i;
        }
        return value;
    }

}
