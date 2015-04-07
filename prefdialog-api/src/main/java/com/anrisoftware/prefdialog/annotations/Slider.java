/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-api.
 *
 * prefdialog-api is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-api is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-api. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JSlider;

/**
 * Field to select a value from a slider. All properties of the slider can be
 * set in addition to the slider model. The initial value can be set and is used
 * as the initial value of the slider.
 * <p>
 * Examples:
 * <p>
 * Sets the field with default slider.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;Slider
 * private int value;
 * </pre>
 * 
 * Sets the field with a custom model. The custom model must have a public
 * standard constructor available for instantiation.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;Slider(modelClass = CustomBoundedRangeModel.class)
 * private int value;
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target(FIELD)
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface Slider {

	/**
	 * The minimum value of the slider. Default to zero.
	 * 
	 * @see JSlider#setMinimum(int)
	 */
	int min() default 0;

	/**
	 * The maximum value of the slider. Defaults to 100.
	 * 
	 * @see JSlider#setMaximum(int)
	 */
	int max() default 100;

	/**
	 * The size of the range "covered" by the knob. Most look and feel
	 * implementations will change the value by this amount if the user clicks
	 * on either side of the knob. Defaults to zero.
	 * 
	 * @see JSlider#setExtent(int)
	 */
	int extent() default 0;

	/**
	 * The major tick spacing. The number that is passed in represents the
	 * distance, measured in values, between each major tick mark. If you have a
	 * slider with a range from 0 to 50 and the major tick spacing is set to 10,
	 * you will get major ticks next to the following values: 0, 10, 20, 30, 40,
	 * 50. Defaults to zero.
	 * 
	 * @see JSlider#setMajorTickSpacing(int)
	 */
	int majorTicks() default 0;

	/**
	 * The minor tick spacing. The number that is passed in represents the
	 * distance, measured in values, between each minor tick mark. If you have a
	 * slider with a range from 0 to 50 and the minor tick spacing is set to 10,
	 * you will get minor ticks next to the following values: 0, 10, 20, 30, 40,
	 * 50. Defaults to zero.
	 * 
	 * @see JSlider#setMinorTickSpacing(int)
	 */
	int minorTicks() default 0;

	/**
	 * Determines whether tick marks are painted on the slider. Defaults to
	 * {@code false}.
	 * 
	 * @see JSlider#setPaintTicks(boolean)
	 */
	boolean paintTicks() default false;

	/**
	 * Determines whether labels are painted on the slider. Defaults to
	 * {@code false}.
	 * 
	 * @see JSlider#setPaintLabels(boolean)
	 */
	boolean paintLabels() default false;

	/**
	 * Determines whether the track is painted on the slider. Defaults to
	 * {@code true}.
	 * 
	 * @see JSlider#setPaintTrack(boolean)
	 */
	boolean paintTrack() default true;

	/**
	 * Specifying true makes the knob (and the data value it represents) resolve
	 * to the closest tick mark next to where the user positioned the knob.
	 * Defaults to {@code false}.
	 * 
	 * @see JSlider#setSnapToTicks(boolean)
	 */
	boolean snapToTicks() default false;

	/**
	 * Sets the {@link BoundedRangeModel} class that handles the slider's three
	 * fundamental properties: value, minimum, maximum and extent. The custom
	 * model must have a public standard constructor available for
	 * instantiation. Defaults to {@link DefaultBoundedRangeModel}.
	 * 
	 * @see JSlider#setModel(BoundedRangeModel)
	 */
	Class<? extends BoundedRangeModel> modelClass() default DefaultBoundedRangeModel.class;
}
