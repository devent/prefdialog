

package com.anrisoftware.prefdialog.fields.spacer

import javax.swing.Box

import org.fest.swing.core.Robot
import org.fest.swing.fixture.ComponentFixture
import org.fest.swing.fixture.FrameFixture

/**
 * Fixture for spacer field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SpacerFixture extends ComponentFixture {

	/**
	 * @see #create(FrameFixture, String)
	 */
	public static SpacerFixture spacerFixture(FrameFixture fixture, String name) {
		return create(fixture, name)
	}

	/**
	 * Create the spacer fixture from the frame fixture.
	 */
	public static SpacerFixture create(FrameFixture fixture, String name) {
		return new SpacerFixture(fixture.robot, name)
	}

	/**
	 * @see ComponentFixture#ComponentFixture(Robot, String, Class)
	 */
	SpacerFixture(Robot robot, String name) {
		super(robot, name, Box.Filler);
	}
}
