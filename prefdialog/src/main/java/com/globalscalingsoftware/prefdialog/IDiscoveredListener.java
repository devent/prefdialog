package com.globalscalingsoftware.prefdialog;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface IDiscoveredListener {

	void fieldAnnotationDiscovered(Field field, Object value, Annotation a);

}
