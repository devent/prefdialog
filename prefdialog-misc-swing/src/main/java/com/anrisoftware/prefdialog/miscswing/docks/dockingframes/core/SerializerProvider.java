/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import javax.inject.Singleton;

import com.anrisoftware.prefdialog.miscswing.docks.layouts.dockingframes.DefaultLayoutTask;
import com.esotericsoftware.kryo.Kryo;
import com.google.inject.Provider;

@Singleton
class SerializerProvider implements Provider<Kryo> {

	@Override
	public Kryo get() {
		Kryo serializer = new Kryo();
		serializer.register(DefaultLayoutTask.class, 50);
		return serializer;
	}

}
