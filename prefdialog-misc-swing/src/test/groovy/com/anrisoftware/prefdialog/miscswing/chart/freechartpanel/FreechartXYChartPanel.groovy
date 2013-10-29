package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel

import static com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.LottoNumbersData.*

import javax.swing.JFrame

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.mnemonic.MnemonicModule
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.miscswing.chart.columnnames.ChartColumnNamesModule
import com.anrisoftware.prefdialog.miscswing.chart.datamodel.ChartDataModelModule
import com.anrisoftware.prefdialog.miscswing.chart.datamodel.DataChartModelFactory
import com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel.FreechartPanelModule
import com.anrisoftware.prefdialog.miscswing.chart.freechartpanel.freechartpanel.FreechartXYChartPanelFactory
import com.anrisoftware.prefdialog.miscswing.colorpalette.ColorPaletteModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see FreechartXYChartPanel
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FreechartXYChartPanel {

	@Test
	void "show panel"() {
		def bean = createDataBean LOTTO_NUMBERS
		def title = "$NAME/show"
		def model = modelFactory.create bean, [
			"0",
			"1",
			"2",
			"3",
			"4",
			"5",
			"6"] as String[]
		def testing = testingFactory.create([title: title, createComponent: { JFrame frame ->
				def panel = panelFactory.create()
				panel.setModel model
				panel.panel
			}])()
		testing.withFixture({Thread.sleep 60600})
	}

	static Injector injector

	static FreechartXYChartPanelFactory panelFactory

	static FrameTestingFactory testingFactory

	static DataChartModelFactory modelFactory

	static final String NAME = FreechartXYChartPanel.class.simpleName

	@BeforeClass
	static void createFactories() {
		injector = createInjector()
		testingFactory = injector.getInstance FrameTestingFactory
		panelFactory = injector.getInstance FreechartXYChartPanelFactory
		modelFactory = injector.getInstance DataChartModelFactory
	}

	static Injector createInjector() {
		Guice.createInjector(new FreechartPanelModule(),
				new ChartDataModelModule(),
				new ChartColumnNamesModule(),
				new ColorPaletteModule(),
				new MnemonicModule(),
				new FrameTestingModule())
	}
}
