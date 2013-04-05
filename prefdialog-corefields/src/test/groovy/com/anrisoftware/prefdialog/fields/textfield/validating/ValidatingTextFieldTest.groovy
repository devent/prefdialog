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
package com.anrisoftware.prefdialog.fields.textfield.validating

import static com.anrisoftware.prefdialog.fields.combobox.ComboBoxBean.*
import static info.clearthought.layout.TableLayoutConstants.*
import info.clearthought.layout.TableLayout

import java.beans.PropertyVetoException
import java.beans.VetoableChangeListener

import javax.swing.JPanel
import javax.swing.JTextField

import org.fest.swing.fixture.FrameFixture
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * Test the {@link ValidatingTextField}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ValidatingTextFieldTest {

	@Test
	void "enter valid value"() {
		def title = "ValidatingTextFieldTest :: enter valid value"
		def field = new ValidatingTextField(createTextField(fieldName))
		def panel = createPanel cols: [FILL], rows: [FILL, PREFERRED, FILL], fields: [
			"0, 1": field.field
		]

		field.addVetoableChangeListener createVetoableChangeListener(validText)
		field.setValue null

		new TestFrameUtil(title, panel).withFixture({ FrameFixture fixture ->
			validText.eachWithIndex { it, i ->
				fixture.textBox().enterText it
				if (i < validText.length() - 1) {
					assert field.isValueValid() == false
				} else {
					assert field.isValueValid() == true
				}
			}
		})
	}

	@Test
	void "restore invalid value"() {
		def title = "ValidatingTextFieldTest :: restore invalid value"
		def oldValue = ""
		def field = new ValidatingTextField(createTextField(fieldName))
		def fieldB = createTextField("fieldB")
		def panel = createPanel cols: [FILL], rows: [
			FILL,
			PREFERRED,
			PREFERRED,
			FILL
		], fields: [
			"0, 1": fieldB,
			"0, 2": field.field
		]

		field.addVetoableChangeListener createVetoableChangeListener(validText)
		field.setValue oldValue

		new TestFrameUtil(title, panel).withFixture({ FrameFixture fixture ->
			fixture.textBox fieldName enterText "invalid"
			fixture.textBox fieldB.getName() focus()
			fixture.textBox fieldName requireText oldValue
		}, { FrameFixture fixture ->
			fixture.textBox fieldName enterText validText
			fixture.textBox fieldB.getName() focus()
			fixture.textBox fieldName requireText validText
		})
	}

	static fieldName = "valid-text"

	static validText = "validText"

	static VetoableChangeListener createVetoableChangeListener(def validValue) {
		[vetoableChange: { ev ->
				if (ev.newValue != validValue) {
					throw new PropertyVetoException("", ev)
				}
			}] as VetoableChangeListener
	}

	static JPanel createPanel(def attributes) {
		def panel = new JPanel(new TableLayout(
				attributes.cols as double[], attributes.rows as double[]))
		attributes.fields.each {
			panel.add it.value, it.key
		}
		return panel
	}

	static JTextField createTextField(def name) {
		def field = new JTextField()
		field.setName name
		return field
	}
}
