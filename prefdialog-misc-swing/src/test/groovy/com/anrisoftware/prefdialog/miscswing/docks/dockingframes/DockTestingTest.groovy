package com.anrisoftware.prefdialog.miscswing.docks.dockingframes

import static com.anrisoftware.prefdialog.miscswing.docks.dockingframes.DocksTestBase.*
import static com.anrisoftware.prefdialog.miscswing.docks.docktesting.DockTesting.*
import static javax.swing.SwingUtilities.invokeAndWait

import org.junit.BeforeClass
import org.junit.Test

import bibliothek.gui.dock.common.theme.ThemeMap

import com.anrisoftware.prefdialog.miscswing.docks.api.DockFactory
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask
import com.anrisoftware.prefdialog.miscswing.docks.docktesting.DockTestingFactory
import com.google.inject.Injector

/**
 * Docking Frames test.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class DockTestingTest {

	@Test
	void "flat theme"() {
		String title = "$NAME::flat theme"
		def testing = testingFactory.create([title: title]).createDock()
		testing.withFixture({
			invokeAndWait {
				viewDocks.each {
					testing.dock.addViewDock(it)
				}
			}
			invokeAndWait { testing.dock.addEditorDock editorDocks[0] }
			invokeAndWait { testing.dock.addEditorDock editorDocks[1] }
			invokeAndWait { testing.dock.setTheme ThemeMap.KEY_FLAT_THEME }
		})
	}

	static NAME = DocksTest.class.simpleName

	static Injector injector

	static DockFactory factory

	static LayoutTask defaultLayout

	static DockTestingFactory testingFactory

	@BeforeClass
	static void setup() {
		injector = createInjector()
		defaultLayout = createDefaultPerspective(injector, "default")
		testingFactory = injector.getInstance DockTestingFactory
	}
}
