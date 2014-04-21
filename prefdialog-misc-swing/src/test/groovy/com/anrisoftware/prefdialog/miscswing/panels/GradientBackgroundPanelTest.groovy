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
package com.anrisoftware.prefdialog.miscswing.panels

import java.awt.BorderLayout
import java.awt.Dimension

import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

import net.miginfocom.swing.MigLayout

import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

class GradientBackgroundPanelTest {

    @Test
    void "gray gradient panel"() {
        String title = "GradientBackgroundPanelTest::gray gradient panel"
        def panel = new JPanel()
        panel.setUI new GradientBackgroundPanelUi()
        def frame = createFrame title, panel
        frame.withFixture { }
    }

    TestFrameUtil createFrame(String title, JPanel panel) {
        panel.setLayout new BorderLayout()
        def someStuff = new JPanel()
        someStuff.opaque = false
        someStuff.setLayout new MigLayout()
        someStuff.add new JLabel("Hello"), "wrap"
        someStuff.add new JButton("Hello"), "wrap"
        someStuff.add new JTextField("Hello"), "wrap"
        panel.add someStuff
        def frame = new TestFrameUtil(title, panel)
        frame.frameSize = new Dimension(480, 360)
        frame
    }
}
