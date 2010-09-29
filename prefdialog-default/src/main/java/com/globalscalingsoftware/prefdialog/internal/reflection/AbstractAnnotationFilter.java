package com.globalscalingsoftware.prefdialog.internal.reflection;

import java.lang.annotation.Annotation;
import java.util.List;

import com.globalscalingsoftware.annotations.Stateless;

@Stateless
public abstract class AbstractAnnotationFilter {

	private final List<Class<? extends Annotation>> annotations;

	@SuppressWarnings("unchecked")
	public AbstractAnnotationFilter(List<?> annotations) {
		this.annotations = (List<Class<? extends Annotation>>) annotations;
	}

	public boolean accept(Annotation annotation) {
		for (Class<? extends Annotation> a : annotations) {
			if (a.isInstance(annotation)) {
				return true;
			}
		}
		return false;
	}

}