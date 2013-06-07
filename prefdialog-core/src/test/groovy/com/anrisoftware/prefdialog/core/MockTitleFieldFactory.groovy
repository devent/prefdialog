/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
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

import javax.swing.JComponent

/**
 * Factory to create a new abstract title field. Is used so Guice can
 * inject the method-inject dependencies.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
interface MockTitleFieldFactory {

	/**
	 * @see AbstractTitleField#AbstractTitleField(java.awt.Component, Object, String)
	 * 
	 * @return the {@link MockTitleField}.
	 * 
	 * @since 3.0
	 */
	MockTitleField create(JComponent component, Object parentObject, String fieldName)
}

