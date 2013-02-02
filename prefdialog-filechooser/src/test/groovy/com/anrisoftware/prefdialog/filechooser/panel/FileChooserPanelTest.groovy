package com.anrisoftware.prefdialog.filechooser.panel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.*
import static org.apache.commons.io.FileUtils.*

import java.awt.BorderLayout
import java.awt.Dimension

import javax.swing.BorderFactory
import javax.swing.JFileChooser
import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelFactory
import com.anrisoftware.prefdialog.filechooser.panel.core.FileChooserPanelModule
import com.google.inject.Guice
import com.google.inject.Injector

class FileChooserPanelTest {

	JPanel container

	FileChooserPanel panel

	def dirs

	def files

	@Test
	void "show JFileChooser"() {
		def title = "FileChooserPanelTest::show JFileChooser"
		def chooser = new JFileChooser()
		def frame = new TestFrameUtil(title, new JPanel())
		frame.withFixture {
			chooser.showOpenDialog(frame.frame)
		}
	}

	@Test
	void "manually"() {
		def title = "FileChooserPanelTest::manually"
		withFiles "files", {
			def frame = createFrame(it, title)
			panel.getFileSelectionModel().setMultiSelectionEnabled true
			frame.frameSize = new Dimension(480, 360)
			frame.withFixture({ Thread.sleep 60*1000 })
		}, { createFiles(it) }
	}

	@Test
	void "go in directory, select file"() {
		def title = "FileChooserPanelTest::go in directory, select file"
		withFiles "files", {
			def frame = createFrame(it, title)
			panel.getFileSelectionModel().setMultiSelectionEnabled true
			frame.frameSize = new Dimension(480, 360)
			frame.withFixture({ FrameFixture f ->
				f.list(FILES_LIST_NAME).item(0).doubleClick()
			}, { FrameFixture f ->
				f.list(FILES_LIST_NAME).item(0).doubleClick()
			}, { FrameFixture f ->
				assert panel.getFileSelectionModel().getSelectedFileList() == [files[3]]
			})
		}, { createFiles(it) }
	}

	@Test
	void "set multi selection enabled, only files"() {
		def title = "FileChooserPanelTest::set multi selection enabled, only files"
		withFiles "files", {
			def frame = createFrame(it, title)
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
		}, { createFiles(it) }
	}

	@Test
	void "set multi selection disabled, only files"() {
		def title = "FileChooserPanelTest::set multi selection disabled, only files"
		withFiles "files", {
			def frame = createFrame(it, title)
			panel.getFileSelectionModel().setMultiSelectionEnabled false
			frame.frameSize = new Dimension(480, 360)
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
				assert panel.getFileSelectionModel().getSelectedFileList() == [files[2]]
			})
		}, { createFiles(it) }
	}

	private TestFrameUtil createFrame(File currentDirectory, String title) {
		container = new JPanel(new BorderLayout())
		container.setBorder BorderFactory.createEmptyBorder(2, 2, 2, 2)
		panel = factory.create(container, currentDirectory)
		def frame = new TestFrameUtil(title, container)
		return frame
	}

	private createFiles(File parent) {
		dirs = [
			new File(parent, "Aaa"),
			new File(parent, "Bbb"),
			new File(parent, "ccc")
		]
		dirs.each { it.mkdir() }

		files = [
			new File(parent, "aaa.txt"),
			new File(parent, "bbb.txt"),
			new File(parent, "ccc.txt"),
			new File(dirs[0], "Aaa-aaa.txt")
		]
		files.each { touch it }
	}

	static Injector injector

	static FileChooserPanelFactory factory

	@BeforeClass
	static void setupFactory() {
		injector = Guice.createInjector(new FileChooserPanelModule())
		factory = injector.getInstance FileChooserPanelFactory
	}
}
