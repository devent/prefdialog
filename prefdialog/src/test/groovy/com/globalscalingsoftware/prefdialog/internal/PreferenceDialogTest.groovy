package com.globalscalingsoftware.prefdialog.internal

import javax.swing.JFrame;

import com.globalscalingsoftware.prefdialog.IPreferencesDialogOwner;

import groovy.util.GroovyTestCase

class PreferenceDialogTest extends GroovyTestCase {
	
	void testConstructor() {
		def preferenceDialog = new PreferenceDialog(null, null, null, null)
		assert preferenceDialog != null
	}
	
	void testOpenNoPreferences() {
		def owner = { new JFrame() } as IPreferencesDialogOwner
		def dialog = new UiPreferencesDialog(owner)
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
		
		def preferenceDialog = new PreferenceDialog(dialog, null, null, null)
		preferenceDialog.open()
	}
	
	void testOpenWithController() {
		def annotationDiscovery = new AnnotationDiscovery()
		def annotationsFilter = new AnnotationsFilter()
		def owner = { new JFrame() } as IPreferencesDialogOwner
		def dialog = new UiPreferencesDialog(owner)
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
		
		def preferenceDialog = new PreferenceDialog(dialog, null, null, null)
		def prefereceController = new PreferenceDialogController(
				annotationDiscovery, annotationsFilter, preferenceDialog)
		
		preferenceDialog.open()
	}
	
	void testOpenWithControllerAndPreferences() {
		def demoPreferences = new Preferences()
		def annotationDiscovery = new AnnotationDiscovery()
		def annotationsFilter = new AnnotationsFilter()
		def owner = { new JFrame() } as IPreferencesDialogOwner
		def dialog = new UiPreferencesDialog(owner)
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
		
		def preferenceDialog = new PreferenceDialog(dialog, null, null, null)
		def prefereceController = new PreferenceDialogController(
				annotationDiscovery, annotationsFilter, preferenceDialog)
		
		prefereceController.setPreferences(demoPreferences)
		preferenceDialog.open()
	}
}

