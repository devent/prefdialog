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
	void "show panel"() {
		def title = "FileChooserPanelTest::show panel"
		def frame = createFrame(title)
		frame.frameSize = new Dimension(480, 360)
		frame.withFixture { }
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
				assert panel.getFileSelectionModel().getSelectedFileList() == [
					new File(it, "aaa.txt"),
					new File(it, "bbb.txt"),
					new File(it, "ccc.txt")
				]
			}, { FrameFixture f ->
				f.list(FILES_LIST_NAME).clearSelection()
			}, { FrameFixture f ->
				panel.fileSelectionModel.setSelectedFiles([
					new File(it, "Aaa"),
					new File(it, "Bbb"),
					new File(it, "ccc"),
					new File(it, "aaa.txt"),
					new File(it, "bbb.txt"),
					new File(it, "ccc.txt")
				])
				assert panel.getFileSelectionModel().getSelectedFileList() == [
					new File(it, "aaa.txt"),
					new File(it, "bbb.txt"),
					new File(it, "ccc.txt")
				]
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
				assert panel.getFileSelectionModel().getSelectedFileList() == [
					new File(it, "ccc.txt")
				]
			}, { FrameFixture f ->
				f.list(FILES_LIST_NAME).clearSelection()
			}, { FrameFixture f ->
				panel.fileSelectionModel.setSelectedFiles([
					new File(it, "Aaa"),
					new File(it, "Bbb"),
					new File(it, "ccc"),
					new File(it, "aaa.txt"),
					new File(it, "bbb.txt"),
					new File(it, "ccc.txt")
				])
				assert panel.getFileSelectionModel().getSelectedFileList() == [
					new File(it, "ccc.txt")
				]
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
		new File(parent, "Aaa").mkdir()
		new File(parent, "Bbb").mkdir()
		new File(parent, "ccc").mkdir()
		touch new File(parent, "aaa.txt")
		touch new File(parent, "bbb.txt")
		touch new File(parent, "ccc.txt")
	}

	static Injector injector

	static FileChooserPanelFactory factory

	@BeforeClass
	static void setupFactory() {
		injector = Guice.createInjector(new FileChooserPanelModule())
		factory = injector.getInstance FileChooserPanelFactory
	}
}
