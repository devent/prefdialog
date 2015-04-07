/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.filechooser;

import java.awt.Component;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingWorker;
import javax.swing.filechooser.FileFilter;

import com.anrisoftware.prefdialog.annotations.FileChooserModel;

/**
 * Opens a file chooser dialog in the AWT thread and returns the selected file
 * and file filter.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class OpenWorker extends SwingWorker<OpenWorker, Runnable> {

	private final Runnable openFileChooser;

	private FileChooserModel model;

	private Component parent;

	private CountDownLatch latch;

	public OpenWorker() {
		this.openFileChooser = new Runnable() {

			@Override
			public void run() {
				openFileChooser();
			}

		};
	}

	/**
	 * Sets the parent component where the file chooser should be opened.
	 * 
	 * @param parent
	 *            the parent {@link Component}.
	 */
	public void setParent(Component parent) {
		this.parent = parent;
	}

	/**
	 * Sets the file chooser model.
	 * 
	 * @param model
	 *            the {@link FileChooserModel}.
	 */
	public void setModel(FileChooserModel model) {
		this.model = model;
	}

	/**
	 * @see FileChooserModel#getFileFilter()
	 */
	public FileFilter getFileFilter() {
		return model.getFileFilter();
	}

	/**
	 * @see FileChooserModel#getFile()
	 */
	public File getFile() {
		return model.getFile();
	}

	@Override
	protected OpenWorker doInBackground() throws Exception {
		latch = new CountDownLatch(1);
		publish(openFileChooser);
		waitForDialog();
		return this;
	}

	@Override
	protected void process(List<Runnable> chunks) {
		for (Runnable runnable : chunks) {
			runnable.run();
		}
	}

	private void openFileChooser() {
		try {
			model.openDialog(parent);
		} catch (PropertyVetoException e) {
		}
		latch.countDown();
	}

	private void waitForDialog() throws InterruptedException {
		latch.await();
	}

}
