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
import static com.anrisoftware.prefdialog.csvimportdialog.dialog.CsvImportDialog.*
import static com.anrisoftware.prefdialog.csvimportdialog.dialog.CsvImportDialogModule.*
import static com.anrisoftware.prefdialog.fields.textfield.TextFieldBean.*

import java.awt.Dimension

import javax.swing.JDialog
import javax.swing.JPanel

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory

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
		def bean = propertiesFactory.create()
		def frame = new TestFrameUtil(title: title, component: new JPanel())
		frame.withFixture({
			dialog = new JDialog(frame.frame, title)
			importDialog = decorateCsvImportDialog(dialog, frame.frame, bean).createDialog()
			dialog.pack()
			dialog.setLocationRelativeTo(frame.frame)
			importDialog.openDialog()
		})
	}

	//@Test
	void "manually decorate"() {
		def title = "$NAME::manually decorate"
		def dialog
		def importDialog
		def bean = propertiesFactory.create()
		def frame = new TestFrameUtil(title: title, component: new JPanel())
		frame.withFixture({
			dialog = new JDialog(frame.frame, title)
			importDialog = decorateCsvImportDialog(dialog, frame.frame, bean).createDialog()
			dialog.pack()
			dialog.setLocationRelativeTo(frame.frame)
			importDialog.openDialog()
		}, {
			Thread.sleep 60*1000
			assert false : "deactivate manually test"
		})
	}

	static final String NAME = CsvImportDialogTest.class.simpleName

	static CsvImportDialogFactory factory

	static CsvPanelPropertiesFactory propertiesFactory

	static size = new Dimension(400, 362)

	@BeforeClass
	static void setupFactories() {
		factory = getCsvImportDialogFactory()
		propertiesFactory = getCsvImportPropertiesFactory()
	}
}
