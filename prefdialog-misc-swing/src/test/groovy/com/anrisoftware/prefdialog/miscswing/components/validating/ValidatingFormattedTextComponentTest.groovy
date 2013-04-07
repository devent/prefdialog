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
package com.anrisoftware.prefdialog.miscswing.components.validating

import static info.clearthought.layout.TableLayoutConstants.*
import info.clearthought.layout.TableLayout

import java.awt.event.KeyEvent
import java.beans.PropertyVetoException
import java.beans.VetoableChangeListener
import java.text.NumberFormat

import javax.swing.JFormattedTextField
import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * Test the {@link ValidatingFormattedTextComponent}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ValidatingFormattedTextComponentTest {

	@Test
	void "enter valid value"() {
		def title = "ValidatingFormattedTextComponentTest :: enter valid value"
		def field = new ValidatingFormattedTextComponent(createNumberTextField(fieldName))
		def panel = createPanel cols: [FILL], rows: [FILL, PREFERRED, FILL], fields: [
			"0, 1": field.component
		]

		field.addVetoableChangeListener createVetoableChangeListener(validNumber)

		new TestFrameUtil(title, panel).withFixture({ FrameFixture fixture ->
			fixture.textBox().enterText "$validNumber"
			fixture.textBox fieldName pressAndReleaseKeys KeyEvent.VK_ENTER
			assert field.isValueValid() == true
		})
	}

	@Test
	void "restore invalid value"() {
		def title = "ValidatingFormattedTextComponentTest :: restore invalid value"
		def oldValue = ""
		def field = new ValidatingFormattedTextComponent(createNumberTextField(fieldName))
		def fieldB = createNumberTextField("fieldB")
		def panel = createPanel cols: [FILL], rows: [
			FILL,
			PREFERRED,
			PREFERRED,
			FILL
		], fields: [
			"0, 1": fieldB,
			"0, 2": field.component
		]

		field.addVetoableChangeListener createVetoableChangeListener(validNumber)

		new TestFrameUtil(title, panel).withFixture({ FrameFixture fixture ->
			fixture.textBox fieldName enterText "invalid"
			fixture.textBox fieldB.getName() focus()
			fixture.textBox fieldName requireText oldValue
		}, { FrameFixture fixture ->
			fixture.textBox fieldName deleteText()
			fixture.textBox fieldName enterText "$validNumber"
			fixture.textBox fieldB.getName() focus()
			fixture.textBox fieldName requireText "$validNumber"
		}, { FrameFixture fixture ->
			fixture.textBox fieldName deleteText()
			fixture.textBox fieldName enterText "invalid"
			fixture.textBox fieldB.getName() focus()
			fixture.textBox fieldName requireText "$validNumber"
		})
	}

	@Test(timeout = 60000l)
	void "manually"() {
		def title = "ValidatingFormattedTextComponentTest :: manually"
		def parseError = "Invalid number"
		def field = new ValidatingFormattedTextComponent(createNumberTextField(fieldName))
		field.setParseErrorText parseError

		def fieldB = createNumberTextField("fieldB")
		def panel = createPanel cols: [FILL], rows: [
			FILL,
			PREFERRED,
			PREFERRED,
			FILL
		], fields: [
			"0, 1": fieldB,
			"0, 2": field.component
		]

		field.addVetoableChangeListener createVetoableChangeListener(validNumber)

		new TestFrameUtil(title, panel).withFixture({ FrameFixture fixture ->
			Thread.sleep 60 * 1000
		})
	}

	static fieldName = "valid-text"

	static int validNumber = 10

	static VetoableChangeListener createVetoableChangeListener(def validValue) {
		[vetoableChange: { ev ->
				if (ev.newValue != validValue) {
					throw new PropertyVetoException("Only value $validValue is allowed.", ev)
				}
			}] as VetoableChangeListener
	}

	static JPanel createPanel(def attributes) {
		def cols = attributes.cols as double[]
		def rows = attributes.rows as double[]
		def panel = new JPanel(new TableLayout(cols, rows))
		attributes.fields.each {
			panel.add it.value, it.key
		}
		return panel
	}

	static JFormattedTextField createNumberTextField(def name) {
		def field = new JFormattedTextField(NumberFormat.getNumberInstance())
		field.setName name
		return field
	}
}
