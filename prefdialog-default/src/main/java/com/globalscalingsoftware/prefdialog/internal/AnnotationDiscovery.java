package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IDiscoveredListener;
import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IFilter;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.google.inject.Inject;

public class AnnotationDiscovery implements IAnnotationDiscovery {

	private final IReflectionToolbox reflectionToolbox;

	@Inject
	AnnotationDiscovery(IReflectionToolbox reflectionToolbox) {
		this.reflectionToolbox = reflectionToolbox;
	}

	@Override
	public void discover(Object object, IFilter filter,
			IDiscoveredListener listener) {
		if (object == null) {
			return;
		}
		discoverFields(object, filter, listener);
	}

	private void discoverFields(Object object, IFilter filter,
			IDiscoveredListener listener) {
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
