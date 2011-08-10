package com.globalscalingsoftware.prefdialog.reflection;

import com.globalscalingsoftware.prefdialog.reflection.api.AnnotationDiscoveryWorker;
import com.globalscalingsoftware.prefdialog.reflection.api.AnnotationDiscoveryWorkerFactory;
import com.globalscalingsoftware.prefdialog.reflection.api.AnnotationFilter;
import com.globalscalingsoftware.prefdialog.reflection.api.PredefinedAnnotationFilterFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ReflectionModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				AnnotationDiscoveryWorker.class,
				AnnotationDiscoveryWorkerImpl.class).build(
				AnnotationDiscoveryWorkerFactory.class));
		install(new FactoryModuleBuilder().implement(AnnotationFilter.class,
				PredefinedAnnotationFilter.class).build(
				PredefinedAnnotationFilterFactory.class));
	}

}
