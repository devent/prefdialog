/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-spreadsheetimportdialog.
 *
 * prefdialog-spreadsheetimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-spreadsheetimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-spreadsheetimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.spreadsheetimportdialog.utils

import groovy.transform.CompileStatic

import javax.inject.Inject

import org.apache.commons.io.IOUtils

import com.anrisoftware.globalpom.data.DataModule
import com.anrisoftware.globalpom.spreadsheetimport.DefaultSpreadsheetImportPropertiesFactory
import com.anrisoftware.globalpom.spreadsheetimport.OpenDocumentImporterFactory
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporterModule
import com.anrisoftware.globalpom.utils.frametesting.DialogTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesModule
import com.anrisoftware.prefdialog.simpledialog.SimpleDialogModule
import com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog.SpreadsheetImportDialogFactory
import com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog.SpreadsheetImportDialogModule
import com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanelFactory
import com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel.SpreadsheetImportPanelModule
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties.SpreadsheetPanelPropertiesFactory
import com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpanel.SpreadsheetPreviewPanelFactory
import com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpanel.SpreadsheetPreviewPanelModule
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
    new SpreadsheetPreviewPanelModule(),
    new SpreadsheetImportDialogModule(),
    new DataModule(),
    new CoreFieldComponentModule(),
    new SimpleDialogModule(),
    new DockingFramesModule(),
    new TextsResourcesDefaultModule(),
    new ImagesResourcesModule(),
    new ResourcesImagesCachedMapModule(),
    new ResourcesSmoothScalingModule(),
    new FrameTestingModule())

    static final URL lottoOds = Dependencies.class.getResource("lotto_2001.ods")

    static final Locale locale = Locale.ENGLISH

    static File copyDocument(URL source, File dest) {
        IOUtils.copy source.openStream(), new FileOutputStream(dest)
        return dest
    }

    @Inject
    FrameTestingFactory frameTestingFactory

    @Inject
    DialogTestingFactory dialogTestingFactory

    @Inject
    TextsFactory extsFactory

    @Inject
    ImagesFactory imagesFactory

    @Inject
    DefaultSpreadsheetImportPropertiesFactory spreadsheetImportPropertiesFactory

    @Inject
    SpreadsheetPanelPropertiesFactory spreadsheetPanelPropertiesFactory

    @Inject
    SpreadsheetImportPanelFactory spreadsheetImportPanelFactory

    @Inject
    OpenDocumentImporterFactory importerFactory

    @Inject
    SpreadsheetPreviewPanelFactory spreadsheetPreviewPanelFactory

    @Inject
    SpreadsheetImportDialogFactory spreadsheetImportDialogFactory
}
