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
import java.lang.reflect.Field;

import javax.swing.JFrame;
import javax.swing.SwingUtilities 
import javax.swing.UIManager;
import org.fest.swing.edt.GuiActionRunner 
import org.fest.swing.edt.GuiQuery 
import org.fest.swing.fixture.FrameFixture 
import org.junit.After;
import org.junit.Before;

import com.globalscalingsoftware.prefdialog.internal.inputfield.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;



abstract class AbstractPreferencePanelTest {
	
	static {
		setGTKLookAndFeel()
		//setPlasticLookAndFeel()
		//setSubstanceLookAndFeel()
	}
	
	static setGTKLookAndFeel() {
		setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
	}
	
	static setPlasticLookAndFeel() {
		setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel")
	}
	
	static setSubstanceLookAndFeel() {
		setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel")
	}
	
	static setLookAndFeel(def lookAndFeelClass) {
		try {
			SwingUtilities.invokeLater({UIManager.setLookAndFeel(lookAndFeelClass); }as Runnable)
		} catch (Exception e) {
			e.printStackTrace();
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
		window = createFrameFixture(preferencesClass, preferences, preferencesParentName, preferencesParentValue)
		window.show();
	}
	
	abstract setupPreferences()
	
	@After
	void afterTest() {
		window.cleanUp()
	}
	
	private createFrameFixture(def preferencesClass, def preferences, def parentName, def parentValue) {
		def injector = new PreferencesDialogInjectorFactory().create(preferences)
		def field = getPreferencesField(preferencesClass, parentName)
		//createFrame(injector, preferences, field, parentValue)
		def frame = GuiActionRunner.execute([executeInEDT: { 
				return createFrame(injector, preferences, field, parentValue)
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
