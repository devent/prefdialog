package com.anrisoftware.prefdialog.filechooser.panel.defaults.fileview;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.FileChooserUI;
import javax.swing.plaf.basic.BasicFileChooserUI;

/**
 * Factory to create a default file view.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class DefaultFileViewFactory {

	/**
	 * Creates a default file view from the {@link FileChooserUI}.
	 * 
	 * @return the default {@link FileView}.
	 */
	public static FileView createDefaultFileView() {
		JFileChooser chooser = new JFileChooser();
		FileChooserUI ui = (FileChooserUI) BasicFileChooserUI.createUI(chooser);
		ui.installUI(chooser);
		return ui.getFileView(chooser);
	}

}
