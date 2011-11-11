/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.panel.inputfields


import groovy.swing.SwingBuilder

import java.awt.BorderLayout

import javax.swing.JFrame

import org.apache.commons.lang.builder.ToStringBuilder
import org.apache.commons.lang.builder.ToStringStyle
import org.fest.swing.edt.GuiActionRunner
import org.fest.swing.edt.GuiQuery
import org.fest.swing.fixture.FrameFixture

import com.globalscalingsoftware.prefdialog.FieldHandlerFactory
import com.google.inject.Guice
import com.google.inject.Injector

abstract class FieldFixtureHandler {

	public static Injector injector = Guice.createInjector(new PrefdialogCoreFieldsModule())

	static toStringStyle = ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE)

	def parentObject

	def value

	def field

	def inputField

	FrameFixture fixture

	JFrame frame

	def createFieldFixture(def parentObject, def fieldName, FieldHandlerFactory fieldFactory) {
		this.parentObject = parentObject
		this.value = parentObject."$fieldName"
		this.field = parentObject.class.declaredFields.find { it.name == fieldName }
		this.inputField = fieldFactory.create(parentObject, value, field).setup()
		this.frame = createPanelFrame()
	}

	def createPanelFrame() {
		return new SwingBuilder().frame(title: 'Core Field Test', pack: true, preferredSize: [480, 360]) {
			borderLayout()
			widget(inputField.getAWTComponent(), constraints: BorderLayout.CENTER)
		}
	}

	void beginFixture() {
		fixture = createFrameFixture()
		fixture.show();
	}

	void endFixture() {
		fixture.cleanUp()
		fixture = null
	}

	def createFrameFixture() {
		def result = GuiActionRunner.execute([executeInEDT: { frame } ] as GuiQuery);
		return new FrameFixture(result);
	}
}
