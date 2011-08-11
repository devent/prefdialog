/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.panel.inputfield


import groovy.swing.SwingBuilder

import java.awt.event.ActionEvent

import javax.swing.AbstractAction
import javax.swing.JFrame

import org.fest.swing.edt.GuiActionRunner
import org.fest.swing.edt.GuiQuery
import org.fest.swing.fixture.FrameFixture
import org.junit.After
import org.junit.Before

import com.globalscalingsoftware.prefdialog.PreferencePanelHandler
import com.globalscalingsoftware.prefdialog.PreferencePanelHandlerFactory
import com.globalscalingsoftware.prefdialog.panel.PanelModule;
import com.google.inject.Guice



abstract class AbstractPreferencePanelTest {

	static class ApplyAction extends AbstractAction {
		ApplyAction() {
			super("Apply")
		}
		void actionPerformed(ActionEvent e) {
		}
	}

	static class RestoreAction extends AbstractAction {
		RestoreAction() {
			super("Restore")
		}
		void actionPerformed(ActionEvent e) {
		}
	}

	def preferences

	def panelName

	PreferencePanelHandler panelHandler

	FrameFixture fixture

	JFrame frame

	@Before
	void beforeTest() {
		setupPreferences()
		fixture = createFrameFixture()
		fixture.show();
	}

	abstract setupPreferences()

	@After
	void afterTest() {
		fixture.cleanUp()
		fixture = null
	}

	def createFrameFixture() {
		def injector = Guice.createInjector(new PanelModule())
		def factory = injector.getInstance(PreferencePanelHandlerFactory)
		panelHandler = factory.create(preferences, panelName).createPanel()
		createPanelFrame(panelHandler)
		def result = GuiActionRunner.execute([executeInEDT: { frame } ] as GuiQuery);
		return new FrameFixture(result);
	}

	def createPanelFrame(PreferencePanelHandler panelHandler) {
		def panel = panelHandler.getAWTComponent()
		frame = new SwingBuilder().frame(title: 'Preference Panel Test', pack: true) {
			borderLayout()
			widget(panel)
		}
	}
}
