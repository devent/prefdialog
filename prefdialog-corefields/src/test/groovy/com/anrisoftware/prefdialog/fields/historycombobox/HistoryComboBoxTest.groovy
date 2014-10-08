/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.historycombobox

import static com.anrisoftware.prefdialog.fields.historycombobox.HistoryComboBoxBean.*
import static com.anrisoftware.prefdialog.fields.utils.FieldTestUtils.*

import java.awt.event.KeyEvent

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ComboBoxHistoryModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see HistoryComboBoxField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class HistoryComboBoxTest {

    @Test
    void "elements, editable"() {
        def title = "$NAME::elements, editable"
        def fieldName = LIST_ELEMENTS
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def fixbox

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixbox = fixture.comboBox fieldName
            fixbox.requireSelection ""
            fixbox.selectItem 0
            assert bean."$fieldName" == "One"
            fixbox.selectItem 1
            assert bean."$fieldName" == "Two"
            fixbox.selectItem 2
            assert bean."$fieldName" == "Three"
        }, { FrameFixture fixture ->
            fixbox.selectAllText()
            fixbox.enterText "1"
            fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
            assert bean."$fieldName" == "1"
        })
    }

    @Test
    void "elements, editable, default items"() {
        def title = "$NAME::elements, editable, default items"
        def fieldName = LIST_ELEMENTS_DEFAULT_ITEMS
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def fixbox

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixbox = fixture.comboBox fieldName
            fixbox.selectAllText()
            fixbox.enterText "1"
            fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
            assert bean."$fieldName" == "1"
            fixbox.selectAllText()
            fixbox.enterText "2"
            fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
            assert bean."$fieldName" == "2"
            fixbox.selectAllText()
            fixbox.enterText "3"
            fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
            assert bean."$fieldName" == "3"
            fixbox.selectAllText()
            fixbox.enterText "Default A"
            fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
            assert bean."$fieldName" == "Default A"
        }, {
            fixbox.selectItem 0
            assert bean."$fieldName" == "3"
            fixbox.selectItem 1
            assert bean."$fieldName" == "2"
            fixbox.selectItem 2
            assert bean."$fieldName" == "1"
            fixbox.selectItem 3
            assert bean."$fieldName" == "One"
            fixbox.selectItem 4
            assert bean."$fieldName" == "Two"
            fixbox.selectItem 5
            assert bean."$fieldName" == "Default A"
            fixbox.selectItem 6
            assert bean."$fieldName" == "Default B"
        })
    }

    @Test
    void "elements, editable, history"() {
        def title = "$NAME::elements, editable, history"
        def fieldName = LIST_ELEMENTS_HISTORY
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def fixbox

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixbox = fixture.comboBox fieldName
            fixbox.selectAllText()
            fixbox.enterText "1"
            fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
            fixbox.selectAllText()
            fixbox.enterText "2"
            fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
            fixbox.selectAllText()
            fixbox.enterText "3"
            fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
            fixbox.selectAllText()
            fixbox.enterText "Default A"
            fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
        }, {
            assert bean.history.contains("1")
            assert bean.history.contains("2")
            assert bean.history.contains("3")
            assert !bean.history.contains("Default A")
        })
    }

    @Test
    void "elements, editable, maximum"() {
        def title = "$NAME::elements, editable, maximum"
        def fieldName = LIST_ELEMENTS_MAXIMUM
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        def fixbox

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            fixbox = fixture.comboBox fieldName
            fixbox.selectAllText()
            fixbox.enterText "1"
            fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
            fixbox.selectAllText()
            fixbox.enterText "2"
            fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
            fixbox.selectAllText()
            fixbox.enterText "3"
            fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
        }, {
            fixbox.selectItem 0
            assert bean."$fieldName" == "3"
            fixbox.selectItem 1
            assert bean."$fieldName" == "2"
        })
    }

    @Test
    void "editable, veto value"() {
        def title = "$NAME::editable, veto value"
        def fieldName = LIST_ELEMENTS
        def field = factory.create(bean, fieldName)
        setupVetoableListener field, getValidValues(bean)
        def container = field.getAWTComponent()
        def comboBox

        new TestFrameUtil(title, container).withFixture({ FrameFixture fixture ->
            comboBox = fixture.comboBox fieldName
            comboBox.replaceText "Some"
            comboBox.pressAndReleaseKeys KeyEvent.VK_ENTER
            assert bean."$fieldName" == null
        }, {
            comboBox.replaceText "Valid"
            comboBox.pressAndReleaseKeys KeyEvent.VK_ENTER
            assert bean."$fieldName" == "Valid"
        }, {
            comboBox.selectItem 0
            assert bean."$fieldName" == "Valid"
            comboBox.selectItem 1
            assert bean."$fieldName" == "One"
        })
    }

    //@Test
    void "manually"() {
        def title = "$NAME::manually"
        def fieldName = LIST_ELEMENTS
        def field = factory.create(bean, fieldName)
        def container = field.getAWTComponent()
        new TestFrameUtil(title, container).withFixture({
            Thread.sleep 60*1000
            assert false : "manually test"
        })
    }

    //@Test
    void "manually, validated"() {
        def title = "$NAME::manually, validated"
        def fieldName = LIST_ELEMENTS
        def field = factory.create(bean, fieldName)
        setupVetoableListener field, getValidValues(bean)
        def container = field.getAWTComponent()
        new TestFrameUtil(title, container).withFixture({
            Thread.sleep 60*1000
            assert false : "manually test"
        })
    }

    static final String NAME = HistoryComboBoxTest.class.simpleName

    static Injector injector

    static HistoryComboBoxFactory factory

    HistoryComboBoxBean bean

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.injector = Guice.createInjector(
                new CoreFieldComponentModule(), new ComboBoxHistoryModule(),
                new HistoryComboBoxModule())
        this.factory = injector.getInstance HistoryComboBoxFactory
    }

    @Before
    void setupBean() {
        this.bean = new HistoryComboBoxBean()
    }

    static getValidValues(HistoryComboBoxBean bean) {
        [
            "Valid",
            bean.listElements,
            bean.defaultItems
        ].flatten()
    }

}
