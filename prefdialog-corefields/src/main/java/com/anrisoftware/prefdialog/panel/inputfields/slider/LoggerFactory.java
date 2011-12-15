package com.anrisoftware.prefdialog.panel.inputfields.slider;

import javax.swing.BoundedRangeModel;

import com.anrisoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new slider handler {@link Logger}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
interface LoggerFactory {

	/**
	 * Creates a new slider handler {@link Logger} for the given {@link Class}.
	 */
	Logger create(@Assisted Class<?> clazz);

	/**
	 * Log messages for the slider handler.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 2.1
	 */
	class Logger extends AbstractSwingLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		void setExtent(int extent, Object handler) {
			log.debug("Set the extent {} for the handler {}.", extent, handler);
		}

		void setModel(BoundedRangeModel model, Object handler) {
			log.debug("Set the model {} for the handler {}.", model, handler);
		}

		void setMin(int min, Object handler) {
			log.debug("Set the minimum value {} for the handler {}.", min,
					handler);
		}

		void setMax(int max, Object handler) {
			log.debug("Set the maximum value {} for the handler {}.", max,
					handler);
		}

		void setMajorTicks(int value, Object handler) {
			log.debug("Set the major ticks at {} for the handler {}.", value,
					handler);
		}

		void setMinorTicks(int value, Object handler) {
			log.debug("Set the minor ticks at {} for the handler {}.", value,
					handler);
		}

		void setPaintTicks(boolean value, Object handler) {
			log.debug("Set paint ticks to {} for the handler {}.", value,
					handler);
		}

		void setPaintLabels(boolean value, Object handler) {
			log.debug("Set paint labels to {} for the handler {}.", value,
					handler);
		}

		void setPaintTrack(boolean value, Object handler) {
			log.debug("Set paint track to {} for the handler {}.", value,
					handler);
		}

		void setSnapToTicks(boolean value, Object handler) {
			log.debug("Set snap to ticks to {} for the handler {}.", value,
					handler);
		}

	}
}
