package com.anrisoftware.prefdialog.dialog

import static com.anrisoftware.prefdialog.PreferenceDialog.DIALOG_NAME_POSTFIX

import javax.swing.DefaultListModel
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JPanel

import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.ChildrenPanelFactory
import com.anrisoftware.prefdialog.PreferenceDialogFactory
import com.anrisoftware.prefdialog.panel.inputfields.PrefdialogCoreFieldsModule
import com.google.inject.Guice

class PreferencesDialogTest extends TestFrameUtil {

	static injector = Guice.createInjector(
	new PrefdialogModule(), new PrefdialogCoreFieldsModule())

	static PreferenceDialogFactory factory = injector.getInstance PreferenceDialogFactory

	static ChildrenPanelFactory childrenPanelFactory = injector.getInstance ChildrenPanelFactory

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

		def childrenPanel = childrenPanelFactory.create new JPanel()
		def preferencesDialog = factory.create dialog, childrenPanel

		beginPanelFrame TITLE, null, {
			frame.visible = true
			preferencesDialog.name = name
			dialog.visible = true
			fixture.dialog("$name-$DIALOG_NAME_POSTFIX").requireVisible()
		}
	}

	@Test
	void "set different child panels"() {
		def name = "test"
		def childs = ["Aaa", "Bbb", "Ccc"]
		def model = new DefaultListModel()
		childs.each { model.addElement it }

		def childrenPanel = childrenPanelFactory.create new JPanel()
		childrenPanel.childrenModel = model

		def preferencesDialog = factory.create dialog, childrenPanel

		beginPanelFrame TITLE, null, {
			preferencesDialog.name = name
			dialog.visible = true
			fixture.dialog("$name-$DIALOG_NAME_POSTFIX").requireVisible()
			assert childrenPanel.selectedChild == null
		}, { preferencesDialog.name = name //
		}, {
			childrenPanel.selectedChild = childs[0] //
		}, {
			childrenPanel.selectedChild = childs[1] //
		}, { childrenPanel.selectedChild = childs[2] }
	}
}
