/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation;

import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedChangeListener.lockedChangeListener;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.COLUMN_INDEX_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MAXIMUM_COLUMN_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MINIMUM_COLUMN_PROPERTY;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SpinnerNumberModel;

import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedChangeListener;

@SuppressWarnings("serial")
class CurrentColumnModel extends SpinnerNumberModel {

	private final PropertyChangeListener maximumListener;

	private final PropertyChangeListener minimumListener;

	private final LockedChangeListener currentListener;

	private NavigationModel model;

	CurrentColumnModel() {
		this.maximumListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setMaximum((Comparable<?>) evt.getNewValue());
			}
		};
		this.minimumListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setMinimum((Comparable<?>) evt.getNewValue());
			}
		};
		this.currentListener = lockedChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setValue(evt.getNewValue());
			}
		});
	}

	public void setNavigation(NavigationModel model) {
		removeOldModel(this.model);
		this.model = model;
		setMaximum(model.getMaximumColumn());
		setMinimum(model.getMinimumColumn());
		setStepSize(1);
		setValue(model.getColumnIndex());
		addNewModel(model);
	}

	private void addNewModel(NavigationModel model) {
		model.addPropertyChangeListener(MAXIMUM_COLUMN_PROPERTY,
				maximumListener);
		model.addPropertyChangeListener(MINIMUM_COLUMN_PROPERTY,
				minimumListener);
		model.addPropertyChangeListener(COLUMN_INDEX_PROPERTY, currentListener);
	}

	private void removeOldModel(NavigationModel model) {
		if (model == null) {
			return;
		}
		model.removePropertyChangeListener(MAXIMUM_COLUMN_PROPERTY,
				maximumListener);
		model.removePropertyChangeListener(MINIMUM_COLUMN_PROPERTY,
				minimumListener);
		model.removePropertyChangeListener(COLUMN_INDEX_PROPERTY,
				currentListener);
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
		currentListener.lock();
		model.setColumnIndex(((Number) value).intValue());
		currentListener.unlock();
	}
}
