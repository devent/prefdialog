/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-panel.
 *
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.core

import static com.anrisoftware.globalpom.textposition.TextPosition.*
import static com.anrisoftware.resources.images.api.IconSize.*

import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.fields.FieldComponent
import com.anrisoftware.prefdialog.fields.FieldService
import com.anrisoftware.resources.images.api.Images
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.mapcached.ResourcesImagesCachedMapModule
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
 * @since 3.0
 */
class FieldTestUtils {

    static {
        TestUtils.toStringStyle
    }

    static Injector createInjector() {
        Guice.createInjector new CoreFieldComponentModule()
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
                new ResourcesImagesCachedMapModule(),
                new ResourcesSmoothScalingModule())
        ImagesFactory factory = childInjector.getInstance ImagesFactory
        factory.create "Icons"
    }

    static assertField(Map attributes, FieldComponent field) {
        Map map = [
            showTitle: true,
            toolTip: null,
            titlePosition: TEXT_ONLY,
            readOnly: false,
            width: -2.0d,
            icon: { },
            iconSize: SMALL,
            locale: Locale.getDefault()
        ]
        map.putAll attributes
        assert field.name == map.name
        assert field.title == map.title
        assert field.showTitle == map.showTitle
        assert field.toolTipText == map.toolTip
        assert field.titlePosition == map.titlePosition
        assert field.enabled == !map.readOnly
        assert field.width == map.width
        map.icon(field.icon)
        assert field.iconSize == map.iconSize
        assert field.locale == map.locale
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

    static JPanel createContainer() {
        def panel = new JPanel()
        panel.setBorder BorderFactory.createEtchedBorder()
        return panel
    }

    static FieldService findService(def info) {
        ServiceLoader.load(FieldService).find { it.info == info }
    }
}
