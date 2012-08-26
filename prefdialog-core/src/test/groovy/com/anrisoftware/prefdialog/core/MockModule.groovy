/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-core.
 * 
 * prefdialog-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.core

import com.google.inject.AbstractModule
import com.google.inject.assistedinject.FactoryModuleBuilder
/**
 * Binds the mock field factories.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class MockModule extends AbstractModule {

	@Override
	protected void configure() {
		install new FactoryModuleBuilder().implement(MockFieldComponent, MockFieldComponent).build(MockFieldComponentFactory)
		install new FactoryModuleBuilder().implement(MockContainerField, MockContainerField).build(MockContainerFieldFactory)
		install new FactoryModuleBuilder().implement(MockTitleField, MockTitleField).build(MockTitleFieldFactory)
	}
}

