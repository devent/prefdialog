package com.globalscalingsoftware.prefdialog.dialog.internal;

import java.awt.Frame;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.internal.Nullable;

/**
 * Use this factory to create a new {@link PreferenceDialog}.
 */
interface PreferenceDialogFactory {

	/**
	 * Creates a new {@link PreferenceDialog}.
	 * 
	 * @param owner
	 *            the {@link Frame owner} of the preference dialog, can be
	 *            <code>null</code>.
	 * @param rootNode
	 *            the {@link DefaultMutableTreeNode root tree node} that
	 *            contains all panels.
	 * @return the new created {@link PreferenceDialog}.
	 */
	PreferenceDialog create(@Assisted @Nullable Frame owner,
			@Assisted DefaultMutableTreeNode rootNode);
}
