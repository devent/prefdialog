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

import static javax.swing.SwingUtilities.*
import groovy.util.logging.Slf4j

import org.junit.BeforeClass
import org.junit.Test

import bibliothek.gui.dock.common.theme.ThemeMap
import bibliothek.gui.dock.displayer.DisplayerDockBorder

import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask
import com.anrisoftware.prefdialog.miscswing.docks.docktesting.DockTestingFactory
import com.anrisoftware.prefdialog.miscswing.docks.themes.dockingframes.noborder.NoBorderModifierBridge
import com.google.inject.Injector

/**
 * @see DockingFramesDock
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Slf4j
class DocksTest extends DocksTestBase {

    @Test
    void "flat theme"() {
        def title = "$NAME/flat theme"
        def testing = testingFactory.create([title: title])()
        testing.withFixture({
            invokeAndWait {
                viewDocks.each { testing.dock.addViewDock(it) }
                testing.dock.addEditorDock editorDocks[0]
                testing.dock.addEditorDock editorDocks[1]
                testing.dock.setTheme ThemeMap.KEY_FLAT_THEME
            }
        })
    }

    @Test
    void "basic theme, no border modifier"() {
        def title = "$NAME/basic theme, no border modifier"
        def testing = testingFactory.create([title: title])()
        testing.withFixture({
            invokeAndWait {
                viewDocks.each { testing.dock.addViewDock(it) }
                testing.dock.addEditorDock editorDocks[0]
                testing.dock.addEditorDock editorDocks[1]
                testing.dock.setTheme ThemeMap.KEY_FLAT_THEME
                testing.dock.getThemeManager().setBorderModifierBridge(
                        DisplayerDockBorder.KIND, new NoBorderModifierBridge())
            }
        })
    }

    @Test
    void "no editors"() {
        def title = "$NAME/no editors"
        def testing = testingFactory.create([title: title])()
        testing.withFixture({
            invokeAndWait {
                viewDocks.each { testing.dock.addViewDock(it) }
            }
        }, {
            invokeAndWait { testing.dock.setTheme(ThemeMap.KEY_ECLIPSE_THEME) }
        }, {
            invokeAndWait { testing.dock.setTheme(ThemeMap.KEY_BUBBLE_THEME) }
        }, {
            invokeAndWait { testing.dock.setTheme(ThemeMap.KEY_SMOOTH_THEME) }
        }, {
            invokeAndWait { testing.dock.setTheme(ThemeMap.KEY_BASIC_THEME) }
        }, {
            invokeAndWait {
                testing.dock.getThemeManager().setBorderModifierBridge(
                        DisplayerDockBorder.KIND, new NoBorderModifierBridge())
            }
        })
    }

    static final String NAME = DocksTest.class.simpleName

    static Injector injector

    static DockTestingFactory testingFactory

    static LayoutTask defaultLayout

    @BeforeClass
    static void setup() {
        injector = createInjector()
        testingFactory = injector.getInstance DockTestingFactory
        defaultLayout = createDefaultPerspective(injector, "default")
    }
}
