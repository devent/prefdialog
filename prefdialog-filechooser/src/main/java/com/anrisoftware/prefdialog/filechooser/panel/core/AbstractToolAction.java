package com.anrisoftware.prefdialog.filechooser.panel.core;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.KeyStroke;

import com.anrisoftware.prefdialog.filechooser.panel.api.DirectoyModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileSelectionModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolAction;

@SuppressWarnings("serial")
public abstract class AbstractToolAction extends AbstractAction implements
		ToolAction {

	protected FileModel fileModel;

	protected DirectoyModel directoyModel;

	protected FileSelectionModel fileSelectionModel;

	@Override
	public void setFileModel(FileModel model) {
		this.fileModel = model;
	}

	@Override
	public void setFileSelectionModel(FileSelectionModel model) {
		this.fileSelectionModel = model;
	}

	@Override
	public void setDirectoryModel(DirectoyModel model) {
		this.directoyModel = model;
	}

	@Override
	public void setText(String text) {
		putValue(NAME, text);
	}

	@Override
	public void setMnemonic(int mnemonic) {
		putValue(MNEMONIC_KEY, mnemonic);
	}

	@Override
	public void setDisplayedMnemonicIndex(int index) {
		putValue(DISPLAYED_MNEMONIC_INDEX_KEY, index);
	}

	@Override
	public void setAcceleratorKey(KeyStroke stroke) {
		putValue(ACCELERATOR_KEY, stroke);
	}

	@Override
	public void setActionCommand(String command) {
		putValue(ACTION_COMMAND_KEY, command);
	}

	@Override
	public void setLongDescription(String description) {
		putValue(LONG_DESCRIPTION, description);
	}

	@Override
	public void setShortDescription(String description) {
		putValue(SHORT_DESCRIPTION, description);
	}

	@Override
	public void setLargeIcon(Icon icon) {
		putValue(LARGE_ICON_KEY, icon);
	}

	@Override
	public void setSmallIcon(Icon icon) {
		putValue(SMALL_ICON, icon);
	}

	public abstract String getImageResource();

}
