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
package com.anrisoftware.prefdialog.appdialogs.dialogheader

import static javax.swing.SwingUtilities.invokeAndWait

import java.awt.Dimension

import javax.swing.ImageIcon
import javax.swing.JDialog

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.globalpom.utils.frametesting.DialogTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.appdialogs.dialog.UiPanel;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerModule
import com.anrisoftware.resources.images.api.ImageScalingWorkerFactory
import com.anrisoftware.resources.images.api.Images
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.anrisoftware.resources.texts.api.Texts
import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see DialogHeader
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.1
 */
class DialogHeaderTest {

    @Test
    void "show header"() {
        def title = "$NAME/show header"
        def size = new Dimension(450, 164)
        def dialogHeader
        def testing = testingFactory.create([title: title, size: size, createComponent: { JDialog dialog ->
                dialogHeader = factory.create()
                dialogHeader.getComponent()
            }])()
        testing.withFixture({
        })
    }

    @Test
    void "set header text"() {
        def title = "$NAME/set header text"
        def size = new Dimension(450, 164)
        def dialogHeader
        def testing = testingFactory.create([title: title, size: size, createComponent: { JDialog dialog ->
                dialogHeader = factory.create()
                dialogHeader.setInfoText "<html>Some A<br/>Text</html>"
                dialogHeader.setLinkText "<html><a href=\"http://muellerpublic.de\">http://muellerpublic.de</a></html>"
                dialogHeader.getComponent()
            }])()
        testing.withFixture({
            invokeAndWait {
                dialogHeader.setInfoText "<html>Some B<br/>Text</html>"
                dialogHeader.setLinkText "<html><a href=\"http://anrisoftware.com\">http://anrisoftware.com</a></html>"
            }
        })
    }

    @Test
    void "set header text resource"() {
        def title = "$NAME/set header text resource"
        def size = new Dimension(450, 164)
        def dialogHeader
        def testing = testingFactory.create([title: title, size: size, createComponent: { JDialog dialog ->
                dialogHeader = factory.create()
                dialogHeader.setTexts texts
                dialogHeader.setInfoTextName "header_info_text_a"
                dialogHeader.setLinkTextName "header_link_text_a"
                dialogHeader.getComponent()
            }])()
        testing.withFixture({
            invokeAndWait {
                dialogHeader.setInfoTextName "header_info_text_b"
                dialogHeader.setLinkTextName "header_link_text_b"
            }
        })
    }

    @Test
    void "set logo"() {
        def title = "$NAME/set logo"
        def size = new Dimension(450, 164)
        def dialogHeader
        def testing = testingFactory.create([title: title, size: size, createComponent: { JDialog dialog ->
                dialogHeader = factory.create()
                dialogHeader.setImageScalingWorkerFactory(imageScalingWorkerFactory)
                dialogHeader.setLogoImage(new ImageIcon(LOGO_B_RESOURCE).getImage())
                dialogHeader.getComponent()
            }])()
        testing.withFixture({
            invokeAndWait {
                dialogHeader.setLogoImage(new ImageIcon(LOGO_A_RESOURCE).getImage()) //.
            }
        })
    }

    @Test
    void "resize logo"() {
        def title = "$NAME/resize logo"
        def size = new Dimension(450, 164)
        def dialogHeader
        def testing = testingFactory.create([title: title, size: size, createComponent: { JDialog dialog ->
                dialogHeader = factory.create()
                dialogHeader.setImageScalingWorkerFactory(imageScalingWorkerFactory)
                dialogHeader.setLogoImage(new ImageIcon(LOGO_B_RESOURCE).getImage())
                dialogHeader.setLogoSize(new Dimension(94, 94))
                dialogHeader.getComponent()
            }])()
        testing.withFixture({
        })
    }

    @Test
    void "set logo resource"() {
        def title = "$NAME/set logo resource"
        def size = new Dimension(450, 164)
        def dialogHeader
        def testing = testingFactory.create([title: title, size: size, createComponent: { JDialog dialog ->
                dialogHeader = factory.create()
                dialogHeader.setLogoSize(new Dimension(128, 128))
                dialogHeader.setImages(images)
                dialogHeader.setLogoImageName("header_logo_b")
                dialogHeader.getComponent()
            }])()
        testing.withFixture({
            invokeAndWait {
                dialogHeader.setLogoImageName("header_logo_a") //.
            }
        })
    }

    static final String NAME = DialogHeaderTest.class.simpleName

    static Injector injector

    static DialogTestingFactory testingFactory

    static DialogHeaderFactory factory

    static TextsFactory textsFactory

    static Texts texts

    static ImagesFactory imagesFactory

    static Images images

    static ImageScalingWorkerFactory imageScalingWorkerFactory

    private static final URL LOGO_A_RESOURCE = UiPanel.class.getResource("/com/anrisoftware/prefdialog/appdialogs/dialogheader/resources/images/en/mdpi/iref_logo_transparent_128.png");

    private static final URL LOGO_B_RESOURCE = UiPanel.class.getResource("/com/anrisoftware/prefdialog/appdialogs/dialogheader/resources/images/en/mdpi/iref_logo_changed_128.png");

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.injector = Guice.createInjector(
                new DialogHeaderModule(),
                new OnAwtCheckerModule(),
                new FrameTestingModule())
        this.testingFactory = injector.getInstance DialogTestingFactory
        this.factory = injector.getInstance DialogHeaderFactory
        this.textsFactory = injector.createChildInjector(
                new TextsResourcesDefaultModule()).
                getInstance(TextsFactory)
        this.texts = textsFactory.create(DialogHeaderTest.class.getSimpleName())
        this.imagesFactory = injector.createChildInjector(
                new ImagesResourcesModule(),
                new ResourcesImagesMapsModule(),
                new ResourcesSmoothScalingModule()).
                getInstance(ImagesFactory)
        this.images = imagesFactory.create(DialogHeaderTest.class.getSimpleName())
        this.imageScalingWorkerFactory = injector.createChildInjector(
                new ResourcesSmoothScalingModule()).
                getInstance(ImageScalingWorkerFactory)
    }

    private static createInjector() {
        Guice.createInjector new DialogHeaderTest()
    }
}
