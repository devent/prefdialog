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
package com.anrisoftware.prefdialog.csvimportdialog.panel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.fields.textfield.TextFieldBean.*

import java.awt.Dimension

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportModuleModule
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvProperties
import com.anrisoftware.resources.texts.central.TextsCentralModule
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see CsvImportPanel
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class CsvImportPanelTest {

	@Test
	void "manually"() {
		def title = "$NAME::manually"
		def field = factory.create(properties)
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container, size).withFixture({ FrameFixture fixture ->
			Thread.sleep 60*1000
			assert false : "manually test"
		})
	}

	static final String NAME = CsvImportPanelTest.class.simpleName

	static Injector injector

	static CsvImportPanelFactory factory

	static size = new Dimension(400, 600)

	CsvProperties properties

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(
				new CsvImportPanelModule(), new CsvImportModuleModule(),
				new TextsResourcesDefaultModule(), new TextsCentralModule())
		factory = injector.getInstance CsvImportPanelFactory
	}

	@Before
	void setupBean() {
		properties = injector.getInstance CsvProperties
	}
}