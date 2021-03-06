/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-lookfeelpanel.
 * 
 * prefdialog-lookfeelpanel is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * 
 * prefdialog-lookfeelpanel is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-lookfeelpanel. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel;

import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Asserts that two {@link LookAndFeelInfo}s with the same name are treated
 * equals.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class LookAndFeelInfoListItem {

	private final LookAndFeelInfo lookAndFeelInfo;

	/**
	 * Sets the {@link LookAndFeelInfo} for this item.
	 */
	public LookAndFeelInfoListItem(LookAndFeelInfo lookAndFeelInfo) {
		this.lookAndFeelInfo = lookAndFeelInfo;
	}

	/**
	 * Returns the {@link LookAndFeelInfo} of this item.
	 */
	public LookAndFeelInfo getLookAndFeelInfo() {
		return lookAndFeelInfo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LookAndFeelInfoListItem) {
			return equals((LookAndFeelInfoListItem) obj);

		}
		return false;
	}

	private boolean equals(LookAndFeelInfoListItem that) {
		return new EqualsBuilder().append(lookAndFeelInfo.getName(),
				that.lookAndFeelInfo.getName()).isEquals();
	}

	@Override
	public int hashCode() {
		return lookAndFeelInfo.getName().hashCode();
	}
}
