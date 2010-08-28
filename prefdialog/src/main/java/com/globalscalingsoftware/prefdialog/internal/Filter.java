package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;

public interface Filter {

	boolean accept(Annotation annotation);

}
