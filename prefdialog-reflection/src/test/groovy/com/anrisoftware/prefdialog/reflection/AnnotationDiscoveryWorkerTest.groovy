package com.anrisoftware.prefdialog.reflection

import org.junit.Test

import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryCallback
import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryWorker
import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryWorkerFactory
import com.anrisoftware.prefdialog.reflection.api.PredefinedAnnotationFilterFactory
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.assistedinject.Assisted

class AnnotationDiscoveryWorkerTest {

	static Injector injector = Guice.createInjector(new ReflectionModule())

	static filterFactory = injector.getInstance(PredefinedAnnotationFilterFactory)

	static workerFactory = injector.getInstance(AnnotationDiscoveryWorkerFactory)

	@Test
	void "find annotation in object"() {
		Bean bean = new Bean()

		def discovered = []
		def annotations = [Assisted]
		def filter = filterFactory.create(annotations)
		AnnotationDiscoveryWorker worker = workerFactory.create(filter, [
					fieldAnnotationDiscovered: { field, value, a ->
						discovered << ['field': field, 'value': value, 'a': a]
					}
				]as AnnotationDiscoveryCallback)
		worker.discoverAnnotations(bean)

		assert discovered.size() == 1
		assert discovered[0].field.name == 'assistedField'
		assert discovered[0].value == bean.assistedField
		assert discovered[0].a.toString().contains('Assisted')
	}
}
