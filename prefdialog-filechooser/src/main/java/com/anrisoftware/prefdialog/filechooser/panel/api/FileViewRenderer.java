package com.anrisoftware.prefdialog.filechooser.panel.api;

import javax.swing.ListCellRenderer;

public interface FileViewRenderer<E> extends ListCellRenderer<E> {

	int getLayoutOrientation();

	int getVisibleRowCount();
}
