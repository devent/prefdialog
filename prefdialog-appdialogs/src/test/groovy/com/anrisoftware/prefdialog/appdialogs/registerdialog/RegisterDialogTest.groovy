package com.anrisoftware.prefdialog.appdialogs.registerdialog

import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.util.regex.Pattern

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
import com.google.inject.Injector

/**
 * @see RegisterDialog
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
class RegisterDialogTest {

    @Test
    void "approve dialog"() {
        def title = "$NAME/approve dialog"
        def size = new Dimension(520, 420)
        def content
        def dialog
        def testing = testingFactory.create([title: title, size: size, setupDialog: { JDialog jdialog, Component component ->
                content = new JPanel()
                content.setBorder BorderFactory.createLineBorder(Color.RED)
                dialog = dialogFactory.create()
                dialog.setDialog jdialog
                dialog.setDaysLeft 30
                dialog.setEmail "admin@anrisoftware.com"
                dialog.setKey "XXXX-XXXX-XXXX-XXXX"
                dialog.setLogoImageName "iref_logo"
                dialog.setLogoSize(new Dimension(128, 128))
                dialog.createDialog()
            }])()
        def fields = [:]
        testing.withFixture({ DialogFixture fix ->
            fields.nameField = fix.textBox(RegisterDialog.NAME_FIELD_NAME)
            fields.codeField = fix.textBox(RegisterDialog.CODE_FIELD_NAME)
            fields.approveButton = fix.button(SimpleDialog.APPROVE_BUTTON_NAME)
            fields.errorText = fix.label(SimpleDialog.ERROR_TEXT_NAME)
        }, {
            fields.approveButton.click()
            fields.errorText.requireText Pattern.compile(/.*name.*/)
        }, {
            fields.nameField.enterText "a"
            fields.approveButton.click()
            fields.errorText.requireText Pattern.compile(/.*code.*/)
        }, {
            fields.codeField.enterText "1"
            fields.approveButton.click()
        })
        assert dialog.getStatus() == Status.APPROVED
        assert dialog.getName() == "a"
        assert dialog.getCode() == "1"
    }

    static final String NAME = AppDialogTest.class.simpleName

    static Injector injector

    static DialogTestingFactory testingFactory

    static RegisterDialogFactory dialogFactory

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.injector = RegisterDialogModule.SingletonHolder.injector.createChildInjector(
                new OnAwtCheckerModule(),
                new FrameTestingModule())
        this.testingFactory = injector.getInstance DialogTestingFactory
        this.dialogFactory = injector.getInstance RegisterDialogFactory
    }
}
