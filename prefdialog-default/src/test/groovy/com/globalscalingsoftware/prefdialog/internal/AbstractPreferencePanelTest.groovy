package com.globalscalingsoftware.prefdialog.internal

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery 
import com.globalscalingsoftware.prefdialog.IAnnotationFilter 
import com.globalscalingsoftware.prefdialog.IFormattedTextFieldFactory 
import com.globalscalingsoftware.prefdialog.IPreferencePanel 
import com.globalscalingsoftware.prefdialog.IPreferencePanelFactory 
import com.globalscalingsoftware.prefdialog.IReflectionToolbox 
import com.globalscalingsoftware.prefdialog.internal.formattedtextfield.FormattedTextField 
import com.google.inject.Guice 
import com.google.inject.Module 
import groovy.swing.SwingBuilder 
import org.junit.BeforeClass;
import static javax.swing.UIManager.*;
import static com.google.inject.assistedinject.FactoryProvider.newFactory;

abstract class AbstractPreferencePanelTest {
	
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
	
	static injector
	
	@BeforeClass
	static void beforeClass() {
		injector = Guice.createInjector([configure: { binding ->
			binding.bind(IAnnotationDiscovery).to AnnotationDiscovery
			binding.bind(IAnnotationFilter).to AnnotationsFilter
			binding.bind(IReflectionToolbox).to ReflectionToolbox
			binding.bind(IPreferencePanelFactory).toProvider newFactory(IPreferencePanelFactory,
					PreferencePanelController)
			binding.bind(IPreferencePanel).to PreferencePanel
			binding.bind(IFormattedTextFieldFactory).toProvider newFactory(IFormattedTextFieldFactory,
					FormattedTextField)
		} ] as Module)
	}
	
	protected createDialog(def preferencesPanel) {
		new SwingBuilder().build {
			dialog(title:'Preferences Panel', size:[480,640], modal: true, show: true, locationByPlatform: true) {
				borderLayout()
				widget(preferencesPanel())
			}
		}
	}
	
	protected Field getPreferencesField(def preferences, def fieldName) {
		def field
		Preferences.getDeclaredFields().each{f->
			if(f.getName().equals(fieldName)) {
				field = f
			}
		}
		return field
	}
}
