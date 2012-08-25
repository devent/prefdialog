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
	void "show text field with title resource"() {
		def texts = createTextsResource()
		def component = new JLabel("Label")
		def preferenceField = preferencesTextFieldWithTitleResource
		def field = fieldComponentFactory.create(
						component, preferences, preferenceField).createField().
						withTextsResource(texts)
		assertStringContent field.title, "Test Field Eng"
		beginPanelFrame title, component, {
			fixture.label(preferenceField.name).requireEnabled()
		}
	}

	@Test
	void "show text field with title resource change locale"() {
		def texts = createTextsResource()
		def component = new JLabel("Label")
		def preferenceField = preferencesTextFieldWithTitleResource
		def field = fieldComponentFactory.create(
						component, preferences, preferenceField).createField().
						withTextsResource(texts)
		assertStringContent field.title, "Test Field Eng"
		beginPanelFrame title, component, {
			fixture.label(preferenceField.name).requireEnabled()
		}, {
			field.locale = Locale.GERMAN
			assertStringContent field.title, "Test Field Deu"
		}
	}

	@Test
	void "show text field with title resource de"() {
		def texts = createTextsResource()
		def component = new JLabel("Label")
		def preferenceField = preferencesTextFieldWithTitleResourceDe
		def field = fieldComponentFactory.create(
						component, preferences, preferenceField).createField().
						withTextsResource(texts)
		assertStringContent field.title, "Test Field Deu"
		beginPanelFrame title, component, {
			fixture.label(preferenceField.name).requireEnabled()
		}
	}

	@Test
	void "show text field with title missing resource"() {
		def texts = createTextsResource()
		def component = new JLabel("Label")
		def preferenceField = preferencesTextFieldWithTitleMissingResource
		def field = fieldComponentFactory.create(
						component, preferences, preferenceField).createField().
						withTextsResource(texts)
		assertStringContent field.title, "missing_test_field"
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

	@Test
	void "show text field with tool-tip resource"() {
		def texts = createTextsResource()
		def component = new JLabel("Label")
		def preferenceField = preferencesTextFieldWithToolTipResource
		def field = fieldComponentFactory.create(
						component, preferences, preferenceField).createField().
						withTextsResource(texts)
		beginPanelFrame title, component, {
			fixture.label(preferenceField.name).requireToolTip("Tool Tip Eng")
		}, { field.setShowToolTip(true) }, { field.setShowToolTip(false) }
	}

	@Test
	void "show text field with tool-tip resource change locale"() {
		def texts = createTextsResource()
		def component = new JLabel("Label")
		def preferenceField = preferencesTextFieldWithToolTipResource
		def field = fieldComponentFactory.create(
						component, preferences, preferenceField).createField().
						withTextsResource(texts)
		beginPanelFrame title, component, {
			fixture.label(preferenceField.name).requireToolTip("Tool Tip Eng")
		}, { field.setShowToolTip(true) }, { field.setShowToolTip(false) }, {
			field.locale = Locale.GERMAN
			fixture.label(preferenceField.name).requireToolTip("Tool Tip Deu")
			field.setShowToolTip(true)
		}, { field.setShowToolTip(false) }
	}
}
