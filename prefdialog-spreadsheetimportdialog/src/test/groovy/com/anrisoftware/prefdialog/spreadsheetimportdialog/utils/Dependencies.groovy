package com.anrisoftware.prefdialog.spreadsheetimportdialog.utils

import groovy.transform.CompileStatic

import javax.inject.Inject

import com.anrisoftware.globalpom.spreadsheetimport.OpenDocumentImporterFactory
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporterModule
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanelFactory
import com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanelModule
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties.SpreadsheetPanelPropertiesFactory
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.mapcached.ResourcesImagesCachedMapModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Test dependencies.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
@CompileStatic
class Dependencies {

    static Injector injector = Guice.createInjector(
    new SpreadsheetImportPanelModule(),
    new SpreadsheetImporterModule(),
    new CoreFieldComponentModule(),
    new TextsResourcesDefaultModule(),
    new ImagesResourcesModule(),
    new ResourcesImagesCachedMapModule(),
    new ResourcesSmoothScalingModule(),
    new FrameTestingModule())

    static final URL lottoOds = Dependencies.class.getResource("lotto_2001.ods")

    @Inject
    FrameTestingFactory frameTestingFactory

    @Inject
    TextsFactory extsFactory

    @Inject
    ImagesFactory imagesFactory

    @Inject
    SpreadsheetPanelPropertiesFactory spreadsheetPanelPropertiesFactory

    @Inject
    SpreadsheetImportPanelFactory spreadsheetImportPanelFactory

    @Inject
    OpenDocumentImporterFactory importerFactory
}
