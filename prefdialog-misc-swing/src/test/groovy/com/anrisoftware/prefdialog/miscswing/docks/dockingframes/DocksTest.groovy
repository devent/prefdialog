/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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

import static javax.swing.SwingUtilities.*
import groovy.util.logging.Slf4j

import javax.swing.event.ChangeListener

import org.junit.BeforeClass
import org.junit.Test

import bibliothek.gui.dock.common.theme.ThemeMap
import bibliothek.gui.dock.displayer.DisplayerDockBorder

import com.anrisoftware.prefdialog.miscswing.docks.api.Dock
import com.anrisoftware.prefdialog.miscswing.docks.api.DockFactory
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask
import com.anrisoftware.prefdialog.miscswing.docks.themes.dockingframes.noborder.NoBorderModifierBridge
import com.google.inject.Injector

@Slf4j
class DocksTest extends DocksTestBase {

	@Test
	void "flat theme"() {
		String title = "$NAME::flat theme"
		Dock dock
		withFrame(title, factory, { it ->
			dock = it
			viewDocks.each { dock.addViewDock(it) }
			dock.addEditorDock(editorDocks[0])
			dock.addEditorDock(editorDocks[1])
			dock.setTheme(ThemeMap.KEY_FLAT_THEME)
		}).withFixture({
		})
	}

	@Test
	void "basic theme, no border modifier"() {
		String title = "$NAME::basic theme, no border modifier"
		Dock dock
		withFrame(title, factory, { it ->
			dock = it
			viewDocks.each { dock.addViewDock(it) }
			dock.addEditorDock(editorDocks[0])
			dock.addEditorDock(editorDocks[1])
		}).withFixture({
			invokeAndWait {
				dock.getThemeManager().setBorderModifierBridge(
						DisplayerDockBorder.KIND, new NoBorderModifierBridge())
			}
			Thread.sleep 60*1000
		})
	}

	@Test
	void "no editors"() {
		String title = "$NAME::no editors"
		Dock dock
		withFrame(title, factory, { it ->
			dock = it
			viewDocks.each { dock.addViewDock(it) }
		}).withFixture({
			invokeAndWait { dock.setTheme(ThemeMap.KEY_ECLIPSE_THEME) }
		}, {
			invokeAndWait { dock.setTheme(ThemeMap.KEY_BUBBLE_THEME) }
		}, {
			invokeAndWait { dock.setTheme(ThemeMap.KEY_SMOOTH_THEME) }
		}, {
			invokeAndWait { dock.setTheme(ThemeMap.KEY_BASIC_THEME) }
		}, {
			invokeAndWait {
				dock.getThemeManager().setBorderModifierBridge(
						DisplayerDockBorder.KIND, new NoBorderModifierBridge())
			}
		})
	}

	@Test
	void "eclipse theme"() {
		String title = "$NAME::eclipse theme"
		Dock dock
		withFrame(title, factory, { it ->
			dock = it
			viewDocks.each { dock.addViewDock(it) }
			dock.addEditorDock(editorDocks[0])
			dock.addEditorDock(editorDocks[1])
			dock.setTheme(ThemeMap.KEY_ECLIPSE_THEME)
		}).withFixture({
		})
	}

	//@Test
	void "manually"() {
		String title = "$NAME::manually"
		Dock dock
		withFrame(title, factory, { it ->
			dock = it
			viewDocks.each { dock.addViewDock(it) }
			dock.addEditorDock(editorDocks[0])
			dock.addEditorDock(editorDocks[1])
		}).withFixture({
			Thread.sleep 60*1000
			assert false : "manually test"
		})
	}

	//@Test
	void "manually state change"() {
		String title = "$NAME::manually state change"
		Dock dock
		withFrame(title, factory, { it ->
			dock = it
			dock.addStateChangedListener({ ev -> println ev } as ChangeListener)
			viewDocks.each { dock.addViewDock(it) }
			dock.addEditorDock(editorDocks[0])
			dock.addEditorDock(editorDocks[1])
		}).withFixture({
			Thread.sleep 60*1000
			assert false : "manually test"
		})
	}

	static final String NAME = DocksTest.class.simpleName

	static Injector injector

	static DockFactory factory

	static LayoutTask defaultLayout

	@BeforeClass
	static void setup() {
		injector = createInjector()
		defaultLayout = createDefaultPerspective(injector, "default")
		this.factory = injector.getInstance(DockFactory.class)
	}
}
