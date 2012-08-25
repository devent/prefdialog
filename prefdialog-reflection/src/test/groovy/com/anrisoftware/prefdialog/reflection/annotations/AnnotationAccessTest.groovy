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
		def value = a.getElementValue BeanAnnotation.class, field, String
		assertStringContent value, "Annotation Value"
	}

	@Test
	void "read annotation title value"() {
		AnnotationAccess a = injector.getInstance AnnotationAccess
		def field = FieldUtils.getField Bean, "annotatedField", true
		def name = "title"
		def value = a.getElementValue BeanAnnotation.class, field, String, name
		assertStringContent value, "Annotation Title"
	}

	@Test
	void "read undefined annotation element"() {
		AnnotationAccess a = injector.getInstance AnnotationAccess
		def field = FieldUtils.getField Bean, "annotatedField", true
		def name = "not defined"
		shouldFailWith(ReflectionError) {
			a.getElementValue BeanAnnotation.class, field, String, name
		}
	}
}
