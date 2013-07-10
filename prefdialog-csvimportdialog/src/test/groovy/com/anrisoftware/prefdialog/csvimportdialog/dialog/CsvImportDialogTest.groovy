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
import javax.swing.JFrame

import org.fest.swing.fixture.DialogFixture
import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.csvimportdialog.csvimport.CsvImportModule
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory
import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ComboBoxHistoryModule
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesModule
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
	void "show panel"() {
		def title = "$NAME::show panel"
		JFrame frame
		CsvImportDialog dialog
		def util = new TestFrameUtil(title, null, size) {
					protected def createFrame(String titlea, arg1) {
						frame = new JFrame(titlea)
						dialog = factory.create(frame, CsvImportDialogTest.this.properties)
						dialog.createPanel injector
						frame.add dialog.getAWTComponent()
						return frame
					}
				}
		util.withFixture({
		})
	}

	@Test
	void "import dialog"() {
		def title = "$NAME::import dialog"
		JFrame frame
		JDialog dialog
		CsvImportDialog importDialog
		DialogFixture dia
		def util = new TestFrameUtil(title, null, size) {
					protected def createFrame(String titlea, arg1) {
						frame = new JFrame(titlea)
						dialog = new JDialog(frame)
						importDialog = factory.create(frame, CsvImportDialogTest.this.properties)
						importDialog.createPanel injector
						importDialog.setDialog dialog
						dialog.pack()
						return frame
					}
				}
		util.withFixture({ FrameFixture fix ->
			importDialog.openDialog()
			dia = fix.dialog()
		}, { FrameFixture fix ->
			fix.button("importButton").click()
			dia.requireNotVisible()
			importDialog.getStatus() == CsvImportDialog.Status.APPROVED
		})
	}

	@Test
	void "cancel dialog"() {
		def title = "$NAME::cancel dialog"
		JFrame frame
		JDialog dialog
		CsvImportDialog importDialog
		DialogFixture dia
		def util = new TestFrameUtil(title, null, size) {
					protected def createFrame(String titlea, arg1) {
						frame = new JFrame(titlea)
						dialog = new JDialog(frame)
						importDialog = factory.create(frame, CsvImportDialogTest.this.properties)
						importDialog.createPanel injector
						importDialog.setDialog dialog
						dialog.pack()
						return frame
					}
				}
		util.withFixture({ FrameFixture fix ->
			importDialog.openDialog()
			dia = fix.dialog()
		}, { FrameFixture fix ->
			fix.button("cancelButton").click()
			dia.requireNotVisible()
			importDialog.getStatus() == CsvImportDialog.Status.CANCELED
		})
	}

	@Test
	void "decorate dialog"() {
		def title = "$NAME::decorate dialog"
		JFrame frame
		JDialog dialog
		CsvImportDialog importDialog
		DialogFixture dia
		def util = new TestFrameUtil(title, null, size) {
					protected def createFrame(String titlea, arg1) {
						frame = new JFrame(titlea)
						dialog = new JDialog(frame)
						importDialog = CsvImportDialog.decorate(dialog, frame,
								CsvImportDialogTest.this.properties, injector)
						return frame
					}
				}
		util.withFixture({ FrameFixture fix ->
			importDialog.openDialog()
			dia = fix.dialog()
			Thread.sleep 60*1000
			dia.button("cancelButton").click()
		})
	}

	static final String NAME = CsvImportDialogTest.class.simpleName

	static Injector injector

	static Injector panelInjector

	static CsvImportDialogFactory factory

	static CsvPanelPropertiesFactory propertiesFactory

	static size = new Dimension(400, 362)

	CsvImportProperties properties

	@BeforeClass
	static void setupFactories() {
		injector = Guice.createInjector(
				new CoreFieldComponentModule(),
				new TextsResourcesDefaultModule(),
				new ComboBoxHistoryModule(),
				new DockingFramesModule(),
				new CsvImportModule())
		panelInjector = injector.createChildInjector(
				new CsvImportDialogModule())
		factory = panelInjector.getInstance(CsvImportDialogFactory)
		propertiesFactory = panelInjector.getInstance(CsvPanelPropertiesFactory)
	}

	@Before
	void setupBean() {
		properties = propertiesFactory.create()
	}
}
