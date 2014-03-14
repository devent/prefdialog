package com.anrisoftware.prefdialog.miscswing.multichart.freechart;

import java.text.NumberFormat;

import javax.inject.Inject;

import org.jfree.chart.axis.NumberTickUnit;

import com.google.inject.assistedinject.Assisted;

@SuppressWarnings("serial")
public final class DefaultOffsetNumberTickUnit extends NumberTickUnit implements
        OffsetTickUnit {

    private double offset;

    DefaultOffsetNumberTickUnit(double size, NumberFormat formatter, int minorTickCount) {
        super(size, formatter, minorTickCount);
    }

    DefaultOffsetNumberTickUnit(double size, NumberFormat formatter) {
        super(size, formatter);
    }

    /**
     * @see DefaultOffsetNumberTickUnitFactory#create(double)
     */
    @Inject
    DefaultOffsetNumberTickUnit(@Assisted double size) {
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
