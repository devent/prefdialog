package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.util.List;

import com.globalscalingsoftware.annotations.Stateless;
import com.globalscalingsoftware.prefdialog.IAnnotationFilter;

@Stateless
public abstract class AbstractAnnotationFilter implements IAnnotationFilter {

	private final List<Class<? extends Annotation>> annotations;

	@SuppressWarnings("unchecked")
	public AbstractAnnotationFilter(List<?> annotations) {
		this.annotations = (List<Class<? extends Annotation>>) annotations;
	}

	@Override
	public boolean accept(Annotation annotation) {
		for (Class<? extends Annotation> a : annotations) {
			if (a.isInstance(annotation)) {
				return true;
			}
		}
		return false;
	}

}