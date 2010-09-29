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

	public void discover(AbstractAnnotationFilter filter, Object object,
			DiscoveredListener listener) {
		if (object == null) {
			return;
		}
		discoverFields(filter, object, listener);
	}

	private void discoverFields(AbstractAnnotationFilter filter, Object object,
			DiscoveredListener listener) {
		Class<? extends Object> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				informListenerIfFilterAccepts(filter, object, listener, field,
						annotation);
			}
		}
	}

	private void informListenerIfFilterAccepts(AbstractAnnotationFilter filter,
			Object object, DiscoveredListener listener, Field field,
			Annotation annotation) {
		if (!filter.accept(annotation)) {
			return;
		}

		Object value = reflectionToolbox.getValueFrom(field, object);
		listener.fieldAnnotationDiscovered(field, value, annotation);
		// discover(filter, value, listener);
	}

}
