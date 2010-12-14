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
package com.globalscalingsoftware.prefdialog.internal

import java.awt.BorderLayout;
import java.awt.event.ActionEvent 

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import org.fest.swing.edt.GuiActionRunner 
import org.fest.swing.edt.GuiQuery 
import org.fest.swing.fixture.FrameFixture 
import org.junit.After;
import org.junit.Before;

import com.globalscalingsoftware.prefdialog.PreferenceDialogFactory;



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
	
	protected preferences
	
	protected panelName
	
	protected FrameFixture window
	
	@Before
	void beforeTest() {
		setupPreferences()
		window = createFrameFixture()
		window.show();
	}
	
	abstract setupPreferences()
	
	@After
	void afterTest() {
		window.cleanUp()
	}
	
	protected createFrameFixture() {
		def injector = new PreferencesDialogInjectorFactory().create()
		def factory = injector.getInstance(PreferenceDialogFactory)
		
		//getFrame(factory)
		def frame = GuiActionRunner.execute([executeInEDT: { return getFrame(factory) } ] as GuiQuery);
		return new FrameFixture(frame);
	}
	
	protected getFrame(PreferenceDialogFactory factory) {
		def controller = factory.create(null, preferences)
		controller.setup()
		def panel = controller.getPreferencePanels().get(panelName)
		return createFrame(panel.getAWTComponent())
	}
	
	
	private createFrame(def panel) {
		def frame = new JFrame("Preferences Panel")
		frame.setSize 480, 640
		frame.setLocationByPlatform true
		frame.setLayout new BorderLayout()
		frame.add panel
		return frame
	}
}
