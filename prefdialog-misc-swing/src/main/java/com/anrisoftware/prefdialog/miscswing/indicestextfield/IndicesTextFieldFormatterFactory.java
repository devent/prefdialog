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
package com.anrisoftware.prefdialog.miscswing.indicestextfield;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;

/**
 * Formatter for array range.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
public class IndicesTextFieldFormatterFactory extends AbstractFormatterFactory {

    @SuppressWarnings("serial")
    @Override
    public AbstractFormatter getFormatter(JFormattedTextField tf) {
        return new AbstractFormatter() {

            @Override
            public String valueToString(Object value) throws ParseException {
                return valueToString0(value);
            }

            @Override
            public Object stringToValue(String text) throws ParseException {
                return stringToValue0(text);
            }
        };
    }

    private String valueToString0(Object value) throws InvalidRangeException {
        if (value == null) {
            return null;
        }
        Class<? extends Object> cls = value.getClass();
        Class<?> ccls = cls.getComponentType();
        if (String.class.isAssignableFrom(cls)) {
            return (String) value;
        } else if (Integer.class.isAssignableFrom(cls)) {
            return arrayToString(new int[] { (Integer) value });
        } else if (cls.isArray() && ccls.isPrimitive()
                && int.class.isAssignableFrom(ccls)) {
            return arrayToString((int[]) value);
        } else {
            ArrayRange range = (ArrayRange) value;
            if (range.getStr() != null) {
                return range.getStr();
            } else {
                return range.printRange();
            }
        }
    }

    private String arrayToString(int[] value) {
        ArrayRange range = new ArrayRange(value).calculateRange();
        return range.printRange();
    }

    private Object stringToValue0(String text) throws InvalidRangeException {
        return new ArrayRangeParser(text).parseString();
    }

}
