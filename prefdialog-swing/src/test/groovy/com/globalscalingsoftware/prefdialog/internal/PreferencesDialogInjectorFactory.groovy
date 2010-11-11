package com.globalscalingsoftware.prefdialog.internal

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.annotations.actions.ApplyAction;
import com.globalscalingsoftware.prefdialog.annotations.actions.CancelAction;
import com.globalscalingsoftware.prefdialog.annotations.actions.OkAction;
import com.globalscalingsoftware.prefdialog.annotations.actions.RestoreAction;
import com.globalscalingsoftware.prefdialog.internal.module.PreferenceDialogModule 
import com.google.inject.Guice 
import com.google.inject.Module 
import com.google.inject.name.Names 

class PreferencesDialogInjectorFactory {
	
	def create(def preferences) {
		def module = [configure: {binding -> 
				def okAction = new DefaultAction("Ok")
				def restoreAction = new DefaultAction("Restore")
				def applyAction = new DefaultAction("Apply")
				def cancelAction = new DefaultAction("Cancel")
				
				binding.bind(Action).annotatedWith(RestoreAction).toInstance restoreAction
				binding.bind(Action).annotatedWith(ApplyAction).toInstance applyAction
				binding.bind(Action).annotatedWith(CancelAction).toInstance cancelAction
				binding.bind(Action).annotatedWith(OkAction).toInstance okAction
				
				binding.bind(Object).annotatedWith(Names.named("preferences")).toInstance(preferences)
				binding.bind(Object).annotatedWith(Names.named("preferences_start")).toInstance(preferences.general)
			}] as Module
		return Guice.createInjector(new PreferenceDialogModule(), module)
	}
}
