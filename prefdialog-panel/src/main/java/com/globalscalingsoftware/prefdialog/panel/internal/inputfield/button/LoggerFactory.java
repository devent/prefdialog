package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.button;

import com.globalscalingsoftware.prefdialog.annotations.HorizontalPosition;
import com.globalscalingsoftware.prefdialog.swingutils.internal.log.AbstractLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

interface LoggerFactory {

	Logger create(@Assisted Class<?> clazz);

	class Logger extends AbstractLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		public void setHorizontalPosition(HorizontalPosition position) {
			log.debug("Set horizontal position to ``{}''.", position);
		}

		public void setShowTitle(boolean show) {
			log.debug("Set show title to ``{}''.", show);
		}

	}
}
