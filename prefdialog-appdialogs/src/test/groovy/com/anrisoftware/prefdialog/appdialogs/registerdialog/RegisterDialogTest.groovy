package com.anrisoftware.prefdialog.appdialogs.registerdialog

import java.awt.Color
import java.awt.Component
import java.awt.Dimension

import javax.swing.BorderFactory
import javax.swing.JDialog
import javax.swing.JPanel

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.globalpom.utils.frametesting.DialogTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialogTest
import com.anrisoftware.prefdialog.appdialogs.dialog.UiPanel
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerModule
import com.anrisoftware.resources.images.api.ImageScalingWorkerFactory
import com.anrisoftware.resources.images.api.Images
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.google.inject.Injector

/**
 * @see RegisterDialog
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
class RegisterDialogTest {

    @Test
    void "show dialog"() {
        def title = "$NAME/show dialog"
        def size = new Dimension(520, 400)
        def content
        def dialog
        def testing = testingFactory.create([title: title, size: size, setupDialog: { JDialog jdialog, Component component ->
                content = new JPanel()
                content.setBorder BorderFactory.createLineBorder(Color.RED)
                dialog = dialogFactory.create()
                dialog.setDialog jdialog
                dialog.createDialog()
            }])()
        testing.withFixture({ Thread.sleep 60000 })
    }

    static final String NAME = AppDialogTest.class.simpleName

    static Injector injector

    static DialogTestingFactory testingFactory

    static RegisterDialogFactory dialogFactory

    static ImagesFactory imagesFactory

    static Images images

    static ImageScalingWorkerFactory imageScalingWorkerFactory

    private static final URL LOGO_A_RESOURCE = UiPanel.class.getResource("/com/anrisoftware/prefdialog/appdialogs/dialogheader/resources/images/en/mdpi/iref_logo_transparent_128.png");

    private static final URL LOGO_B_RESOURCE = UiPanel.class.getResource("/com/anrisoftware/prefdialog/appdialogs/dialogheader/resources/images/en/mdpi/iref_logo_changed_128.png");

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.injector = RegisterDialogModule.SingletonHolder.injector.createChildInjector(
                new OnAwtCheckerModule(),
                new FrameTestingModule())
        this.testingFactory = injector.getInstance DialogTestingFactory
        this.dialogFactory = injector.getInstance RegisterDialogFactory
        this.imagesFactory = injector.createChildInjector(
                new ImagesResourcesModule(),
                new ResourcesImagesMapsModule(),
                new ResourcesSmoothScalingModule()).
                getInstance(ImagesFactory)
        this.images = imagesFactory.create(AppDialogTest.class.getSimpleName())
        this.imageScalingWorkerFactory = injector.createChildInjector(
                new ResourcesSmoothScalingModule()).
                getInstance(ImageScalingWorkerFactory)
    }
}
