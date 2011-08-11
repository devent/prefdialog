/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.panel.inputfields.textfield;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

public class ValidatorTexts {

	private static final Map<Class<?>, String> DEFAULT_CLASS_TYPE_TEXTS;

	static {
		DEFAULT_CLASS_TYPE_TEXTS = new HashMap<Class<?>, String>();
		DEFAULT_CLASS_TYPE_TEXTS.put(Object.class, "");
		DEFAULT_CLASS_TYPE_TEXTS.put(Number.class, "Must be a number");
		DEFAULT_CLASS_TYPE_TEXTS.put(
				int.class,
				format("Must be a number from %d to %d", Integer.MIN_VALUE,
						Integer.MAX_VALUE));
		DEFAULT_CLASS_TYPE_TEXTS.put(
				Integer.class,
				format("Must be a number from %d to %d", Integer.MIN_VALUE,
						Integer.MAX_VALUE));
		DEFAULT_CLASS_TYPE_TEXTS.put(
				long.class,
				format("Must be a number from %d to %d", Long.MIN_VALUE,
						Long.MAX_VALUE));
		DEFAULT_CLASS_TYPE_TEXTS.put(
				Long.class,
				format("Must be a number from %d to %d", Long.MIN_VALUE,
						Long.MAX_VALUE));
		DEFAULT_CLASS_TYPE_TEXTS.put(
				float.class,
				format("Must be a float point number from %e to %e",
						Float.MIN_VALUE, Float.MAX_VALUE));
		DEFAULT_CLASS_TYPE_TEXTS.put(
				Float.class,
				format("Must be a float point number from %e to %e",
						Float.MIN_VALUE, Float.MAX_VALUE));
		DEFAULT_CLASS_TYPE_TEXTS.put(
				double.class,
				format("Must be a float point number from %e to %e",
						Double.MIN_VALUE, Double.MAX_VALUE));
		DEFAULT_CLASS_TYPE_TEXTS.put(
				Double.class,
				format("Must be a float point number from %e to %e",
						Double.MIN_VALUE, Double.MAX_VALUE));
		DEFAULT_CLASS_TYPE_TEXTS.put(String.class, "Must be a string");
	}

	public static String getDefaultValidatorText(Class<?> valueClass) {
		return new ValidatorTexts(DEFAULT_CLASS_TYPE_TEXTS)
				.getValidatorText(valueClass);
	}

	private final Map<Class<?>, String> classTypeTexts;

	ValidatorTexts(Map<Class<?>, String> classTypeTexts) {
		this.classTypeTexts = classTypeTexts;
	}

	public String getValidatorText(Class<?> valueClass) {
		return classTypeTexts.get(valueClass);
	}

}
