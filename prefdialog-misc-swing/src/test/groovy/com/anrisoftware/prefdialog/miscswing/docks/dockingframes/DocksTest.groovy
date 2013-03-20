package com.anrisoftware.prefdialog.miscswing.docks.dockingframes

import static javax.swing.SwingUtilities.*
import groovy.util.logging.Slf4j

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import bibliothek.gui.dock.common.theme.ThemeMap

import com.anrisoftware.prefdialog.miscswing.docks.api.Dock
import com.anrisoftware.prefdialog.miscswing.docks.api.PerspectiveTask
import com.google.inject.Injector

@Slf4j
class DocksTest extends DocksTestBase {

	@Test
	void "manually"() {
		String title = "DocksTest::manually"
		withFrame(title, {
			singleWindows.each { dock.addSingleDock(it) }
			dock.addWorkDock(workWindows[0])
			dock.addWorkDock(workWindows[1])
			dock.applyPerspective defaultPerspective
		}).withFixture({ Thread.sleep 60*1000 })
	}

	@Test
	void "manually set flat theme"() {
		String title = "DocksTest::manually set flat theme"
		withFrame(title, {
			singleWindows.each { dock.addSingleDock(it) }
			dock.addWorkDock(workWindows[0])
			dock.addWorkDock(workWindows[1])
			dock.applyPerspective defaultPerspective
			dock.setTheme(ThemeMap.KEY_FLAT_THEME)
		}).withFixture({ Thread.sleep 60*1000 })
	}

	@Test
	void "manually set eclipse theme"() {
		String title = "DocksTest::manually set eclipse theme"
		withFrame(title, {
			singleWindows.each { dock.addSingleDock(it) }
			dock.addWorkDock(workWindows[0])
			dock.addWorkDock(workWindows[1])
			dock.applyPerspective defaultPerspective
			dock.setTheme(ThemeMap.KEY_ECLIPSE_THEME)
		}).withFixture({ Thread.sleep 60*1000 })
	}

	@Test
	void "manually store and load perspective"() {
		File tmp = File.createTempFile("perspective", "")
		String title = "DocksTest::manually store and load perspective"
		String name = "test"
		withFrame(title, {
			singleWindows.each { dock.addSingleDock(it) }
			dock.addWorkDock(workWindows[0])
			dock.addWorkDock(workWindows[1])
			dock.applyPerspective defaultPerspective
		}).withFixture({
			log.info "Choose your layout."
			Thread.sleep 10*1000
			invokeLater { dock.savePerspective(name, tmp) }
			log.info "Layout saved as $name."
		},{
			invokeLater { dock.loadPerspective(name, tmp) }
			log.info "Layout restored as $name."
		},
		{ Thread.sleep 5*1000 })
	}

	static Injector injector

	static PerspectiveTask defaultPerspective

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
