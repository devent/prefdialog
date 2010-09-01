package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IAnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.IDiscoveredListener;
import com.globalscalingsoftware.prefdialog.IAnnotationFilter;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.google.inject.Inject;

public class AnnotationDiscovery implements IAnnotationDiscovery {

	private final IReflectionToolbox reflectionToolbox;
	private final IAnnotationFilter filter;

	@Inject
	AnnotationDiscovery(IAnnotationFilter filter, IReflectionToolbox reflectionToolbox) {
		this.filter = filter;
		this.reflectionToolbox = reflectionToolbox;
	}

	@Override
	public void discover(Object object, IDiscoveredListener listener) {
		if (object == null) {
			return;
		}
		discoverFields(object, listener);
	}

	private void discoverFields(Object object, IDiscoveredListener listener) {
		Class<? extends Object> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (filter.accept(annotation)) {

					Object value = reflectionToolbox
							.getValueFrom(field, object);
					listener.fieldAnnotationDiscovered(field, value, annotation);
					discover(value, listener);
				}
			}
		}
	}

}
