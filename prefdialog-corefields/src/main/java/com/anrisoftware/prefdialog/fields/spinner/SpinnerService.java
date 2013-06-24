/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.spinner;

import static java.util.Arrays.asList;

import java.awt.Component;

import org.mangosdk.spi.ProviderFor;

import com.anrisoftware.globalpom.mnemonic.MnemonicModule;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule;
import com.anrisoftware.globalpom.reflection.beans.BeansModule;
import com.anrisoftware.prefdialog.annotations.Spinner;
import com.anrisoftware.prefdialog.classtask.ClassTaskModule;
import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.anrisoftware.prefdialog.fields.FieldInfo;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * Makes the spinner field available as a service.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@ProviderFor(FieldService.class)
public class SpinnerService implements FieldService {

	/**
	 * The field information.
	 */
	public static final FieldInfo INFO = new FieldInfo(Spinner.class);

	private final Iterable<? extends Module> modules;

	private final Iterable<? extends Module> dependencies;

	public SpinnerService() {
		this.modules = asList(new Module[] { new SpinnerModule() });
		this.dependencies = asList(new Module[] { new AnnotationsModule(),
				new BeansModule(), new ClassTaskModule(), new MnemonicModule() });
	}

	@Override
	public FieldInfo getInfo() {
		return INFO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FieldFactory<? extends Component> getFactory(Object... parent) {
		return createInjector(parent.length > 0 ? (Injector) parent[0] : null)
				.getInstance(FieldFactory.class);
	}

	private Injector createInjector(Injector parent) {
		return parent != null ? parent.createChildInjector(modules) : Guice
				.createInjector(dependencies).createChildInjector(modules);
	}
}
