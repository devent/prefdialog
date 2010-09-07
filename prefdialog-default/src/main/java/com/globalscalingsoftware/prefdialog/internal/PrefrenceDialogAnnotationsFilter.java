package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.globalscalingsoftware.prefdialog.IPreferenceDialogAnnotationFilter;
import com.globalscalingsoftware.prefdialog.annotations.Child;

public class PrefrenceDialogAnnotationsFilter extends AbstractAnnotationFilter
		implements IPreferenceDialogAnnotationFilter {

	private static List<Class<? extends Annotation>> annotations;

	static {
		annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(Child.class);
	}

	public PrefrenceDialogAnnotationsFilter() {
		setAnnotations(annotations);
	}

}
