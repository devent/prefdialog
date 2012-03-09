/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.TransferHandler;

import com.anrisoftware.prefdialog.panel.inputfields.filechooser.FileTextTransferHandlerLoggerFactory.Logger;
import com.google.inject.Inject;

/**
 * Supports the import a URI string representation of a file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
@SuppressWarnings("serial")
class FileTextTransferHandler extends TransferHandler {

	private final Logger log;

	@Inject
	FileTextTransferHandler(FileTextTransferHandlerLoggerFactory loggerFactory) {
		this.log = loggerFactory.create();
	}

	@Override
	public int getSourceActions(JComponent c) {
		return NONE;
	}

	@Override
	public boolean canImport(TransferSupport support) {
		if (support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean importData(TransferSupport support) {
		if (support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			return importFile(support);
		}
		return false;
	}

	private boolean importFile(TransferSupport support) {
		JFormattedTextField field = (JFormattedTextField) support
				.getComponent();
		Transferable transferable = support.getTransferable();
		try {
			String string = (String) transferable
					.getTransferData(DataFlavor.stringFlavor);
			File file = new File(new URI(string));
			field.setValue(file);
			log.importFile(this, file);
		} catch (UnsupportedFlavorException e) {
		} catch (IOException e) {
			log.errorIO(this, e);
			return false;
		} catch (URISyntaxException e) {
			log.errorNotValidURISyntax(this, e);
			return false;
		}
		return true;
	}
}
