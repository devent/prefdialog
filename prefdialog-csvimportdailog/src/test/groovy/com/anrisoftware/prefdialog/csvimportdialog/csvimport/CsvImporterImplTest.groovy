package com.anrisoftware.prefdialog.csvimportdialog.csvimport

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImporterFactory
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see CsvImporterImpl
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class CsvImporterImplTest {

	@Test
	void "import csv"() {
		def prop = new DefaultCsvImportProperties()
		prop.setFile lottoFile
		def importer = factory.create(prop)
		importer()
		assert importer.getValues().toString() == "[Tag, Monat, Jahr, Zahl1, Zahl2, Zahl3, Zahl4, Zahl5, Zahl6, Zusatz, Super]"
		importer()
		assert importer.getValues().toString() == "[3, 1, 2001, 46, 13, 21, 34, 19, 36, 38, 2]"
	}

	@Test
	void "import csv, skip head"() {
		def prop = new DefaultCsvImportProperties()
		prop.setFile lottoFile
		prop.setStartRow 1

		def importer = factory.create(prop)
		importer()
		assert importer.getValues().toString() == "[3, 1, 2001, 46, 13, 21, 34, 19, 36, 38, 2]"
		importer()
		assert importer.getValues().toString() == "[6, 1, 2001, 17, 42, 12, 8, 37, 26, 31, 6]"
	}

	static Injector injector

	static CsvImporterFactory factory

	static URI lottoFile = CsvImporterImplTest.class.getResource("lotto_2001.csv").toURI()

	@BeforeClass
	static void createFactory() {
		TestUtils.toStringStyle
		injector = Guice.createInjector(new CsvImportModule())
		factory = injector.getInstance(CsvImporterFactory)
	}
}
