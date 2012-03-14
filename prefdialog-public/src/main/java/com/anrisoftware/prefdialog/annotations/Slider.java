package com.anrisoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JSlider;

/**
 * Annotation to create a {@link JSlider} for a field.
 * 
 * Example:
 * 
 * <pre>
 * &#064;Slider
 * private int value;
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * 
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Slider {

	/**
	 * The width of the radio buttons group inside the container.
	 */
	double width() default -1.0;

	/**
	 * If this input fields should be read-only. Read-only fields are to show
	 * information for the user without that the user can modify the value.
	 */
	boolean readonly() default false;

	/**
	 * The title of the radio buttons group. The title is shown above of the
	 * group and should contain a description.
	 */
	String title() default "";

	/**
	 * If the title of the should be visible or not.
	 */
	boolean showTitle() default true;

	/**
	 * The {@link TextPosition} of the title of the slider. Default is
	 * {@link TextPosition#TEXT_ONLY}.
	 */
	TextPosition textPosition() default TextPosition.TEXT_ONLY;

	/**
	 * The {@link IconSize} of the slider icon. Default is
	 * {@link IconSize.SMALL}.
	 */
	IconSize iconSize() default IconSize.SMALL;

	/**
	 * The icon for the slider, should be a resource name. The resource name
	 * needs to have the place holder %d for the icon size. There must be one
	 * file for each of the used icon sizes. The icon sizes are 16, 22, 32 and
	 * 48. Default is empty.
	 */
	String icon() default "";

	/**
	 * The minimum value of the slider.
	 */
	int min() default 0;

	/**
	 * The maximum value of the slider.
	 */
	int max() default 100;

	/**
	 * The size of the range "covered" by the knob. Most look and feel
	 * implementations will change the value by this amount if the user clicks
	 * on either side of the knob.
	 */
	int extent() default 0;

	/**
	 * The major tick spacing. The number that is passed in represents the
	 * distance, measured in values, between each major tick mark. If you have a
	 * slider with a range from 0 to 50 and the major tick spacing is set to 10,
	 * you will get major ticks next to the following values: 0, 10, 20, 30, 40,
	 * 50.
	 */
	int majorTicks() default 0;

	/**
	 * The minor tick spacing. The number that is passed in represents the
	 * distance, measured in values, between each minor tick mark. If you have a
	 * slider with a range from 0 to 50 and the minor tick spacing is set to 10,
	 * you will get minor ticks next to the following values: 0, 10, 20, 30, 40,
	 * 50.
	 */
	int minorTicks() default 0;

	/**
	 * Determines whether tick marks are painted on the slider.
	 */
	boolean paintTicks() default false;

	/**
	 * Determines whether labels are painted on the slider.
	 */
	boolean paintLabels() default false;

	/**
	 * Determines whether the track is painted on the slider.
	 */
	boolean paintTrack() default true;

	/**
	 * Specifying true makes the knob (and the data value it represents) resolve
	 * to the closest tick mark next to where the user positioned the knob.
	 */
	boolean snapToTicks() default false;

	/**
	 * Sets the {@link BoundedRangeModel} class that handles the slider's three
	 * fundamental properties: value, minimum, maximum and extent.
	 */
	Class<? extends BoundedRangeModel> model() default DefaultBoundedRangeModel.class;
}
