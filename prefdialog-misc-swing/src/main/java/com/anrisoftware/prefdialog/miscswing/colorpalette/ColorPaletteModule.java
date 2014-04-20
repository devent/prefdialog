/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.colorpalette;

import static com.google.inject.Guice.createInjector;

import com.anrisoftware.globalpom.format.point.PointFormatFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the color palette factories.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ColorPaletteModule extends AbstractModule {

	/**
	 * @see #getFactory()
	 */
	public static PaletteFactory getPaletteFactory() {
		return getFactory();
	}

	/**
	 * Creates the point format factory.
	 * 
	 * @return the {@link PointFormatFactory}.
	 */
	public static PaletteFactory getFactory() {
		return InjectorInstance.injector.getInstance(PaletteFactory.class);
	}

	private static class InjectorInstance {
		static final Injector injector = createInjector(new ColorPaletteModule());
	}

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(BrightPalette.class,
				BrightPalette.class).build(PaletteFactory.class));
	}
}
