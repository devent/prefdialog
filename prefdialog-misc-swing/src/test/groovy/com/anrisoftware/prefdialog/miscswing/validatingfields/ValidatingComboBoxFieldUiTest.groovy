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

import static com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingUtils.*
import static info.clearthought.layout.TableLayoutConstants.*
import static javax.swing.SwingUtilities.*

import javax.swing.JComboBox

import org.fest.swing.fixture.FrameFixture
import org.fest.swing.fixture.JComboBoxFixture
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * @see ValidatingComboBoxFieldUi
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ValidatingComboBoxFieldUiTest {

	@Test
	void "show invalid with tool-tip"() {
		def title = "$NAME::show invalid with tool-tip"
		def fieldName = "fieldA"
		field = createComboBoxField(fieldName, true)
		fieldEditor = ValidatingComboBoxEditor.decorate field
		fieldEditor.setInvalidText "Not valid"
		fieldB = createTextField("fieldB")
		panel = createPanel cols: [FILL], rows: [
			FILL,
			PREFERRED,
			PREFERRED,
			FILL
		], fields: [
			"0, 1": field,
			"0, 2": fieldB
		]
		new TestFrameUtil(title, panel).withFixture({ FrameFixture fix ->
			comboBoxFix = fix.comboBox()
			fix.comboBox().selectItem(0)
			invokeAndWait { fieldEditor.setInputValid false  }
			comboBoxFix.selectItem(0)
			comboBoxFix.selectItem(1)
		}, {
			invokeAndWait { fieldEditor.setInputValid true  }
		}, {
			invokeAndWait { fieldEditor.setInputValid false  }
		}, { FrameFixture fix ->
			fix.textBox("fieldB").focus()
			invokeAndWait { fieldEditor.setInputValid false  }
			comboBoxFix.selectItem(0)
			comboBoxFix.selectItem(1)
		}, {
			invokeAndWait { fieldEditor.setInputValid true  }
		}, {
			invokeAndWait { fieldEditor.setInputValid false  }
		})
	}

	static final String NAME = ValidatingComboBoxFieldUiTest.class.simpleName

	JComboBox field

	JComboBoxFixture comboBoxFix

	ValidatingComboBoxEditor fieldEditor

	def fieldB

	def panel
}
