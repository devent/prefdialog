package com.anrisoftware.prefdialog.miscswing.toolbarmenu

import static com.anrisoftware.prefdialog.miscswing.toolbarmenu.ToolbarMenu.*
import static javax.swing.SwingUtilities.*

import java.awt.BorderLayout

import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.gimport.*
import com.anrisoftware.globalpom.mnemonic.MnemonicModule
import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see ToolbarMenu
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ToolbarMenuTest {

	@Test
	void "popup menu"() {
		def title = "$NAME::popup menu"
		new TestFrameUtil(title, panel).withFixture({ FrameFixture fix ->
			fix.toolBar().showPopupMenu()
			assert fix.menuItem(TEXT_ALONGSIDE_NAME).target.selected
			assert fix.menuItem(SMALL_ICON_SIZE_NAME).target.selected
		})
	}

	@Test
	void "switch text position"() {
		def title = "$NAME::switch text position"
		new TestFrameUtil(title, panel).withFixture({ FrameFixture fix ->
			fix.toolBar().showPopupMenu()
			fix.menuItem ICONS_ONLY_NAME click()
		}, { FrameFixture fix ->
			fix.toolBar().showPopupMenu()
			fix.menuItem TEXT_ONLY_NAME click()
		}, { FrameFixture fix ->
			fix.toolBar().showPopupMenu()
			fix.menuItem ICONS_ONLY_NAME click()
		}, { FrameFixture fix ->
			fix.toolBar().showPopupMenu()
			fix.menuItem TEXT_ONLY_NAME click()
		}, { FrameFixture fix ->
			fix.toolBar().showPopupMenu()
			fix.menuItem TEXT_ALONGSIDE_NAME click()
		})
	}

	@Test
	void "switch icon sizes"() {
		def title = "$NAME::switch icon sizes"
		new TestFrameUtil(title, panel).withFixture({ FrameFixture fix ->
			fix.toolBar().showPopupMenu()
			fix.menuItem HUGE_ICON_SIZE_NAME click()
		}, { FrameFixture fix ->
			fix.toolBar().showPopupMenu()
			fix.menuItem LARGE_ICON_SIZE_NAME click()
		}, { FrameFixture fix ->
			fix.toolBar().showPopupMenu()
			fix.menuItem MEDIUM_ICON_SIZE_NAME click()
		}, { FrameFixture fix ->
			fix.toolBar().showPopupMenu()
			fix.menuItem SMALL_ICON_SIZE_NAME click()
		})
	}

	static final String NAME = ToolbarMenuTest.class.simpleName

	static Injector injector

	static images

	static texts

	ToolbarMenu toolbarMenu

	ToolBar toolBar

	JPanel panel

	@BeforeClass
	static void createResources() {
		injector = Guice.createInjector(new ImagesResourcesModule(),
				new ResourcesImagesMapsModule(), new ResourcesSmoothScalingModule(),
				new TextsResourcesDefaultModule(), new MnemonicModule())
		images = injector.getInstance(ImagesFactory).create("Images")
		texts = injector.getInstance(TextsFactory).create("Texts")
	}

	@Before
	void createToolbar() {
		invokeAndWait {
			toolBar = injector.getInstance ToolBar
			toolBar.buttonAction.setImages images
			toolBar.buttonAction.setTexts texts
			toolbarMenu = injector.getInstance ToolbarMenu
			toolbarMenu.addAction toolBar.buttonAction
			panel = new JPanel(new BorderLayout())
			panel.add toolBar, BorderLayout.NORTH
			toolbarMenu.setToolBar toolBar
		}
	}
}
