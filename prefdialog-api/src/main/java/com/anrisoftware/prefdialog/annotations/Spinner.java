/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-core.
 * 
 * prefdialog-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.util.Calendar.DAY_OF_MONTH;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Calendar;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * Field to a spinner. Depending on the value type different default models are
 * used. For value type number the {@link SpinnerNumberModel} is used; for value
 * type date the {@link SpinnerDateModel} is used; for an array or list a
 * {@link SpinnerListModel} is used.
 * <p>
 * Examples:
 * <p>
 * Sets the number field with minimum, maximum and step size
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;Spinner(minimum = -10, maximum = 10, stepSize = 2)
 * public int someField;
 * </pre>
 * 
 * Sets the field with a custom spinner model
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;Spinner(model = &quot;someModel&quot;)
 * public int someField;
 * // as array
 * public SpinnerModel someModel = new CustomSpinnerModel();
 * </pre>
 * 
 * Sets the field with a custom spinner model class
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;Spinner(modelClass = &quot;CustomSpinnerModel&quot;)
 * public int someField;
 * // as array
 * public SpinnerModel someModel = new CustomSpinnerModel();
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface Spinner {

	String elements() default "";

	/**
	 * If the value type is an integer, long or double then the maximum value
	 * can be set. Defaults to no value, meaning no maximum is set.
	 */
	double[] maximum() default {};

	/**
	 * If the value type is an integer, long or double then the minimum value
	 * can be set. Defaults to no value, meaning no minimum is set.
	 */
	double[] minimum() default {};

	/**
	 * If the value type is an integer, long or double then the step size can be
	 * set. Defaults to step size of one.
	 */
	double stepSize() default 1;

	/**
	 * If the value type is an date sets the start date in the sequence of
	 * dates. The milliseconds of the data must be given. Defaults to no value,
	 * meaning no start limit is set.
	 */
	long[] start() default {};

	/**
	 * If the value type is an date sets the end date in the sequence of dates.
	 * The milliseconds of the data must be given. Defaults to no value, meaning
	 * no end limit is set.
	 */
	long[] end() default {};

	/**
	 * If the value type is an date sets the calendar field. Defaults to
	 * {@link Calendar#DAY_OF_MONTH}.
	 * 
	 * @see Calendar
	 */
	int calendarField() default DAY_OF_MONTH;

	/**
	 * The name of the field name to use for the custom {@link SpinnerModel}.
	 * Defaults to an empty name which means no field is set.
	 */
	String model() default "";

	/**
	 * Sets the {@link SpinnerModel} class that returns the current value, the
	 * next value and the previous value. The custom model must have a public
	 * standard constructor available for instantiation. Defaults to
	 * {@link SpinnerNumberModel}.
	 * 
	 * @see JSpinner#setModel(SpinnerModel)
	 */
	Class<? extends SpinnerModel>[] modelClass() default {};
}
