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
package com.anrisoftware.prefdialog.miscswing.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

import javax.swing.JComponent;
import javax.swing.plaf.PanelUI;

/**
 * Sets a gradient paint after the KDE4 Oxygen theme.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class GradientBackgroundPanelUi extends PanelUI {

	private static final Float TOP_LEFT_POINT = new Point2D.Float(0, 0);

	private Color startColor;

	private Color endColor;

	private final float[] dists;

	public GradientBackgroundPanelUi() {
		this.startColor = Color.decode("#DFDDDB");
		this.endColor = Color.decode("#C2BBB8");
		this.dists = new float[] { 0.0f, 0.40f, 1.0f };
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		int panelHeight = c.getHeight();
		int panelWidth = c.getWidth();
		Point2D start = TOP_LEFT_POINT;
		Point2D end = new Point2D.Float(0, panelHeight);
		LinearGradientPaint paint = createPaint(start, end);
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setPaint(paint);
		graphics2D.fillRect(0, 0, panelWidth, panelHeight);
	}

	private LinearGradientPaint createPaint(Point2D start, Point2D end) {
		Color[] colors = { startColor, endColor, endColor };
		return new LinearGradientPaint(start, end, dists, colors);
	}

	/**
	 * Sets the start gradient color.
	 * 
	 * @param color
	 *            the {@link Color}.
	 */
	public void setStartColor(Color color) {
		this.startColor = color;
	}

	/**
	 * Sets the end gradient color.
	 * 
	 * @param color
	 *            the {@link Color}.
	 */
	public void setEndColor(Color color) {
		this.endColor = color;
	}

}
