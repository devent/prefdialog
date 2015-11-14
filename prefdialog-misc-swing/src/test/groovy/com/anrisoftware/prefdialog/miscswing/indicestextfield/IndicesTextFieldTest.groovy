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
package com.anrisoftware.prefdialog.miscswing.indicestextfield

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.awt.BorderLayout
import java.awt.Component
import java.awt.Dimension
import java.awt.event.KeyEvent

import javax.swing.JFormattedTextField
import javax.swing.JFrame

import org.fest.swing.fixture.FrameFixture
import org.fest.swing.fixture.JTextComponentFixture
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.miscswing.indicestextfield.IndicesTextField.IndicesTextFieldFactory
import com.anrisoftware.prefdialog.miscswing.indicestextfield.IndicesTextField.IndicesTextFieldModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see IndicesTextField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
@CompileStatic
@Slf4j
class IndicesTextFieldTest {

    @Test
    void "indices text field"() {
        def title = "$NAME::indices text field"
        JFormattedTextField indicesField
        JTextComponentFixture indixesFieldFix
        FrameFixture frameFix
        def testing = frameTestingFactory.create(title: title, size: new Dimension(200, 100), createComponent: { JFrame frame ->
            indicesField = indicesTextFieldFactory.create()
            return indicesField
        }, setupFrame: { JFrame frame, Component component ->
            frame.setLayout new BorderLayout()
            frame.add(component, BorderLayout.NORTH)
        })()
        testing.withFixture({ FrameFixture fix ->
            frameFix = fix
            indixesFieldFix = frameFix.textBox()
            indixesFieldFix.text().length() > 0 ? indixesFieldFix.deleteText() : false
            indixesFieldFix.enterText "1"
            indixesFieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
            ArrayRange range = indicesField.getValue() as ArrayRange
            log.info "value: {}", range
            assert range.value == [1] as int[]
        }, {
            indixesFieldFix.deleteText()
            indixesFieldFix.enterText ""
            indixesFieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
            ArrayRange range = indicesField.getValue() as ArrayRange
            log.info "value: {}", range
            assert range.value == [] as int[]
        }, {
            indixesFieldFix.text().length() > 0 ? indixesFieldFix.deleteText() : false
            indixesFieldFix.enterText "[1,5]"
            indixesFieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
            ArrayRange range = indicesField.getValue() as ArrayRange
            log.info "value: {}", range
            assert range.value == [1, 2, 3, 4, 5] as int[]
        }, {
            indixesFieldFix.deleteText()
            indixesFieldFix.enterText "{1,3,5}"
            indixesFieldFix.pressAndReleaseKeys KeyEvent.VK_ENTER
            ArrayRange range = indicesField.getValue() as ArrayRange
            log.info "value: {}", range
            assert range.value == [1, 3, 5] as int[]
        })
    }

    //@Test
    void "manually"() {
        def title = "$NAME::manually"
        def indicesField
        def indixesFieldFix
        def testing = frameTestingFactory.create(title: title, size: new Dimension(200, 100), createComponent: { JFrame frame ->
            indicesField = indicesTextFieldFactory.create()
            return indicesField
        }, setupFrame: { JFrame frame, Component component ->
            frame.setLayout new BorderLayout()
            frame.add(component, BorderLayout.NORTH)
        })()
        testing.withFixture({ FrameFixture fix ->
            Thread.sleep 60*1000; assert false : "Thread.sleep"
        })
    }

    static Injector injector

    static IndicesTextFieldFactory indicesTextFieldFactory

    static FrameTestingFactory frameTestingFactory

    static final String NAME = IndicesTextFieldTest.class.simpleName

    @BeforeClass
    static void createFactories() {
        injector = Guice.createInjector(new IndicesTextFieldModule(), new FrameTestingModule())
        indicesTextFieldFactory = injector.getInstance IndicesTextFieldFactory
        frameTestingFactory = injector.getInstance FrameTestingFactory
    }
}
