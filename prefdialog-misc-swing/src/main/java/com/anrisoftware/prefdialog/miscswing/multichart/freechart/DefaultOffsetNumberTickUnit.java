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
package com.anrisoftware.prefdialog.miscswing.multichart.freechart;

import java.text.NumberFormat;

import javax.inject.Inject;

import org.jfree.chart.axis.NumberTickUnit;

import com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModel;
import com.google.inject.assistedinject.Assisted;

@SuppressWarnings("serial")
public final class DefaultOffsetNumberTickUnit extends NumberTickUnit implements
        OffsetTickUnit {

    private double offset;

    public DefaultOffsetNumberTickUnit(double size, NumberFormat formatter,
            int minorTickCount) {
        super(size, formatter, minorTickCount);
    }

    public DefaultOffsetNumberTickUnit(double size, NumberFormat formatter) {
        super(size, formatter);
    }

    /**
     * @see DefaultOffsetNumberTickUnitFactory#create(ChartModel, double)
     */
    @Inject
    DefaultOffsetNumberTickUnit(@Assisted ChartModel model,
            @Assisted double size) {
        super(size);
    }

    @Override
    public void setOffset(double offset) {
        this.offset = offset;
    }

    @Override
    public String valueToString(double value) {
        return super.valueToString(value + offset);
    }
}
