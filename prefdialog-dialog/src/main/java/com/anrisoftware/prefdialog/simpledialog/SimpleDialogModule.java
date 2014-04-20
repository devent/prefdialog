/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.simpledialog;

import com.anrisoftware.globalpom.mnemonic.MnemonicModule;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule;
import com.anrisoftware.globalpom.reflection.beans.BeansModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs simple dialog factory and dependent modules.
 * 
 * @see SimpleDialogFactory
 * @see SimplePropertiesDialogFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class SimpleDialogModule extends AbstractModule {

	/**
	 * Returns the simple dialog factory.
	 * 
	 * @return the {@link SimpleDialogFactory}.
	 */
	public static SimpleDialogFactory getSimpleDialogFactory() {
		return SingletonHolder.dialogFactory;
	}

	/**
	 * Returns the simple properties dialog factory.
	 * 
	 * @return the {@link SimplePropertiesDialogFactory}.
	 */
	public static SimplePropertiesDialogFactory getSimplePropertiesDialogFactory() {
		return SingletonHolder.propertiesDialogFactory;
	}

	private static class SingletonHolder {

		private static final Module[] modules = new Module[] {
				new SimpleDialogModule(), new MnemonicModule(),
				new AnnotationsModule(), new BeansModule() };

		public static final Injector injector = Guice.createInjector(modules);

		public static final SimplePropertiesDialogFactory propertiesDialogFactory = injector
				.getInstance(SimplePropertiesDialogFactory.class);

		public static final SimpleDialogFactory dialogFactory = injector
				.getInstance(SimpleDialogFactory.class);
	}

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(SimpleDialog.class,
				SimpleDialog.class).build(SimpleDialogFactory.class));
		install(new FactoryModuleBuilder().implement(
				SimplePropertiesDialog.class, SimplePropertiesDialog.class)
				.build(SimplePropertiesDialogFactory.class));
		install(new FactoryModuleBuilder().implement(UiDialogPanel.class,
				UiDialogPanel.class).build(UiDialogPanelFactory.class));
	}

}
