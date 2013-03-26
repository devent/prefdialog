/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-core.
 *
 * prefdialog-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.core

import org.junit.Before

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.resources.binary.binaries.BinariesResourcesModule
import com.anrisoftware.resources.binary.maps.BinariesDefaultMapsModule
import com.anrisoftware.resources.images.api.Images
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.anrisoftware.resources.texts.api.Texts
import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.maps.TextsDefaultMapsModule
import com.anrisoftware.resources.texts.texts.TextsResourcesModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Creates the injector and the field component factory; Create the texts
 * and images resource.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class FieldTestUtils extends TestFrameUtil {

	Injector injector

	@Before
	void beforeTest() {
		injector = createInjector()
	}

	Injector createInjector() {
		Guice.createInjector()
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
