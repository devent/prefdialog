package com.globalscalingsoftware.prefdialog.internal



import com.globalscalingsoftware.prefdialog.IFilter;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.TextField;

import groovy.util.GroovyTestCase;

public class AnnotationsTest extends GroovyTestCase {
	
	void testDiscoverChildAnnotation() {
		def preferences = new Preferences()
		def toolbox = new ReflectionToolbox()
		def discovery = new AnnotationDiscovery(toolbox)
		
		def childAnnotationCount = 0
		def filter = { a -> a instanceof Child } as IFilter
		def listener = [
				fieldAnnotationDiscovered : { f, v, a -> childAnnotationCount++ }
				] as DiscoveredListener 
		
		discovery.discover(preferences, filter, listener)
		assert childAnnotationCount == 2
	}
	
	void testDiscoverTextFieldAnnotation() {
		def preferences = new Preferences()
		def toolbox = new ReflectionToolbox()
		def discovery = new AnnotationDiscovery(toolbox)
		
		def annotationCount = 0
		def filter = { a -> 
			a instanceof Child || a instanceof TextField
		} as IFilter
		def listener = [
				fieldAnnotationDiscovered : { f, v, a ->
					if (a instanceof TextField) {
						annotationCount++
					}
				}
				] as DiscoveredListener 
		
		discovery.discover(preferences, filter, listener)
		assert annotationCount == 1
	}
	
	void testAnnotationsFilter() {
		def preferences = new Preferences()
		def toolbox = new ReflectionToolbox()
		def discovery = new AnnotationDiscovery(toolbox)
		
		def annotationCount = 0
		def filter = new AnnotationsFilter()
		def listener = [
				fieldAnnotationDiscovered : { f, v, a ->  annotationCount++ }
				] as DiscoveredListener 
		
		discovery.discover(preferences, filter, listener)
		assert annotationCount == 5
	}
}
