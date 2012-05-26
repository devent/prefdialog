package com.anrisoftware.prefdialog.dialog

import static com.anrisoftware.prefdialog.ChildrenListPanel.PANEL_NAME_POSTFIX

import java.awt.Component;

import javax.swing.JPanel
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeCellRenderer

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
	void "test set name to the panel"() {
		def panel = new JPanel()
		def name = "test"

		ChildrenListPanel childrenPanel = factory.create panel
		beginPanelFrame TITLE, panel, {
			childrenPanel.name = name
			def fixturePanel = fixture.panel("$name-$PANEL_NAME_POSTFIX")
			fixturePanel.requireVisible()
			fixturePanel.tree().requireVisible()
			fixturePanel.scrollPane().requireVisible()
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
			Thread.sleep 2000
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
			assert childrenPanel.selectedChild == null
			Thread.sleep 2000
			childrenPanel.setSelectedChild childs[0]
			assert childrenPanel.selectedChild == childs[0]
			Thread.sleep 500
			childrenPanel.setSelectedChild childs[1]
			assert childrenPanel.selectedChild == childs[1]
			Thread.sleep 500
			childrenPanel.setSelectedChild childs[2]
			assert childrenPanel.selectedChild == childs[2]
			Thread.sleep 1000
		}
	}

	@Test
	void "custom child nodes renderer"() {
		def panel = new JPanel()
		def name = "test"
		def childs = ["Aaa", "Bbb", "Ccc"]
		def renderer = new DefaultTreeCellRenderer() {
					@Override
					Component getTreeCellRendererComponent(JTree tree,
					Object value, boolean sel, boolean expanded, boolean leaf,
					int row, boolean hasFocus) {
						def component = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
						if (value != null) {
							text = "Custom Renderer $value"
						}
						component
					}
				}

		ChildrenListPanel childrenPanel = factory.create panel
		childrenPanel.childRenderer = renderer
		childrenPanel.addChildNode(new DefaultMutableTreeNode(childs[0]))
		childrenPanel.addChildNode(new DefaultMutableTreeNode(childs[1]))
		childrenPanel.addChildNode(new DefaultMutableTreeNode(childs[2]))

		beginPanelFrame TITLE, panel, {
			childrenPanel.name = name
			assert childrenPanel.childRenderer == renderer
			Thread.sleep 2000
		}
	}
}
