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
 * Create a frame fixture for a {@link Component} field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class AbstractFixtureHandler {

	FrameFixture fixture

	JFrame frame

	def createPanelFrame(def component, def closure) {
		return new SwingBuilder().frame(title: 'Core Field Test', pack: true, preferredSize: [480, 360]) {
			borderLayout()
			widget(component, constraints: BorderLayout.CENTER)
		}
	}

	void beginFixture() {
		fixture = createFrameFixture()
		fixture.show();
	}

	void endFixture() {
		fixture.cleanUp()
		fixture = null
	}

	def createFrameFixture() {
		def result = GuiActionRunner.execute([executeInEDT: { frame } ] as GuiQuery);
		return new FrameFixture(result);
	}
}
