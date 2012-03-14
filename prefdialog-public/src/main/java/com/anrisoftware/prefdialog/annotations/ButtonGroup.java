package com.anrisoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.JButton;

/**
 * <p>
 * Annotation to create {@link JButton}s in a row.
 * </p>
 * Example:
 * 
 * <pre>
 * &#064;ButtonGroup
 * private List&lt;Action&gt; buttons = Lists.newArrayList(new ButtonAction1(),
 * 		new ButtonAction2());
 * </pre>
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface ButtonGroup {

	/**
	 * The width of the button inside the container.
	 */
	double width() default -1.0d;

	/**
	 * If this input field should be read-only. Read-only fields are to show
	 * information for the user without that the user can modify the value.
	 */
	boolean readonly() default false;

	/**
	 * The title of the buttons group. The title is shown above of the buttons
	 * group and should contain a description.
	 */
	String title() default "";

	/**
	 * If the title of the should be visible or not.
	 */
	boolean showTitle() default false;

	/**
	 * The {@link TextPosition} of the title of the button group. Default is
	 * {@link TextPosition#TEXT_ONLY}.
	 */
	TextPosition textPosition() default TextPosition.TEXT_ONLY;

	/**
	 * The {@link IconSize} of the button group icon. Default is
	 * {@link IconSize.SMALL}.
	 */
	IconSize iconSize() default IconSize.SMALL;

	/**
	 * The icon for the button group, should be a resource name. The resource
	 * name needs to have the place holder %d for the icon size. There must be
	 * one file for each of the used icon sizes. The icon sizes are 16, 22, 32
	 * and 48. Default is empty.
	 */
	String icon() default "";

	/**
	 * The {@link HorizontalPositions} of the buttons in the group.
	 */
	HorizontalPositions horizontalPosition() default HorizontalPositions.RIGHT;
}
