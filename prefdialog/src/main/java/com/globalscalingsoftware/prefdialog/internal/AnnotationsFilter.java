package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.globalscalingsoftware.prefdialog.Child;
import com.globalscalingsoftware.prefdialog.FormattedTextField;
import com.globalscalingsoftware.prefdialog.Parsed;
import com.globalscalingsoftware.prefdialog.TextField;
import com.globalscalingsoftware.prefdialog.Validated;

public class AnnotationsFilter implements Filter {

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
