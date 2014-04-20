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
package com.anrisoftware.prefdialog.miscswing.components.menus

import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JMenuItem
import javax.swing.JPanel
import javax.swing.JPopupMenu

import net.miginfocom.swing.MigLayout

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.miscswing.components.menu.PopupMenuComponent;

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
		new TestFrameUtil(title, panel)
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
