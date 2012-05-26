package com.anrisoftware.prefdialog.dialog.children

import static com.anrisoftware.prefdialog.ChildrenPanel.PANEL_NAME_POSTFIX

import javax.swing.DefaultListModel
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.tree.DefaultMutableTreeNode

import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.ChildrenListPanel
import com.anrisoftware.prefdialog.ChildrenPanel
import com.anrisoftware.prefdialog.ChildrenPanelFactory
import com.google.inject.Guice

class ChildrenPanelTest extends TestFrameUtil {

	static injector = Guice.createInjector(
	new PrefdialogChildrenModule())

	static ChildrenPanelFactory factory = injector.getInstance ChildrenPanelFactory

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
			sequencedActions([{ }])
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
			sequencedActions([
				{ childrenPanel.name = name },
				{ model.addElement "Ddd" },
				{ model.addElement "Eee" },
				{ model.addElement "Fff" }
			])
		}
	}

	@Test
	void "set different child panels"() {
		def panel = new JPanel()
		def name = "test"
		def childs = ["Aaa", "Bbb", "Ccc"]
		def panels = [
			new JPanel(),
			new JPanel(),
			new JPanel()
		]
		panels[0].add new JLabel("Aaa")
		panels[1].add new JLabel("Bbb")
		panels[2].add new JLabel("Ccc")

		ChildrenListPanel childrenListPanel = childrenListPanelFactory.create new JPanel()
		childrenListPanel.addChildNode(new DefaultMutableTreeNode(childs[0]))
		childrenListPanel.addChildNode(new DefaultMutableTreeNode(childs[1]))
		childrenListPanel.addChildNode(new DefaultMutableTreeNode(childs[2]))

		ChildrenPanel childrenPanel = factory.create panel, childrenListPanel

		beginPanelFrame TITLE, panel, {
			childrenPanel.name = name
			assert childrenListPanel.selectedChild == null
			Thread.sleep 2000
			childrenListPanel.setSelectedChild childs[0]
			childrenPanel.childPanel = panels[0]
			Thread.sleep 500
			childrenListPanel.setSelectedChild childs[1]
			childrenPanel.childPanel = panels[1]
			Thread.sleep 500
			childrenListPanel.setSelectedChild childs[2]
			childrenPanel.childPanel = panels[2]
			Thread.sleep 1000
		}
	}
}
