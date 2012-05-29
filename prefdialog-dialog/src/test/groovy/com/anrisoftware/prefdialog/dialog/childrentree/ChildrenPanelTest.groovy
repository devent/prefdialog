package com.anrisoftware.prefdialog.dialog.childrentree

import static com.anrisoftware.prefdialog.ChildrenPanel.PANEL_NAME_POSTFIX

import javax.swing.DefaultListModel
import javax.swing.JPanel

import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.ChildrenPanel
import com.anrisoftware.prefdialog.ChildrenPanelFactory
import com.anrisoftware.prefdialog.dialog.childrentree.PrefdialogChildrenTreeModule
import com.google.inject.Guice

class ChildrenPanelTest extends TestFrameUtil {

	static injector = Guice.createInjector(
	new PrefdialogChildrenTreeModule())

	static ChildrenPanelFactory factory = injector.getInstance ChildrenTreePanelFactory

	static final String TITLE = "Children Panel Test"

	@Test
	void "test panel name"() {
		def panel = new JPanel()
		def name = "test"

		ChildrenPanel childrenPanel = factory.create panel
		beginPanelFrame TITLE, panel, {
			childrenPanel.name = name
			def fixturePanel = fixture.panel("$name-$PANEL_NAME_POSTFIX")
			fixturePanel.requireVisible()
			fixturePanel.splitPane().requireVisible()
		}
	}

	@Test
	void "set child nodes"() {
		def panel = new JPanel()
		def name = "test"

		def model = new DefaultListModel()
		model.addElement "Aaa"
		model.addElement "Bbb"
		model.addElement "Ccc"

		ChildrenPanel childrenPanel = factory.create panel
		childrenPanel.childrenModel = model

		beginPanelFrame TITLE, panel, {
			//
			childrenPanel.name = name //
		}, {  model.addElement "Ddd" //
		}, { model.addElement "Eee" //
		}, { model.addElement "Fff" }
	}

	@Test
	void "set different child panels"() {
		def panel = new JPanel()
		def name = "test"
		def childs = ["Aaa", "Bbb", "Ccc"]

		def model = new DefaultListModel()
		childs.each { model.addElement it }

		ChildrenPanel childrenPanel = factory.create panel
		childrenPanel.childrenModel = model

		beginPanelFrame TITLE, panel, {
			childrenPanel.name = name
			assert childrenPanel.selectedChild == childs[0] },
		{ childrenPanel.name = name },
		{ childrenPanel.selectedChild = childs[0] },
		{ childrenPanel.selectedChild = childs[1] },
		{ childrenPanel.selectedChild = childs[2] }
	}
}
