/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.formattedtextfield

import groovy.transform.CompileStatic

import javax.swing.JFormattedTextField
import javax.swing.JFormattedTextField.AbstractFormatter
import javax.swing.JFormattedTextField.AbstractFormatterFactory
import javax.swing.text.MaskFormatter

/**
 * Custom formatter factory.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
@CompileStatic
class CustomFormatterFactory extends AbstractFormatterFactory {

    public AbstractFormatter getFormatter(JFormattedTextField tf) {
        def formatter = new MaskFormatter("####-##-##")
        formatter.setAllowsInvalid(true);
        formatter.setPlaceholderCharacter("_" as char);
        return formatter
    }
}
