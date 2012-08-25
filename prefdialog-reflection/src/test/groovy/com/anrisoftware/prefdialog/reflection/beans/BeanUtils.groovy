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
package com.anrisoftware.prefdialog.reflection.beans

import org.junit.Before

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.reflection.utils.ParentBean
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Create the injector to test annotation access.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class BeanUtils extends TestUtils {

	Injector injector

	ParentBean bean

	@Before
	void beforeTest() {
		injector = createInjector()
		bean = new ParentBean()
	}

	Injector createInjector() {
		Guice.createInjector new BeansModule()
	}
}
