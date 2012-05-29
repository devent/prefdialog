/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-dialog.
 * 
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.dialog

import java.awt.Dimension

import javax.swing.JDialog
import javax.swing.JFrame

import org.apache.commons.lang.ArrayUtils

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.ChildrenPanel
import com.anrisoftware.prefdialog.ChildrenPanelFactory
import com.anrisoftware.prefdialog.ChildrenPanels
import com.anrisoftware.prefdialog.PreferenceDialog
import com.anrisoftware.prefdialog.PreferenceDialogFactory
import com.anrisoftware.prefdialog.dialog.childrenpanels.DefaultChildrenPanelsFactory
import com.anrisoftware.prefdialog.dialog.childrenpanels.PrefdialogChildrenPanelsModule
import com.anrisoftware.prefdialog.dialog.childrentab.ChildrenTabPanelFactory
import com.anrisoftware.prefdialog.dialog.childrentab.PrefdialogChildrenTabModule
import com.anrisoftware.prefdialog.dialog.childrentree.ChildrenTreePanelFactory
import com.anrisoftware.prefdialog.dialog.childrentree.PrefdialogChildrenTreeModule
import com.anrisoftware.prefdialog.panel.inputfields.PrefdialogCoreFieldsModule
import com.google.inject.Guice

abstract class TestPreferenceDialogUtil extends TestFrameUtil {

	static injector = Guice.createInjector(
	new PrefdialogModule(),
	new PrefdialogCoreFieldsModule(),
	new PrefdialogChildrenPanelsModule(),
	new PrefdialogChildrenTreeModule(),
	new PrefdialogChildrenTabModule())

	static ChildrenPanelFactory childrenTreePanelfactory = injector.getInstance ChildrenTreePanelFactory

	static ChildrenPanelFactory childrenTabPanelfactory = injector.getInstance ChildrenTabPanelFactory

	static PreferenceDialogFactory factory = injector.getInstance PreferenceDialogFactory

	static DefaultChildrenPanelsFactory childrenPanelsFactory = injector.getInstance DefaultChildrenPanelsFactory

	ChildrenPanel childrenPanel

	PreferenceDialog preferenceDialog

	ChildrenPanels childrenPanels

	JDialog dialog

	@Override
	public Object createFrame(Object title, Object component) {
		startDelay = 0
		frameSize = new Dimension(640, 480)
		frame = new JFrame()
		frame.size = frameSize
		dialog = new JDialog(frame)
		dialog.size = frameSize
		dialog.modal = false
		createChildrenPanel()
		preferenceDialog = factory.create dialog, childrenPanel
		return frame
	}

	/**
	 * Creates the ChildrenPanel for the PreferenceDialog.
	 */
	abstract createChildrenPanel()

	@Override
	void beginPanelFrame(def title, def preferences, Object... tests) {
		childrenPanels = childrenPanelsFactory.create preferences
		tests = ArrayUtils.add tests, 0, {
			childrenPanel.childrenModel = childrenPanels
			childrenPanel.childrenPanels = childrenPanels
			dialog.visible = true
		}
		super.beginPanelFrame title, null, tests
	}
}
