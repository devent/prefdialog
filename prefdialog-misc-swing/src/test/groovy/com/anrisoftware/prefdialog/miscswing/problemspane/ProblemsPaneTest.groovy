/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.problemspane

import java.awt.BorderLayout

import javax.swing.JPanel
import javax.swing.JScrollPane

import org.joda.time.DateTime
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see ProblemsPane
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ProblemsPaneTest {

	@Test
	void "show pane"() {
		def title = "$NAME::show pane"
		ProblemsPane pane = injector.getInstance ProblemsPane
		pane.setColumns([
			"Time",
			"Type",
			"Problem",
			"Details"
		])

		def ex = new NullPointerException("Some error")

		def errors = new CategoryNode()
		errors.setName "Errors"
		pane.addCategory errors

		def info = new CategoryNode()
		info.setName "Info"
		pane.addCategory info


		def frame = createFrame title, pane
		frame.withFixture({
			def message = new MessageNode(errors)
			message.setValueAt DateTime.now(), 0
			message.setValueAt "Exception", 1
			message.setValueAt "Some message", 2
			message.setValueAt "${ex.localizedMessage}", 3
			pane.addMessage message
		}, {
			def message = new MessageNode(errors)
			message.setValueAt DateTime.now(), 0
			message.setValueAt "Exception", 1
			message.setValueAt "Some message", 2
			message.setValueAt "${ex.localizedMessage}", 3
			pane.addMessage message
		}, {
			def message = new MessageNode(info)
			message.setValueAt DateTime.now(), 0
			message.setValueAt "Info", 1
			message.setValueAt "Some message", 2
			message.setValueAt "Some text to describe", 3
			pane.addMessage message
		}, {
			def message = new MessageNode(info)
			message.setValueAt DateTime.now(), 0
			message.setValueAt "Info", 1
			message.setValueAt "Some message", 2
			message.setValueAt "Some text to describe", 3
			pane.addMessage message
		})
	}

	static Injector injector

	@BeforeClass
	static void createPane() {
		injector = Guice.createInjector()
	}

	static TestFrameUtil createFrame(String title, ProblemsPane pane) {
		def panel = new JPanel(new BorderLayout())
		panel.add new JScrollPane(pane.awtComponent)
		new TestFrameUtil(title: title, component: panel)
	}

	static final String NAME = ProblemsPaneTest.class.simpleName
}
