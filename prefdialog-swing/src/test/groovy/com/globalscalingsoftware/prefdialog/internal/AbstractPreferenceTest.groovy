package com.globalscalingsoftware.prefdialog.internal

import java.lang.reflect.Field;

import javax.swing.SwingUtilities 
import javax.swing.UIManager;
import org.fest.swing.edt.GuiActionRunner 
import org.fest.swing.edt.GuiQuery 
import org.fest.swing.fixture.FrameFixture 
import org.junit.After;
import org.junit.Before;

import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;


import groovy.swing.SwingBuilder 

abstract class AbstractPreferenceTest {
	
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
		return new SwingBuilder().build {
			frame(title:'Preferences Panel', size:[480, 640], locationByPlatform: true) {
				borderLayout()
				widget(preferencesPanel())
			}
		}
	}
}
