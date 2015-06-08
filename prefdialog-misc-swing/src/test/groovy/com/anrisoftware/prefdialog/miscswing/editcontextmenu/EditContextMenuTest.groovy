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
package com.anrisoftware.prefdialog.miscswing.editcontextmenu

import java.awt.BorderLayout
import java.awt.Dimension

import javax.swing.JPanel
import javax.swing.JTextField

import org.fest.swing.fixture.FrameFixture
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.google.inject.Injector

/**
 * @see EditContextMenu
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3,1
 */
class EditContextMenuTest {

    @Test
    void "popup menu"() {
        def textFieldA
        def textFieldB
        def editContextMenu
        def testing = testingFactory.create(size: size, title: "$NAME:popup menu", createComponent: { frame ->
            def ret = createTextBox()
            textFieldA = ret.textFieldA
            textFieldB = ret.textFieldB
            editContextMenu = editContextMenuFactory.create()
            editContextMenu.addTextField textFieldA
            editContextMenu.addTextField textFieldB
            editContextMenu.createMenu()
            return ret.panel
        })
        testing().withFixture({ FrameFixture fix ->
            textFieldA = fix.textBox "textFieldA"
            textFieldA.showPopupMenu()
        })
    }

    @Test
    void "delete"() {
        def textFieldA
        def textFieldB
        def editContextMenu
        def testing = testingFactory.create(size: size, title: "$NAME:delete", createComponent: { frame ->
            def ret = createTextBox()
            editContextMenu = editContextMenuFactory.create()
            editContextMenu.addTextField ret.textFieldA
            editContextMenu.addTextField ret.textFieldB
            editContextMenu.createMenu()
            return ret.panel
        })
        testing().withFixture({ FrameFixture fix ->
            textFieldA = fix.textBox "textFieldA"
            textFieldB = fix.textBox "textFieldB"
            textFieldA.setText "a"
            textFieldB.setText "b"
            textFieldA.selectAll()
            def popupMenu = fix.textBox("textFieldA").showPopupMenu()
            popupMenu.menuItem("delete_action").click()
            textFieldA.requireEmpty()
            textFieldB.requireText("b")
        })
    }

    @Test
    void "copy, paste, cut"() {
        def textFieldA
        def textFieldB
        def editContextMenu
        def testing = testingFactory.create(size: size, title: "$NAME:copy, paste, cut", createComponent: { frame ->
            def ret = createTextBox()
            editContextMenu = editContextMenuFactory.create()
            editContextMenu.addTextField ret.textFieldA
            editContextMenu.addTextField ret.textFieldB
            editContextMenu.createMenu()
            return ret.panel
        })
        testing().withFixture({ FrameFixture fix ->
            textFieldA = fix.textBox "textFieldA"
            textFieldB = fix.textBox "textFieldB"
            textFieldA.setText "a"
            textFieldB.setText "b"
            textFieldA.selectAll()
            def popupMenu = textFieldA.showPopupMenu()
            popupMenu.menuItem("cut_action").click()
            textFieldA.requireEmpty()
        }, { FrameFixture fix ->
            def popupMenu = textFieldA.showPopupMenu()
            popupMenu.menuItem("paste_action").click()
            textFieldA.requireText "a"
        }, { FrameFixture fix ->
            textFieldA.setText "foo"
            textFieldA.selectAll()
            def popupMenu = textFieldA.showPopupMenu()
            popupMenu.menuItem("delete_action").click()
            textFieldA.requireEmpty()
        }, { FrameFixture fix ->
            def popupMenu = textFieldA.showPopupMenu()
            popupMenu.menuItem("paste_action").click()
            textFieldA.requireText "a"
        }, { FrameFixture fix ->
            def popupMenu = textFieldA.showPopupMenu()
            popupMenu.menuItem("select_all_action").click()
            assert textFieldA.target.getSelectionStart() == 0
            assert textFieldA.target.getSelectionEnd() == 1
        })
    }

    static final String NAME = EditContextMenuTest.class.simpleName

    static final size = new Dimension(128, 64)

    static Injector injector

    static FrameTestingFactory testingFactory

    static EditContextMenuFactory editContextMenuFactory

    @BeforeClass
    static void createResources() {
        injector = EditContextMenuModule.SingletonHolder.injector.createChildInjector(
                new FrameTestingModule())
        editContextMenuFactory = injector.getInstance EditContextMenuFactory
        testingFactory = injector.getInstance FrameTestingFactory
    }

    static createTextBox() {
        def panel = new JPanel(new BorderLayout())
        def textFieldA = new JTextField()
        textFieldA.setName "textFieldA"
        def textFieldB = new JTextField()
        textFieldB.setName "textFieldB"
        panel.add textFieldA, BorderLayout.NORTH
        panel.add textFieldB, BorderLayout.SOUTH
        [textFieldA: textFieldA, textFieldB: textFieldB, panel: panel]
    }
}
