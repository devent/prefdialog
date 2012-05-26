package com.anrisoftware.prefdialog.dialog;

import static java.lang.String.format;
import static javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION;

import java.awt.BorderLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.anrisoftware.prefdialog.ChildrenListPanel;
import com.google.common.collect.Maps;
import com.google.inject.assistedinject.Assisted;

/**
 * The panel that contains a list of all children in the preferences dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ChildrenListPanelImpl implements ChildrenListPanel {

	private final JPanel panel;

	private final JTree childrenTree;

	private final JScrollPane childrenTreeScroll;

	private final DefaultMutableTreeNode rootNode;

	private final PropertyChangeSupport support;

	private final Map<Object, TreeNode[]> childrenNodes;

	private Object selectedChild;

	@Inject
	ChildrenListPanelImpl(@Assisted JPanel panel) {
		this.panel = panel;
		this.support = new SwingPropertyChangeSupport(this);
		this.childrenTree = new JTree();
		this.childrenTreeScroll = new JScrollPane(childrenTree);
		this.rootNode = new DefaultMutableTreeNode();
		this.selectedChild = null;
		this.childrenNodes = Maps.newHashMap();
		setupPanel();
		setupChildTree();
	}

	private void setupPanel() {
		panel.setLayout(new BorderLayout());
		panel.add(childrenTreeScroll, BorderLayout.CENTER);
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
		return panel;
	}

	@Override
	public void setName(String name) {
		panel.setName(format("%s-%s", name, PANEL_NAME_POSTFIX));
	}

	@Override
	public void setSelectedChild(TreeNode[] path) {
		TreePath selectedPath = new TreePath(path);
		childrenTree.setSelectionPath(selectedPath);
		childrenTree.scrollPathToVisible(selectedPath);
	}

	@Override
	public void setSelectedChild(Object child) {
		TreeNode[] path = childrenNodes.get(child);
		TreePath selectedPath = new TreePath(path);
		childrenTree.setSelectionPath(selectedPath);
		childrenTree.scrollPathToVisible(selectedPath);
	}

	@Override
	public Object getSelectedChild() {
		return selectedChild;
	}

	@Override
	public void addChildNode(DefaultMutableTreeNode node) {
		rootNode.add(node);
		TreeNode[] path = node.getPath();
		childrenNodes.put(node.getUserObject(), path);
		DefaultTreeModel model = (DefaultTreeModel) childrenTree.getModel();
		model.reload(rootNode);
	}

	@Override
	public void setChildRenderer(TreeCellRenderer renderer) {
		childrenTree.setCellRenderer(renderer);
	}

	@Override
	public TreeCellRenderer getChildRenderer() {
		return childrenTree.getCellRenderer();
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
