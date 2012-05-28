package com.anrisoftware.prefdialog.dialog.childrentree;

import static java.awt.BorderLayout.CENTER;
import static java.lang.String.format;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;
import static javax.swing.tree.TreeSelectionModel.SINGLE_TREE_SELECTION;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.anrisoftware.prefdialog.ChildrenPanel;
import com.anrisoftware.prefdialog.ChildrenPanels;
import com.google.inject.assistedinject.Assisted;

/**
 * Contains a list of available children in a {@link JTree} at the left and at
 * the right the currently selected child {@link JPanel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ChildrenPanelImpl implements ChildrenPanel {

	private final PropertyChangeSupport support;

	private final JPanel panel;

	private final JSplitPane panelSplit;

	private final ChildrenPanelModelListener modelListener;

	private final JTree childrenTree;

	private final JScrollPane childrenTreeScroll;

	private ListModel model;

	private Object selectedChild;

	private ChildrenPanels panels;

	@Inject
	ChildrenPanelImpl(ChildrenPanelModelListener modelListener,
			EmptyChildrenPanels emptyChildrenPanels, @Assisted JPanel panel) {
		this.support = new SwingPropertyChangeSupport(this);
		this.panel = panel;
		this.panelSplit = new JSplitPane(HORIZONTAL_SPLIT);
		this.childrenTree = new JTree();
		this.childrenTreeScroll = new JScrollPane(childrenTree);
		this.modelListener = modelListener;
		this.selectedChild = null;
		this.panels = emptyChildrenPanels;
		setupModelChangeListener();
		setupPanelsChangeListener();
		setupSelectedChildChangeListener();
		setupPanel();
		setupPanelSplit();
		setupChildTree();
	}

	private void setupModelChangeListener() {
		support.addPropertyChangeListener(PROPERTY_CHILDREN_MODEL,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						updateModelListener(evt);
					}

				});
	}

	private void updateModelListener(PropertyChangeEvent evt) {
		ListModel oldValue = (ListModel) evt.getOldValue();
		ListModel newValue = (ListModel) evt.getNewValue();
		if (oldValue != null) {
			oldValue.removeListDataListener(modelListener);
		}
		newValue.addListDataListener(modelListener);
		modelListener.updateChildren(newValue);
		Object firstChild = newValue.getElementAt(0);
		setSelectedChild(firstChild);
	}

	private void setupPanelsChangeListener() {
		support.addPropertyChangeListener(PROPERTY_CHILDREN_PANELS,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						updateChildPanel();
					}

				});
	}

	private void updateChildPanel() {
		if (selectedChild == null || panels == null) {
			return;
		}
		JPanel panel = panels.getChildPanel(this, selectedChild);
		panelSplit.setRightComponent(panel);
	}

	private void setupSelectedChildChangeListener() {
		support.addPropertyChangeListener(PROPERTY_SELECTED_CHILD,
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						updateChildPanel();
					}

				});
	}

	private void setupPanel() {
		panel.setLayout(new BorderLayout());
		panel.add(panelSplit, CENTER);
	}

	private void setupPanelSplit() {
		panelSplit.setLeftComponent(childrenTreeScroll);
		panelSplit.setRightComponent(new JPanel());
	}

	private void setupChildTree() {
		TreeNode rootNode = modelListener.getRootNode();
		DefaultTreeModel model = new DefaultTreeModel(rootNode);
		modelListener.setModel(model);
		childrenTree.setModel(model);
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
	public void setChildrenModel(ListModel model) {
		ListModel oldValue = this.model;
		this.model = model;
		support.firePropertyChange(PROPERTY_CHILDREN_MODEL, oldValue, model);
	}

	@Override
	public ListModel getChildrenModel() {
		return model;
	}

	@Override
	public void setChildrenPanels(ChildrenPanels panels) {
		ChildrenPanels oldValue = this.panels;
		this.panels = panels;
		support.firePropertyChange(PROPERTY_CHILDREN_PANELS, oldValue, panels);
	}

	@Override
	public ChildrenPanels getChildrenPanels() {
		return panels;
	}

	@Override
	public void setSelectedChild(Object child) {
		TreeNode[] path = modelListener.getChildPath(child);
		TreePath selectedPath = new TreePath(path);
		childrenTree.setSelectionPath(selectedPath);
		childrenTree.scrollPathToVisible(selectedPath);
	}

	@Override
	public Object getSelectedChild() {
		return selectedChild;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		support.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		support.removePropertyChangeListener(propertyName, listener);
	}

}
