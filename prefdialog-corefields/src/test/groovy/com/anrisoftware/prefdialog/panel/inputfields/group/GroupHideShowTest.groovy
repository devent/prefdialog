/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields.group

import static com.anrisoftware.prefdialog.panel.inputfields.button.ButtonGroupPanel.BUTTON
import info.clearthought.layout.TableLayout

import java.awt.Container
import java.awt.event.ActionListener
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.SwingUtilities

import org.junit.Test

import aurelienribon.tweenengine.Tween
import aurelienribon.tweenengine.TweenManager

import com.anrisoftware.globalpom.utils.TestFrameUtil

class GroupHideShowTest extends TestFrameUtil {

	static class MockupPanel extends JPanel {

		def button

		def panel

		MockupPanel() {
			def col = [
				TableLayout.FILL,
				TableLayout.PREFERRED
			]as double[]
			def row = [
				TableLayout.PREFERRED,
				TableLayout.FILL,
				TableLayout.PREFERRED,
			]as double[]
			layout = new TableLayout(col, row)
			button = new JButton("...")
			panel = createPanel()

			add button, "1, 0"
			add panel, "0, 1, 1, 1"
			add(new JTextField("Text"), "0, 2, 1, 2")

			layout.setRow 1, 0

			Tween.registerAccessor(Container.class, new LayoutAccessor());
			def manager = new TweenManager();

			button.addActionListener([actionPerformed: {
					SwingUtilities.invokeLater {
						def target = owner.owner
						target.layout.setRow(1, 1)
						Tween.to(target, LayoutAccessor.POSITION_Y, 500)
								.target(100)
								.start(manager);
						def future = Executors.newScheduledThreadPool(1).scheduleAtFixedRate( {
							if (!manager.containsTarget(target)) {
								future.cancel false
							}
							manager.update 20
						}, 0, 20, TimeUnit.MILLISECONDS)
					}
				}]as ActionListener)
		}

		def createPanel() {
			def panel = new JPanel()
			def col = [TableLayout.FILL]as double[]
			def row = [
				TableLayout.PREFERRED,
				TableLayout.PREFERRED
			]as double[]
			panel.layout = new TableLayout(col, row)
			panel.add(new JLabel("Text:"), "0, 0")
			panel.add(new JTextField(""), "0, 1")
			panel
		}
	}

	@Test
	void "manually"() {
		def panel = new MockupPanel()
		beginPanelFrame "Group Show Hide", panel, { //
			Thread.sleep 0 // 30000
		}
	}
}
