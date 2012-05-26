package com.anrisoftware.prefdialog.dialog

import static com.anrisoftware.prefdialog.PreferenceDialog.DIALOG_NAME_POSTFIX

import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.tree.DefaultMutableTreeNode

import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.ChildrenListPanel
import com.anrisoftware.prefdialog.ChildrenListPanelFactory
import com.anrisoftware.prefdialog.ChildrenPanel
import com.anrisoftware.prefdialog.ChildrenPanelFactory
import com.anrisoftware.prefdialog.PreferenceDialogFactory;
import com.anrisoftware.prefdialog.panel.inputfields.PrefdialogCoreFieldsModule
import com.google.inject.Guice

class PreferencesDialogTest extends TestFrameUtil {

	static injector = Guice.createInjector(
	new PrefdialogModule(), new PrefdialogCoreFieldsModule())

	static PreferenceDialogFactory factory = injector.getInstance PreferenceDialogFactory

	static ChildrenPanelFactory childrenPanelFactory = injector.getInstance ChildrenPanelFactory

	static ChildrenListPanelFactory childrenListPanelFactory = injector.getInstance ChildrenListPanelFactory

	static final String TITLE = "Children Panel Test"

	JFrame frame

	JDialog dialog

	@Before
	void beforeTest() {
		frame = new JFrame()
		dialog = new JDialog(frame)
		dialog.size = frameSize
		dialog.modal = false
		dialog.pack()
	}

	@Override
	public Object createFrame(Object title, Object component) {
		frame.title = title
		frame
	}

	@Test
	void "test dialog name"() {
		def name = "test"

		def childrenListPanel = childrenListPanelFactory.create new JPanel()
		def childrenPanel = childrenPanelFactory.create new JPanel(), childrenListPanel
		def preferencesDialog = factory.create dialog, childrenPanel

		beginPanelFrame TITLE, null, {
			frame.visible = true
			preferencesDialog.name = name
			dialog.visible = true
			fixture.dialog("$name-$DIALOG_NAME_POSTFIX").requireVisible()

			Thread.sleep 5000
		}
	}

	@Test
	void "add child nodes"() {
		def name = "test"

		def childrenListPanel = childrenListPanelFactory.create new JPanel()
		childrenListPanel.addChildNode(new DefaultMutableTreeNode("Aaa"))
		childrenListPanel.addChildNode(new DefaultMutableTreeNode("Bbb"))
		childrenListPanel.addChildNode(new DefaultMutableTreeNode("Ccc"))

		def childrenPanel = childrenPanelFactory.create new JPanel(), childrenListPanel
		def preferencesDialog = factory.create dialog, childrenPanel

		beginPanelFrame TITLE, null, {
			frame.visible = false
			preferencesDialog.name = name
			dialog.visible = true
			dialog.name = name
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

		ChildrenPanel childrenPanel = childrenPanelFactory.create panel, childrenListPanel

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
