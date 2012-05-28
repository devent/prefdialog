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
package com.anrisoftware.prefdialog.dialog


import javax.swing.JPanel

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.PreferenceDialogHandlerFactory
import com.anrisoftware.prefdialog.panel.inputfields.PrefdialogCoreFieldsModule
import com.google.inject.Guice

abstract class AbstractPreferenceDialogFixture extends TestFrameUtil {

	static injector = Guice.createInjector(
	new PrefdialogModule(),
	new PrefdialogCoreFieldsModule())

	static factory = injector.getInstance PreferenceDialogHandlerFactory

	def dialogHandler

	def dialog

	def doDialogTest(def title, def preferences, def test) {
		beginPanelFrame title, new JPanel(), {
			dialogHandler = createDialogHandler preferences
			dialog = dialogHandler.getAWTComponent()
			dialog.modal = false
			dialog.size = frameSize
			dialog.visible = true
			test()
		}
	}

	def createDialogHandler(def preferences) {
		factory.create(frame, preferences).createDialog()
	}
}
