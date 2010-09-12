package com.globalscalingsoftware.prefdialog.internal

import com.globalscalingsoftware.prefdialog.IApplyAction 
import com.globalscalingsoftware.prefdialog.ICancelAction 
import com.globalscalingsoftware.prefdialog.IOkAction 
import com.globalscalingsoftware.prefdialog.IPreferenceDialogOwner 
import com.globalscalingsoftware.prefdialog.IRestoreAction 
import com.globalscalingsoftware.prefdialog.internal.module.PreferenceDialogModule 
import com.google.inject.Guice 
import com.google.inject.Module 
import com.google.inject.name.Names 

class PreferencesDialogInjectorFactory {
	
	def create(def preferences) {
		def module = [configure: {binding -> 
			binding.bind(IPreferenceDialogOwner).to PreferenceDialogOwner
			
			def okAction = new DefaultAction("Ok")
			def restoreAction = new DefaultAction("Restore")
			def applyAction = new DefaultAction("Apply")
			def cancelAction = new DefaultAction("Cancel")
			
			binding.bind(IRestoreAction).toInstance restoreAction
			binding.bind(IApplyAction).toInstance applyAction
			binding.bind(ICancelAction).toInstance cancelAction
			binding.bind(IOkAction).toInstance okAction
			
			binding.bind(Object).annotatedWith(Names.named("preferences")).toInstance(preferences)
			binding.bind(Object).annotatedWith(Names.named("preferences_start")).toInstance(preferences.general)
		}] as Module
		return Guice.createInjector(new PreferenceDialogModule(), module)
	}
}