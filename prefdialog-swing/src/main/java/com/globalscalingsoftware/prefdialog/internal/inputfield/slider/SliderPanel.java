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
package com.globalscalingsoftware.prefdialog.internal.inputfield.slider;

import javax.swing.JSlider;

import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractLabelFieldPanel;

public class SliderPanel extends AbstractLabelFieldPanel<JSlider> {

	public SliderPanel() {
		super(new JSlider());
	}

	public void setMin(int min) {
		getPanelField().setMinimum(min);
	}

	public void setMax(int max) {
		getPanelField().setMaximum(max);
	}

	@Override
	public Object getValue() {
		return getPanelField().getValue();
	}

	@Override
	public void setValue(Object value) {
		getPanelField().setValue((Integer) value);
	}

	public void setMajorTicks(int value) {
		getPanelField().setMajorTickSpacing(value);
	}

	public void setMinorTicks(int value) {
		getPanelField().setMinorTickSpacing(value);
	}

	public void setPaintTicks(boolean value) {
		getPanelField().setPaintTicks(value);
	}

	public void setPaintLabels(boolean value) {
		getPanelField().setPaintLabels(value);
	}

	public void setPaintTrack(boolean value) {
		getPanelField().setPaintTrack(value);
	}

	public void setSnapToTicks(boolean value) {
		getPanelField().setSnapToTicks(value);
	}

}
