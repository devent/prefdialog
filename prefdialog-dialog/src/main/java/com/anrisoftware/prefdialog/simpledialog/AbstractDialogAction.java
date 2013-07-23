package com.anrisoftware.prefdialog.simpledialog;

import static com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status.CANCELED;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.threads.api.Threads;

/**
 * Creates a simple dialog in a background thread, opens it and waits for the
 * dialog to be canceled or approved. The dialog is only created if all
 * dependencies are set.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public abstract class AbstractDialogAction<Value, Dialog extends SimpleDialog>
		implements Callable<Value> {

	private AbstractDialogActionLogger log;

	private final Runnable openDialog;

	private JFrame frame;

	private Dialog createdDialog;

	private SwingWorker<Value, Runnable> worker;

	private Threads threads;

	private Dialog dialog;

	private boolean creatingDialog;

	protected AbstractDialogAction() {
		this.creatingDialog = false;
		this.openDialog = new Runnable() {

			@Override
			public void run() {
				openDialog();
			}

		};
	}

	/**
	 * Injects the logger.
	 * 
	 * @param logger
	 *            the {@link AbstractDialogActionLogger}.
	 */
	@Inject
	void setAbstractDialogActionLogger(AbstractDialogActionLogger logger) {
		this.log = logger;
		this.createdDialog = null;
	}

	/**
	 * Sets the threads to create the dialog in the background.
	 * 
	 * @param threads
	 *            the {@link Threads}.
	 */
	public void setThreads(Threads threads) {
		this.threads = threads;
		createDialog();
	}

	/**
	 * Returns the threads to create the dialog in the background.
	 * 
	 * @return the {@link Threads}.
	 */
	public Threads getThreads() {
		return threads;
	}

	/**
	 * Sets the parent frame for the dialog.
	 * 
	 * @param frame
	 *            the {@link JFrame} parent.
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
		createDialog();
	}

	/**
	 * Returns the parent frame for the dialog.
	 * 
	 * @return the {@link JFrame} parent.
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Sets the dialog that should be created.
	 * 
	 * @param dialog
	 *            the {@link SimpleDialog}.
	 * 
	 * @see SimpleDialog#createDialog()
	 */
	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
		createDialog();
	}

	/**
	 * Waits for the dialog to be created and opens the dialog. Waits until the
	 * user either canceled or approved the dialog. After the dialog was
	 * approved a value is created and returned.
	 * 
	 * @see #createValue(SimpleDialog)
	 */
	@Override
	public Value call() throws Exception {
		worker = new SwingWorker<Value, Runnable>() {

			@Override
			protected Value doInBackground() throws Exception {
				publish(openDialog);
				waitForDialog();
				return createValue();
			}

			@Override
			protected void process(List<Runnable> chunks) {
				for (Runnable runnable : chunks) {
					runnable.run();
				}
			}

		};
		worker.execute();
		return worker.get();
	}

	private void openDialog() {
		Dialog dialog = getDialog();
		dialog.getDialog().pack();
		dialog.getDialog().setLocationRelativeTo(frame);
		dialog.openDialog();
		synchronized (worker) {
			worker.notify();
		}
	}

	private void waitForDialog() throws InterruptedException {
		synchronized (worker) {
			worker.wait();
		}
	}

	/**
	 * Creates the dialog in a background thread. The dialog is only created if
	 * all dependencies are set.
	 * 
	 * @see #canCreateDialog()
	 */
	public final void createDialog() {
		if (!canCreateDialog() || creatingDialog) {
			return;
		}
		creatingDialog = true;
		doCreateDialog();
	}

	/**
	 * Creates the dialog in a background thread. The dialog is only created if
	 * all dependencies are set.
	 * 
	 * @see #canCreateDialog()
	 */
	protected void doCreateDialog() {
		CreateDialogAction action = new CreateDialogAction(dialog);
		threads.submit(action, new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				dialogCreated(evt);
			}

		});
	}

	/**
	 * Returns if all dependencies are set to create the dialog.
	 * 
	 * @return {@code true} if the dialog can be created.
	 */
	protected boolean canCreateDialog() {
		return log.checkFrame(this, frame) && log.checkThreads(this, threads)
				&& log.checkDialog(this, dialog);
	}

	private void dialogCreated(PropertyChangeEvent evt) {
		Dialog dialog = log.fromFuture(this, evt);
		this.createdDialog = dialog;
		synchronized (this) {
			notify();
		}
	}

	/**
	 * Waits for the dialog to be created and returns it.
	 * 
	 * @return the {@link SimpleDialog}.
	 * 
	 * @see #createDialog()
	 */
	public Dialog getDialog() {
		while (createdDialog == null) {
			try {
				synchronized (this) {
					wait(1000);
				}
				log.checkDialogCreated(this, createdDialog);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		createdDialog = dialog;
		return createdDialog;
	}

	private Value createValue() {
		if (createdDialog.getStatus() == CANCELED) {
			return null;
		}
		return createValue(createdDialog);
	}

	/**
	 * Created the value if the dialog was approved.
	 * 
	 * @param dialog
	 *            the {@link SimpleDialog}.
	 * 
	 * @return the value.
	 */
	protected abstract Value createValue(Dialog dialog);

	@Override
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
