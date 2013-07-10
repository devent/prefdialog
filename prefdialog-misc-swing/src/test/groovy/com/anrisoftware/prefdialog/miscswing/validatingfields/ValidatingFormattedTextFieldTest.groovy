/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.validatingfields

import static com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingUtils.*
import static info.clearthought.layout.TableLayoutConstants.*
import static javax.swing.SwingUtilities.*

import java.awt.event.KeyEvent

import javax.swing.InputVerifier
import javax.swing.JFormattedTextField

import org.fest.swing.fixture.FrameFixture
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * @see ValidatingFormattedTextField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ValidatingFormattedTextFieldTest {

	@Test
	void "show invalid with tool-tip"() {
		def title = "$NAME::show invalid with tool-tip"
		def fieldName = "fieldA"
		def value = 10
		def textFieldFix
		def textField = createFormattedTextField(fieldName, value)
		def field = ValidatingFormattedTextField.decorate(textField, createVerifier(textField))
		field.setInvalidText "Not valid"
		def fieldBFix
		def fieldB = createTextField("fieldB")
		def panel = createPanel cols: [FILL], rows: [
			FILL,
			PREFERRED,
			PREFERRED,
			FILL
		], fields: [
			"0, 1": textField,
			"0, 2": fieldB
		]
		new TestFrameUtil(title, panel).withFixture({ FrameFixture fix ->
			textFieldFix = fix.textBox(fieldName)
			fieldBFix = fix.textBox("fieldB")
			textFieldFix.selectAll()
			textFieldFix.enterText "20"
			textFieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
			assert field.isValid() == false
		}, {
			textFieldFix.selectAll()
			textFieldFix.enterText "5"
			textFieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
			assert field.isValid() == true
		}, {
			textFieldFix.selectAll()
			textFieldFix.enterText "20"
			fieldBFix.focus()
			assert field.isValid() == false
		}, {
			textFieldFix.selectAll()
			textFieldFix.enterText "10"
			fieldBFix.focus()
			assert field.isValid() == true
		}, { Thread.sleep 60000 //
		})
	}

	static final String NAME = ValidatingFormattedTextFieldTest.class.simpleName

	static createVerifier(JFormattedTextField field) {
		[ verify: { return field.value == 10 || field.value == 5 } ] as InputVerifier
	}
}
