package com.globalscalingsoftware.prefdialog.internal;

import java.util.Arrays;

import com.globalscalingsoftware.prefdialog.annotations.Child;

public class PreferenceDialogAnnotationsFilter extends AbstractAnnotationFilter {

	PreferenceDialogAnnotationsFilter() {
		super(Arrays.asList(new Class[] { Child.class }));
	}

}
