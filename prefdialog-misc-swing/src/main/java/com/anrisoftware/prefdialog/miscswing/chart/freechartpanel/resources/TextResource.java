/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.resources;

import java.util.Locale;

import com.anrisoftware.resources.texts.api.Texts;

/**
 * Graph panel texts.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
enum TextResource {

	OPTIONS_ACTION("context_options_action");

	/**
	 * Retrieve the text resources.
	 * 
	 * @param texts
	 *            the {@link Texts} texts resources.
	 * 
	 * @param locale
	 *            the {@link Locale} locale.
	 */
	public static void retrieveTextResources(Texts texts, Locale locale) {
		for (TextResource value : values()) {
			value.setText(texts, locale);
		}
	}

	private String name;

	private String text;

	private TextResource(String name) {
		this.name = name;
	}

	/**
	 * Sets the text resource.
	 * 
	 * @param texts
	 *            the {@link Texts} texts resources.
	 * 
	 * @param locale
	 *            the {@link Locale} locale.
	 */
	public void setText(Texts texts, Locale locale) {
		this.text = texts.getResource(name, locale).getText();
	}

	@Override
	public String toString() {
		return text;
	}
}
