package com.anrisoftware.prefdialog.swingutils;

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

		void setWidth(double width, Object handler) {
			log.debug("Set width to {} for the handler {}.", width, handler);
		}

		void setName(String name, Object handler) {
			log.debug("Set name to ``{}'' for the handler {}.", name, handler);
		}

		void setReadOnly(boolean readonly, Object handler) {
			log.debug("Set read only to {} for the handler {}.", readonly,
					handler);
		}

		void setEnabled(boolean enabled, Object handler) {
			log.debug("Set enabled to {} for the handler {}.", enabled, handler);
		}

		void setValue(Object value, Object handler) {
			log.debug("Set value to ``{}'' for the handler {}.", value, handler);
		}

		void setToolTipText(String text, Object handler) {
			log.debug("Set tool-tip text to ``{}'' for the handler {}.", text,
					handler);
		}

		void setShowToolTip(boolean show, Object handler) {
			log.debug("Set show tool-tip to {} for the handler {}.", show,
					handler);
		}

		void setShowTitle(boolean show, Object handler) {
			log.debug("Set show title to {} for the handler {}.", show, handler);
		}

		void setTitle(String title, Object handler) {
			log.debug("Set the title to ``{}'' for the handler {}.", title,
					handler);
		}

		void applyInput(Object value, Object handler) {
			log.debug("Apply the input ``{}'' for the handler.", value, handler);
		}

		void restoreInput(Object value, Object handler) {
			log.debug("Restore the input ``{}'' for the handler.", value,
					handler);
		}

	}
}
