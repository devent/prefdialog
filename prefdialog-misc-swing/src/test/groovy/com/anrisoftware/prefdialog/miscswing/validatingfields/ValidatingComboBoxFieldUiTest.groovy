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

import org.fest.swing.fixture.FrameFixture
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
	void "not editable, show invalid with tool-tip"() {
		def title = "$NAME::not editable, show invalid with tool-tip"
		def fieldName = "fieldA"
		def field = ValidatingComboBoxUi.decorate(createComboBoxField(fieldName, false))
		def comboBox
		field.setInvalidText "Not valid"
		def fieldB = createTextField("fieldB")
		def panel = createPanel cols: [FILL], rows: [
			FILL,
			PREFERRED,
			PREFERRED,
			FILL
		], fields: [
			"0, 1": field.component,
			"0, 2": fieldB
		]
		new TestFrameUtil(title, panel).withFixture({ FrameFixture fix ->
			comboBox = fix.comboBox()
			fix.comboBox().selectItem(0)
			invokeAndWait { field.setValid false  }
			comboBox.selectItem(0)
			comboBox.selectItem(1)
		}, {
			invokeAndWait { field.setValid true  }
		}, {
			invokeAndWait { field.setValid false  }
		}, { FrameFixture fix ->
			fix.textBox().focus()
			invokeAndWait { field.setValid false  }
			comboBox.selectItem(0)
			comboBox.selectItem(1)
		}, {
			invokeAndWait { field.setValid true  }
		}, {
			invokeAndWait { field.setValid false  }
		})
	}

	@Test
	void "editable, show invalid with tool-tip"() {
		def title = "$NAME::editable, show invalid with tool-tip"
		def fieldName = "fieldA"
		def field = ValidatingComboBoxUi.decorate(createComboBoxField(fieldName, true))
		field.setInvalidText "Not valid"
		def fieldB = createTextField("fieldB")
		def panel = createPanel cols: [FILL], rows: [
			FILL,
			PREFERRED,
			PREFERRED,
			FILL
		], fields: [
			"0, 1": field.component,
			"0, 2": fieldB
		]
		new TestFrameUtil(title, panel).withFixture({ FrameFixture fix ->
			invokeAndWait { field.setValid false }
			fix.comboBox().selectItem(0)
			fix.comboBox().selectItem(1)
		}, { FrameFixture fix ->
			invokeAndWait { field.setValid true  }
		}, {
			invokeAndWait { field.setValid false  }
		}, { FrameFixture fix ->
			fix.textBox("fieldB").focus()
			invokeAndWait { field.setValid false  }
			fix.comboBox().selectItem(0)
			fix.comboBox().selectItem(1)
		}, {
			invokeAndWait { field.setValid true  }
		}, {
			invokeAndWait { field.setValid false  }
		})
	}

	static final String NAME = ValidatingComboBoxFieldUiTest.class.simpleName
}
