package com.globalscalingsoftware.prefdialog.annotations;

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
	 * The title of the buttons group.
	 */
	String value() default "";

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
	 * If the title of the buttons group should be visible or not.
	 */
	boolean showTitle() default false;

	/**
	 * The {@link HorizontalPosition} of the buttons in the group.
	 */
	HorizontalPosition horizontalPosition() default HorizontalPosition.RIGHT;
}
