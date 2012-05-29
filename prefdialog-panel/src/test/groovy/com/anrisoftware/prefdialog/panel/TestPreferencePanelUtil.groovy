/*
 * Copyright 2010-2012 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.panel

import javax.swing.JPanel


import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.PreferencePanel
import com.anrisoftware.prefdialog.PreferencePanelFactory
import com.anrisoftware.prefdialog.panel.inputfields.PrefdialogCoreFieldsModule
import com.google.inject.Guice
import com.google.inject.Injector

abstract class TestPreferencePanelUtil extends TestFrameUtil {

	static Injector injector = Guice.createInjector(
	new PrefdialogPanelModule(), new PrefdialogCoreFieldsModule())

	static PreferencePanelFactory factory = injector.getInstance(PreferencePanelFactory)

	PreferencePanel preferencePanel

	JPanel panel

	@Override
	void beginPanelFrame(def preferences, def childName, Object... runTest) {
		panel = new JPanel()
		preferencePanel = factory.create(panel, preferences, childName)
		super.beginPanelFrame("$childName Test", panel, runTest)
	}
}
