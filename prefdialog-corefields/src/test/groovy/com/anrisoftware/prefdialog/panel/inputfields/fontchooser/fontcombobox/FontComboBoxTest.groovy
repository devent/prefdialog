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
package com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox

import javax.swing.JFrame
import javax.swing.JPanel

import org.apache.commons.lang.builder.ToStringBuilder
import org.apache.commons.lang.builder.ToStringStyle
import org.junit.After
import org.junit.Before
import org.junit.Test

import com.anrisoftware.prefdialog.panel.inputfields.AbstractFixtureHandler
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Tests the basic behavior of the font combobox.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class FontComboBoxTest extends AbstractFixtureHandler {

	public static Injector injector = Guice.createInjector(new FontComboBoxModule())

	static toStringStyle = ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE)

	@Before
	void beforeTest() {
		def panel = createPanel()
		frame = createPanelFrame panel
		beginFixture()
	}

	private JPanel createPanel() {
		def panel = new JPanel()
		def fontNames = FontComboBox.getAvailableFontNames()
		def fontComboBox = injector.getInstance(FontComboBoxFactory).create(fontNames)
		panel.add fontComboBox
		return panel
	}

	@After
	void afterTest() {
		endFixture()
	}

	@Test
	void "manually"() {
		//Thread.sleep 30000
	}
}
