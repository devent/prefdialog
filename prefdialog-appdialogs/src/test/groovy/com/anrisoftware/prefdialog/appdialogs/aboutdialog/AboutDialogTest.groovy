/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-appdialogs.
 *
 * prefdialog-appdialogs is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-appdialogs is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-appdialogs. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.appdialogs.aboutdialog

import static com.anrisoftware.prefdialog.appdialogs.aboutdialog.AboutDialog.*
import static com.anrisoftware.prefdialog.appdialogs.aboutdialog.AboutDialogResource.*

import java.awt.Color
import java.awt.Component
import java.awt.Dimension

import javax.swing.BorderFactory
import javax.swing.JDialog
import javax.swing.JPanel

import org.fest.swing.fixture.DialogFixture
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.globalpom.utils.frametesting.DialogTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialogTest
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerModule
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status
import com.anrisoftware.resources.texts.api.TextsFactory
import com.google.inject.Injector

/**
 * @see AboutDialog
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
class AboutDialogTest {

    @Test
    void "approve dialog"() {
        def title = "$NAME/approve dialog"
        def size = new Dimension(520, 420)
        def locale = Locale.US
        def licenseArgs = [
            version: '1.0',
            copyright: 'Copyright Erwin Müller.<br/>Visit <a href="https://anrisoftware.com">https://anrisoftware.com</a>',
            license: gpl3_license.getText(loadTexts(injector.getInstance(TextsFactory)), locale).getText(),
        ]
        def freepikArgs = [
            license: freepik_cc_by_3_0_license.getText(loadTexts(injector.getInstance(TextsFactory)), locale).getText(),
        ]
        def content
        def dialog
        def testing = testingFactory.create([title: title, size: size, setupDialog: { JDialog jdialog, Component component ->
                content = new JPanel()
                content.setBorder BorderFactory.createLineBorder(Color.RED)
                dialog = dialogFactory.create()
                dialog.setDialog jdialog
                dialog.addAboutText license_title.name(), aboutTextRenderer.getText(locale, licenseArgs)
                dialog.addAboutText "Freepik", aboutTextRenderer.getText(locale, freepikArgs)
                dialog.createDialog()
            }])()
        def fields = [:]
        testing.withFixture({ DialogFixture fix ->
            fields.cancelButton = fix.button(SimpleDialog.CANCEL_BUTTON_NAME)
            fields.aboutPane = fix.tabbedPane ABOUT_PANE_NAME
        }, {
            fields.aboutPane.selectTab 1 //
        }, {
            fields.aboutPane.selectTab 0 //
        }, {
            //Thread.sleep 30000
            fields.cancelButton.click()
        })
        assert dialog.getStatus() == Status.CANCELED
    }

    static final String NAME = AppDialogTest.class.simpleName

    static Injector injector

    static DialogTestingFactory testingFactory

    static AboutDialogFactory dialogFactory

    static AboutTextRenderer aboutTextRenderer

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.injector = AboutDialogModule.SingletonHolder.injector.createChildInjector(
                new OnAwtCheckerModule(),
                new FrameTestingModule())
        this.testingFactory = injector.getInstance DialogTestingFactory
        this.dialogFactory = injector.getInstance AboutDialogFactory
        this.aboutTextRenderer = injector.getInstance AboutTextRenderer
    }
}
