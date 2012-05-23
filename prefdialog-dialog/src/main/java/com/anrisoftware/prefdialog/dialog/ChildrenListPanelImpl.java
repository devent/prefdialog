package com.anrisoftware.prefdialog.dialog;

import static java.lang.String.format;
import static javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION;

import java.awt.BorderLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.anrisoftware.prefdialog.ChildrenListPanel;
import com.google.inject.assistedinject.Assisted;

/**
 * The panel that contains a list of all children in the preferences dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
@SuppressWarnings("serial")
class ChildrenListPanelImpl extends JPanel implements ChildrenListPanel {

	private final JTree childrenTree;

	private final JScrollPane childrenTreeScroll;

	private final DefaultMutableTreeNode rootNode;

	private final PropertyChangeSupport support;

	private Object selectedChild;

	@Inject
	ChildrenListPanelImpl(@Assisted JPanel panel) {
		this.support = new SwingPropertyChangeSupport(this);
		this.childrenTree = new JTree();
		this.childrenTreeScroll = new JScrollPane(childrenTree);
		this.rootNode = new DefaultMutableTreeNode();
		this.selectedChild = null;
		setupPanel();
		setupChildTree();
	}

	private void setupPanel() {
		setLayout(new BorderLayout());
		add(childrenTreeScroll, BorderLayout.CENTER);
	}

	private void setupChildTree() {
		childrenTree.setModel(new DefaultTreeModel(rootNode));
		childrenTree.setRootVisible(false);
		childrenTree.getSelectionModel()
				.setSelectionMode(SINGLE_TREE_SELECTION);
		childrenTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				JTree tree = childrenTree;
				Object pathComponent = tree.getLastSelectedPathComponent();
				if (pathComponent != null) {
					updateSelectedChild(pathComponent);
				}
			}

		});
	}

	private void updateSelectedChild(Object pathComponent) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) pathComponent;
		Object oldValue = selectedChild;
		selectedChild = node.getUserObject();
		support.firePropertyChange(PROPERTY_SELECTED_CHILD, oldValue,
				selectedChild);
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public void setName(String name) {
		setName(format("%s-%s", name, PANEL));
		childrenTreeScroll.setName(format("%s-%s", name,
				CHILDREN_TREE_SCROLL_PANEL));
		childrenTree.setName(format("%s-%s", name, CHILDREN_TREE));
	}

	@Override
	public void setSelectedChild(TreeNode[] path) {
		TreePath selectedPath = new TreePath(path);
		childrenTree.setSelectionPath(selectedPath);
		childrenTree.scrollPathToVisible(selectedPath);
	}

	@Override
	public void addChildNode(DefaultMutableTreeNode node) {
		rootNode.add(node);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		support.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		support.removePropertyChangeListener(propertyName, listener);
	}

}
