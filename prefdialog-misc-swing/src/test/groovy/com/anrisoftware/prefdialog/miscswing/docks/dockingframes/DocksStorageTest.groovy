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

import org.junit.BeforeClass
import org.junit.Test

import bibliothek.gui.dock.common.theme.ThemeMap

import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask
import com.anrisoftware.prefdialog.miscswing.docks.docktesting.DockTestingFactory
import com.google.inject.Injector

/**
 * Layout save and load test.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Slf4j
class DocksStorageTest extends DocksTestBase {

    @Test
    void "set layout, store and load layout"() {
        File tmp = File.createTempFile("docks", "layout")
        int number = 0
        String title = "Docks"
        String name = "test"
        def testing = testingFactory.create([title: title])()
        testing.withFixture({
            invokeAndWait {
                testing.dock.applyLayout defaultLayout
                testing.dock.setTheme ThemeMap.KEY_ECLIPSE_THEME
            }
        }, {
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, NORTH) }
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, WEST) }
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, SOUTH) }
            invokeAndWait { testing.dock.addEditorDock createEditorDock(number++) }
            invokeAndWait { testing.dock.addEditorDock createEditorDock(number++) }
        }, {
            log.info "Store your layout."
            testing.dock.saveLayout(name, tmp)
        },{
            log.info "Load your layout."
            testing.dock.loadLayout(name, tmp)
        },
        { Thread.sleep 3*1000 })
    }

    @Test
    void "manually store and load layout"() {
        File tmp = File.createTempFile("docks-storane", null)
        tmp.deleteOnExit()
        String title = "Docks"
        int number = 0
        String name = "test"
        def testing = testingFactory.create([title: title])()
        testing.withFixture({
            invokeAndWait {
                testing.dock.applyLayout defaultLayout
                testing.dock.setTheme ThemeMap.KEY_ECLIPSE_THEME
            }
        }, {
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, NORTH) }
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, WEST) }
            invokeAndWait { testing.dock.addViewDock createViewDock(number++, SOUTH) }
            invokeAndWait { testing.dock.addEditorDock createEditorDock(number++) }
            invokeAndWait { testing.dock.addEditorDock createEditorDock(number++) }
        }, {
            Thread.sleep 10*1000
            log.info "Store your layout."
            testing.dock.saveLayout(name, tmp)
        },{
            Thread.sleep 10*1000
            log.info "Load your layout."
            testing.dock.loadLayout(name, tmp)
        },
        { Thread.sleep 3*1000 })
    }

    static Injector injector

    static DockTestingFactory testingFactory

    static LayoutTask defaultLayout

    @BeforeClass
    static void setup() {
        injector = createInjector()
        defaultLayout = createDefaultPerspective(injector, "default")
        testingFactory = injector.getInstance DockTestingFactory
    }
}
