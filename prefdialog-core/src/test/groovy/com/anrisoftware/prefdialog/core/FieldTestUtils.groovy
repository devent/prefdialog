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

import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.JTextField

import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule
import com.anrisoftware.globalpom.reflection.beans.BeansModule
import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.fields.FieldComponent
import com.anrisoftware.resources.images.api.Images
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.anrisoftware.resources.texts.api.Texts
import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Creates the injector and the field component factory; Create the texts
 * and images resource.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FieldTestUtils {

	static {
		TestUtils.toStringStyle
	}

	static Injector createInjector() {
		Guice.createInjector new MockModule(), new AnnotationsModule(), new BeansModule()
	}

	static Texts createTextsResource(Injector injector) {
		Injector childInjector = injector.createChildInjector(
				new TextsResourcesDefaultModule())
		TextsFactory factory = childInjector.getInstance TextsFactory
		factory.create "Texts"
	}

	static Images createImagesResource(Injector injector) {
		Injector childInjector = injector.createChildInjector(
				new ImagesResourcesModule(),
				new ResourcesImagesMapsModule(),
				new ResourcesSmoothScalingModule())
		ImagesFactory factory = childInjector.getInstance ImagesFactory
		factory.create "Icons"
	}

	static assertField(Map attributes, FieldComponent field) {
		assert field.name == attributes.name
		assert field.title == attributes.title
		assert field.showTitle == attributes.showTitle
		assert field.toolTipText == attributes.toolTip
		assert field.titlePosition == attributes.titlePosition
		assert field.enabled == !attributes.readOnly
		assert field.width == attributes.width
		attributes.icon(field.icon)
		assert field.iconSize == attributes.iconSize
		assert field.locale == attributes.locale
	}

	static JLabel createLabel() {
		def label = new JLabel()
		label.setBorder BorderFactory.createEtchedBorder()
		return label
	}

	static createTextField() {
		def field = new JTextField()
		return field
	}
}
