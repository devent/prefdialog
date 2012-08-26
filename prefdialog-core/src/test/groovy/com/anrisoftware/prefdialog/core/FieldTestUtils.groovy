package com.anrisoftware.prefdialog.core

import org.junit.Before

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.reflection.annotations.AnnotationsModule
import com.anrisoftware.prefdialog.reflection.beans.BeansModule
import com.anrisoftware.resources.api.Images
import com.anrisoftware.resources.api.ImagesFactory
import com.anrisoftware.resources.api.Texts
import com.anrisoftware.resources.api.TextsFactory
import com.anrisoftware.resources.binary.BinariesResourcesModule
import com.anrisoftware.resources.binary.maps.BinariesDefaultMapsModule
import com.anrisoftware.resources.images.ResourcesImagesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.anrisoftware.resources.texts.ResourcesTextsCharsetModule
import com.anrisoftware.resources.texts.TextsResourcesModule
import com.anrisoftware.resources.texts.maps.TextsDefaultMapsModule
import com.google.inject.Guice
import com.google.inject.Injector

class FieldTestUtils extends TestFrameUtil {

	Injector injector

	MockFieldComponentFactory fieldComponentFactory

	Preferences preferences

	@Before
	void beforeTest() {

		injector = createInjector()
		fieldComponentFactory = injector.getInstance MockFieldComponentFactory
		preferences = new Preferences()
	}

	Injector createInjector() {
		Guice.createInjector new MockModule(), new AnnotationsModule(), new BeansModule()
	}

	Texts createTextsResource() {
		Injector childInjector = injector.createChildInjector new TextsResourcesModule(),
						new TextsDefaultMapsModule(),
						new BinariesResourcesModule(), new BinariesDefaultMapsModule(),
						new ResourcesTextsCharsetModule()
		TextsFactory factory = childInjector.getInstance TextsFactory
		factory.create "Texts"
	}

	Images createImagesResource() {
		Injector childInjector = injector.createChildInjector new ResourcesImagesModule(),
						new ResourcesImagesMapsModule(),
						new ResourcesSmoothScalingModule()
		ImagesFactory factory = childInjector.getInstance ImagesFactory
		factory.create "Icons"
	}
}
