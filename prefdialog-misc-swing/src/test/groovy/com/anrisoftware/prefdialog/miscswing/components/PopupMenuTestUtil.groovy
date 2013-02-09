package com.anrisoftware.prefdialog.miscswing.components

import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JMenuItem
import javax.swing.JPanel
import javax.swing.JPopupMenu

import net.miginfocom.swing.MigLayout

import com.anrisoftware.globalpom.utils.TestFrameUtil

class PopupMenuTestUtil {

	public static final String POPUP_BUTTON_NAME = "popup-button"

	public static final String OTHER_BUTTON_NAME = "other-button"

	public static final String ITEM_MENU_NAME = "item-menu"

	def component

	def menu

	def popup

	def panel

	TestFrameUtil createFrame(def title) {
		component = createComponent()
		menu = createMenu()
		popup = createPopup(component, menu)
		panel = createPanel(component)
		def frame = new TestFrameUtil(title, panel)
		frame.withFixture { Thread.sleep 60*1000 }
	}

	JPanel createPanel(def component) {
		def otherButton = new JButton("Other")
		otherButton.setName OTHER_BUTTON_NAME
		def panel = new JPanel(new MigLayout("", "[grow,fill][][][grow,fill]", "[grow,fill][][grow,fill]"))
		panel.add component, "cell 1 1"
		panel.add otherButton, "cell 2 1"
		panel
	}

	PopupMenuComponent createPopup(def popupButton, def menu) {
		new PopupMenuComponent(popupButton, menu)
	}

	JPopupMenu createMenu() {
		def menu = new JPopupMenu()
		def item = new JMenuItem("Test")
		item.setName ITEM_MENU_NAME
		menu.add item
		menu
	}

	JComponent createComponent() {
		def popupButton = new JButton("Popup")
		popupButton.setName POPUP_BUTTON_NAME
		popupButton
	}
}
