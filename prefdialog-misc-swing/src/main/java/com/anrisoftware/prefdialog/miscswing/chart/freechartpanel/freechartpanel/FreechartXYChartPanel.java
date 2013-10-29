package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel;

import java.awt.Component;
import java.io.Serializable;

import javax.inject.Inject;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.chart.model.ChartModel;
import com.anrisoftware.prefdialog.miscswing.chart.model.ChartModelEvent;
import com.anrisoftware.prefdialog.miscswing.chart.model.ChartModelListener;

/**
 * {@code JFreeChart} X/Y line chart panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FreechartXYChartPanel implements Serializable {

	private transient ChartModelListener chartModelListener;

	private final UiGraphPanel panel;

	@Inject
	private GraphScrollModelFactory scrollModelFactory;

	private ChartModel model;

	private GraphScrollModel scrollModel;

	/**
	 * @see FreechartXYChartPanelFactory#create()
	 */
	@Inject
	@OnAwt
	FreechartXYChartPanel(UiGraphPanel panel) {
		this.panel = panel;
		resolveObject();
	}

	private Object resolveObject() {
		this.chartModelListener = new ChartModelListener() {

			@Override
			public void chartChanged(ChartModelEvent e) {
				int row0 = e.getFirstRow();
				int row1 = e.getLastRow();
				switch (e.getType()) {
				case INSERTED:
					updateInsertData(row0, row1);
					break;
				case DELETED:
					updateDeletedData(row0, row1);
					break;
				case UPDATED:
					updateData(row0, row1);
					break;
				}
			}
		};
		return this;
	}

	/**
	 * Returns the panel's component to be added in a container.
	 * 
	 * @return the {@link Component}.
	 */
	public Component getPanel() {
		return panel;
	}

	/**
	 * Sets the chart model.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param model
	 *            the {@link ChartModel}.
	 */
	@OnAwt
	public void setChartModel(ChartModel model) {
		removeOldModel(this.model);
		removeOldScrollModel(this.scrollModel);
		this.model = model;
		this.scrollModel = scrollModelFactory.create(model);
		model.addChartModelListener(chartModelListener);
		updateInsertData(0, model.getMaximumRowCount());
		panel.setGraphScrollModel(scrollModel);
	}

	private void removeOldScrollModel(GraphScrollModel model) {
		if (model != null) {
			model.uninstall();
		}
	}

	private void removeOldModel(ChartModel model) {
		if (model != null) {
			model.removeChartModelListener(chartModelListener);
		}
	}

	private void updateData(int row0, int row1) {
		XYSeriesCollection series = getCategory();
		int col0 = 0;
		int col1 = series.getSeriesCount() - 1;
		for (int col = col0; col <= col1; col++) {
			XYSeries xyseries = series.getSeries(col);
			for (int row = row0; row <= row1; row++) {
				xyseries.updateByIndex(row, model.getValueAt(row, col));
			}
		}
	}

	private void updateDeletedData(int row0, int row1) {
		XYSeriesCollection series = getCategory();
		for (int col = 0; col < series.getSeriesCount(); col++) {
			XYSeries xyseries = series.getSeries(col);
			for (int row = row1; row >= row0; row--) {
				xyseries.remove(row);
			}
		}
	}

	private void updateInsertData(int row0, int row1) {
		XYSeriesCollection series = getCategory();
		for (int col = 0; col < series.getSeriesCount(); col++) {
			XYSeries xyseries = series.getSeries(col);
			for (int row = row0; row <= row1; row++) {
				xyseries.add(row, model.getValueAt(row, col), false);
			}
			xyseries.fireSeriesChanged();
		}
	}

	private XYSeriesCollection getCategory() {
		return (XYSeriesCollection) panel.getChart().getXYPlot().getDataset();
	}

}
