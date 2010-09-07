package com.globalscalingsoftware.prefdialog.internal

import java.lang.reflect.Field;

import groovy.swing.SwingBuilder 
import static javax.swing.UIManager.*;

abstract class AbstractPreferenceTest {
	
	static {
		setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")
	}
	
	static setLookAndFeel(def lookAndFeelClass) {
		try {
			setLookAndFeel(lookAndFeelClass);
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
