/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import java.util.Collection;

import javax.swing.MutableComboBoxModel;

/**
 * Factory to decorate a model as the history combo box model.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface HistoryComboBoxModelFactory {

    /**
     * Decorate a model as the history combo box model.
     *
     * @param model
     *            the parent {@link MutableComboBoxModel}.
     *
     * @param defaultItems
     *            {@link Collection} of default items for the model. The default
     *            items can not be removed.
     *
     * @return the {@link HistoryComboBoxModel}
     */
    @SuppressWarnings("rawtypes")
    HistoryComboBoxModel create(MutableComboBoxModel model,
            Collection defaultItems);
}
