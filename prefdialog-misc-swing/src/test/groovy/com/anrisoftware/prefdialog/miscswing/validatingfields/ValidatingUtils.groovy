package com.anrisoftware.prefdialog.miscswing.validatingfields

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
		def panel = new JPanel(new TableLayout(cols, rows))
		attributes.fields.each {
			panel.add it.value, it.key
		}
		return panel
	}

	/**
	 * Create a text field with the specified name.
	 */
	static JTextField createTextField(String name) {
		def field = new JTextField()
		field.setName name
		return field
	}

	/**
	 * Create a formatted text field with the specified name.
	 */
	static JFormattedTextField createFormattedTextField(String name, def value) {
		def field = new JFormattedTextField(value)
		field.setName name
		return field
	}

	/**
	 * Create a combo-box field with the specified name.
	 */
	static JComboBox createComboBoxField(String name, boolean editable) {
		def field = new JComboBox(["A", "B"] as String[])
		field.setName name
		field.setEditable editable
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
