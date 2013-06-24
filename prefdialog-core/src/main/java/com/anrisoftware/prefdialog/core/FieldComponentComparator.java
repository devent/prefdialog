package com.anrisoftware.prefdialog.core;

import java.util.Comparator;

import com.anrisoftware.prefdialog.fields.FieldComponent;

/**
 * Compares two fields based on their order number.
 * 
 * @see FieldComponent
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class FieldComponentComparator implements Comparator<FieldComponent<?>> {

	@Override
	public int compare(FieldComponent<?> o1, FieldComponent<?> o2) {
		return o1.getOrder() < o2.getOrder() ? -1 : o1.getOrder() > o2
				.getOrder() ? 1 : 0;
	}

}
