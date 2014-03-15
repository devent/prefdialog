/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.fadingpane;

import static com.anrisoftware.prefdialog.miscswing.fadingpane.FadingColorProperty.ALPHA_PROPERTY;

import java.awt.Color;
import java.beans.PropertyChangeSupport;

/**
 * Color with alpha fading.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FadingColor {

	public static final int ALPHA = 1;

	private final PropertyChangeSupport p;

	private final Color color;

	private float alpha;

	FadingColor(Color color, float alpha) {
		this.p = new PropertyChangeSupport(this);
		this.color = color;
		this.alpha = alpha;
	}

	public Color getColor() {
		float r = color.getRed() / 255.0f;
		float g = color.getGreen() / 255.0f;
		float b = color.getBlue() / 255.0f;
		float a = alpha;
		return new Color(r, g, b, a);
	}

	public void setAlpha(float alpha) {
		float oldValue = this.alpha;
		this.alpha = alpha;
		p.firePropertyChange(ALPHA_PROPERTY.toString(), oldValue, alpha);
	}

	public float getAlpha() {
		return alpha;
	}
}
