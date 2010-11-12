package com.globalscalingsoftware.prefdialog.internal


import com.globalscalingsoftware.prefdialog.module.PreferenceDialogModule;
import com.google.inject.Guice 
import com.google.inject.Module 
import com.google.inject.name.Names 

class PreferencesDialogInjectorFactory {
	
	def create(def preferences) {
		def module = [configure: {binding -> 
				binding.bind(Object).annotatedWith(Names.named("preferences")).toInstance(preferences)
			}] as Module
		return Guice.createInjector(new PreferenceDialogModule(), module)
	}
}
