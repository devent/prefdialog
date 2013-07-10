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
package com.anrisoftware.prefdialog.csvimportdialog.importpaneldock

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.fields.textfield.TextFieldBean.*

import java.awt.Dimension

import javax.swing.JFrame
import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanelModule
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesModule;
import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ComboBoxHistoryModule
import com.anrisoftware.prefdialog.miscswing.docks.api.Dock
import com.anrisoftware.prefdialog.miscswing.docks.api.DockFactory
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesModule
import com.anrisoftware.prefdialog.miscswing.docks.layouts.dockingframes.DefaultLayoutTask
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see CsvImportPanel
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ImportPanelDockTest {

	@Test
	void "show"() {
		def title = "$NAME::show"
		def field = factory.create(new JPanel(), properties)
		field.createPanel injector
		def container = field.getAWTComponent()
		new TestFrameUtil(title, container, size).withFixture({
		})
	}

	@Test
	void "manually"() {
		def title = "$NAME::manually"
		ImportPanelDock panelDock = panelInjector.getInstance(ImportPanelDock)
		panelDock.createPanel injector, properties
		JFrame frame
		Dock dock
		def util = new TestFrameUtil(title, null, size) {
					protected def createFrame(String titlea, arg1) {
						frame = new JFrame(titlea)
						dock = dockFactory.create(frame)
						frame.add dock.getAWTComponent()
						dock.applyLayout defaultLayout
						dock.addEditorDock panelDock
						return frame
					}
				}
		util.withFixture({
			Thread.sleep 60*1000
			assert false : "manually test"
		})
	}

	static final String NAME = ImportPanelDockTest.class.simpleName

	static Injector injector

	static Injector panelInjector

	static size = new Dimension(400, 362)

	static CsvPanelPropertiesFactory propertiesFactory

	static DockFactory dockFactory

	static defaultLayout

	CsvPanelProperties properties

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(
				new CoreFieldComponentModule(),
				new TextsResourcesDefaultModule(),
				new CsvPanelPropertiesModule(),
				new ComboBoxHistoryModule(),
				new DockingFramesModule())
		panelInjector = injector.createChildInjector(new CsvImportPanelModule())
		dockFactory = panelInjector.getInstance(DockFactory)
		propertiesFactory = injector.getInstance(CsvPanelPropertiesFactory)
		defaultLayout = injector.getInstance(DefaultLayoutTask)
	}

	@Before
	void setupBean() {
		properties = propertiesFactory.create()
	}
}
