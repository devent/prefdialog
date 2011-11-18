/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.panel.inputfields.color;

import static java.lang.String.format;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import com.globalscalingsoftware.prefdialog.swingutils.AbstractLabelFieldPanel;
import com.google.inject.Inject;

/**
 * Sets a {@link JFormattedTextField} with a {@link JButton}. In the text field
 * we can enter and show the file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ColorPanel extends AbstractLabelFieldPanel<UiColorPanel> {

	private Color color;

	private String title;

	/**
	 * Set the {@link UiFileChooserPanel}.
	 */
	@Inject
	ColorPanel(UiColorPanel panel) {
		super(panel);
	}

	/**
	 * Set a {@link Runnable} that is called if the user clicks on the open file
	 * button.
	 */
	public void setOpenColorChoserAction(final Runnable runnable) {
		getPanelField().getColorButton().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						runnable.run();
					}
				});
	}

	@Override
	public Object getValue() {
		return color;
	}

	@Override
	public void setValue(Object value) {
		if (value instanceof Color) {
			color = (Color) value;
			getPanelField().getColorButton().setText(colorToString());
			getPanelField().getColorButton().setForeground(
					textColorContrastYIQ(color));
			getPanelField().getColorButton().setBackground(color);
		}
	}

	private String colorToString() {
		return format("#%s", colorToHexString(color));
	}

	private String colorToHexString(Color color) {
		int value = color.getRGB() & 0x00ffffff;
		return Integer.toHexString(0x1000000 | value).substring(1);
	}

	private Color textColorContrastYIQ(Color color) {
		int r = color.getRed();
		int b = color.getBlue();
		int g = color.getGreen();
		int yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000;
		return (yiq >= 128) ? Color.BLACK : Color.WHITE;
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		getPanelField().getColorButton()
				.setName(format("colorbutton-%s", name));
	}

	@Override
	public void setEnabled(boolean enabled) {
		getPanelField().getColorButton().setEnabled(enabled);
	}

	@Override
	public boolean isInputValid() {
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public Color getColor() {
		return color;
	}
}
