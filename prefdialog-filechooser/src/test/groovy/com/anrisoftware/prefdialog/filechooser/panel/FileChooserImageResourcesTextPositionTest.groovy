package com.anrisoftware.prefdialog.filechooser.panel

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
class FileChooserImageResourcesTextPositionTest extends FileChooserTextPositionTest {

	@Test
	void "iterate text positions"() {
		super."iterate text positions"()
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
