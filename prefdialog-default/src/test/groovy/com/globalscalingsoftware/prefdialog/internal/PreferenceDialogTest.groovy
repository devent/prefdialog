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
		def owner = new PreferenceDialogOwner()
		owner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		
		def demoPreferences = new Preferences()
		def demoPreferencesStart = demoPreferences.general
		def toolbox = new ReflectionToolbox()
		def annotationsFilter = new AnnotationsFilter()
		def annotationDiscovery = new AnnotationDiscovery(annotationsFilter, 
				toolbox)
		
		def okAction = new DefaultAction("Ok")
		def restoreAction = new DefaultAction("Restore")
		def applyAction = new DefaultAction("Apply")
		def cancelAction = new DefaultAction("Cancel")
		
		def preferenceDialog = new PreferenceDialog(owner, okAction, cancelAction)
		def preferencePanel = new PreferencePanelCreator(
				annotationDiscovery, toolbox,
				applyAction, restoreAction)
		def prefereceController = new PreferenceDialogController(
				annotationDiscovery, preferenceDialog,preferencePanel, 
				demoPreferences, demoPreferencesStart)
		
		prefereceController.openDialog()
		
		def fields = demoPreferences.general.fields
		def device = demoPreferences.device.device
		println "fields = $fields; device = $device"
	}
}
