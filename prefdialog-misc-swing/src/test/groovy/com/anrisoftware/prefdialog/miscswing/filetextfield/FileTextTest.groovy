/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.filetextfield

import org.junit.Test

/**
 * Test the file text field.
 *
 * @see FileTextField
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FileTextTest extends FileTextTestUtil {

	@Test
	void "show"() {
		def title = "FileTextTest::show"
		def frame = createFrame title
		frame.withFixture {}
	}

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
