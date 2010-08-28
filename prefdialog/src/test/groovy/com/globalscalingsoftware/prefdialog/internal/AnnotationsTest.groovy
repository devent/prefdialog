package com.globalscalingsoftware.prefdialog.internal



import com.globalscalingsoftware.prefdialog.Child;
import com.globalscalingsoftware.prefdialog.TextField;

import groovy.util.GroovyTestCase;

public class AnnotationsTest extends GroovyTestCase {
	
	void testDiscoverChildAnnotation() {
		def preferences = new Preferences()
		def discovery = new AnnotationDiscovery()
		
		def childAnnotationCount = 0
		def filter = { a -> a instanceof Child } as Filter
		def listener = [
				fieldAnnotationDiscovered : { f, v, a -> childAnnotationCount++ }
				] as DiscoveredListener 
		
		discovery.discover(preferences, filter, listener)
		assert childAnnotationCount == 2
	}
	
	void testDiscoverTextFieldAnnotation() {
		def preferences = new Preferences()
		def discovery = new AnnotationDiscovery()
		
		def annotationCount = 0
		def filter = { a -> 
			a instanceof Child || a instanceof TextField
		} as Filter
		def listener = [
				fieldAnnotationDiscovered : { f, v, a ->
					if (a instanceof TextField) {
						annotationCount++
					}
				}
				] as DiscoveredListener 
		
		discovery.discover(preferences, filter, listener)
		assert annotationCount == 2
	}
	
	void testAnnotationsFilter() {
		def preferences = new Preferences()
		def discovery = new AnnotationDiscovery()
		
		def annotationCount = 0
		def filter = new AnnotationsFilter()
		def listener = [
				fieldAnnotationDiscovered : { f, v, a ->  annotationCount++ }
				] as DiscoveredListener 
		
		discovery.discover(preferences, filter, listener)
		assert annotationCount == 5
	}
}
