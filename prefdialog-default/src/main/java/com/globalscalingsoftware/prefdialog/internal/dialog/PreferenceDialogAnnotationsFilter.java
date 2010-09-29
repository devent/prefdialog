package com.globalscalingsoftware.prefdialog.internal.dialog;

import java.util.Arrays;

import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.internal.reflection.AbstractAnnotationFilter;

public class PreferenceDialogAnnotationsFilter extends AbstractAnnotationFilter {

	PreferenceDialogAnnotationsFilter() {
		super(Arrays.asList(new Class[] { Child.class }));
	}

}
