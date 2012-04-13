package com.anrisoftware.prefdialog.panel

import static com.anrisoftware.swingcomponents.fontchooser.api.FontChooserHandler.*
import static com.anrisoftware.prefdialog.panel.inputfields.button.ButtonGroupFieldHandler.BUTTON
import static com.google.inject.Guice.createInjector
import static com.anrisoftware.prefdialog.panel.inputfields.fontchooser.FontChooserFieldHandler.OPEN_FONT_BUTTON;

import java.awt.Dimension;
import java.awt.Font

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
	void "select font and apply dialog"() {
		def panel = injector.getInstance(Panel)
		beginPanelFrame TITLE, new JPanel(), {
			def dialogHandler = factory.create(frame, panel).createDialog()
			def dialog = dialogHandler.getAWTComponent()
			dialog.modal = false
			dialog.size = frameSize
			dialog.visible = true

			fixture.checkBox()
			fixture.toggleButton("$OPEN_FONT_BUTTON-font").click()
			fixture.textBox(NAME_FIELD_NAME).deleteText()
			fixture.textBox(NAME_FIELD_NAME).enterText("Serif")
			fixture.textBox(STYLE_FIELD_NAME).deleteText()
			fixture.textBox(STYLE_FIELD_NAME).enterText("Bold")
			fixture.textBox(SIZE_FIELD_NAME).deleteText()
			fixture.textBox(SIZE_FIELD_NAME).enterText("12")

			fixture.button("apply").click()
		}
		assert panel.appearance.lookAndFeelFont.font == new Font("Serif", Font.BOLD, 12)
	}
}
