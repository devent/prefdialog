package com.globalscalingsoftware.prefdialog.internal



import com.globalscalingsoftware.prefdialog.IDiscoveredListener;
import com.globalscalingsoftware.prefdialog.IAnnotationFilter;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.TextField;

import groovy.util.GroovyTestCase;

public class AnnotationsTest extends GroovyTestCase {
	
	void testDiscoverChildAnnotation() {
		def preferences = new Preferences()
		def toolbox = new ReflectionToolbox()
		def discovery = new AnnotationDiscovery(toolbox)
		
		def childAnnotationCount = 0
		def filter = { a -> a instanceof Child } as IAnnotationFilter
		def listener = [
				fieldAnnotationDiscovered : { f, v, a -> childAnnotationCount++ }
				] as IDiscoveredListener
		
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
		} as IAnnotationFilter
		def listener = [
				fieldAnnotationDiscovered : { f, v, a ->
					if (a instanceof TextField) {
						annotationCount++
					}
				}
				] as IDiscoveredListener 
		
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
				] as IDiscoveredListener 
		
		discovery.discover(preferences, filter, listener)
		assert annotationCount == 5
	}
}
