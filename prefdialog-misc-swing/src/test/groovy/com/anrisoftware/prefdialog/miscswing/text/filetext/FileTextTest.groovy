package com.anrisoftware.prefdialog.miscswing.text.filetext

import org.junit.Test

/**
 * Test the file text field.
 * 
 * @see FileTextField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FileTextTest extends FileTextTestUtil {

	@Test
	void "manually"() {
		def title = "FileTextTest::manually"
		def frame = createFrame title
		frame.withFixture { Thread.sleep 60*1000 }
	}
}
