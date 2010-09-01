package com.globalscalingsoftware.prefdialog.internal

import javax.swing.JFrame;
import groovy.util.GroovyTestCase

class PreferenceDialogTest extends GroovyTestCase { {
		// Set Look & Feel
		try {
			javax.swing.UIManager
			.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	void testOpenWithControllerAndPreferences() {
		def owner = new JFrame()
		owner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		
		def demoPreferences = new Preferences()
		def toolbox = new ReflectionToolbox()
		def annotationDiscovery = new AnnotationDiscovery(toolbox)
		def annotationsFilter = new AnnotationsFilter()
		
		def okAction = new DefaultAction("Ok")
		def restoreAction = new DefaultAction("Restore")
		def applyAction = new DefaultAction("Apply")
		def cancelAction = new DefaultAction("Cancel")
		
		def preferenceDialog = new PreferenceDialog(okAction, cancelAction)
		def preferencePanel = new PreferencePanelCreator(
				annotationDiscovery, annotationsFilter, toolbox,
				applyAction, restoreAction)
		def prefereceController = new PreferenceDialogController(
				annotationDiscovery, annotationsFilter, preferenceDialog,
				preferencePanel)
		
		prefereceController.setOwner owner
		prefereceController.setPreferences(demoPreferences)
		prefereceController.setChildObject(demoPreferences.general)
		
		prefereceController.openDialog()
		
		def fields = demoPreferences.general.fields
		def device = demoPreferences.device.device
		println "fields = $fields; device = $device"
	}
}

