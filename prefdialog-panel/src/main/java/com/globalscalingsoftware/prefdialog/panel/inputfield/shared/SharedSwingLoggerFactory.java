package com.globalscalingsoftware.prefdialog.panel.inputfield.shared;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public interface SharedSwingLoggerFactory {

	SharedSwingLogger create(@Assisted Class<?> clazz);

	public class SharedSwingLogger extends AbstractSwingLogger {

		@Inject
		protected SharedSwingLogger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		public void setShowTitle(Field field, boolean show) {
			log.debug("Set show title to ``{}'' for the field ``{}''.", show,
					field);
		}

		public void setTitle(Field field, String title) {
			log.debug("Set the title to ``{}'' for the field ``{}''.", title,
					field);
		}

	}
}
