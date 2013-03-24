package com.anrisoftware.prefdialog.miscswing.docks.dockingframes

import static com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition.*
import static javax.swing.SwingUtilities.*
import groovy.util.logging.Slf4j

import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.prefdialog.miscswing.docks.api.Dock
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask
import com.google.inject.Injector

@Slf4j
class DocksPositionTest extends DocksTestBase {

	@Test
	void "add docks to N,W,S,E"() {
		String title = "DocksTest::add docks to N,W,S,E"
		int number = 0
		withFrame(title, { //
			dock.applyLayout defaultPerspective }
		).withFixture( {
			dock.addViewDock createViewDock(number++, NORTH)
		}, {
			dock.addViewDock createViewDock(number++, WEST)
		}, {
			dock.addViewDock createViewDock(number++, SOUTH)
		}, {
			dock.addViewDock createViewDock(number++, EAST)
		}, { Thread.sleep 5*1000 })
	}

	@Test
	void "add 2x docks to N"() {
		String title = "DocksTest::add 2x docks to N"
		int number = 0
		withFrame(title, { //
			dock.applyLayout defaultPerspective }
		).withFixture( {
			dock.addViewDock createViewDock(number++, NORTH)
		}, {
			dock.addViewDock createViewDock(number++, NORTH)
		}, { Thread.sleep 5*1000 })
	}

	@Test
	void "add 2x docks to W, add 2x edit"() {
		String title = "DocksTest::add 2x docks to W, add 2x edit"
		int number = 0
		withFrame(title, { //
			dock.applyLayout defaultPerspective }
		).withFixture( {
			dock.addViewDock createViewDock(number++, NORTH)
		}, {
			dock.addViewDock createViewDock(number++, NORTH)
		}, {
			dock.addEditorDock createEditorDock(number++)
		}, {
			dock.addEditorDock createEditorDock(number++)
		}, { Thread.sleep 5*1000 })
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
