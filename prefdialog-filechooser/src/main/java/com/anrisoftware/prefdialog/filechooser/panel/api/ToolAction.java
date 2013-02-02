package com.anrisoftware.prefdialog.filechooser.panel.api;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.KeyStroke;

public interface ToolAction extends Action {

	public void setText(String text);

	public void setMnemonic(int mnemonic);

	public void setDisplayedMnemonicIndex(int index);

	public void setAcceleratorKey(KeyStroke stroke);

	public void setActionCommand(String command);

	public void setLongDescription(String description);

	public void setShortDescription(String description);

	public void setLargeIcon(Icon icon);

	public void setSmallIcon(Icon icon);

	boolean isToggleButton();

	boolean isSeparator();
}
