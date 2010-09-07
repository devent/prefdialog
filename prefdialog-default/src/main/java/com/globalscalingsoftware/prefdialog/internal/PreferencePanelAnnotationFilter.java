package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.globalscalingsoftware.prefdialog.IPreferencePanelAnnotationFilter;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.TextField;

public class PreferencePanelAnnotationFilter extends AbstractAnnotationFilter
		implements IPreferencePanelAnnotationFilter {

	private static List<Class<? extends Annotation>> annotations;

	static {
		annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(TextField.class);
		annotations.add(FormattedTextField.class);
	}

	PreferencePanelAnnotationFilter() {
		setAnnotations(annotations);
	}

}
