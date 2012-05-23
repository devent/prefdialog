package com.anrisoftware.prefdialog.dialog

import static com.anrisoftware.prefdialog.ChildrenListPanel.*

import javax.swing.JPanel
import javax.swing.tree.DefaultMutableTreeNode

import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.ChildrenListPanel
import com.anrisoftware.prefdialog.ChildrenListPanelFactory
import com.anrisoftware.prefdialog.panel.inputfields.PrefdialogCoreFieldsModule
import com.google.inject.Guice

class ChildrenListPanelTest extends TestFrameUtil {

	static injector = Guice.createInjector(
	new PrefdialogModule(), new PrefdialogCoreFieldsModule())

	static ChildrenListPanelFactory factory = injector.getInstance ChildrenListPanelFactory

	static final String TITLE = "Children List Panel Test"

	@Test
	void "test names"() {
		def panel = new JPanel()
		def name = "test"

		ChildrenListPanel childrenPanel = factory.create panel
		beginPanelFrame TITLE, panel, {
			childrenPanel.name = name
			fixture.panel("$name-$PANEL").requireVisible()
			fixture.tree("$name-$CHILDREN_TREE").requireVisible()
			fixture.scrollPane("$name-$CHILDREN_TREE_SCROLL_PANEL").requireVisible()
		}
	}

	@Test
	void "add child nodes"() {
		def panel = new JPanel()
		def name = "test"

		ChildrenListPanel childrenPanel = factory.create panel
		childrenPanel.addChildNode(new DefaultMutableTreeNode("Aaa"))
		childrenPanel.addChildNode(new DefaultMutableTreeNode("Bbb"))
		childrenPanel.addChildNode(new DefaultMutableTreeNode("Ccc"))

		beginPanelFrame TITLE, panel, {
			childrenPanel.name = name
			Thread.sleep 2000
			childrenPanel.addChildNode(new DefaultMutableTreeNode("Ddd"))
			childrenPanel.addChildNode(new DefaultMutableTreeNode("Eee"))
			childrenPanel.addChildNode(new DefaultMutableTreeNode("Fff"))
			Thread.sleep 20000
		}
	}

	@Test
	void "select child nodes"() {
		def panel = new JPanel()
		def name = "test"
		def childs = ["Aaa", "Bbb", "Ccc"]

		ChildrenListPanel childrenPanel = factory.create panel
		childrenPanel.addChildNode(new DefaultMutableTreeNode(childs[0]))
		childrenPanel.addChildNode(new DefaultMutableTreeNode(childs[1]))
		childrenPanel.addChildNode(new DefaultMutableTreeNode(childs[2]))

		beginPanelFrame TITLE, panel, {
			childrenPanel.name = name
			Thread.sleep 2000
			childrenPanel.setSelectedChild childs[0]
			Thread.sleep 500
			childrenPanel.setSelectedChild childs[1]
			Thread.sleep 500
			childrenPanel.setSelectedChild childs[2]
			Thread.sleep 20000
		}
	}
}
