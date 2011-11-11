package com.globalscalingsoftware.prefdialog.swingutils;

import java.lang.reflect.Field;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new swing-utils {@link Logger}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
interface LoggerFactory {

	/**
	 * Creates a new swing-utils {@link Logger} for the given {@link Class}.
	 */
	Logger create(@Assisted Class<?> clazz);

	/**
	 * Log messages for the swing-utils classes.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 2.1
	 */
	class Logger extends AbstractSwingLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		void setShowTitle(Field field, boolean show) {
			log.debug("Set show title to {} for the field {}.", show, field);
		}

		void setTitle(String title, Object field) {
			log.debug("Set the title to ``{}'' for the field {}.", title, field);
		}

		void setWidth(double width, Object field) {
			log.debug("Set width to {} for the field {}.", width, field);
		}

		void setName(String name, Object field) {
			log.debug("Set name to ``{}'' for the field {}.", name, field);
		}

		void setReadOnly(boolean readonly, Object field) {
			log.debug("Set read only to {} for the field {}.", readonly, field);
		}

		void setEnabled(boolean enabled, Object field) {
			log.debug("Set enabled to {} for the field {}.", enabled, field);
		}

		void setValue(Object value, Object field) {
			log.debug("Set value to ``{}'' for the field {}.", value, field);
		}

		void setToolTipText(String text, Object field) {
			log.debug("Set tool-tip text to ``{}'' for the field {}.", text,
					field);
		}

		void setShowToolTip(boolean show, Object field) {
			log.debug("Set show tool-tip to {} for the field {}.", show, field);
		}

		void setShowTitle(boolean show, Object field) {
			log.debug("Set show title to {} for the field {}.", show, field);
		}

		void applyInput(Object value, Object field) {
			log.debug("Apply the input ``{}'' for the field.", value, field);
		}

		void restoreInput(Object value, Object field) {
			log.debug("Restore the input ``{}'' for the field.", value, field);
		}

	}
}
