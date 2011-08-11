/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.panel.inputfields.checkbox

import org.junit.Test

import com.globalscalingsoftware.prefdialog.annotations.Checkbox
import com.globalscalingsoftware.prefdialog.panel.inputfields.AbstractFieldFixture
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.CheckBoxFieldHandlerFactory;

class CheckboxTitleTest extends AbstractFieldFixture {

	static factory = injector.getInstance(CheckBoxFieldHandlerFactory)

	static class General {

		@Checkbox(title='Should be save automatically?', text='yes/no')
		def automaticSave = false
	}

	CheckboxTitleTest() {
		super(new General(), 'automaticSave', factory)
	}

	@Test
	void "checkbox set title and text"() {
		fixture.checkBox('automaticSave').click()
		assert fixture.label('label-automaticSave').text() == 'Should be save automatically?'
		assert fixture.checkBox('automaticSave').text() == 'yes/no'
	}
}
