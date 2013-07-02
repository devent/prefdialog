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
