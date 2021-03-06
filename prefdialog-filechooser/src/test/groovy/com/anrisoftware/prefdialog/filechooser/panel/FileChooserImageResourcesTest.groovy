package com.anrisoftware.prefdialog.filechooser.panel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.*
import static org.apache.commons.io.FileUtils.*

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel

/**
 * Test the image resources for the file chooser.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FileChooserImageResourcesTest extends FileChooserPanelTestUtil {

	@Test
	void "manually"() {
		def title = "FileChooserImageResourcesTest::manually"
		def frame = createFrame(title)
		panel.getFileSelectionModel().setMultiSelectionEnabled true
		frame.withFixture({ Thread.sleep 60*1000 })
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
