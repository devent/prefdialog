package com.anrisoftware.prefdialog.miscswing.menus

import javax.swing.JButton
import javax.swing.JMenuItem
import javax.swing.JPopupMenu

import org.fest.swing.fixture.FrameFixture
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.globalpom.utils.TestUtils

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
