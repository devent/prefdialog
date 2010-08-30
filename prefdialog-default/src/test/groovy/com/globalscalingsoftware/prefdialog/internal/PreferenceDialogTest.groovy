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
	
	
	void testConstructor() {
		def preferenceDialog = new PreferenceDialog()
		assert preferenceDialog != null
	}
	
	void testOpenNoController() {
		def owner = new JFrame()
		owner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		
		def preferenceDialog = new PreferenceDialog()
		preferenceDialog.setOwner owner
		preferenceDialog.open()
	}
	
	void testOpenNoControllerWithActions() {
		def owner = new JFrame()
		owner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		
		def okAction = new DefaultAction("Ok")
		def cancelAction = new DefaultAction("Cancel")
		
		def preferenceDialog = new PreferenceDialog()
		preferenceDialog.setOwner owner
		preferenceDialog.setOkAction okAction
		preferenceDialog.setCancelAction cancelAction
		preferenceDialog.open()
	}
	
	void testOpenWithController() {
		def owner = new JFrame()
		owner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		
		def toolbox = new ReflectionToolbox()
		def annotationDiscovery = new AnnotationDiscovery(toolbox)
		def annotationsFilter = new AnnotationsFilter()
		
		def preferenceDialog = new PreferenceDialog()
		def preferencePanel = new PreferencePanelCreator(
				annotationDiscovery, annotationsFilter, toolbox)
		def prefereceController = new PreferenceDialogController(
				annotationDiscovery, annotationsFilter, preferenceDialog, 
				preferencePanel)
		
		preferenceDialog.setOwner owner
		preferenceDialog.open()
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
		
		def preferenceDialog = new PreferenceDialog()
		def preferencePanel = new PreferencePanelCreator(
				annotationDiscovery, annotationsFilter, toolbox)
		def prefereceController = new PreferenceDialogController(
				annotationDiscovery, annotationsFilter, preferenceDialog,
				preferencePanel)
		
		prefereceController.setOwner owner
		prefereceController.setApplyAction applyAction
		prefereceController.setRestoreAction restoreAction
		prefereceController.setCancelAction cancelAction
		prefereceController.setOkAction okAction
		prefereceController.setPreferences(demoPreferences)
		prefereceController.setChildObject(demoPreferences.general)
		
		prefereceController.openDialog()
		
		def fields = demoPreferences.general.fields
		def device = demoPreferences.device.device
		println "fields = $fields; device = $device"
	}
}

