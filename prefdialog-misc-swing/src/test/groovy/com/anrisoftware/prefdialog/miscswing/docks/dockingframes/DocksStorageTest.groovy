package com.anrisoftware.prefdialog.miscswing.docks.dockingframes

import static javax.swing.SwingUtilities.*
import groovy.util.logging.Slf4j

import javax.swing.JOptionPane

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import bibliothek.gui.dock.common.theme.ThemeMap

@Slf4j
class DocksStorageTest extends DocksTestBase {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder()

	@Test
	void "manually store and load perspective"() {
		File tmp = File.createTempFile("docks-storane", null)
		tmp.deleteOnExit()
		String title = "PerspectiveManuallyTest::manually store and load perspective"
		withFrame(title, {
			singleWindows.each { dock.addSingleDock(it) }
			dock.addWorkDock(workWindows[0])
			dock.addWorkDock(workWindows[1])
			dock.setDefaultPerspective()
			dock.setTheme(ThemeMap.KEY_ECLIPSE_THEME)
		}).withFixture({
			log.info "Choose your layout."
			Thread.sleep 10*1000
			invokeLater {
				String name = JOptionPane.showInputDialog("Name")
				dock.savePerspective(name, tmp)
			}
		},{
			Thread.sleep 10*1000
			log.info "Load your layout."
			invokeLater {
				String name = JOptionPane.showInputDialog("Name")
				dock.loadPerspective(name, tmp)
			}
		},
		{ Thread.sleep 60*1000 })
	}
}
