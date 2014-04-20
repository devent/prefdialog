/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask
import com.anrisoftware.prefdialog.miscswing.docks.docktesting.DockTestingFactory
import com.google.inject.Injector

/**
 * Dock position.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Slf4j
class DocksPositionTest extends DocksTestBase {

    @Test
    void "add docks to N,W,S,E"() {
        String title = "DocksTest::add docks to N,W,S,E"
        int number = 0
        def testing = testingFactory.create([title: title])()
        testing.withFixture({
            invokeAndWait { testing.dock.applyLayout defaultLayout }
        }, {
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, NORTH) }
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, WEST) }
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, SOUTH) }
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, EAST) }
        }, {
            log.info "Set new layout"
            invokeAndWait { testing.dock.applyLayout defaultLayout }
        })
    }

    @Test
    void "add 2x docks to N"() {
        String title = "DocksTest::add 2x docks to N"
        int number = 0
        def testing = testingFactory.create([title: title])()
        testing.withFixture({
            invokeAndWait { testing.dock.applyLayout defaultLayout }
        }, {
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, NORTH) }
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, NORTH) }
        }, {
            log.info "Set new layout"
            invokeAndWait { testing.dock.applyLayout defaultLayout }
        })
    }

    @Test
    void "add 2x docks to W, add 2x edit"() {
        String title = "DocksTest::add 2x docks to W, add 2x edit"
        int number = 0
        def testing = testingFactory.create([title: title])()
        testing.withFixture({
            invokeAndWait { testing.dock.applyLayout defaultLayout }
        }, {
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, WEST) }
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, WEST) }
        }, {
            invokeAndWait {
                testing.dock.addEditorDock createEditorDock(number++)
                testing.dock.addEditorDock createEditorDock(number++)
            }
        }, {
            log.info "Set new layout"
            invokeAndWait { testing.dock.applyLayout defaultLayout }
        })
    }

    static Injector injector

    static LayoutTask defaultLayout

    static DockTestingFactory testingFactory

    @BeforeClass
    static void setup() {
        injector = createInjector()
        defaultLayout = createDefaultPerspective(injector, "default")
        testingFactory = injector.getInstance DockTestingFactory
    }
}
