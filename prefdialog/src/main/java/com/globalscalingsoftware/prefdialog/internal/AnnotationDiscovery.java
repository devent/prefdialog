package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.google.inject.Inject;

public class AnnotationDiscovery {

	private final ReflectionToolbox reflectionToolbox;

	@Inject
	AnnotationDiscovery(ReflectionToolbox reflectionToolbox) {
		this.reflectionToolbox = reflectionToolbox;
	}

	public void discover(Object object, Filter filter,
			DiscoveredListener listener) {
		if (object == null) {
			return;
		}
		discoverFields(object, filter, listener);
	}

	private void discoverFields(Object object, Filter filter,
			DiscoveredListener listener) {
		Class<? extends Object> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (filter.accept(annotation)) {

					Object value = reflectionToolbox
							.getValueFrom(field, object);
					listener.fieldAnnotationDiscovered(field, value, annotation);
					discover(value, filter, listener);
				}
			}
		}
	}

}
