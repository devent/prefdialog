package com.globalscalingsoftware.prefdialog.panel.fields;

import java.lang.reflect.Field;

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
