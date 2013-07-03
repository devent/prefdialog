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
import static javax.swing.SwingUtilities.*

import org.fest.swing.fixture.FrameFixture
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * @see ValidatingSpinnerFieldUi
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ValidatingSpinnerFieldUiTest {

	@Test
	void "show invalid with tool-tip"() {
		def title = "$NAME::show invalid with tool-tip"
		def fieldName = "fieldA"
		def field = ValidatingSpinnerFieldUi.decorate(createSpinner(fieldName))
		field.setInvalidText "Not valid"
		def fieldB = createSpinner("fieldB")
		def fieldC = createTextField("fieldC")
		def panel = createPanel cols: [FILL], rows: [
			FILL,
			PREFERRED,
			PREFERRED,
			PREFERRED,
			FILL
		], fields: [
			"0, 1": field.component,
			"0, 2": fieldB,
			"0, 3": fieldC
		]
		new TestFrameUtil(title, panel).withFixture({ FrameFixture fix ->
			invokeAndWait { field.setValid false  }
		}, {
			invokeAndWait { field.setValid true  }
		}, {
			invokeAndWait { field.setValid false  }
		})
	}

	@Test
	void "show invalid with tool-tip, GTK+"() {
		TestFrameUtil.setLookAndFeel(TestFrameUtil.GTK_LOOK_AND_FEEL)
		def title = "$NAME::show invalid with tool-tip, GTK+"
		def fieldName = "fieldA"
		def field = ValidatingSpinnerFieldUi.decorate(createSpinner(fieldName))
		field.setInvalidText "Not valid"
		def fieldB = createSpinner("fieldB")
		def fieldC = createTextField("fieldC")
		def panel = createPanel cols: [FILL], rows: [
			FILL,
			PREFERRED,
			PREFERRED,
			PREFERRED,
			FILL
		], fields: [
			"0, 1": field.component,
			"0, 2": fieldB,
			"0, 3": fieldC
		]
		new TestFrameUtil(title: title, component: panel).
		withFixture({ FrameFixture fix ->
			invokeAndWait { field.setValid false  }
		}, {
			invokeAndWait { field.setValid true  }
		}, {
			invokeAndWait { field.setValid false  }
		})
	}

	static final String NAME = ValidatingSpinnerFieldUiTest.class.simpleName
}
