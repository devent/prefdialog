/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.dialog;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;

import com.anrisoftware.prefdialog.ChildrenListPanel;
import com.anrisoftware.prefdialog.ChildrenListPanelFactory;
import com.anrisoftware.prefdialog.ChildrenPanel;
import com.anrisoftware.prefdialog.ChildrenPanelFactory;
import com.anrisoftware.prefdialog.PreferenceDialog;
import com.anrisoftware.prefdialog.PreferenceDialogFactory;
import com.anrisoftware.prefdialog.PreferenceDialogHandler;
import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.dialog.children.ChildrenPanelImpl;
import com.anrisoftware.prefdialog.dialog.children.ChildrenTreeListPanelImpl;
import com.anrisoftware.prefdialog.panel.PrefdialogPanelModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;

/**
 * Binds the default dependencies for the preference dialog.
 * 
 * We can use the module to create a new instance of
 * {@link PreferenceDialogHandler} with the help of an {@link Injector}. As in
 * the example:
 * 
 * <pre>
 * injector = Guice.createInjector(new PreferenceDialogModule());
 * factory = injector.getInstance(PreferenceDialogFactory.class);
 * controller = factory.create(owner, preferences);
 * controller.openDialog();
 * if (controller.getOption() == OK) {
 *     compute preferences
 * }
 * </pre>
 */
public class PrefdialogModule extends PrefdialogPanelModule {

	@Override
	protected void configure() {
		super.configure();
		install(new FactoryModuleBuilder().implement(ChildrenListPanel.class,
				ChildrenTreeListPanelImpl.class).build(
				ChildrenListPanelFactory.class));
		install(new FactoryModuleBuilder().implement(ChildrenPanel.class,
				ChildrenPanelImpl.class).build(ChildrenPanelFactory.class));
		install(new FactoryModuleBuilder().implement(PreferenceDialog.class,
				PreferenceDialogImpl.class)
				.build(PreferenceDialogFactory.class));
	}

	@Provides
	@Named("childAnnotations")
	Collection<Class<? extends Annotation>> bindAnnotations() {
		Collection<Class<? extends Annotation>> annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(Child.class);
		return annotations;
	}
}
