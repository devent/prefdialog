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
			dock.addViewDock createViewDock(number++, WEST)
		}, {
			dock.addViewDock createViewDock(number++, WEST)
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
