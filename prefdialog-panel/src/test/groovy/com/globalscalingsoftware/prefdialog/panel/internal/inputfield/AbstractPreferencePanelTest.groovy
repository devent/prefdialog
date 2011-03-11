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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield


import groovy.swing.SwingBuilder 
import java.awt.event.ActionEvent 

import javax.swing.AbstractAction;
import org.fest.swing.edt.GuiActionRunner 
import org.fest.swing.edt.GuiQuery 
import org.fest.swing.fixture.FrameFixture 
import org.junit.After;
import org.junit.Before;

import com.globalscalingsoftware.prefdialog.PreferencePanelHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.module.PanelModule;
import com.google.inject.Guice;



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
	
	def fixture
	
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
	}
	
	def createFrameFixture() {
		def injector = Guice.createInjector(new PanelModule())
		def factory = injector.getInstance(PreferencePanelHandlerFactory)
		
		getFrame(factory)
		def frame = GuiActionRunner.execute([executeInEDT: { return getFrame(factory) } ] as GuiQuery);
		return new FrameFixture(frame);
	}
	
	def getFrame(def factory) {
		def handler = factory.create(preferences)
		def panel = handler.getAWTComponent()
		new SwingBuilder().frame(title: 'Preference Panel Test', pack: true) {
			borderLayout()
			widget(panel)
		}
	}
}
