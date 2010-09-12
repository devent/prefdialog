package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IAnnotationFilter;
import com.globalscalingsoftware.prefdialog.IDiscoveredListener;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.google.inject.Inject;

public class AnnotationDiscovery implements IAnnotationDiscovery {

	private final IReflectionToolbox reflectionToolbox;

	@Inject
	AnnotationDiscovery(IReflectionToolbox reflectionToolbox) {
		this.reflectionToolbox = reflectionToolbox;
	}

	@Override
	public void discover(IAnnotationFilter filter, Object object,
			IDiscoveredListener listener) {
		if (object == null) {
			return;
		}
		discoverFields(filter, object, listener);
	}

	private void discoverFields(IAnnotationFilter filter, Object object,
			IDiscoveredListener listener) {
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

	private void informListenerIfFilterAccepts(IAnnotationFilter filter,
			Object object, IDiscoveredListener listener, Field field,
			Annotation annotation) {
		if (!filter.accept(annotation)) {
			return;
		}

		Object value = reflectionToolbox.getValueFrom(field, object);
		listener.fieldAnnotationDiscovered(field, value, annotation);
		// discover(filter, value, listener);
	}

}
