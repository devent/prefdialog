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

	private static final String DIALOG_NULL = "Dialog was not created for %s.";
	private static final String NO_DIALOG = "No dialog set for {}.";
	private static final String NO_THREADS = "No threads set for {}.";
	private static final String NO_FRAME = "No parent frame set for {}.";
	private static final String ERROR_DIALOG = "Error while creating CSV import dialog: {}.";
	private static final String INTERRUPTED_DIALOG = "Interrupted while creating CSV import dialog.";

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

	boolean checkFrame(AbstractDialogAction<?, ?> action, Object frame) {
		if (frame == null) {
			log.debug(NO_FRAME, action);
			return false;
		}
		return true;
	}

	boolean checkThreads(AbstractDialogAction<?, ?> action, Object threads) {
		if (threads == null) {
			log.debug(NO_THREADS, action);
			return false;
		}
		return true;
	}

	boolean checkDialog(AbstractDialogAction<?, ?> action, Object dialog) {
		if (dialog == null) {
			log.debug(NO_DIALOG, action);
			return false;
		}
		return true;
	}

	void checkDialogCreated(AbstractDialogAction<?, ?> action, Object dialog) {
		notNull(dialog, DIALOG_NULL, action);
	}

}
