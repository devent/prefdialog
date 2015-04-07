/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.docks.dockingframes

import static com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition.*
import static java.awt.Color.*

import java.awt.Color

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerModule
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesModule
import com.anrisoftware.prefdialog.miscswing.docks.docktesting.DockTestingModule
import com.anrisoftware.prefdialog.miscswing.docks.layouts.dockingframes.DefaultLayoutTask
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Creates view and editor docks.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class DocksTestBase {

	static viewDocks = [
		new ColorViewDock("view_a", "View West A", WEST, BLUE),
		new ColorViewDock("view_b", "View West B", WEST, RED)
	]

	static editorDocks = [
		new ColorEditorDock("editor_1", "Editor 1", CENTER, BLUE),
		new ColorEditorDock("editor_2", "Editor 2", CENTER, RED)
	]

	static ColorViewDock createViewDock(int number, def pos) {
		def color = RGBtoHSB(0, 255, 255, null)
		color = new Color(HSBtoRGB(color[0], color[1], (float)color[2] * 0.2f * number + 0.2f))
		new ColorViewDock("view_${number}", "View ${number}", pos, color)
	}

	static ColorEditorDock createEditorDock(int number) {
		def color = RGBtoHSB(0, 255, 255, null)
		color = new Color(HSBtoRGB(color[0], color[1], (float)color[2] * 0.2f * number))
		new ColorEditorDock("editor_${number}", "Editor ${number}", CENTER, color)
	}

	static Injector createInjector() {
		TestUtils.toStringStyle
		Guice.createInjector(new DockingFramesModule(), new DockTestingModule(), new OnAwtCheckerModule())
	}

	static LayoutTask createDefaultPerspective(Injector injector, String name) {
		LayoutTask task = injector.getInstance(DefaultLayoutTask)
		task.setName name
		return task
	}
}
