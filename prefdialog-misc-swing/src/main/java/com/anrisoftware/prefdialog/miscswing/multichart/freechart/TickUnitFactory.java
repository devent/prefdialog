package com.anrisoftware.prefdialog.miscswing.multichart.freechart;

import com.anrisoftware.prefdialog.miscswing.multichart.model.ChartModel;

public interface TickUnitFactory {

    OffsetTickUnit create(ChartModel model, double size);
}
