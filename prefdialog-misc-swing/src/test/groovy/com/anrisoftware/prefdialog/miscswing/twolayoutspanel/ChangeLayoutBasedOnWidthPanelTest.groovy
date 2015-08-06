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
package com.anrisoftware.prefdialog.miscswing.twolayoutspanel

import static com.anrisoftware.prefdialog.miscswing.toolbarmenu.ToolbarMenu.*
import static javax.swing.SwingUtilities.*
import groovy.util.logging.Slf4j

import java.awt.Color
import java.awt.Dimension

import javax.swing.BorderFactory
import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.gimport.*
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see ChangeLayoutBasedOnWidthPanel
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.1
 */
@Slf4j
class ChangeLayoutBasedOnWidthPanelTest {

    @Test
    void "resize panel"() {
        def title = "$NAME-resize panel"
        int verticalLayoutWidth = 128
        ChangeLayoutBasedOnWidthPanel panel
        FrameFixture fix
        def horizontalPanel
        def verticalPanel
        def testing = testingFactory.create(title: title, size: new Dimension(135, 128), createComponent: { frame ->
            panel = createPanel()
            horizontalPanel = createHorizontalPanel()
            verticalPanel = createVerticalPanel()
            panel.setHorizontalPanel horizontalPanel
            panel.setVerticalPanel verticalPanel
            panel.setVerticalLayoutWidth verticalLayoutWidth
            return panel.getPanel()
        })
        testing().withFixture({ FrameFixture it ->
            fix = it
            fix.panel "horizontalPanel" requireVisible()
            fix.resizeTo new Dimension(128, 128)
            fix.panel "verticalPanel" requireVisible()
        }, {
            fix.resizeTo new Dimension(135, 128)
            fix.panel "horizontalPanel" requireVisible()
        })
    }

    static final String NAME = ChangeLayoutBasedOnWidthPanelTest.class.simpleName

    static Injector injector

    static FrameTestingFactory testingFactory

    static ChangeLayoutBasedOnWidthPanelFactory panelFactory

    @BeforeClass
    static void createResources() {
        injector = Guice.createInjector(
                new TwoLayoutsPanelModule(),
                new FrameTestingModule())
        panelFactory = injector.getInstance ChangeLayoutBasedOnWidthPanelFactory
        testingFactory = injector.getInstance FrameTestingFactory
    }

    static createPanel() {
        def panel = panelFactory.create()
        panel.createPanel()
        return panel
    }

    static createHorizontalPanel() {
        def panel = new JPanel()
        panel.setName "horizontalPanel"
        panel.setBorder BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red), "Horizontal")
        return panel
    }

    static createVerticalPanel() {
        def panel = new JPanel()
        panel.setName "verticalPanel"
        panel.setBorder BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue), "Vertical")
        return panel
    }
}
