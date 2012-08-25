package com.anrisoftware.prefdialog.core

import javax.swing.JLabel

import org.junit.Test

/**
 * Test the {@link AbstractFieldComponent}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FieldComponentTest extends FieldTestUtils {

	def title = "Field Component Test"

	@Test
	void "show text field"() {
		def component = new JLabel()
		def field = fieldComponentFactory.create(component, preferences, preferencesTextField).createField()
		beginPanelFrame title, component, { }
	}
}
