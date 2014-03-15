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

import static aurelienribon.tweenengine.Tween.to;
import static com.anrisoftware.prefdialog.miscswing.fadingpane.FadingColor.ALPHA;
import static com.google.inject.Guice.createInjector;
import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;
import static javax.swing.SwingUtilities.invokeAndWait;
import static org.joda.time.Duration.millis;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.joda.time.Duration;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * Layer on top of a window that can be faded away. Useful to hide temporal
 * changes from the user.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class FadingPane extends JComponent {

	/**
	 * @see #create()
	 */
	public static FadingPane createFadingPane() {
		return create();
	}

	/**
	 * Create the fading pane.
	 * 
	 * @return the {@link FadingPane}.
	 */
	public static FadingPane create() {
		return InjectorInstance.factory.create();
	}

	private static class InjectorInstance {
		static final FadingPaneFactory factory = createInjector(
				new FadingPaneModule()).getInstance(FadingPaneFactory.class);
	}

	private final KeyListener consumedKeyListener;

	private final TweenManager manager;

	private final Runnable showAction;

	private final Runnable repaintAction;

	private final Runnable hideAction;

	private Duration durationIn;

	private Duration durationOut;

	private long ellapsedMilliSeconds;

	private Color color;

	private FadingColor fading;

	private Component oldGlassPane;

	private JFrame frame;

	private final MouseListener consumedMouseListener;

	private final float alpha;

	FadingPane() {
		this.manager = new TweenManager();
		this.durationIn = millis(500);
		this.durationOut = millis(500);
		this.ellapsedMilliSeconds = 0;
		this.color = Color.LIGHT_GRAY;
		this.alpha = 0f;
		this.fading = new FadingColor(color, alpha);
		this.consumedMouseListener = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				e.consume();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				e.consume();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				e.consume();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				e.consume();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				e.consume();
			}
		};
		this.consumedKeyListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				e.consume();
			}

			@Override
			public void keyTyped(KeyEvent e) {
				e.consume();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				e.consume();
			}
		};
		this.showAction = new Runnable() {
			@Override
			public void run() {
				frame.setGlassPane(FadingPane.this);
				setVisible(true);
				requestFocusInWindow();
			}

		};
		this.hideAction = new Runnable() {
			@Override
			public void run() {
				setVisible(false);
				frame.setGlassPane(oldGlassPane);
			}

		};
		this.repaintAction = new Runnable() {

			@Override
			public void run() {
				repaint();
			}
		};
		setupPane();
	}

	@Inject
	void setFadingColorAccessor(FadingColorAccessor accessor) {
		Tween.registerAccessor(FadingColor.class, accessor);
	}

	private void setupPane() {
		setOpaque(false);
		setFocusTraversalKeysEnabled(false);
		addKeyListener(consumedKeyListener);
		addMouseListener(consumedMouseListener);
	}

	public void installPane(JFrame frame) {
		this.oldGlassPane = frame.getGlassPane();
		this.frame = frame;
		frame.setGlassPane(this);
	}

	public void uninstallPane(JFrame frame) {
		frame.setGlassPane(oldGlassPane);
		this.frame = null;
	}

	/**
	 * Sets the color of the layout.
	 * 
	 * @param color
	 *            the {@link Color}.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Sets the alpha of the pane.
	 * 
	 * @param alpha
	 *            alpha in the range of [0.0,1.0].
	 */
	public void setAlpha(float alpha) {
		this.fading.setAlpha(alpha);
	}

	/**
	 * Sets the fade in duration.
	 * 
	 * @param duration
	 *            the {@link Duration}.
	 */
	public void setDurationIn(Duration duration) {
		this.durationIn = duration;
	}

	/**
	 * Sets the fade out duration.
	 * 
	 * @param duration
	 *            the {@link Duration}.
	 */
	public void setDurationOut(Duration duration) {
		this.durationOut = duration;
	}

	/**
	 * Fades the layer in.
	 * 
	 * @throws InterruptedException
	 */
	public void fadeIn() throws InterruptedException {
		try {
			fadeIn0();
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getCause());
		}
		sleep(durationIn.getMillis());
	}

	private void fadeIn0() throws InvocationTargetException,
			InterruptedException {
		ellapsedMilliSeconds = currentTimeMillis();
		fading = new FadingColor(color, 0.0f);
		to(fading, ALPHA, durationIn.getMillis()).target(1f).start(manager);
		invokeAndWait(showAction);
		while (fading.getAlpha() < 1.0f) {
			invokeAndWait(repaintAction);
			manager.update(getEllapsedSeconds());
		}
	}

	/**
	 * Fades the layer out.
	 * 
	 * @throws InterruptedException
	 */
	public void fadeOut() throws InterruptedException {
		try {
			fadeOut0();
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getCause());
		}
		sleep(durationOut.getMillis());
	}

	private void fadeOut0() throws InvocationTargetException,
			InterruptedException {
		ellapsedMilliSeconds = currentTimeMillis();
		fading = new FadingColor(color, 1.0f);
		to(fading, ALPHA, durationOut.getMillis()).target(0f).start(manager);
		invokeAndWait(showAction);
		while (fading.getAlpha() > 0.0f) {
			invokeAndWait(repaintAction);
			manager.update(getEllapsedSeconds());
		}
		invokeAndWait(hideAction);
	}

	private float getEllapsedSeconds() {
		long current = currentTimeMillis();
		long delta = current - ellapsedMilliSeconds;
		ellapsedMilliSeconds = current;
		return delta;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(fading.getColor());
		g.fillRect(0, 0, getSize().width, getSize().height);
	}
}
