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
