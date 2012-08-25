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
package com.anrisoftware.prefdialog.reflection.annotations

import org.apache.commons.lang3.reflect.FieldUtils
import org.junit.Test

import com.anrisoftware.prefdialog.reflection.exceptions.ReflectionError
import com.anrisoftware.prefdialog.reflection.utils.Bean
import com.anrisoftware.prefdialog.reflection.utils.BeanAnnotation

/**
 * Tests for {@link AnnotationAccessImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class AnnotationAccessTest extends AnnotationUtils {

	@Test
	void "read annotation value"() {
		AnnotationAccess a = injector.getInstance AnnotationAccess
		def field = FieldUtils.getField Bean, "annotatedField", true
		def value = a.getElementValue BeanAnnotation.class, field
		assertStringContent value, "Annotation Value"
	}

	@Test
	void "read annotation title value"() {
		AnnotationAccess a = injector.getInstance AnnotationAccess
		def field = FieldUtils.getField Bean, "annotatedField", true
		def name = "title"
		def value = a.getElementValue BeanAnnotation.class, field, name
		assertStringContent value, "Annotation Title"
	}

	@Test
	void "read undefined annotation element"() {
		AnnotationAccess a = injector.getInstance AnnotationAccess
		def field = FieldUtils.getField Bean, "annotatedField", true
		def name = "not defined"
		shouldFailWith(ReflectionError) {
			a.getElementValue BeanAnnotation.class, field, name
		}
	}
}
