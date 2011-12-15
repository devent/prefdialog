package com.anrisoftware.prefdialog.panel.inputfields

import groovy.swing.SwingBuilder

import java.awt.BorderLayout

import javax.swing.JFrame

import org.fest.swing.edt.GuiActionRunner
import org.fest.swing.edt.GuiQuery
import org.fest.swing.fixture.FrameFixture

/**
 * Create a frame fixture for a {@link Component}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class AbstractFixtureHandler {

	FrameFixture fixture

	JFrame frame

	def createPanelFrame(def component) {
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
