/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-panel.
 *
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.simpledialog

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.simpledialog.SimpleDialogBean.*

import javax.swing.JDialog
import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status
import com.anrisoftware.resources.texts.api.Texts
import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see SimpleDialog
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SimpleDialogTest {

	@Test
	void "cancel dialog"() {
		def title = "$NAME::cancel dialog"
		def fieldName = NULL_VALUE
		def frame = new TestFrameUtil(title: title, component: new JPanel())
		def dialog
		def simpleDialog
		def dialogFix
		frame.withFixture({
			dialog = new JDialog(frame.frame, title)
			simpleDialog = SimpleDialog.decorate(dialog, bean, fieldName, texts, injector).createDialog()
			dialog.pack()
			dialog.setLocationRelativeTo(frame.frame)
			simpleDialog.openDialog()
		}, { FrameFixture fix ->
			fix.button "cancelButton" click()
			assert simpleDialog.status == Status.CANCELED
		})
	}

	@Test
	void "approve dialog"() {
		def title = "$NAME::approve dialog"
		def fieldName = NULL_VALUE
		def frame = new TestFrameUtil(title: title, component: new JPanel())
		def dialog
		def simpleDialog
		def dialogFix
		frame.withFixture({
			dialog = new JDialog(frame.frame, title)
			simpleDialog = SimpleDialog.decorate(dialog, bean, fieldName, texts, injector).createDialog()
			dialog.pack()
			dialog.setLocationRelativeTo(frame.frame)
			simpleDialog.openDialog()
		}, { FrameFixture fix ->
			fix.button "approveButton" click()
			assert simpleDialog.status == Status.APPROVED
		})
	}

	//@Test
	void "manually decorate"() {
		def title = "$NAME::manually decorate"
		def fieldName = NULL_VALUE
		def frame = new TestFrameUtil(title: title, component: new JPanel())
		def dialog
		def simpleDialog
		frame.withFixture({
			dialog = new JDialog(frame.frame, title)
			simpleDialog = SimpleDialog.decorate(dialog, bean, fieldName, texts, injector).createDialog()
			dialog.pack()
			dialog.setLocationRelativeTo(frame.frame)
			simpleDialog.openDialog()
			Thread.sleep 60 * 1000l
			assert false : "manually test"
		})
	}

	static Injector injector

	static final String NAME = SimpleDialogTest.class.simpleName

	static TextsFactory textsFactory

	static Texts texts

	SimpleDialogBean bean

	@BeforeClass
	static void setupFactories() {
		TestUtils.toStringStyle
		injector = Guice.createInjector(new CoreFieldComponentModule())
		textsFactory = injector.createChildInjector(
				new TextsResourcesDefaultModule()).getInstance(TextsFactory)
		texts = textsFactory.create(SimpleDialog.class.getSimpleName())
	}

	@Before
	void setupBean() {
		bean = new SimpleDialogBean()
	}
}
