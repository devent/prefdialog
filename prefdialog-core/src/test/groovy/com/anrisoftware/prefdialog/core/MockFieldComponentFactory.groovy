package com.anrisoftware.prefdialog.core

import java.lang.reflect.Field

import javax.swing.JComponent

interface MockFieldComponentFactory {

	MockFieldComponent create(JComponent component, Object parentObject, Field field)
}

