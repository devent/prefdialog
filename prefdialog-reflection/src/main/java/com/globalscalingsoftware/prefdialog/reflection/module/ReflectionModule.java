package com.globalscalingsoftware.prefdialog.reflection.module;

import com.globalscalingsoftware.prefdialog.reflection.AnnotationDiscoveryFactory;
import com.globalscalingsoftware.prefdialog.reflection.AnnotationFilterFactory;
import com.globalscalingsoftware.prefdialog.reflection.internal.AnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.reflection.internal.AnnotationFilter;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ReflectionModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(AnnotationDiscovery.class,
				AnnotationDiscovery.class).build(
				AnnotationDiscoveryFactory.class));
		install(new FactoryModuleBuilder().implement(AnnotationFilter.class,
				AnnotationFilter.class).build(AnnotationFilterFactory.class));
	}

}
