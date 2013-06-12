package com.anrisoftware.prefdialog.miscswing.docks.dockingframes

import static javax.swing.SwingUtilities.*
import groovy.util.logging.Slf4j

import javax.swing.event.ChangeListener

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import bibliothek.gui.dock.common.theme.ThemeMap

import com.anrisoftware.prefdialog.miscswing.docks.api.Dock
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask
import com.google.inject.Injector

@Slf4j
class DocksTest extends DocksTestBase {

	@Test
	void "flat theme"() {
		String title = "DocksTest::flat theme"
		withFrame(title, {
			viewDocks.each { dock.addViewDock(it) }
			dock.addEditorDock(editorDocks[0])
			dock.addEditorDock(editorDocks[1])
			dock.applyLayout defaultPerspective
			dock.setTheme(ThemeMap.KEY_FLAT_THEME)
		}).withFixture({
		})
	}

	@Test
	void "eclipse theme"() {
		String title = "DocksTest::eclipse theme"
		withFrame(title, {
			viewDocks.each { dock.addViewDock(it) }
			dock.addEditorDock(editorDocks[0])
			dock.addEditorDock(editorDocks[1])
			dock.applyLayout defaultPerspective
			dock.setTheme(ThemeMap.KEY_ECLIPSE_THEME)
		}).withFixture({
		})
	}

	@Test
	void "manually"() {
		String title = "DocksTest::manually"
		withFrame(title, {
			viewDocks.each { dock.addViewDock(it) }
			dock.addEditorDock(editorDocks[0])
			dock.addEditorDock(editorDocks[1])
			dock.applyLayout defaultPerspective
		}).withFixture({
			Thread.sleep 60*1000
			assert false : "Deactivate manually test"
		})
	}

	@Test
	void "manually state change"() {
		String title = "DocksTest::manually state change"
		dock.addStateChangedListener({ ev -> println ev } as ChangeListener)
		withFrame(title, {
			dock.applyLayout defaultPerspective
			viewDocks.each { dock.addViewDock(it) }
			dock.addEditorDock(editorDocks[0])
			dock.addEditorDock(editorDocks[1])
		}).withFixture({
			Thread.sleep 60*1000
			assert false : "Deactivate manually test"
		})
	}

	static Injector injector

	static LayoutTask defaultPerspective

	@BeforeClass
	static void setup() {
		injector = createInjector()
		defaultPerspective = createDefaultPerspective(injector, "default")
	}

	Dock dock

	@Before
	void setupDock() {
		dock = createDock(injector)
	}
}
