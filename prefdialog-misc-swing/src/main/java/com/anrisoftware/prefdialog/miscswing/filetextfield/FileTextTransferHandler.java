/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.filetextfield;

import static java.awt.datatransfer.DataFlavor.stringFlavor;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.TransferHandler;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.resources.ConvertException;
import com.anrisoftware.globalpom.resources.StringToURI;
import com.google.inject.Inject;

/**
 * Supports the import a file or a URI string representation of a file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class FileTextTransferHandler extends TransferHandler {

	private final FileTextTransferHandlerLogger log;

	private final StringToURI stringToURI;

	@Inject
	FileTextTransferHandler(FileTextTransferHandlerLogger logger,
			StringToURI stringToURI) {
		this.log = logger;
		this.stringToURI = stringToURI;
	}

	@Override
	public int getSourceActions(JComponent c) {
		return NONE;
	}

	@Override
	public boolean canImport(TransferSupport support) {
		if (support.isDataFlavorSupported(stringFlavor)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean importData(TransferSupport support) {
		if (support.isDataFlavorSupported(stringFlavor)) {
			return importFile(support);
		}
		return false;
	}

	private boolean importFile(TransferSupport support) {
		JFormattedTextField field = (JFormattedTextField) support
				.getComponent();
		Transferable transferable = support.getTransferable();
		try {
			String string = (String) transferable.getTransferData(stringFlavor);
			File file = new File(stringToURI.convert(string));
			field.setValue(file);
			log.importFile(this, file);
		} catch (UnsupportedFlavorException e) {
		} catch (IOException e) {
			log.errorIO(this, e);
			return false;
		} catch (ConvertException e) {
			log.errorNotValidURISyntax(this, e);
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
