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

import java.awt.Dimension

import javax.swing.JDialog
import javax.swing.SwingUtilities

import org.fest.swing.fixture.FrameFixture
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialogTest
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerModule
import com.anrisoftware.resources.texts.api.TextsFactory
import com.google.inject.Injector

/**
 * @see AboutDialogCreateWorker
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.3
 */
class AboutDialogCreateWorkerTest {

    @Test
    void "show dialog"() {
        def title = "$NAME/show dialog"
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
        JDialog dialog
        AboutDialogCreateWorker worker
        def testing = testingFactory.create([title: title, size: new Dimension(20, 20)])()
        def fields = [:]
        testing.withFixture({ FrameFixture fix ->
            worker = injector.getInstance AboutDialogCreateWorker
            worker.setTexts aboutTextRenderer.getTexts()
            worker.setDialogTitleResourceName "about_dialog_title"
            worker.setLocale locale
            worker.addAboutText license_title.name(), aboutTextRenderer.getText(locale, licenseArgs)
            worker.addAboutText "Freepik", aboutTextRenderer.getText(locale, freepikArgs)
        }, {
            dialog = worker.getDialog()
            SwingUtilities.invokeLater { dialog.setVisible true }
        })
    }

    static final String NAME = AppDialogTest.class.simpleName

    static Injector injector

    static FrameTestingFactory testingFactory

    static AboutDialogFactory dialogFactory

    static AboutTextRenderer aboutTextRenderer

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.injector = AboutDialogModule.SingletonHolder.injector.createChildInjector(
                new OnAwtCheckerModule(),
                new FrameTestingModule())
        this.testingFactory = injector.getInstance FrameTestingFactory
        this.dialogFactory = injector.getInstance AboutDialogFactory
        this.aboutTextRenderer = injector.getInstance AboutTextRenderer
    }
}
