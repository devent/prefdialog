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
