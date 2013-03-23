package com.anrisoftware.prefdialog.miscswing.docks.dockingframes

import static javax.swing.SwingUtilities.*
import groovy.util.logging.Slf4j

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
	void "manually"() {
		String title = "DocksTest::manually"
		withFrame(title, {
			viewDocks.each { dock.addViewDock(it) }
			dock.addEditorDock(editorDocks[0])
			dock.addEditorDock(editorDocks[1])
			dock.applyLayout defaultPerspective
		}).withFixture({ Thread.sleep 60*1000 })
	}

	@Test
	void "manually set flat theme"() {
		String title = "DocksTest::manually set flat theme"
		withFrame(title, {
			viewDocks.each { dock.addViewDock(it) }
			dock.addEditorDock(editorDocks[0])
			dock.addEditorDock(editorDocks[1])
			dock.applyLayout defaultPerspective
			dock.setTheme(ThemeMap.KEY_FLAT_THEME)
		}).withFixture({ Thread.sleep 60*1000 })
	}

	@Test
	void "manually set eclipse theme"() {
		String title = "DocksTest::manually set eclipse theme"
		withFrame(title, {
			viewDocks.each { dock.addViewDock(it) }
			dock.addEditorDock(editorDocks[0])
			dock.addEditorDock(editorDocks[1])
			dock.applyLayout defaultPerspective
			dock.setTheme(ThemeMap.KEY_ECLIPSE_THEME)
		}).withFixture({ Thread.sleep 60*1000 })
	}

	@Test
	void "manually store and load perspective"() {
		File tmp = File.createTempFile("perspective", "")
		String title = "DocksTest::manually store and load perspective"
		String name = "test"
		withFrame(title, {
			dock.applyLayout defaultPerspective
			viewDocks.each { dock.addViewDock(it) }
			dock.applyLayout defaultPerspective
		}).withFixture({
			dock.addEditorDock(editorDocks[0])
		},{
			dock.addEditorDock(editorDocks[1])
		},{
			log.info "Choose your layout."
			Thread.sleep 10*1000
			invokeLater { dock.saveLayout(name, tmp) }
			log.info "Layout saved as $name."
		},{
			invokeLater { dock.loadLayout(name, tmp) }
			log.info "Layout restored as $name."
		},
		{ Thread.sleep 5*1000 })
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
