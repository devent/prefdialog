package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.globalscalingsoftware.prefdialog.IPreferencePanelAnnotationFilter;
import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;

public class PreferencePanelAnnotationFilter extends AbstractAnnotationFilter
		implements IPreferencePanelAnnotationFilter {

	private static List<Class<? extends Annotation>> annotations;

	static {
		annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(Checkbox.class);
		annotations.add(FormattedTextField.class);
	}

	PreferencePanelAnnotationFilter() {
		setAnnotations(annotations);
	}

}
