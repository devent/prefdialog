/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of forecast-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.resources;

import java.util.Locale;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;

/**
 * Graph panel images.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
enum ImageResource {

	CONTEXT_OPTIONS("context_options"),

	ZOOM_OUT("zoom_out"),

	ZOOM_IN("zoom_in"),

	ZOOM_AUTO("zoom_auto");

	/**
	 * Retrieve the image resources.
	 * 
	 * @param images
	 *            the {@link Images} image resources.
	 * 
	 * @param locale
	 *            the {@link Locale} locale.
	 * 
	 * @param size
	 *            the {@link IconSize} size.
	 */
	public static void retrieveImageResources(Images images, Locale locale,
			IconSize size) {
		for (ImageResource value : values()) {
			value.setImages(images, locale, size);
		}
	}

	private String name;

	private com.anrisoftware.resources.images.api.ImageResource image;

	private ImageResource(String name) {
		this.name = name;
	}

	public void setImages(Images images, Locale locale, IconSize size) {
		this.image = images.getResource(name, locale, size);
	}

	public com.anrisoftware.resources.images.api.ImageResource getImage() {
		return image;
	}

	public Icon getIcon() {
		return new ImageIcon(image.getImage());
	}
}
