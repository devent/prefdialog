package com.anrisoftware.prefdialog.filechooser.panel

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.*

import java.awt.Dimension

import org.fest.swing.fixture.FrameFixture
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel

/**
 * Test the different text positions of the file chooser.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FileChooserTextPositionTest extends FileChooserPanelTestUtil {

	@Test
	void "iterate text positions"() {
		def title = "FileChooserTextPositionTest::iterate text positions"
		def frame = createFrame(title)
		panel.getFileSelectionModel().setMultiSelectionEnabled true
		frame.frameSize = new Dimension(480, 360)
		frame.withFixture({ FrameFixture f ->
			f.button OPTIONS_BUTTON_NAME click()
			f.menuItem TEXT_ONLY_MENU_NAME click()
		}, { FrameFixture f ->
			f.button OPTIONS_BUTTON_NAME requireText "Options"
		}, { FrameFixture f ->
			f.button OPTIONS_BUTTON_NAME click()
			f.menuItem ICONS_ONLY_MENU_NAME click()
		}, { FrameFixture f ->
			f.button OPTIONS_BUTTON_NAME click()
			f.menuItem TEXT_ONLY_MENU_NAME click()
		}, { FrameFixture f ->
			f.button OPTIONS_BUTTON_NAME requireText "Options"
		}, { FrameFixture f ->
			f.button OPTIONS_BUTTON_NAME click()
			f.menuItem TEXT_ALONGSIDE_ICONS_MENU_NAME click()
		}, { FrameFixture f ->
			f.button OPTIONS_BUTTON_NAME requireText "Options"
		})
	}

	@BeforeClass
	public static void setupFactory() {
		FileChooserPanelTestUtil.setupFactory()
	}

	@AfterClass
	public static void deleteFiles() {
		FileChooserPanelTestUtil.deleteFiles()
	}
}
