/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;
import javax.swing.plaf.metal.MetalComboBoxEditor;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

/**
 * Metal Look&Feel combo-box editor field. Based on the code of
 * {@link MetalComboBoxEditor}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @author Steve Wilson
 * @since 3.0
 */
@SuppressWarnings("serial")
class MetalComboBoxEditorField extends ValidatingTextField {

	/**
	 * @see ValidatingTextField#ValidatingTextField(String, int)
	 */
	public MetalComboBoxEditorField(String text, int columns) {
		super(text, columns);
		setBorder(new EditorBorder());
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension pref = super.getPreferredSize();
		pref.height += 4;
		return pref;
	}

	@Override
	public Dimension getMinimumSize() {
		Dimension min = super.getMinimumSize();
		min.height += 4;
		return min;
	}

	/**
	 * Used code from {@link MetalComboBoxEditor}.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 3.0
	 */
	private class EditorBorder extends AbstractBorder {

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int w,
				int h) {
			g.translate(x, y);
			if (usingOcean()) {
				g.setColor(MetalLookAndFeel.getControlDarkShadow());
				g.drawRect(0, 0, w, h - 1);
				g.setColor(MetalLookAndFeel.getControlShadow());
				g.drawRect(1, 1, w - 2, h - 3);
			} else {
				g.setColor(MetalLookAndFeel.getControlDarkShadow());
				g.drawLine(0, 0, w - 1, 0);
				g.drawLine(0, 0, 0, h - 2);
				g.drawLine(0, h - 2, w - 1, h - 2);
				g.setColor(MetalLookAndFeel.getControlHighlight());
				g.drawLine(1, 1, w - 1, 1);
				g.drawLine(1, 1, 1, h - 1);
				g.drawLine(1, h - 1, w - 1, h - 1);
				g.setColor(MetalLookAndFeel.getControl());
				g.drawLine(1, h - 2, 1, h - 2);
			}

			g.translate(-x, -y);
		}

		/**
		 * Returns true if we're using the Ocean Theme.
		 */
		private boolean usingOcean() {
			return (MetalLookAndFeel.getCurrentTheme() instanceof OceanTheme);
		}

		@Override
		public Insets getBorderInsets(Component c, Insets insets) {
			insets.set(2, 2, 2, 0);
			return insets;
		}
	}

}
