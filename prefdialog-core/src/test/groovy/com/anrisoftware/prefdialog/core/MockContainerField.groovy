/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-core.
 *
 * prefdialog-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.core

import javax.inject.Inject
import javax.swing.JComponent

import com.google.inject.assistedinject.Assisted

/**
 * Wrapper around the abstract container field for the factory.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class MockContainerField extends AbstractContainerField<JComponent> {

	/**
	 * @see AbstractContainerField#AbstractContainerField(java.awt.Component, Object, String)
	 * 
	 * @since 3.0
	 */
	@Inject
	MockContainerField(
	@Assisted JComponent component,
	@Assisted Object parentObject,
	@Assisted String fieldName) {
		super(component, parentObject, fieldName)
	}
}

