/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.lists

import java.awt.BorderLayout

import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.UIManager

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * @see RubberBandingList
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class RubberBandingListTest {

	//@Test
	void "manually"() {
		String title = "$NAME::manually"
		def panel = new JPanel()
		def list = new RubberBandingList([
			"Aaa",
			"Bbb",
			"Ccc",
			"Aaa",
			"Bbb",
			"Ccc",
			"Aaa",
			"Bbb",
			"Ccc",
			"Aaa",
			"Bbb",
			"Ccc",
			"Aaa",
			"Bbb",
			"Ccc"] as Object[])
		def frame = createFrame title, list
		frame.withFixture { Thread.sleep 60 * 1000 }
	}

	//@Test
	void "manually GTK style"() {
		UIManager.setLookAndFeel TestFrameUtil.GTK_LOOK_AND_FEEL
		String title = "$NAME::manually"
		def panel = new JPanel()
		def list = new RubberBandingList([
			"Aaa",
			"Bbb",
			"Ccc",
			"Aaa",
			"Bbb",
			"Ccc",
			"Aaa",
			"Bbb",
			"Ccc",
			"Aaa",
			"Bbb",
			"Ccc",
			"Aaa",
			"Bbb",
			"Ccc"] as Object[])
		def frame = createFrame title, list
		frame.withFixture { Thread.sleep 60 * 1000 }
	}

	TestFrameUtil createFrame(String title, def list) {
		def panel = new JPanel(new BorderLayout())
		panel.add new JScrollPane(list)
		new TestFrameUtil(title, panel)
	}

	static final String NAME = RubberBandingListTest.class.simpleName
}
