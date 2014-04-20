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
package com.anrisoftware.prefdialog.miscswing.validatingfields

import static com.anrisoftware.globalpom.utils.TestFrameUtil.*
import static com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingUtils.*
import static info.clearthought.layout.TableLayoutConstants.*
import static javax.swing.SwingUtilities.*

import java.awt.event.KeyEvent

import javax.swing.InputVerifier
import javax.swing.JFormattedTextField

import org.fest.swing.fixture.FrameFixture
import org.fest.swing.fixture.JTextComponentFixture
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
		//setLookAndFeel SUBSTANCE_BUSINESS_LOOK_AND_FEEL
		def title = "$NAME::show invalid with tool-tip"
		field = createFormattedTextField(fieldName, value, validatingFormattedTextField)
		field.setVerifier createVerifier(field)
		field.setInvalidText "Not valid"
		fieldB = createTextField("fieldB")
		def panel = createPanel cols: [FILL], rows: [
			FILL,
			PREFERRED,
			PREFERRED,
			FILL
		], fields: [
			"0, 1": field,
			"0, 2": fieldB
		]
		new TestFrameUtil(title, panel).withFixture({ FrameFixture fix ->
			fieldFix = fix.textBox(fieldName)
			fieldBFix = fix.textBox("fieldB")
			fieldFix.selectAll()
			fieldFix.enterText "20"
			fieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
			assert field.isInputValid() == false
		}, {
			fieldFix.selectAll()
			fieldFix.enterText "5"
			fieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
			assert field.isInputValid() == true
		}, {
			fieldFix.selectAll()
			fieldFix.enterText "20"
			fieldBFix.focus()
			assert field.isInputValid() == false
		}, {
			fieldFix.selectAll()
			fieldFix.enterText "10"
			fieldBFix.focus()
			assert field.isInputValid() == true
		})
	}

	static final String NAME = ValidatingFormattedTextFieldTest.class.simpleName

	static createVerifier(JFormattedTextField field) {
		[ verify: { return field.value == 10 || field.value == 5 } ] as InputVerifier
	}

	def value = 10

	def fieldName = "fieldA"

	JTextComponentFixture fieldBFix

	def fieldB

	ValidatingFormattedTextField field

	JTextComponentFixture fieldFix
}
