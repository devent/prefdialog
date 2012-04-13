package com.anrisoftware.prefdialog.panel

import static com.anrisoftware.prefdialog.panel.inputfields.button.ButtonGroupFieldHandler.BUTTON
import static com.google.inject.Guice.createInjector

import java.awt.Dimension;

import javax.swing.JPanel

import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.PreferenceDialogHandlerFactory
import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.dialog.PrefdialogModule
import com.anrisoftware.prefdialog.panel.api.Appearance
import com.anrisoftware.prefdialog.panel.inputfields.PrefdialogCoreFieldsModule
import com.google.inject.Inject
import com.google.inject.Injector

class AppearanceDialogTest extends TestFrameUtil {

	static Injector injector = createInjector(
	new PrefdialogAppearanceModule(),
	new PrefdialogModule(),
	new PrefdialogCoreFieldsModule())

	static factory = injector.getInstance PreferenceDialogHandlerFactory

	static final String TITLE = "Appearance Dialog Test"

	static class Panel {

		@Inject
		@Child(title="Appearance")
		Appearance appearance
	}

	@Before
	void beforeTest() {
		frameSize = new Dimension(480, 640)
	}

	@Test
	void "manually"() {
		def panel = injector.getInstance(Panel)
		beginPanelFrame TITLE, new JPanel(), {
			def dialogHandler = factory.create(frame, panel).createDialog()
			def dialog = dialogHandler.getAWTComponent()
			dialog.modal = false
			dialog.size = frameSize
			dialog.visible = true
			Thread.sleep 60000
		}
	}
}
