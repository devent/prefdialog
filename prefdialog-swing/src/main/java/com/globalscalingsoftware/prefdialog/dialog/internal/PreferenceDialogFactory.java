package com.globalscalingsoftware.prefdialog.dialog.internal;

import java.awt.Frame;

import javax.swing.tree.DefaultMutableTreeNode;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.internal.Nullable;

interface PreferenceDialogFactory {
	PreferenceDialog create(@Assisted @Nullable Frame owner,
			@Assisted DefaultMutableTreeNode rootNode);
}
