/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-reflection.
 * 
 * prefdialog-reflection is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-reflection is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-reflection. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.reflection;

import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryWorker;
import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryWorkerFactory;
import com.anrisoftware.prefdialog.reflection.api.AnnotationFilter;
import com.anrisoftware.prefdialog.reflection.api.PredefinedAnnotationFilterFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds reflection classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
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
