package com.anrisoftware.prefdialog.filechooser.panel

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.*

import java.awt.Dimension

import org.fest.swing.fixture.FrameFixture
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel

/**
 * Test the different text positions of the file chooser with image resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FileChooserImageResourcesIconSizesTest extends FileChooserPanelTestUtil {

	@Test
	void "iterate icon sizes"() {
		def title = "FileChooserTextPositionTest::iterate icon sizes"
		def frame = createFrame(title)
		panel.getFileSelectionModel().setMultiSelectionEnabled true
		frame.frameSize = new Dimension(480, 360)
		frame.withFixture({ FrameFixture f ->
			f.button OPTIONS_BUTTON_NAME click()
			f.menuItem HUGE_ICON_MENU_NAME click()
		}, { FrameFixture f ->
			assert f.button(OPTIONS_BUTTON_NAME).target.icon.iconWidth == 48
			assert f.button(OPTIONS_BUTTON_NAME).target.icon.iconHeight == 48
		}, { FrameFixture f ->
			f.button OPTIONS_BUTTON_NAME click()
			f.menuItem LARGE_ICON_MENU_NAME click()
		}, { FrameFixture f ->
			assert f.button(OPTIONS_BUTTON_NAME).target.icon.iconWidth == 32
			assert f.button(OPTIONS_BUTTON_NAME).target.icon.iconHeight == 32
		}, { FrameFixture f ->
			f.button OPTIONS_BUTTON_NAME click()
			f.menuItem MEDIUM_ICON_MENU_NAME click()
		}, { FrameFixture f ->
			assert f.button(OPTIONS_BUTTON_NAME).target.icon.iconWidth == 22
			assert f.button(OPTIONS_BUTTON_NAME).target.icon.iconHeight == 22
		}, { FrameFixture f ->
			f.button OPTIONS_BUTTON_NAME click()
			f.menuItem SMALL_ICON_MENU_NAME click()
		}, { FrameFixture f ->
			assert f.button(OPTIONS_BUTTON_NAME).target.icon.iconWidth == 16
			assert f.button(OPTIONS_BUTTON_NAME).target.icon.iconHeight == 16
		}, { FrameFixture f ->
			f.button OPTIONS_BUTTON_NAME click()
			f.menuItem DEFAULT_ICON_MENU_NAME click()
		}, { FrameFixture f ->
			assert f.button(OPTIONS_BUTTON_NAME).target.icon.iconWidth == 16
			assert f.button(OPTIONS_BUTTON_NAME).target.icon.iconHeight == 16
		})
	}

	@Override
	void createPanel(FileChooserPanel panel) {
		super.createPanel(panel);
		FileChooserImageResourcesTestUtils.createImageResources("ReflectionsKDE4", panel)
	}

	@BeforeClass
	public static void setupFactory() {
		FileChooserImageResourcesTestUtils.setupFactory()
	}

	@AfterClass
	public static void deleteFiles() {
		FileChooserPanelTestUtil.deleteFiles()
	}
}
