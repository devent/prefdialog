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

import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedPropertyChangeListener.lockedPropertyChangeListener;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MAXIMUM_ROW_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.MINIMUM_ROW_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation.NavigationModel.Property.ROW_INDEX_PROPERTY;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SpinnerNumberModel;

import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedPropertyChangeListener;

@SuppressWarnings("serial")
class CurrentRowModel extends SpinnerNumberModel {

	private final PropertyChangeListener maximumListener;

	private final PropertyChangeListener minimumListener;

	private final LockedPropertyChangeListener currentListener;

	private NavigationModel model;

	CurrentRowModel() {
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
		this.currentListener = lockedPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				setValue(evt.getNewValue());
			}
		});
	}

	public void setNavigation(NavigationModel model) {
		removeOldModel(this.model);
		this.model = model;
		setMaximum(model.getMaximumRow());
		setMinimum(model.getMinimumRow());
		setStepSize(1);
		setValue(model.getRowIndex());
		addNewModel(model);
	}

	private void addNewModel(NavigationModel model) {
		model.addPropertyChangeListener(MAXIMUM_ROW_PROPERTY, maximumListener);
		model.addPropertyChangeListener(MINIMUM_ROW_PROPERTY, minimumListener);
		model.addPropertyChangeListener(ROW_INDEX_PROPERTY, currentListener);
	}

	private void removeOldModel(NavigationModel model) {
		if (model == null) {
			return;
		}
		model.removePropertyChangeListener(MAXIMUM_ROW_PROPERTY,
				maximumListener);
		model.removePropertyChangeListener(MINIMUM_ROW_PROPERTY,
				minimumListener);
		model.removePropertyChangeListener(ROW_INDEX_PROPERTY, currentListener);
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
		currentListener.lock();
		model.setRowIndex(((Number) value).intValue());
		currentListener.unlock();
	}
}
