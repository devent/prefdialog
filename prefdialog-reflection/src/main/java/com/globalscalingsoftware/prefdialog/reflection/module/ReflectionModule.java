package com.globalscalingsoftware.prefdialog.reflection.module;

import static com.google.inject.assistedinject.FactoryProvider.newFactory;

import com.globalscalingsoftware.prefdialog.reflection.AnnotationDiscoveryFactory;
import com.globalscalingsoftware.prefdialog.reflection.AnnotationFilterFactory;
import com.globalscalingsoftware.prefdialog.reflection.internal.AnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.reflection.internal.AnnotationFilter;
import com.google.inject.AbstractModule;

public class ReflectionModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AnnotationDiscoveryFactory.class).toProvider(
				newFactory(AnnotationDiscoveryFactory.class,
						AnnotationDiscovery.class));
		bind(AnnotationFilterFactory.class).toProvider(
				newFactory(AnnotationFilterFactory.class,
						AnnotationFilter.class));
	}

}
