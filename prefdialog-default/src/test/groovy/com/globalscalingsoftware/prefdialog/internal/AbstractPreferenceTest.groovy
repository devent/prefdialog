package com.globalscalingsoftware.prefdialog.internal

import java.lang.reflect.Field;

import javax.swing.SwingUtilities 
import javax.swing.UIManager;


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
	
	protected createDialog(def preferencesPanel) {
		new SwingBuilder().build {
			dialog(title:'Preferences Panel', size:[480,640], modal: true, show: true, locationByPlatform: true) {
				borderLayout()
				widget(preferencesPanel())
			}
		}
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
}
