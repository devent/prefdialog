package com.anrisoftware.prefdialog.dialog

import static com.anrisoftware.prefdialog.ChildrenPanel.PANEL

import java.awt.Component;

import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode
import javax.swing.tree.DefaultTreeCellRenderer

import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.ChildrenListPanel
import com.anrisoftware.prefdialog.ChildrenListPanelFactory
import com.anrisoftware.prefdialog.ChildrenPanel
import com.anrisoftware.prefdialog.ChildrenPanelFactory
import com.anrisoftware.prefdialog.panel.inputfields.PrefdialogCoreFieldsModule
import com.google.inject.Guice

class ChildrenPanelTest extends TestFrameUtil {

	static injector = Guice.createInjector(
	new PrefdialogModule(), new PrefdialogCoreFieldsModule())

	static ChildrenPanelFactory factory = injector.getInstance ChildrenPanelFactory

	static ChildrenListPanelFactory childrenListPanelFactory = injector.getInstance ChildrenListPanelFactory

	static final String TITLE = "Children Panel Test"

	@Test
	void "test panel name"() {
		def panel = new JPanel()
		def name = "test"

		def childrenListPanel = childrenListPanelFactory.create(new JPanel())
		ChildrenPanel childrenPanel = factory.create panel, childrenListPanel
		beginPanelFrame TITLE, panel, {
			childrenPanel.name = name
			def fixturePanel = fixture.panel("$name-$PANEL")
			fixturePanel.requireVisible()
			fixturePanel.splitPane().requireVisible()
			fixturePanel.panel("$name-${ChildrenListPanel.PANEL}").requireVisible()
		}
	}

	@Test
	void "add child nodes"() {
		def panel = new JPanel()
		def name = "test"

		ChildrenListPanel childrenListPanel = childrenListPanelFactory.create new JPanel()
		childrenListPanel.addChildNode(new DefaultMutableTreeNode("Aaa"))
		childrenListPanel.addChildNode(new DefaultMutableTreeNode("Bbb"))
		childrenListPanel.addChildNode(new DefaultMutableTreeNode("Ccc"))

		ChildrenPanel childrenPanel = factory.create panel, childrenListPanel

		beginPanelFrame TITLE, panel, {
			childrenPanel.name = name
			Thread.sleep 2000
			childrenListPanel.addChildNode(new DefaultMutableTreeNode("Ddd"))
			childrenListPanel.addChildNode(new DefaultMutableTreeNode("Eee"))
			childrenListPanel.addChildNode(new DefaultMutableTreeNode("Fff"))
			Thread.sleep 60000
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
