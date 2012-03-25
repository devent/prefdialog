/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields

import groovy.swing.SwingBuilder

import java.awt.BorderLayout

import javax.swing.JFrame

import org.fest.swing.edt.GuiActionRunner
import org.fest.swing.edt.GuiQuery
import org.fest.swing.fixture.FrameFixture

/**
 * Create a {@link FrameFixture} for a {@link Component} field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class AbstractFixtureHandler {

	private FrameFixture fixture

	private JFrame frame

	/**
	 * Creates a new {@link FrameFixture} with a {@link JFrame}, runs the test
	 * and end the fixture after the test.
	 * 
	 * @param title
	 * 		the title of the {@link JFrame}.
	 * 
	 * @param component
	 * 		the {@link Component} we test.
	 * 
	 * @param runTest
	 * 		the tests to run.
	 * 
	 * @param frameSize
	 * 		optional the {@link JFrame} size, default is 480x360.
	 */
	void beginPanelFrame(def title, def component, def runTest, def frameSize=[480, 360]) {
		createFrame(title, component, frameSize)
		beginFixture()
		runTest()
		endFixture()
	}

	/**
	 * Creates the {@link JFrame} for the fixture.
	 * 
	 * @param title
	 * 		the title of the {@link JFrame}.
	 * 
	 * @param component
	 * 		the {@link Component} we test.
	 * 
	 * @param frameSize
	 * 		optional the {@link JFrame} size, default is 480x360.
	 */
	void createFrame(def title, def component, def frameSize=[480, 360]) {
		frame = new SwingBuilder().frame(title: title, pack: true, preferredSize: frameSize) {
			borderLayout()
			widget(component, constraints: BorderLayout.CENTER)
		}
	}

	/**
	 * Creates and show the {@link FrameFixture}. 
	 */
	void beginFixture() {
		fixture = createFrameFixture()
		fixture.show();
	}

	private createFrameFixture() {
		def result = GuiActionRunner.execute([executeInEDT: { frame } ] as GuiQuery);
		new FrameFixture(result);
	}

	/**
	 * End the {@link FrameFixture}.
	 */
	void endFixture() {
		fixture.cleanUp()
		fixture = null
	}

	/**
	 * Returns the current {@link FrameFixture}.
	 */
	FrameFixture getFixture() {
		fixture
	}
}
