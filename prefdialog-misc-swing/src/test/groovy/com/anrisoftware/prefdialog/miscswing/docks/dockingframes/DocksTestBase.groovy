package com.anrisoftware.prefdialog.miscswing.docks.dockingframes

import static com.anrisoftware.prefdialog.miscswing.docks.api.PerspectivePosition.*
import static java.awt.Color.*

import java.awt.Color
import java.awt.Dimension

import javax.swing.JFrame
import javax.swing.JPanel

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.miscswing.docks.api.Dock
import com.anrisoftware.prefdialog.miscswing.docks.api.PerspectiveTask
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DefaultWorkDockWindow
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesModule
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.viewdockable.DefaultViewDockWindow
import com.anrisoftware.prefdialog.miscswing.docks.perspectives.dockingframes.DefaultPerspectiveTask
import com.google.inject.Guice
import com.google.inject.Injector

class DocksTestBase {

	TestFrameUtil withFrame(String title, def setupDock) {
		def util = new TestFrameUtil(title, new JPanel()) {
					protected createFrame(String titlea, def component) {
						def frame = new JFrame(titlea)
						dock.withFrame(frame)
						setupDock()
						frame.setPreferredSize frameSize
						frame
					}
				}
		util.frameSize = new Dimension(800, 640)
		util
	}

	static singleWindows = [
		new DefaultViewDockWindow("view_a", "View West A", WEST, BLUE),
		new DefaultViewDockWindow("view_b", "View West B", WEST, RED)
	]

	static workWindows = [
		new DefaultWorkDockWindow("editor_1", "Editor 1", CENTER, BLUE),
		new DefaultWorkDockWindow("editor_2", "Editor 2", CENTER, RED)
	]

	static DefaultWorkDockWindow createWorkWindow(int number) {
		def color = RGBtoHSB(0, 255, 255, null)
		color = new Color(HSBtoRGB(color[0], color[1], (float)color[2] * 0.2f * number))
		new DefaultWorkDockWindow("editor_${number}", "Editor ${number}", CENTER, color)
	}

	static Injector createInjector() {
		TestUtils.toStringStyle
		Guice.createInjector new DockingFramesModule()
	}

	static PerspectiveTask createDefaultPerspective(Injector injector, name) {
		def task = injector.getInstance(DefaultPerspectiveTask)
		task.setName name
		return task
	}

	static Dock createDock(Injector injector) {
		injector.getInstance Dock
	}
}
