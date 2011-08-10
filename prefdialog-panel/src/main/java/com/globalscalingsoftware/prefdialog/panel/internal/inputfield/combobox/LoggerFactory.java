package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.combobox;

import java.lang.reflect.Field;
import java.util.Collection;

import javax.swing.ComboBoxModel;
import javax.swing.ListCellRenderer;

import com.globalscalingsoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

interface LoggerFactory {

	Logger create(@Assisted Class<?> clazz);

	class Logger extends AbstractSwingLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		public void setRenderer(Field field, ListCellRenderer renderer) {
			log.debug(
					"Set new list cell renderer ``{}'' for the field ``{}''.",
					renderer, field);
		}

		public void setModel(Field field, ComboBoxModel model) {
			log.debug("Set new combo box model ``{}'' for the field ``{}''.",
					model, field);
		}

		public void setValues(Field field, Collection<?> values) {
			log.debug("Set new values ``{}'' for the field ``{}''.", values,
					field);
		}

	}
}
