package com.anrisoftware.prefdialog.filechooser.panel.api;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.KeyStroke;

public interface ToolAction extends Action {

	String getName();

	void setFileModel(FileModel model);

	void setFileSelectionModel(FileSelectionModel model);

	void setDirectoryModel(DirectoyModel model);

	void setText(String text);

	void setMnemonic(int mnemonic);

	void setDisplayedMnemonicIndex(int index);

	void setAcceleratorKey(KeyStroke stroke);

	void setActionCommand(String command);

	void setLongDescription(String description);

	void setShortDescription(String description);

	void setLargeIcon(Icon icon);

	void setSmallIcon(Icon icon);

	boolean isToggleButton();

	boolean isSeparator();

}
