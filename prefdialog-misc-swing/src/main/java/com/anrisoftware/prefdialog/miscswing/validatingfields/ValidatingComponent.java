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
package com.anrisoftware.prefdialog.miscswing.validatingfields;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.prefdialog.miscswing.tooltip.ToolTipShower;

/**
 * Decorated the component depending whether or not the input is valid.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ValidatingComponent {

    private static final AlphaComposite ALPHA = AlphaComposite.getInstance(
            AlphaComposite.SRC_OVER, 0.1f);

    private final JComponent component;

    private final ToolTipShower toolTip;

    private Color invalidBackground;

    private boolean valid;

    /**
     * Sets the component.
     *
     * @param component
     *            the {@link JComponent}.
     */
    public ValidatingComponent(JComponent component, ToolTipShower toolTip) {
        this.component = component;
        this.toolTip = toolTip;
        this.valid = true;
        this.invalidBackground = Color.RED;
    }

    /**
     * Sets the invalid background color for the component.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called on the AWT thread.
     *
     * @param color
     *            the background {@link Color}.
     */
    public void setInvalidBackground(Color color) {
        Color oldValue = this.invalidBackground;
        this.invalidBackground = color;
        if (oldValue != color) {
            component.repaint();
        }
    }

    /**
     * Returns the invalid background color for the component.
     *
     * @return the background {@link Color}.
     */
    public Color getInvalidBackground() {
        return invalidBackground;
    }

    /**
     * Sets that the input is valid. The component is repaint when the state
     * changed.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called on the AWT thread.
     *
     * @param valid
     *            set to {@code true} if the current input is valid.
     */
    public void setValid(boolean valid) {
        boolean oldValue = this.valid;
        this.valid = valid;
        if (oldValue != valid) {
            component.repaint();
        }
        toolTip.setShowToolTip(!valid);
    }

    /**
     * Returns that the input is valid.
     *
     * @return {@code true} if the current input is valid.
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Sets the invalid text that is shown in a tool-tip if the input is set as
     * not valid.
     *
     * @param text
     *            the text {@link String}.
     */
    public void setInvalidText(String text) {
        toolTip.setText(text);
    }

    /**
     * Returns the invalid text that is shown in a tool-tip if the input is set
     * as not valid.
     *
     * @return the text {@link String}.
     */
    public String getInvalidText() {
        return toolTip.getText();
    }

    /**
     * Paints the overlay for the component.
     *
     * @param g
     *            the {@link Graphics}.
     */
    public void paint(Graphics g) {
        if (valid) {
            return;
        }
        paintInvalidOverlay(g);
    }

    private void paintInvalidOverlay(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(ALPHA);
        g2.setPaint(invalidBackground);
        Rectangle bounds = g.getClipBounds();
        g2.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
