package com.globalscalingsoftware.prefdialog;

import java.lang.annotation.Annotation;

public interface IAnnotationFilter {

	boolean accept(Annotation annotation);

}
