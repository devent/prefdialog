package com.anrisoftware.prefdialog.miscswing.validatingfields

import info.clearthought.layout.TableLayout

import java.beans.PropertyVetoException
import java.beans.VetoableChangeListener

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
	 * Create a text field with the specified name.
	 */
	static JSpinner createSpinner(String name) {
		def field = new JSpinner()
		field.setName name
		return field
	}
}
