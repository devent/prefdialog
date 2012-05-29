/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-dialog.
 * 
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.dialog.childrentab;

import com.anrisoftware.prefdialog.ChildrenPanel;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the preferences dialog children tree panel classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public class PrefdialogChildrenTabModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(ChildrenPanel.class,
				ChildrenPanelImpl.class).build(ChildrenTabPanelFactory.class));
	}
}
