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
