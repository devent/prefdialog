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
package com.anrisoftware.prefdialog.csvimportdialog.dialog

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.fields.textfield.TextFieldBean.*

import java.awt.Dimension

import javax.swing.JDialog
import javax.swing.JPanel

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.dataimport.CsvImportModule
import com.anrisoftware.globalpom.dataimport.CsvImportProperties
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory
import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ComboBoxHistoryModule
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesModule
import com.anrisoftware.resources.texts.api.Texts
import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see CsvImportDialog
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class CsvImportDialogTest {

	@Test
	void "show dialog"() {
		def title = "$NAME::show dialog"
		def dialog
		def importDialog
		def frame = new TestFrameUtil(title: title, component: new JPanel())
		frame.withFixture({
			dialog = new JDialog(frame.frame, title)
			importDialog = CsvImportDialog.decorate(
					dialog, frame.frame, bean, texts, injector).createDialog()
			dialog.pack()
			dialog.setLocationRelativeTo(frame.frame)
			importDialog.openDialog()
		})
	}

	@Test
	void "manually decorate"() {
		def title = "$NAME::manually decorate"
		def dialog
		def importDialog
		def frame = new TestFrameUtil(title: title, component: new JPanel())
		frame.withFixture({
			dialog = new JDialog(frame.frame, title)
			importDialog = CsvImportDialog.decorate(
					dialog, frame.frame, bean, texts, injector).createDialog()
			dialog.pack()
			dialog.setLocationRelativeTo(frame.frame)
			importDialog.openDialog()
		}, { Thread.sleep 60*1000 })
	}

	static final String NAME = CsvImportDialogTest.class.simpleName

	static Injector injector

	static Injector panelInjector

	static CsvImportDialogFactory factory

	static CsvPanelPropertiesFactory propertiesFactory

	static size = new Dimension(400, 362)

	static TextsFactory textsFactory

	static Texts texts

	CsvImportProperties bean

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(
				new CoreFieldComponentModule(),
				new ComboBoxHistoryModule(),
				new DockingFramesModule(),
				new CsvImportModule(),
				new TextsResourcesDefaultModule())
		panelInjector = injector.createChildInjector(
				new CsvImportDialogModule())
		factory = panelInjector.getInstance(CsvImportDialogFactory)
		propertiesFactory = panelInjector.getInstance(CsvPanelPropertiesFactory)
		textsFactory = injector.getInstance(TextsFactory)
		texts = textsFactory.create(CsvImportDialog.class.getSimpleName())
	}

	@Before
	void setupBean() {
		bean = propertiesFactory.create()
	}
}
