/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-dialog.
 * 
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.simpledialog;

import static org.apache.commons.lang3.Validate.notNull;

import java.beans.PropertyChangeEvent;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link AbstractDialogAction}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
class AbstractDialogActionLogger extends AbstractLogger {

	private static final String CAN_CREATE = "Can create dialog for {}.";
	private static final String DIALOG_NULL = "Dialog was not created for %s.";
	private static final String NO_DIALOG = "No dialog set for {}.";
	private static final String NO_THREADS = "No threads set for {}.";
	private static final String NO_FRAME = "No parent frame set for {}.";
	private static final String ERROR_DIALOG = "Error while creating dialog: {}.";
	private static final String INTERRUPTED_DIALOG = "Interrupted while creating dialog.";

	/**
	 * Creates a logger for {@link AbstractDialogAction}.
	 */
	public AbstractDialogActionLogger() {
		super(AbstractDialogAction.class);
	}

	@SuppressWarnings("unchecked")
	<Dialog extends SimpleDialog> Dialog fromFuture(
			AbstractDialogAction<?, Dialog> action, PropertyChangeEvent evt) {
		try {
			return ((Future<Dialog>) evt.getSource()).get();
		} catch (InterruptedException e) {
			logException(e, INTERRUPTED_DIALOG);
			return null;
		} catch (ExecutionException e) {
			logException(e.getCause(), ERROR_DIALOG, e.getCause()
					.getLocalizedMessage());
			return null;
		}
	}

	boolean checkCanCreateDialog(AbstractDialogAction<?, ?> action) {
		if (action.getFrame() == null) {
			log.debug(NO_FRAME, action);
			return false;
		}
		if (action.getThreads() == null) {
			log.debug(NO_THREADS, action);
			return false;
		}
		if (action.getNotCreatedDialog() == null) {
			log.debug(NO_DIALOG, action);
			return false;
		}
		log.debug(CAN_CREATE, action);
		return true;
	}

	void checkDialogCreated(AbstractDialogAction<?, ?> action, Object dialog) {
		notNull(dialog, DIALOG_NULL, action);
	}

}
