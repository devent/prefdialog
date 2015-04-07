/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.spacer;

import info.clearthought.layout.TableLayout;

import java.awt.Component;

import javax.inject.Inject;
import javax.swing.Box;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.prefdialog.core.AbstractFieldComponent;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the default height to table layout's fill size.
 * 
 * @see Spacer
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class SpacerField extends AbstractFieldComponent<Component> {

	/**
	 * @see SpacerFieldFactory#create(Object, String)
	 */
	@Inject
	SpacerField(SpacerFieldLogger logger, @Assisted Object parentObject,
			@Assisted String fieldName) {
		super(Box.createVerticalGlue(), parentObject, fieldName);
	}

	@Inject
	void setBeanAccessFactory(AnnotationAccessFactory annotationAccessFactory) {
		setupHeight();
	}

	private void setupHeight() {
		int height = getHeight().intValue();
		if (height < 0) {
			setHeight(TableLayout.FILL);
		} else {
			setComponent(Box.createVerticalStrut(height));
		}
	}
}
