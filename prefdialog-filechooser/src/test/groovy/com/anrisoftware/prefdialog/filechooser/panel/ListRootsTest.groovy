package com.anrisoftware.prefdialog.filechooser.panel

import org.junit.Test

/**
 * Lists the root directories of the system.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ListRootsTest {

	@Test
	void "list roots"() {
		File.listRoots().eachWithIndex { root, i ->
			println "Root[$i]: $root"
		}
	}
}
