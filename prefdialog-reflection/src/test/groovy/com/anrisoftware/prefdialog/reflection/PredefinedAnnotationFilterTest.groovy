package com.anrisoftware.prefdialog.reflection

import org.junit.Test

import com.anrisoftware.prefdialog.reflection.api.AnnotationFilter
import com.anrisoftware.prefdialog.reflection.api.PredefinedAnnotationFilterFactory
import com.anrisoftware.prefdialog.reflection.utils.ParentBean;
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.assistedinject.Assisted

class PredefinedAnnotationFilterTest {

	static Injector injector = Guice.createInjector(new ReflectionModule())

	static factory = injector.getInstance(PredefinedAnnotationFilterFactory)

	@Test
	void "find annotation in object"() {
		ParentBean bean = new ParentBean()
		def findAnnotation = ParentBean.fields.find { it.name == 'assistedField' }.annotations[0]
		def notFindAnnotation = ParentBean.fields.find { it.name == 'injectField' }.annotations[0]

		def annotations = [Assisted]
		AnnotationFilter filter = factory.create(annotations)
		assert filter.accept(findAnnotation)
		assert filter.accept(notFindAnnotation) == false
	}
}
