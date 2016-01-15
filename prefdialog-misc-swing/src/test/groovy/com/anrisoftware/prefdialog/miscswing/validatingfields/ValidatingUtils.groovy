/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.validatingfields

import static javax.swing.SwingUtilities.*
import info.clearthought.layout.TableLayout

import java.beans.PropertyVetoException
import java.beans.VetoableChangeListener

import javax.swing.JComboBox
import javax.swing.JFormattedTextField
import javax.swing.JPanel
import javax.swing.JSpinner
import javax.swing.JTextField

/**
 * Utilities for validating components.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ValidatingUtils {

	public static final String validatingTextField = ValidatingTextField.class.name

	public static final String jtextField = JTextField.class.name

	public static final String validatingFormattedTextField = ValidatingFormattedTextField.class.name

	public static final String jformattedTextField = JFormattedTextField.class.name

	/**
	 * Create a vetoable change listener.
	 */
	static VetoableChangeListener createVetoableChangeListener(def validValue) {
		[vetoableChange: { ev ->
				if (ev.newValue != validValue) {
					throw new PropertyVetoException("Value '$ev.newValue' not valid.", ev)
				}
			}] as VetoableChangeListener
	}

	/**
	 * Create panel that contains the fields.
	 *
	 * @param attributes
	 * 			  <dl>
	 * 			  <dt>cols</dt>
	 * 			  <dd>the columns of the layout;</dd>
	 * 			  <dt>rows</dt>
	 * 			  <dd>the rows of the layout;</dd>
	 * 			  <dt>fields</dt>
	 * 			  <dd>a map of the fields, key is the position, value is the field.</dd>
	 * 			  </dl>
	 *
	 * @return
	 */
	static JPanel createPanel(def attributes) {
		def cols = attributes.cols as double[]
		def rows = attributes.rows as double[]
		def panel
		invokeAndWait {
			panel = new JPanel(new TableLayout(cols, rows))
			attributes.fields.each {
				panel.add it.value, it.key
			}
		}
		return panel
	}

	/**
	 * Create a text field with the specified name.
	 */
	static JTextField createTextField(String name, String type = jtextField) {
		def field
		invokeAndWait {
			field = Class.forName(type).newInstance()
			field.setName name
		}
		return field
	}

	/**
	 * Create a formatted text field with the specified name.
	 */
	static JFormattedTextField createFormattedTextField(String name, def value, String type = jformattedTextField) {
		def field
		invokeAndWait {
			field = Class.forName(type).getConstructor([Object]as Class[]).newInstance(value)
			field.setName name
		}
		return field
	}

	/**
	 * Create a combo-box field with the specified name.
	 */
	static JComboBox createComboBoxField(String name, boolean editable) {
		def field
		invokeAndWait {
			field = new JComboBox(["A", "B"] as String[])
			field.setName name
			field.setEditable editable
		}
		return field
	}

	/**
	 * Create a text field with the specified name.
	 */
	static JSpinner createSpinner(String name) {
		def field = new JSpinner()
		field.setName name
		return field
	}
}
