package com.anrisoftware.prefdialog.miscswing.multichart.multichartpanel;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

public interface MultiChartPanelFactory {

    @OnAwt
    MultiChartPanel create();
}
