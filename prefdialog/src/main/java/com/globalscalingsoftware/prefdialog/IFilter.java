package com.globalscalingsoftware.prefdialog;

import java.lang.annotation.Annotation;

public interface IFilter {

	boolean accept(Annotation annotation);

}
