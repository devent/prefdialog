/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.fadingpane

import static javax.swing.SwingUtilities.*
import groovy.swing.SwingBuilder
import groovy.util.logging.Slf4j

import java.awt.BorderLayout

import javax.swing.JButton
import javax.swing.JPanel

import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * @see FadingPane
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Slf4j
class FadingPaneTest {

	@Test
	void "fading frame"() {
		def titlea = "$NAME-fading frame"
		FadingPane fading = FadingPane.create()
		def frame = new TestFrameUtil(title: titlea, component: createPanel()) {
			protected createFrame(String title, def component) {
				def frame
				invokeAndWait {
					frame = new SwingBuilder().frame(title: title, pack: true, preferredSize: frameSize) {
						borderLayout()
						widget(component, constraints: BorderLayout.CENTER)
					}
					fading.installPane frame
				}
				return frame
			}
		}
		frame.withFixture({
			log.info "fade in"
			fading.fadeIn()
		}, {
			log.info "fade out"
			fading.fadeOut()
		})
	}

	@Test
	void "fade out"() {
		def titlea = "$NAME-fade out"
		FadingPane fading = FadingPane.create()
		fading.setAlpha 1.0f
		def frame = new TestFrameUtil(title: titlea, component: createPanel()) {
			protected createFrame(String title, def component) {
				def frame
				invokeAndWait {
					frame = new SwingBuilder().frame(title: title, pack: true, preferredSize: frameSize) {
						borderLayout()
						widget(component, constraints: BorderLayout.CENTER)
					}
					fading.installPane frame
					fading.setVisible true
				}
				return frame
			}
		}
		frame.withFixture({
		}, {
			log.info "fade out"
			fading.fadeOut()
		})
	}

	static final NAME = FadingPaneTest.class.simpleName

	static JPanel createPanel() {
		def panel
		def button
		invokeAndWait {
			panel = new JPanel(new BorderLayout())
			button = new JButton(NAME)
			panel.add button, BorderLayout.NORTH
		}
		return panel
	}
}
