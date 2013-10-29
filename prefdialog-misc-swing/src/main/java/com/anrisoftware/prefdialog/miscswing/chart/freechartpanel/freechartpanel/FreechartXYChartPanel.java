package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel;

import static java.lang.Math.round;

import java.awt.Component;
import java.io.Serializable;

import javax.inject.Inject;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
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
				System.out.println(e.getType());// TODO println
				System.out.printf("%d-%d%n", row0, row1); // TODO print
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
	public void setModel(ChartModel model) {
		removeOldModel(this.model);
		removeOldScrollModel(this.scrollModel);
		this.model = model;
		this.scrollModel = scrollModelFactory.create(model);
		setupNewModel();
		setAutoZoomDomain(true);
	}

	/**
	 * Returns the chart model.
	 * 
	 * @return the {@link ChartModel}.
	 */
	public ChartModel getModel() {
		return model;
	}

	/**
	 * Sets to use anti-aliasing in the data graph.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param antiAliasing
	 *            set to {@code true} to enable anti-aliasing.
	 */
	@OnAwt
	public void setAntiAliasing(boolean antiAliasing) {
		panel.getChart().setAntiAlias(antiAliasing);
	}

	/**
	 * Sets black/white or color data graph.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param blackWhite
	 *            set to {@code true} to enable black/white.
	 */
	@OnAwt
	public void setBlackWhite(boolean blackWhite) {
		panel.setBlackWhite(blackWhite);
	}

	/**
	 * Sets show shapes graph.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param showShapes
	 *            set to {@code true} to enable black/white.
	 */
	@OnAwt
	public void setShowShapes(boolean showShapes) {
		panel.setShapeGraph(showShapes);
	}

	/**
	 * Sets auto zooms of the domain axis.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param autoZoom
	 *            set to {@code true} for auto zoom.
	 */
	@OnAwt
	public void setAutoZoomDomain(boolean autoZoom) {
		if (!autoZoom) {
			return;
		}
		ChartModel model = getModel();
		ChartPanel panel = this.panel.getChartPanel();
		panel.restoreAutoDomainBounds();
		int size = model.getRowCount();
		model.setMaximum(size / 4);
	}

	/**
	 * Zooms the domain axis.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param factor
	 *            set to 1 to zoom in or -1 to zoom out the domain axis.
	 */
	@OnAwt
	public void setZoomDomain(int factor) {
		ChartModel model = getModel();
		int max = model.getMaximum();
		float zoom = factor < 0 ? 1.25f : 0.75f;
		max = round(max * zoom);
		model.setMaximum(max);
	}

	/**
	 * Sets the orientation of the chart.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param orientation
	 *            the {@link PlotOrientation}.
	 */
	@OnAwt
	public void setPlotOrientation(PlotOrientation orientation) {
		panel.setPlotOrientation(orientation);
	}

	private void setupNewModel() {
		model.addChartModelListener(chartModelListener);
		setupData(0, model.getMaximumRowCount());
		updateInsertData(0, model.getMaximumRowCount() - 1);
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

	private void setupData(int row0, int row1) {
		ChartModel model = this.model;
		int cols = model.getColumnCount();
		XYSeriesCollection dataset = getCategory();
		for (int i = 0; i < cols; i++) {
			XYSeries series = new XYSeries(model.getColumnName(i), false, false);
			dataset.addSeries(series);
		}
	}

	private void updateData(int row0, int row1) {
		ChartModel model = this.model;
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
		ChartModel model = this.model;
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
