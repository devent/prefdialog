/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-csvimportdialog.
 *
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.panel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.fields.textfield.TextFieldBean.*

import java.awt.Dimension

import javax.swing.JPanel

import org.apache.commons.io.FileUtils
import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import com.anrisoftware.globalpom.dataimport.CsvImportModule
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanelFactory
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanelModule
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelProperties
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory
import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ComboBoxHistoryModule
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
	void "show"() {
		def title = "$NAME::show"
		def field = factory.create(new JPanel(), properties)
		field.createPanel injector
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container, size).withFixture({
		})
	}

	//@Test
	void "manually"() {
		def title = "$NAME::manually"
		def field = factory.create(new JPanel(), properties)
		field.createPanel injector
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container, size).withFixture({ FrameFixture fixture ->
			Thread.sleep 60*1000
			assert false : "manually test"
		})
	}

	@Rule
	public TemporaryFolder tmp = new TemporaryFolder()

	static final String NAME = CsvImportPanelTest.class.simpleName

	static URL LOTTO = CsvImportPanelTest.class.getResource("/com/anrisoftware/prefdialog/csvimportdialog/csvimport/lotto_2001.csv")

	static Injector injector

	static Injector panelInjector

	static CsvImportPanelFactory factory

	static size = new Dimension(400, 566)

	static CsvPanelPropertiesFactory propertiesFactory

	CsvPanelProperties properties

	File lotto

	@BeforeClass
	static void setupFactories() {
		TestUtils.toStringStyle
		injector = Guice.createInjector(
				new CoreFieldComponentModule(),
				new TextsResourcesDefaultModule(),
				new ComboBoxHistoryModule(),
				new CsvImportModule())
		panelInjector = injector.createChildInjector(new CsvImportPanelModule())
		factory = panelInjector.getInstance(CsvImportPanelFactory)
		propertiesFactory = panelInjector.getInstance(CsvPanelPropertiesFactory)
	}

	@Before
	void setupBean() {
		properties = propertiesFactory.create()
	}

	@Before
	void setupData() {
		lotto = tmp.newFile("lotto")
		FileUtils.copyURLToFile LOTTO, lotto
	}
}
