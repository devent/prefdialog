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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.FieldComponent;
import com.globalscalingsoftware.prefdialog.FieldHandler;

public interface ChildComponent extends FieldComponent {

	void addField(FieldHandler<?> inputField);

	void setApplyCallback(Runnable e);

	void setApplyAction(Action a);

	void setApplyButtonEnabled(boolean enabled);

	void setRestoreAction(Action a);

	void setRestoreCallback(Runnable e);

	void setRestoreButtonEnabled(boolean enabled);

	public void setButtonsTransparent(boolean transparent);
}
