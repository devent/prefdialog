package com.anrisoftware.prefdialog.miscswing.multichart

import static com.anrisoftware.prefdialog.miscswing.toolbarmenu.ToolbarMenu.*

import org.ejml.simple.SimpleMatrix
import org.fest.swing.fixture.FrameFixture
import org.jfree.chart.ChartFactory
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.axis.ValueAxis
import org.jfree.chart.plot.XYPlot
import org.jfree.data.xy.XYSeriesCollection
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.data.DataModule
import com.anrisoftware.globalpom.data.DefaultDataBeanFactory
import com.anrisoftware.globalpom.data.MatrixDataFactory
import com.anrisoftware.globalpom.mnemonic.MnemonicModule
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.miscswing.colorpalette.ColorPaletteModule
import com.anrisoftware.prefdialog.miscswing.multichart.chart.AxisNegative
import com.anrisoftware.prefdialog.miscswing.multichart.chart.PlotOrientation
import com.anrisoftware.prefdialog.miscswing.multichart.columnnames.ChartColumnNamesModule
import com.anrisoftware.prefdialog.miscswing.multichart.databeanmodel.DataBeanChartModel
import com.anrisoftware.prefdialog.miscswing.multichart.databeanmodel.DataBeanChartModelFactory
import com.anrisoftware.prefdialog.miscswing.multichart.databeanmodel.DataBeanModelModule
import com.anrisoftware.prefdialog.miscswing.multichart.freechart.FreechartModule
import com.anrisoftware.prefdialog.miscswing.multichart.freechart.FreechartXYChartFactory
import com.anrisoftware.prefdialog.miscswing.multichart.multichartpanel.MultiChartPanel
import com.anrisoftware.prefdialog.miscswing.multichart.multichartpanel.MultiChartPanelFactory
import com.anrisoftware.prefdialog.miscswing.multichart.multichartpanel.MultiChartPanelModule
import com.anrisoftware.resources.images.api.IconSize
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see MultiChartPanel
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class MultiChartPanelTest {

    @Test
    void "set three graphs"() {
        MultiChartPanel panel
        def charts = []
        FrameFixture fix
        def testing = testingFactory.create title: "Multi-Chart", createComponent: { frame ->
            def chartTitle = "Chart 1"
            def jchart = createJChart(title: chartTitle)
            charts[0] = chartFactory.create chartTitle, jchart

            chartTitle = "Chart 2"
            jchart = createJChart(title: chartTitle)
            charts[1] = chartFactory.create chartTitle, jchart

            chartTitle = "Chart 3"
            jchart = createJChart(title: chartTitle)
            charts[2] = chartFactory.create chartTitle, jchart

            panel = panelFactory.create()
            panel.texts = texts
            panel.images = images
            panel.createPanel()

            panel.addChart charts[0]
            panel.addChart charts[1]
            panel.addChart charts[2]

            panel.getPanel()
        }
        testing().withFixture({ fix = it }, {
            panel.setIconsOnly true
            panel.setIconSize IconSize.SMALL
        })
    }

    @Test
    void "set three graphs data"() {
        MultiChartPanel panel
        def charts = []
        FrameFixture fix
        def testing = testingFactory.create title: "Multi-Chart", createComponent: { frame ->
            def chartTitle = "Chart 1"
            def jchart = createJChart(title: chartTitle)
            charts[0] = chartFactory.create chartTitle, jchart
            charts[0].setModel createDataBeanChartModel()

            chartTitle = "Chart 2"
            jchart = createJChart(title: chartTitle)
            charts[1] = chartFactory.create chartTitle, jchart
            charts[1].setModel createDataBeanChartModel()

            chartTitle = "Chart 3"
            jchart = createJChart(title: chartTitle)
            charts[2] = chartFactory.create chartTitle, jchart
            charts[2].setModel createDataBeanChartModel()

            panel = panelFactory.create()
            panel.texts = texts
            panel.images = images
            panel.createPanel()

            panel.addChart charts[0]
            panel.addChart charts[1]
            panel.addChart charts[2]

            panel.setIconsOnly true
            panel.setIconSize IconSize.SMALL
            panel.setPlotOrientation PlotOrientation.HORIZONTAL
            panel.setDomainAxisNegative AxisNegative.NEGATIVE
            panel.getPanel()
        }
        testing().withFixture({ fix = it }, {
            panel.setBlackWhite false
            panel.setShowShapes true
            panel.setAntiAliasing false
        }, {
            panel.setBlackWhite true
            panel.setShowShapes false
            panel.setAntiAliasing true
        }, {
            panel.setBlackWhite true
            panel.setShowShapes true
            panel.setAntiAliasing true
        })
    }

    static Injector injector

    static FrameTestingFactory testingFactory

    static MultiChartPanelFactory panelFactory

    static FreechartXYChartFactory chartFactory

    static DataBeanChartModelFactory dataBeanModelFactory

    static DefaultDataBeanFactory dataBeanFactory

    static MatrixDataFactory dataFactory

    static images

    static texts

    @BeforeClass
    static void createFactories() {
        injector = Guice.createInjector(
                new MultiChartPanelModule(),
                new FreechartModule(),
                new ChartColumnNamesModule(),
                new ColorPaletteModule(),
                new DataBeanModelModule(),
                new DataModule(),
                new MnemonicModule(),
                new ImagesResourcesModule(),
                new ResourcesImagesMapsModule(),
                new ResourcesSmoothScalingModule(),
                new TextsResourcesDefaultModule(),
                new FrameTestingModule())
        testingFactory = injector.getInstance FrameTestingFactory
        panelFactory = injector.getInstance MultiChartPanelFactory
        chartFactory = injector.getInstance FreechartXYChartFactory
        images = injector.getInstance(ImagesFactory).create("MultiChartPanelImages")
        texts = injector.getInstance(TextsFactory).create("MultiChartPanelTexts")
        dataBeanModelFactory = injector.getInstance DataBeanChartModelFactory
        dataBeanFactory = injector.getInstance DefaultDataBeanFactory
        dataFactory = injector.getInstance MatrixDataFactory
    }

    static JFreeChart createJChart(Map args) {
        String xAxisLabel = null;
        String yAxisLabel = null;
        XYSeriesCollection dataset = new XYSeriesCollection();
        org.jfree.chart.plot.PlotOrientation orientation = org.jfree.chart.plot.PlotOrientation.VERTICAL;
        boolean legend = true;
        boolean tooltips = true;
        boolean urls = false;
        JFreeChart chart = ChartFactory.createXYLineChart(args.chartTitle, xAxisLabel,
                yAxisLabel, dataset, orientation, legend, tooltips, urls);
        setupPlot(chart);
        integerTicks(chart);
        return chart
    }

    private static XYPlot setupPlot(JFreeChart chart) {
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        plot.setDomainZeroBaselineVisible(true);
        plot.setRangeZeroBaselineVisible(true);
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setAutoRange(true);
        return plot;
    }

    private static void integerTicks(JFreeChart chart) {
        XYPlot plot = (XYPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    }

    static DataBeanChartModel createDataBeanChartModel() {
        String[] columns = ["Data"]
        def data = dataBeanFactory.create dataFactory.create(SimpleMatrix.random(512, 1, 1, 100, new Random()).matrix)
        def model = dataBeanModelFactory.create data, columns
        return model
    }
}
