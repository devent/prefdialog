package com.globalscalingsoftware.prefdialog.panel.inputfield.slider;

import java.lang.reflect.Field;

import javax.swing.BoundedRangeModel;

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

		public void setExtent(Field field, int extent) {
			log.debug("Set the extent {} for the field ``{}''.", extent, field);
		}

		public void setModel(Field field, BoundedRangeModel model) {
			log.debug("Set the model {} for the field ``{}''.", model, field);
		}

		public void setMin(Field field, int min) {
			log.debug("Set the minimum value {} for the field ``{}''.", min,
					field);
		}

		public void setMax(Field field, int max) {
			log.debug("Set the maximum value {} for the field ``{}''.", max,
					field);
		}

		public void setMajorTicks(Field field, int value) {
			log.debug("Set the major ticks at {} for the field ``{}''.", value,
					field);
		}

		public void setMinorTicks(Field field, int value) {
			log.debug("Set the minor ticks at {} for the field ``{}''.", value,
					field);
		}

		public void setPaintTicks(Field field, boolean value) {
			log.debug("Set paint ticks to {} for the field ``{}''.", value,
					field);
		}

		public void setPaintLabels(Field field, boolean value) {
			log.debug("Set paint labels to {} for the field ``{}''.", value,
					field);
		}

		public void setPaintTrack(Field field, boolean value) {
			log.debug("Set paint track to {} for the field ``{}''.", value,
					field);
		}

		public void setSnapToTicks(Field field, boolean value) {
			log.debug("Set snap to ticks to {} for the field ``{}''.", value,
					field);
		}

	}
}
