package com.anrisoftware.prefdialog.miscswing.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.plaf.PanelUI;

/**
 * Sets a gradient paint after the KDE4 Oxygen theme.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class GradientBackgroundPanelUi extends PanelUI {

	private Color startColor;

	private Color endColor;

	public GradientBackgroundPanelUi() {
		this.startColor = Color.decode("#DFDDDB");
		this.endColor = Color.decode("#C2BBB8");
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		int panelHeight = c.getHeight();
		int panelWidth = c.getWidth();
		Point2D start = new Point2D.Float(0, 0);
		Point2D end = new Point2D.Float(0, panelHeight);
		float[] dist = { 0.0f, 0.40f, 1.0f };
		Color[] colors = { startColor, endColor, endColor };
		LinearGradientPaint paint = new LinearGradientPaint(start, end, dist,
				colors);
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.setPaint(paint);
		graphics2D.fillRect(0, 0, panelWidth, panelHeight);
	}

	public void setStartColor(Color color) {
		this.startColor = color;
	}

	public void setEndColor(Color color) {
		this.endColor = color;
	}

}
