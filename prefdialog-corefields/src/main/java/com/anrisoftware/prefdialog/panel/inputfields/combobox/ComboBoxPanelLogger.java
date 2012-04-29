package com.anrisoftware.prefdialog.panel.inputfields.combobox;

import java.util.Collection;

import javax.swing.ComboBoxModel;
import javax.swing.ListCellRenderer;

import com.anrisoftware.prefdialog.swingutils.AbstractSwingLogger;

/**
 * Logger messages for the {@link ComboBoxPanel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ComboBoxPanelLogger extends AbstractSwingLogger {

	ComboBoxPanelLogger() {
		super(ComboBoxPanel.class);
	}

	void rendererSet(ComboBoxPanel panel, ListCellRenderer renderer) {
		log.debug("Set list cell renderer {} for the panel {}.", renderer,
				panel);
	}

	void modelSet(ComboBoxPanel panel, ComboBoxModel model) {
		log.debug("Set combo box model {} for the handler {}.", model, panel);
	}

	void valuesSet(ComboBoxPanel handler, Collection<?> values) {
		log.debug("Set values {} for the handler {}.", values, handler);
	}

}
