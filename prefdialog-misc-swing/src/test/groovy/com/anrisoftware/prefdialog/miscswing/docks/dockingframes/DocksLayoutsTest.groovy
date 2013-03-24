package com.anrisoftware.prefdialog.miscswing.docks.dockingframes

import static com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition.*
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
class DocksLayoutsTest extends DocksTestBase {

	@Test
	void "set two layouts"() {
		String title = "DocksTest::set two layouts"
		int number = 0
		withFrame(title, {
			dock.applyLayout defaultPerspective
			dock.setTheme(ThemeMap.KEY_ECLIPSE_THEME)
		}).withFixture({
			dock.addViewDock createViewDock(number++, EAST)
			dock.addViewDock createViewDock(number++, EAST)
			dock.addViewDock createViewDock(number++, EAST)
			dock.addEditorDock createEditorDock(number++)
			dock.addEditorDock createEditorDock(number++)
		}, {
			log.info "Set new layout"
			dock.applyLayout defaultPerspective
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
