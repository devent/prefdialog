/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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
 * Dock layout.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Slf4j
class DocksLayoutsTest extends DocksTestBase {

    @Test
    void "set two layouts"() {
        String title = "DocksTest::set two layouts"
        int number = 0
        def testing = testingFactory.create([title: title])()
        testing.withFixture({
            invokeAndWait {
                testing.dock.applyLayout defaultLayout
                testing.dock.setTheme(ThemeMap.KEY_ECLIPSE_THEME)
            }
        }, {
            invokeAndWait {
                testing.dock.addViewDock createViewDock(number++, EAST)
                testing.dock.addViewDock createViewDock(number++, EAST)
                testing.dock.addViewDock createViewDock(number++, EAST)
                testing.dock.addEditorDock createEditorDock(number++)
                testing.dock.addEditorDock createEditorDock(number++)
            }
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
