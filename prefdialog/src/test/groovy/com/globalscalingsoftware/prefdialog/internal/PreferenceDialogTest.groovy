package com.globalscalingsoftware.prefdialog.internal

import javax.swing.JFrame;

import groovy.util.GroovyTestCase

class PreferenceDialogTest extends GroovyTestCase {
	
	void testConstructor() {
		def preferenceDialog = new PreferenceDialog(null, null, null, null, null, null)
		assert preferenceDialog != null
	}
	
	void _testOpenNoPreferences() {
		def uiPreferencesDialog = new UiPreferencesDialog()
		uiPreferencesDialog.setDefaultCloseOperation JFrame.DISPOSE_ON_CLOSE
		def preferenceDialog = new PreferenceDialog(uiPreferencesDialog, null, null, null)
		preferenceDialog.open(null)
	}
	
	void testOpenWithDemoPreferences() {
		def annotationDiscovery = new AnnotationDiscovery()
		def annotationsFilter = new AnnotationsFilter()
		def uiPreferencesDialog = new UiPreferencesDialog()
		uiPreferencesDialog.setDefaultCloseOperation JFrame.DISPOSE_ON_CLOSE
		def preferenceDialog = new PreferenceDialog(
				annotationDiscovery, annotationsFilter,
				uiPreferencesDialog, 
				null, null, null)
		def demoPreferences = new Preferences()
		preferenceDialog.open(demoPreferences)
	}
}

