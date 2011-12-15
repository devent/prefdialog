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
package com.anrisoftware.prefdialog.panel.inputfields.slider;

import javax.swing.BoundedRangeModel;
import javax.swing.JSlider;

import com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel;

/**
 * Sets a {@link JSlider} to the panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class SliderPanel extends AbstractLabelFieldPanel<JSlider> {

	/**
	 * Create the {@link JSlider}.
	 */
	SliderPanel() {
		super(new JSlider());
		setup();
	}

	private void setup() {
	}

	@Override
	public Object getValue() {
		return getPanelField().getValue();
	}

	@Override
	public void setValue(Object value) {
		getPanelField().setValue((Integer) value);
	}

	@Override
	public boolean isInputValid() {
		return true;
	}

	/**
	 * Sets the slider's minimum value.
	 * 
	 * @see JSlider#setMinimum(int)
	 */
	public void setMin(int min) {
		getPanelField().setMinimum(min);
	}

	/**
	 * Sets the slider's maximum value.
	 * 
	 * @see JSlider#setMaximum(int)
	 */
	public void setMax(int max) {
		getPanelField().setMaximum(max);
	}

	/**
	 * Sets the major tick spacing.
	 * 
	 * @see JSlider#setMajorTickSpacing(int)
	 */
	public void setMajorTicks(int value) {
		getPanelField().setMajorTickSpacing(value);
	}

	/**
	 * Sets the minor tick spacing.
	 * 
	 * @see JSlider#setMinorTickSpacing(int)
	 */
	public void setMinorTicks(int value) {
		getPanelField().setMinorTickSpacing(value);
	}

	/**
	 * Sets whether tick marks are painted on the slider.
	 * 
	 * @see JSlider#setPaintTicks(boolean)
	 */
	public void setPaintTicks(boolean value) {
		getPanelField().setPaintTicks(value);
	}

	/**
	 * Sets whether the labels are painted on the slider.
	 * 
	 * @see JSlider#setPaintLabels(boolean)
	 */
	public void setPaintLabels(boolean value) {
		getPanelField().setPaintLabels(value);
	}

	/**
	 * Sets whether the track is painted on the slider.
	 * 
	 * @see JSlider#setPaintTrack(boolean)
	 */
	public void setPaintTrack(boolean value) {
		getPanelField().setPaintTrack(value);
	}

	/**
	 * Sets whether the knob (and the data value it represents) resolve to the
	 * closest tick mark next to where the user positioned the knob.
	 * 
	 * @see JSlider#setSnapToTicks(boolean)
	 */
	public void setSnapToTicks(boolean value) {
		getPanelField().setSnapToTicks(value);
	}

	/**
	 * Sets the {@code BoundedRangeModel} that handles the slider's three
	 * fundamental properties: minimum, maximum, value.
	 * 
	 * @see JSlider#setModel(BoundedRangeModel)
	 */
	public void setModel(BoundedRangeModel model) {
		getPanelField().setModel(model);
	}

	/**
	 * Sets whether tick marks are painted on the slider.
	 * 
	 * @see JSlider#setExtent(int)
	 */
	public void setExtent(int extent) {
		getPanelField().setExtent(extent);
	}

}
