package com.anrisoftware.prefdialog.filechooser.panel

import javax.swing.JButton
import javax.swing.JMenuItem
import javax.swing.JPanel
import javax.swing.JPopupMenu

import net.miginfocom.swing.MigLayout

import org.fest.swing.fixture.FrameFixture
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.filechooser.panel.core.PopupButton

/**
 * Test the popup button.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class PopupButtonTest {

	static final String POPUP_BUTTON_NAME = "popup-button"

	static final String OTHER_BUTTON_NAME = "other-button"

	static final String ITEM_MENU_NAME = "item-menu"

	//@Test
	void "manually"() {
		def title = "PopupButtonTest::manually"
		def panel = createPanel()
		def frame = new TestFrameUtil(title, panel)
		frame.withFixture { Thread.sleep 60*1000 }
	}

	@Test
	void "show and hide popup"() {
		def title = "PopupButtonTest::show and hide popup"
		def panel = createPanel()
		def frame = new TestFrameUtil(title, panel)
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
		def title = "PopupButtonTest::show popup and select item"
		def panel = createPanel()
		def frame = new TestFrameUtil(title, panel)
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
		def title = "PopupButtonTest::show popup and click other button"
		def panel = createPanel()
		def frame = new TestFrameUtil(title, panel)
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

	private JPanel createPanel() {
		def menu = new JPopupMenu()
		def item = new JMenuItem("Test")
		item.setName ITEM_MENU_NAME
		menu.add item
		def popupButton = new JButton("Popup")
		popupButton.setName POPUP_BUTTON_NAME
		def otherButton = new JButton("Other")
		otherButton.setName OTHER_BUTTON_NAME
		def panel = new JPanel(new MigLayout("", "[grow,fill][][][grow,fill]", "[grow,fill][][grow,fill]"))
		panel.add popupButton, "cell 1 1"
		panel.add otherButton, "cell 2 1"
		PopupButton.decorate popupButton, menu
		return panel
	}
}
