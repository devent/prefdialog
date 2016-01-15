/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.comboboxhistory

import static com.anrisoftware.prefdialog.miscswing.comboboxhistory.ComboBoxHistoryModule.InstanceHolder.*

import org.junit.Test

/**
 * @see ItemDefault
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ItemDefaultTest {

    @Test
    void "equals"() {
        String item = "default A"
        def defitem = injector.getInstance(ItemDefaultFactory).create(item)
        assert item.hashCode() == defitem.hashCode()
        assert defitem.equals(item)
        assert !item.equals(defitem)
        assert defitem == item
        assert item != defitem
    }
}
