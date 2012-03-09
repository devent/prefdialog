package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
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

@SuppressWarnings("serial")
class FileTextTransferHandler extends TransferHandler {

	private final Logger log;

	@Inject
	FileTextTransferHandler(FileTextTransferHandlerLoggerFactory loggerFactory) {
		this.log = loggerFactory.create();
	}

	@Override
	public int getSourceActions(JComponent c) {
		return COPY;
	}

	@Override
	public boolean canImport(TransferSupport support) {
		if (support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			return true;
		}
		if (support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
			return true;
		}
		return false;
	}

	@Override
	protected Transferable createTransferable(JComponent c) {
		JFormattedTextField field = (JFormattedTextField) c;
		File file = (File) field.getValue();
		URI uri = file.toURI();
		return new StringSelection(uri.toString());
	}

	@Override
	public boolean importData(TransferSupport support) {
		if (support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			return importFile(support);
		}
		return true;
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
