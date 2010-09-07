package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.util.List;

import com.globalscalingsoftware.prefdialog.IAnnotationFilter;

abstract class AbstractAnnotationFilter implements IAnnotationFilter {

	private List<Class<? extends Annotation>> annotations;

	public void setAnnotations(List<Class<? extends Annotation>> annotations) {
		this.annotations = annotations;
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