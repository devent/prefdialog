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
