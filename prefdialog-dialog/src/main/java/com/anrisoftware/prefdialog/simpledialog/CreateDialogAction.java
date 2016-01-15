/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import static javax.swing.SwingUtilities.invokeAndWait;

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
		invokeAndWait(new Runnable() {

			@Override
			public void run() {
				dialog.createDialog();
				dialog.getDialog().pack();
			}
		});
		return dialog;
	}

}
