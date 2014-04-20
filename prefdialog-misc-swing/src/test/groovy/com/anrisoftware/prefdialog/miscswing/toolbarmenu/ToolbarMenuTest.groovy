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
package com.anrisoftware.prefdialog.miscswing.toolbarmenu

import static com.anrisoftware.prefdialog.miscswing.toolbarmenu.ToolbarMenu.*
import static javax.swing.SwingUtilities.*
import groovy.util.logging.Slf4j

import java.awt.BorderLayout
import java.beans.PropertyChangeListener

import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.gimport.*
import com.anrisoftware.globalpom.mnemonic.MnemonicModule
import com.anrisoftware.globalpom.textposition.TextPosition
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.resources.images.api.IconSize
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see ToolbarMenu
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Slf4j
class ToolbarMenuTest {

    @Test
    void "popup menu"() {
        ToolbarMenu toolbarMenu
        def testing = testingFactory.create(title: "Tool-bar Menu", createComponent: { frame ->
            def ret = createToolbar()
            toolbarMenu = ret.toolbarMenu
            return ret.panel
        })
        testing().withFixture({ FrameFixture fix ->
            fix.toolBar().showPopupMenu()
            assert fix.menuItem(TEXT_ALONGSIDE_NAME).target.selected
            assert fix.menuItem(SMALL_ICON_SIZE_NAME).target.selected
        })
    }

    @Test
    void "switch text position"() {
        FrameFixture fix
        ToolbarMenu toolbarMenu
        def property
        def testing = testingFactory.create(title: "Tool-bar Menu", createComponent: { frame ->
            def ret = createToolbar()
            toolbarMenu = ret.toolbarMenu
            toolbarMenu.addPropertyChangeListener({ e ->
                log.info "Text position: {}", e.newValue
                property = e.newValue
            } as PropertyChangeListener)
            return ret.panel
        })
        testing().withFixture({ fix = it }, {
            fix.toolBar().showPopupMenu()
            fix.menuItem TEXT_ONLY_NAME click()
            assert toolbarMenu.getTextPosition() == TextPosition.TEXT_ONLY
            assert property == TextPosition.TEXT_ONLY
        }, {
            fix.toolBar().showPopupMenu()
            fix.menuItem ICONS_ONLY_NAME click()
            assert toolbarMenu.getTextPosition() == TextPosition.ICON_ONLY
            assert property == TextPosition.ICON_ONLY
        }, {
            fix.toolBar().showPopupMenu()
            fix.menuItem TEXT_ONLY_NAME click()
            assert toolbarMenu.getTextPosition() == TextPosition.TEXT_ONLY
            assert property == TextPosition.TEXT_ONLY
        }, {
            fix.toolBar().showPopupMenu()
            fix.menuItem TEXT_ALONGSIDE_NAME click()
            assert toolbarMenu.getTextPosition() == TextPosition.TEXT_ALONGSIDE_ICON
            assert property == TextPosition.TEXT_ALONGSIDE_ICON
        })
    }

    @Test
    void "switch icon sizes"() {
        FrameFixture fix
        ToolbarMenu toolbarMenu
        def testing = testingFactory.create(title: "Tool-bar Menu", createComponent: { frame ->
            def ret = createToolbar()
            toolbarMenu = ret.toolbarMenu
            return ret.panel
        })
        testing().withFixture({ fix = it }, {
            fix.toolBar().showPopupMenu()
            fix.menuItem HUGE_ICON_SIZE_NAME click()
            assert toolbarMenu.getIconSize() == IconSize.HUGE
        }, {
            fix.toolBar().showPopupMenu()
            fix.menuItem LARGE_ICON_SIZE_NAME click()
            assert toolbarMenu.getIconSize() == IconSize.LARGE
        }, {
            fix.toolBar().showPopupMenu()
            fix.menuItem MEDIUM_ICON_SIZE_NAME click()
            assert toolbarMenu.getIconSize() == IconSize.MEDIUM
        }, {
            fix.toolBar().showPopupMenu()
            fix.menuItem SMALL_ICON_SIZE_NAME click()
            assert toolbarMenu.getIconSize() == IconSize.SMALL
        })
    }

    static final String NAME = ToolbarMenuTest.class.simpleName

    static Injector injector

    static FrameTestingFactory testingFactory

    static images

    static texts

    @BeforeClass
    static void createResources() {
        injector = Guice.createInjector(
                new ImagesResourcesModule(),
                new ResourcesImagesMapsModule(),
                new ResourcesSmoothScalingModule(),
                new TextsResourcesDefaultModule(),
                new MnemonicModule(),
                new FrameTestingModule())
        images = injector.getInstance(ImagesFactory).create("Images")
        texts = injector.getInstance(TextsFactory).create("Texts")
        testingFactory = injector.getInstance FrameTestingFactory
    }

    static createToolbar() {
        ToolBar toolBar = injector.getInstance ToolBar
        ToolbarMenu toolbarMenu = injector.getInstance ToolbarMenu
        def panel = new JPanel(new BorderLayout())

        toolBar.buttonAction.setImages images
        toolBar.buttonAction.setTexts texts

        toolbarMenu.addAction toolBar.buttonAction
        toolbarMenu.setToolBar toolBar

        panel.add toolBar, BorderLayout.NORTH

        [toolBar: toolBar, toolbarMenu: toolbarMenu, panel: panel]
    }
}
