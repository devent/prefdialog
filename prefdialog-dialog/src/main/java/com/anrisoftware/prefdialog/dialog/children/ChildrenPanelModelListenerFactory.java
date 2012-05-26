package com.anrisoftware.prefdialog.dialog.children;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 * Factory to create a new {@link ChildrenPanelModelListener} from a tree model.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
interface ChildrenPanelModelListenerFactory {

	/**
	 * Create a new {@link ChildrenPanelModelListener} from the given tree
	 * model.
	 * 
	 * @param treeModel
	 *            the {@link DefaultTreeModel} that is updated. We use the
	 *            {@link DefaultTreeModel#reload(TreeNode)} to update the tree.
	 */
	ChildrenPanelModelListener create(DefaultTreeModel treeModel);

}
