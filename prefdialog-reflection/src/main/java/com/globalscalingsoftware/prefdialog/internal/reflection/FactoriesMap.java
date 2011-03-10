package com.globalscalingsoftware.prefdialog.internal.reflection;

import java.lang.annotation.Annotation;
import java.util.HashMap;

import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;

@SuppressWarnings("serial")
public class FactoriesMap extends
		HashMap<Class<? extends Annotation>, FieldHandlerFactory> {

}
