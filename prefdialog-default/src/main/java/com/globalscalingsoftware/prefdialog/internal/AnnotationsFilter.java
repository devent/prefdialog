package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.globalscalingsoftware.prefdialog.IAnnotationFilter;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.Parsed;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.annotations.Validated;

public class AnnotationsFilter implements IAnnotationFilter {

	private final List<Class<? extends Annotation>> annotations;

	public AnnotationsFilter() {
		annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(Child.class);
		annotations.add(Parsed.class);
		annotations.add(TextField.class);
		annotations.add(FormattedTextField.class);
		annotations.add(Validated.class);
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
