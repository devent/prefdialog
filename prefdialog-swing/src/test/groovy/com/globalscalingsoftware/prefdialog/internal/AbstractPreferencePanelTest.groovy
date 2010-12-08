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
import java.lang.reflect.Field;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import org.fest.swing.edt.GuiActionRunner 
import org.fest.swing.edt.GuiQuery 
import org.fest.swing.fixture.FrameFixture 
import org.junit.After;
import org.junit.Before;

import com.globalscalingsoftware.prefdialog.internal.inputfield.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;



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
	
	protected preferencesClass
	
	protected preferences
	
	protected preferencesParentValue
	
	protected preferencesParentName
	
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
		def field = getPreferencesField(preferencesClass, preferencesParentName)
		//createFrame(injector, preferences, field, preferencesParentValue)
		def frame = GuiActionRunner.execute([executeInEDT: { 
				return createFrame(injector, preferences, field, preferencesParentValue)
			} ] as GuiQuery);
		return new FrameFixture(frame);
	}
	
	protected Field getPreferencesField(def preferencesClass, def fieldName) {
		def field
		preferencesClass.getDeclaredFields().each{f->
			if(f.getName().equals(fieldName)) {
				field = f
			}
		}
		return field
	}
	
	protected createFrame(def injector, def preferences, def field, def parentValue) {
		def factory = injector.getInstance(FieldsFactory)
		def reflectionToolbox = injector.getInstance(ReflectionToolbox)
		def inputfield = factory.createField(preferences, field, parentValue)
		
		inputfield.setReflectionToolbox(reflectionToolbox)
		inputfield.setFieldsFactory(factory)
		inputfield.setApplyAction(new ApplyAction())
		inputfield.setRestoreAction(new RestoreAction())
		inputfield.setup()
		
		return createFrame({ return inputfield.getAWTComponent() })
	}
	
	private createFrame(def preferencesPanel) {
		def frame = new JFrame("Preferences Panel")
		frame.setSize 480, 640
		frame.setLocationByPlatform true
		frame.setLayout new BorderLayout()
		frame.add preferencesPanel()
		return frame
	}
}
