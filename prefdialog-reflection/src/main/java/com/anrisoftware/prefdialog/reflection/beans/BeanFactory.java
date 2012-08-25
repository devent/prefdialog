/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-reflection.
 * 
 * prefdialog-reflection is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-reflection is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-reflection. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.reflection.beans;

import com.anrisoftware.prefdialog.reflection.exceptions.ReflectionError;

/**
 * Creates bean objects.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface BeanFactory {

	/**
	 * Creates the bean with the standard constructor.
	 * 
	 * @param type
	 *            the {@link Class} type of the bean. The bean must have the
	 *            standard constructor available for initialization.
	 * 
	 * @return the bean.
	 * 
	 * @throws ReflectionError
	 *             if the standard constructor can not be found or can not be
	 *             accessed, if the constructor throws an exception or if the
	 *             type can not be instantiated.
	 */
	<T> T createBean(Class<T> type);
}
