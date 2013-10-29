package com.anrisoftware.prefdialog.miscswing.chart.freechartpanel

import com.anrisoftware.globalpom.data.DataBean
import com.anrisoftware.globalpom.data.DataModule
import com.anrisoftware.globalpom.data.DefaultDataBeanFactory
import com.anrisoftware.globalpom.data.MatrixDataCsvImportFactory
import com.anrisoftware.globalpom.dataimport.CsvImportModule
import com.anrisoftware.globalpom.dataimport.CsvImporterFactory
import com.anrisoftware.globalpom.dataimport.DefaultCsvImportPropertiesFactory
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Lotto numbers data.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class LottoNumbersData {

	static LOTTO_NUMBERS = LottoNumbersData.class.getResource("/data/lotto_2001_onlynumbers.csv")

	private static class SingletonHolder {

		static final Injector injector = Guice.createInjector(new CsvImportModule(), new DataModule())

		static final DefaultCsvImportPropertiesFactory propertiesFactory = injector.getInstance(DefaultCsvImportPropertiesFactory)

		static final DefaultDataBeanFactory beanFactory = injector.getInstance(DefaultDataBeanFactory)

		static final CsvImporterFactory importerFactory = injector.getInstance(CsvImporterFactory)

		static final MatrixDataCsvImportFactory dataImportFactory = injector.getInstance(MatrixDataCsvImportFactory)
	}

	public static DataBean createDataBean(URL resource) {
		def properties = SingletonHolder.propertiesFactory.create()
		properties.setFile resource.toURI()
		properties.setSeparator ';' as char
		properties.setNumCols 7
		def importer = SingletonHolder.importerFactory.create properties
		def data = SingletonHolder.dataImportFactory.create(importer)()
		SingletonHolder.beanFactory.create(data)
	}
}
