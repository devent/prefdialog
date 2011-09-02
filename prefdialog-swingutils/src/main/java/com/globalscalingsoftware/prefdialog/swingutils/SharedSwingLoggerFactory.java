package com.globalscalingsoftware.prefdialog.swingutils;

import java.lang.reflect.Field;

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

		public void setTitle(String title, Object field) {
			log.debug("Set the title to ``{}'' for the field ``{}''.", title,
					field);
		}

		public void setWidth(double width, Object field) {
			log.debug("Set width to {} for the field ``{}''.", width, field);
		}

		public void setName(String name, Object field) {
			log.debug("Set name to ``{}'' for the field ``{}''.", name, field);
		}

		public void setReadOnly(boolean readonly, Object field) {
			log.debug("Set read only to {} for the field ``{}''.", readonly,
					field);
		}

		public void setEnabled(boolean enabled, Object field) {
			log.debug("Set enabled to {} for the field ``{}''.", enabled, field);
		}

		public void setValue(Object value, Object field) {
			log.debug("Set value to ``{}'' for the field ``{}''.", value, field);
		}

		public void setToolTipText(String text, Object field) {
			log.debug("Set tool-tip text to {} for the field ``{}''.", text,
					field);
		}

		public void setShowToolTip(boolean show, Object field) {
			log.debug("Set show tool-tip to {} for the field ``{}''.", show,
					field);
		}

		public void setShowTitle(boolean show, Object field) {
			log.debug("Set show title to {} for the field ``{}''.", show, field);
		}

		public void applyInput(Object value, Object field) {
			log.debug("Apply the input ``{}'' for the field.", value, field);
		}

		public void restoreInput(Object value, Object field) {
			log.debug("Restore the input ``{}'' for the field.", value, field);
		}

	}
}
