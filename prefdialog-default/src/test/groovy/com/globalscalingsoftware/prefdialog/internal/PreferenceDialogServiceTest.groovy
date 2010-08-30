package com.globalscalingsoftware.prefdialog.internal

import groovy.util.GroovyTestCase;
import javax.swing.JFrame 

class PreferenceDialogServiceTest extends GroovyTestCase {
	
	void testGetPreferenceDialog() {
		def service = new PreferenceDialogService()
		assert service.getPreferenceDialog() != null
	}
	
	void testPrefereneDialog() {
		def owner = new JFrame()
		owner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		
		def demoPreferences = new Preferences()
		
		def okAction = new DefaultAction("Ok")
		def restoreAction = new DefaultAction("Restore")
		def applyAction = new DefaultAction("Apply")
		def cancelAction = new DefaultAction("Cancel")
		
		def service = new PreferenceDialogService()
		def prefereceController = service.getPreferenceDialog()
		
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
