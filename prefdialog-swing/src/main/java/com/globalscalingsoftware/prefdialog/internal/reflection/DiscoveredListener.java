package com.globalscalingsoftware.prefdialog.internal.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface DiscoveredListener {

	void fieldAnnotationDiscovered(Field field, Object value, Annotation a);

}
