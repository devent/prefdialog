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
package com.anrisoftware.prefdialog.panel;

import javax.swing.JPanel;

import com.anrisoftware.prefdialog.fields.FieldFactory;

/**
 * Factory to create a new check box field. A check box field can only be
 * checked or unchecked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface PreferencesPanelFieldFactory extends FieldFactory<JPanel> {

}
