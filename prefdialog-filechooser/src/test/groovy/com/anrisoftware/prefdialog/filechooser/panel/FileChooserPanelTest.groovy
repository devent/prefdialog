package com.anrisoftware.prefdialog.filechooser.panel


import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.*

import java.awt.Dimension

import javax.swing.JFileChooser
import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel

class FileChooserPanelTest extends FileChooserPanelTestUtil {

	//@Test
	void "show JFileChooser"() {
		def title = "FileChooserPanelTest::show JFileChooser"
		def chooser = new JFileChooser()
		def frame = new TestFrameUtil(title, new JPanel())
		frame.withFixture {
			chooser.showOpenDialog(frame.frame)
		}
	}

	//@Test
	void "manually"() {
		def title = "FileChooserPanelTest::manually"
		def frame = createFrame(title)
		panel.getFileSelectionModel().setMultiSelectionEnabled true
		frame.frameSize = new Dimension(480, 360)
		frame.withFixture({ Thread.sleep 60*1000 })
	}

	@Test
	void "go in directory, select file"() {
		def title = "FileChooserPanelTest::go in directory, select file"
		def frame = createFrame(title)
		panel.getFileSelectionModel().setMultiSelectionEnabled true
		frame.frameSize = new Dimension(480, 360)
		frame.withFixture({ FrameFixture f ->
			f.list(FILES_LIST_NAME).item(0).doubleClick()
		})
	}

	@Test
	void "set multi selection enabled, only files"() {
		def title = "FileChooserPanelTest::set multi selection enabled, only files"
		def frame = createFrame(title)
		panel.getFileSelectionModel().setMultiSelectionEnabled true
		frame.frameSize = new Dimension(480, 360)
		frame.withFixture({ FrameFixture f ->
			f.list(FILES_LIST_NAME).selectItems(0, 1, 2, 3, 4, 5)
			assert panel.getFileSelectionModel().getSelectedFileList() == [files[0], files[1], files[2]]
		}, { FrameFixture f ->
			f.list(FILES_LIST_NAME).clearSelection()
		}, { FrameFixture f ->
			panel.fileSelectionModel.setSelectedFiles([
				dirs[0],
				dirs[1],
				dirs[2],
				files[0],
				files[1],
				files[2]
			])
			assert panel.getFileSelectionModel().getSelectedFileList() == [files[0], files[1], files[2]]
		})
	}

	@Test
	void "set multi selection disabled, only files"() {
		def title = "FileChooserPanelTest::set multi selection disabled, only files"
		def frame = createFrame(title)
		panel.getFileSelectionModel().setMultiSelectionEnabled false
		frame.withFixture({ FrameFixture f ->
			f.list(FILES_LIST_NAME).selectItems(0, 1, 2, 3, 4, 5)
			assert panel.getFileSelectionModel().getSelectedFileList() == [files[2]]
		}, { FrameFixture f ->
			f.list(FILES_LIST_NAME).clearSelection()
		}, { FrameFixture f ->
			panel.fileSelectionModel.setSelectedFiles([
				dirs[0],
				dirs[1],
				dirs[2],
				files[0],
				files[1],
				files[2]
			])
			assert panel.fileSelectionModel.getSelectedFileList() == [files[2]]
		})
	}

	@Test
	void "selected files set"() {
		def title = "FileChooserPanelTest::selected files set"
		def frame = createFrame(title)
		panel.getFileSelectionModel().setMultiSelectionEnabled true
		panel.getFileChooserPanelProperties().addSelectedFilesToQueue(new HashSet([files[0], files[1]]))
		panel.getFileChooserPanelProperties().addSelectedFilesToQueue(new HashSet([files[2]]))
		frame.withFixture({ FrameFixture f ->
			f.comboBox(NAME_FIELD_NAME).selectItem(0)
		}, { FrameFixture f ->
			f.comboBox(NAME_FIELD_NAME).selectItem(1)
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
