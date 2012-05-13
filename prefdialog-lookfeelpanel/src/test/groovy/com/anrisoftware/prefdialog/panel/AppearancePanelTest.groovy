package com.anrisoftware.prefdialog.panel

import static com.anrisoftware.prefdialog.panel.inputfields.button.ButtonGroupFieldHandler.BUTTON
import static com.google.inject.Guice.createInjector

import java.awt.Dimension;

import org.junit.Before
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.PreferencePanelHandlerFactory
import com.anrisoftware.prefdialog.annotations.Child
import com.anrisoftware.prefdialog.panel.api.Appearance
import com.anrisoftware.prefdialog.panel.inputfields.PrefdialogCoreFieldsModule
import com.google.inject.Inject
import com.google.inject.Injector

class AppearancePanelTest extends TestFrameUtil {

	static Injector injector = createInjector(
	new PrefdialogAppearanceModule(),
	new PrefdialogPanelModule(),
	new PrefdialogCoreFieldsModule())

	static factory = injector.getInstance(PreferencePanelHandlerFactory)

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
		Panel appearancePanel = injector.getInstance Panel
		def panel = factory.create(appearancePanel, "appearance").createPanel()
		beginPanelFrame "Appearance Panel Test", panel.getAWTComponent(), {
			//
			Thread.sleep 0 // 60000
		}
	}
}
