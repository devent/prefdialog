package com.anrisoftware.prefdialog.filechooser.panel

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.*

import java.awt.Dimension

import org.fest.swing.fixture.FrameFixture
import org.junit.Test

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel

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
}
