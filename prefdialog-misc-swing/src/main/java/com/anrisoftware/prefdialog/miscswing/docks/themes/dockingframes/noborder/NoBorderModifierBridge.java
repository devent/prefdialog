/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.docks.themes.dockingframes.noborder;

import javax.swing.border.Border;

import bibliothek.gui.Dockable;
import bibliothek.gui.dock.common.CWorkingArea;
import bibliothek.gui.dock.common.intern.CDockable;
import bibliothek.gui.dock.common.intern.CommonDockable;
import bibliothek.gui.dock.displayer.DisplayerDockBorder;
import bibliothek.gui.dock.themes.border.BorderModifier;
import bibliothek.gui.dock.util.UIBridge;
import bibliothek.gui.dock.util.UIValue;

/**
 * Answer requests for border modifiers.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class NoBorderModifierBridge implements
		UIBridge<BorderModifier, UIValue<BorderModifier>> {

	/**
	 * Replaces any border with null border.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 3.0
	 */
	private static class NoBorderModifier implements BorderModifier {

		@Override
		public Border modify(Border border) {
			return null;
		}
	}

	@Override
	public void add(String id, UIValue<BorderModifier> uiValue) {
	}

	@Override
	public void remove(String id, UIValue<BorderModifier> uiValue) {
	}

	@Override
	public void set(String id, BorderModifier value,
			UIValue<BorderModifier> uiValue) {
		DisplayerDockBorder displayerDockBorder = (DisplayerDockBorder) uiValue;
		Dockable dockable = displayerDockBorder.getDisplayer().getDockable();
		if (dockable instanceof CommonDockable) {
			CDockable cdockable = ((CommonDockable) dockable).getDockable();
			if (cdockable.asStation() instanceof CWorkingArea) {
				value = new NoBorderModifier();
			}
		}
		uiValue.set(value);
	}
}
