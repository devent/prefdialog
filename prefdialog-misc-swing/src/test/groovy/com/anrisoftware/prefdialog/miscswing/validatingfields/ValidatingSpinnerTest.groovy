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
package com.anrisoftware.prefdialog.miscswing.validatingfields

import static com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingUtils.*
import static info.clearthought.layout.TableLayoutConstants.*

import java.awt.event.KeyEvent

import org.fest.swing.fixture.FrameFixture
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * @see ValidatingSpinner
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ValidatingSpinnerTest {

	@Test
	void "enter valid value"() {
		def title = "$NAME::enter valid value"
		def field = new ValidatingSpinner(createSpinner(fieldName))
		def panel = createPanel cols: [FILL], rows: [FILL, PREFERRED, FILL], fields: [
			"0, 1": field.component
		]

		field.addVetoableChangeListener createVetoableChangeListener(validValue)

		new TestFrameUtil(title, panel).withFixture({ FrameFixture fixture ->
			fixture.spinner fieldName enterText "$validValue"
			fixture.spinner fieldName pressAndReleaseKeys KeyEvent.VK_ENTER
			assert field.isValueValid() == true
		})
	}

	@Test
	void "restore invalid value"() {
		def title = "$NAME::restore invalid value"
		def oldValue = validValue
		def field = new ValidatingSpinner(createSpinner(fieldName))
		def fieldB = createTextField("fieldB")
		def panel = createPanel cols: [FILL], rows: [
			FILL,
			PREFERRED,
			PREFERRED,
			FILL
		], fields: [
			"0, 1": fieldB,
			"0, 2": field.component
		]

		field.addVetoableChangeListener createVetoableChangeListener(validValue)

		new TestFrameUtil(title, panel).withFixture({ FrameFixture fixture ->
			fixture.spinner fieldName enterText "$validValue"
			fixture.textBox fieldB.getName() focus()
			fixture.spinner fieldName requireValue validValue
		}, { FrameFixture fixture ->
			fixture.spinner fieldName enterText "1"
			fixture.textBox fieldB.getName() focus()
			fixture.spinner fieldName requireValue oldValue
		}, { FrameFixture fixture ->
			fixture.spinner fieldName enterText "$validValue"
			fixture.textBox fieldB.getName() focus()
			fixture.spinner fieldName requireValue oldValue
		})
	}

	@Test
	void "manually"() {
		def title = "$NAME::manually"
		def field = new ValidatingSpinner(createSpinner(fieldName))
		def fieldB = createTextField("fieldB")
		def panel = createPanel cols: [FILL], rows: [
			FILL,
			PREFERRED,
			PREFERRED,
			FILL
		], fields: [
			"0, 1": fieldB,
			"0, 2": field.component
		]

		field.addVetoableChangeListener createVetoableChangeListener(validValue)

		new TestFrameUtil(title, panel).withFixture({ FrameFixture fixture ->
			Thread.sleep 60 * 1000
			assert false : "Manually test"
		})
	}

	static final String NAME = ValidatingSpinnerTest.class.simpleName

	static fieldName = "validatingSpinner"

	static validValue = 5
}
