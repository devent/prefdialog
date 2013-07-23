package com.anrisoftware.prefdialog.simpledialog;

import static javax.swing.SwingUtilities.invokeLater;

import java.util.concurrent.Callable;

/**
 * Create the simple dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class CreateDialogAction implements Callable<SimpleDialog> {

	private final SimpleDialog dialog;

	/**
	 * Sets the dialog to be created.
	 * 
	 * @param dialog
	 *            the {@link SimpleDialog}.
	 */
	public CreateDialogAction(SimpleDialog dialog) {
		this.dialog = dialog;
	}

	/**
	 * Created the dialog.
	 * 
	 * @see SimpleDialog#createDialog()
	 */
	@Override
	public SimpleDialog call() throws Exception {
		dialog.createDialog();
		invokeLater(new Runnable() {

			@Override
			public void run() {
				dialog.getDialog().pack();
			}
		});
		return dialog;
	}

}
