package com.anrisoftware.prefdialog.miscswing.filetextfield

/**
 * Test the file text field.
 * 
 * @see FileTextField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FileTextTest extends FileTextTestUtil {

	//@Test
	void "manually"() {
		def title = "FileTextTest::manually"
		def frame = createFrame title
		frame.withFixture {
			Thread.sleep 60*1000
			assert false : "manually test"
		}
	}
}
