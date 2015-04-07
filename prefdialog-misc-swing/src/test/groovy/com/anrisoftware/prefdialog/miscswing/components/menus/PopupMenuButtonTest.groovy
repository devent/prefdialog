/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.components.menus

import javax.swing.JButton
import javax.swing.JMenuItem
import javax.swing.JPopupMenu

import org.fest.swing.fixture.FrameFixture
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.miscswing.components.menu.PopupMenuComponent;

/**
 * Test the menu popup with a JButton.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class PopupMenuButtonTest extends PopupMenuTestUtil {

	//@Test
	void "manually"() {
		def title = "PopupMenuComponentTest::manually"
		def frame = createFrame title
		frame.withFixture { Thread.sleep 60*1000 }
	}

	@Test
	void "show and hide popup"() {
		def title = "PopupMenuComponentTest::show and hide popup"
		def frame = createFrame title
		frame.withFixture({ FrameFixture it ->
			it.button(POPUP_BUTTON_NAME).click()
		}, { FrameFixture it ->
			it.button(POPUP_BUTTON_NAME).click()
		}, { FrameFixture it ->
			it.button(POPUP_BUTTON_NAME).click()
		}, { FrameFixture it ->
			it.button(POPUP_BUTTON_NAME).click()
		})
	}

	@Test
	void "show popup and select item"() {
		def title = "PopupMenuComponentTest::show popup and select item"
		def frame = createFrame title
		frame.withFixture({ FrameFixture it ->
			it.button(POPUP_BUTTON_NAME).click()
		}, { FrameFixture it ->
			it.menuItem(ITEM_MENU_NAME).click()
		}, { FrameFixture it ->
			it.button(POPUP_BUTTON_NAME).click()
		}, { FrameFixture it ->
			it.menuItem(ITEM_MENU_NAME).click()
		})
	}

	@Test
	void "show popup and click other button"() {
		def title = "PopupMenuComponentTest::show popup and click other button"
		def frame = createFrame title
		frame.withFixture({ FrameFixture it ->
			it.button(POPUP_BUTTON_NAME).click()
		}, { FrameFixture it ->
			it.button(OTHER_BUTTON_NAME).click()
		}, { FrameFixture it ->
			it.button(POPUP_BUTTON_NAME).click()
		}, { FrameFixture it ->
			it.button(OTHER_BUTTON_NAME).click()
		})
	}

	@Test
	void "test serializable"() {
		def menu = new JPopupMenu()
		def item = new JMenuItem("Test")
		def popupButton = new JButton("Popup")
		def popup = new PopupMenuComponent(popupButton, menu)
		TestUtils.reserialize popup
	}
}
