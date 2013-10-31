package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel;

import static com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel.GraphScrollModel.Property.VALUE_PROPERTY;
import static java.lang.Math.round;
import static javax.swing.SwingUtilities.invokeLater;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

import javax.inject.Inject;

import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.anrisoftware.globalpom.textposition.TextPosition;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.chart.model.AbstractChartModel;
import com.anrisoftware.prefdialog.miscswing.chart.model.AxisNegative;
import com.anrisoftware.prefdialog.miscswing.chart.model.ChartModel;
import com.anrisoftware.prefdialog.miscswing.chart.model.ChartModelEvent;
import com.anrisoftware.prefdialog.miscswing.chart.model.ChartModelListener;
import com.anrisoftware.prefdialog.miscswing.chart.model.PlotOrientation;
import com.anrisoftware.resources.images.api.IconSize;

/**
 * {@code JFreeChart} X/Y line chart panel.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FreechartXYChartPanel implements Serializable {

	private static final String SWING_VALUE_PROPERTY = "value";

	private transient ChartModelListener chartModelListener;

	@Inject
	private FreechartXYChartPanelLogger log;

	@Inject
	private GraphScrollModelFactory scrollModelFactory;

	private UiGraphPanel panel;

	private ChartModel model;

	private GraphScrollModel scrollModel;

	private PropertyChangeListener rangeValueListener;

	private PropertyChangeListener viewScrollValueListener;

	private PlotOrientation orientation;

	private int negativeFactor;

	private AxisNegative negative;

	/**
	 * @see FreechartXYChartPanelFactory#create()
	 */
	@OnAwt
	FreechartXYChartPanel() {
		resolveObject();
	}

	private Object resolveObject() {
		if (negative == null) {
			negative = AxisNegative.POSITIVE;
		}
		if (orientation == null) {
			orientation = PlotOrientation.VERTICAL;
		}
		this.chartModelListener = new ChartModelListener() {

			@Override
			public void chartChanged(ChartModelEvent e) {
				int row0 = e.getFirstRow();
				int row1 = e.getLastRow();
				int offset = e.getOffset();
				log.chartChanged(FreechartXYChartPanel.this, e);
				switch (e.getType()) {
				case INSERTED:
					updateInsertData(row0, row1, offset);
					break;
				case DELETED:
					updateDeletedData(row0, row1, offset);
					break;
				case UPDATED:
					updateData(row0, row1, offset);
					break;
				}
			}
		};
		this.rangeValueListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
				invokeLater(new Runnable() {

					@Override
					public void run() {
						setViewMaximum((Integer) evt.getNewValue());
					}

				});
			}
		};
		this.viewScrollValueListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
				invokeLater(new Runnable() {

					@Override
					public void run() {
						setOffset((Integer) evt.getNewValue());
					}

				});
			}
		};
		return this;
	}

	@Inject
	@OnAwt
	void setUiGraphPanel(UiGraphPanel panel) {
		this.panel = panel;
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
	 * @see ChartModel#setViewMaximum(int)
	 */
	@OnAwt
	public void setViewMaximum(int max) {
		model.setViewMaximum(max);
		scrollModel.setViewMaximum(max);
	}

	/**
	 * @see ChartModel#setOffset(int)
	 */
	@OnAwt
	public void setOffset(int offset) {
		model.setOffset(offset);
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
		setViewMaximum(size / 4);
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
		int max = model.getViewMaximum();
		float zoom = factor < 0 ? 1.25f : 0.75f;
		max = round(max * zoom);
		setViewMaximum(max);
	}

	/**
	 * Sets the icon size of the tool-bar buttons.
	 *
	 * @param size
	 *            the {@link IconSize}.
	 */
	@OnAwt
	public void setIconSize(IconSize size) {
		// TODO Auto-generated method stub

	}

	public void setTextPosition(TextPosition position) {
		// TODO Auto-generated method stub

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
		PlotOrientation oldValue = this.orientation;
		if (oldValue == orientation) {
			return;
		}
		panel.setPlotOrientation(orientation);
		this.orientation = orientation;
	}

	/**
	 * Sets the domain axis to be negative.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param negative
	 *            the {@link AxisNegative}.
	 */
	@OnAwt
	public void setDomainAxisNegative(AxisNegative negative) {
		AxisNegative oldValue = this.negative;
		if (oldValue == negative) {
			return;
		}
		switch (negative) {
		case NEGATIVE:
			this.negativeFactor = -1;
			break;
		case POSITIVE:
			this.negativeFactor = 1;
			break;
		}
		this.negative = negative;
		fireChartChanged();
	}

	private void setupNewModel() {
		model.addChartModelListener(chartModelListener);
		setupData(0, model.getMaximumRowCount());
		updateInsertData(0, model.getMaximumRowCount() - 1, 0);
		panel.setGraphScrollModel(scrollModel);
		panel.getRangeField().addPropertyChangeListener(SWING_VALUE_PROPERTY,
				rangeValueListener);
		scrollModel.addPropertyChangeListener(VALUE_PROPERTY,
				viewScrollValueListener);
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

	private void updateData(int row0, int row1, int offset) {
		ChartModel model = this.model;
		XYSeriesCollection series = getCategory();
		int col0 = 0;
		int col1 = series.getSeriesCount() - 1;
		for (int col = col0; col <= col1; col++) {
			XYSeries xyseries = series.getSeries(col);
			for (int row = row0; row <= row1; row++) {
				double value = model.getValueAt(row + offset, col);
				xyseries.updateByIndex(row, value);
			}
		}
	}

	private void updateDeletedData(int row0, int row1, int offset) {
		XYSeriesCollection series = getCategory();
		for (int col = 0; col < series.getSeriesCount(); col++) {
			XYSeries xyseries = series.getSeries(col);
			for (int row = row1; row >= row0; row--) {
				xyseries.remove(row);
			}
		}
	}

	private void updateInsertData(int row0, int row1, int offset) {
		ChartModel model = this.model;
		int x;
		XYSeriesCollection series = getCategory();
		for (int col = 0; col < series.getSeriesCount(); col++) {
			XYSeries xyseries = series.getSeries(col);
			for (int row = row0; row <= row1; row++) {
				x = row * negativeFactor;
				xyseries.add(x, model.getValueAt(row, col), false);
			}
			xyseries.fireSeriesChanged();
		}
	}

	private XYSeriesCollection getCategory() {
		return (XYSeriesCollection) panel.getChart().getXYPlot().getDataset();
	}

	private void fireChartChanged() {
		if (model instanceof AbstractChartModel) {
			ChartModelEvent e = new ChartModelEvent(model);
			((AbstractChartModel) model).fireChartChanged(e);
		}
	}

}
