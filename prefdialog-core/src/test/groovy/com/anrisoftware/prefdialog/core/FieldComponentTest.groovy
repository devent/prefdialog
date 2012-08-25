package com.anrisoftware.prefdialog.core

import static com.anrisoftware.prefdialog.core.Preferences.*

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
		def component = new JLabel("Label")
		def preferenceField = preferencesTextField
		def field = fieldComponentFactory.create(component, preferences, preferenceField).createField()
		assertStringContent field.title, "textField"
		beginPanelFrame title, component, {
			fixture.label(preferenceField.name).requireEnabled()
		}
	}

	@Test
	void "show text field with title"() {
		def component = new JLabel("Label")
		def preferenceField = preferencesTextFieldWithTitle
		def field = fieldComponentFactory.create(component, preferences, preferenceField).createField()
		assertStringContent field.title, "Test Field"
		beginPanelFrame title, component, {
			fixture.label(preferenceField.name).requireEnabled()
		}
	}

	@Test
	void "show text field read-only"() {
		def component = new JLabel("Label")
		def preferenceField = preferencesTextFieldReadOnly
		def field = fieldComponentFactory.create(component, preferences, preferenceField).createField()
		assert field.enabled == false
		beginPanelFrame title, component, {
			fixture.label(preferenceField.name).requireDisabled()
		}
	}

	@Test
	void "show text field with tool-tip"() {
		def component = new JLabel("Label")
		def preferenceField = preferencesTextFieldWithToolTip
		def field = fieldComponentFactory.create(component, preferences, preferenceField).createField()
		beginPanelFrame title, component, {
			fixture.label(preferenceField.name).requireToolTip("Tool Tip")
		}, { field.setShowToolTip(true) }, { field.setShowToolTip(false) }
	}
}
