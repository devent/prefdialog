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
