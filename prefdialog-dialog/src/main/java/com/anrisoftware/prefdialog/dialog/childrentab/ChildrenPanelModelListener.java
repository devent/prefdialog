package com.anrisoftware.prefdialog.dialog.childrentab;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * <p>
 * Updates the tabbed pane according to the children model.
 * </p>
 * <p>
 * We add new tabs or delete old tabs if the model changes.
 * </p>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ChildrenPanelModelListener implements ListDataListener {

	private JTabbedPane tabbedPane;

	@Override
	public void intervalAdded(ListDataEvent e) {
		int begin = e.getIndex0();
		int end = e.getIndex1();
		ListModel model = (ListModel) e.getSource();
		insert(begin, end, model);
	}

	private void insert(int begin, int end, ListModel model) {
		int i = begin;
		do {
			Object child = model.getElementAt(i);
			tabbedPane.addTab(child.toString(), new JPanel());
		} while (++i < end);
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		int begin = e.getIndex0();
		int end = e.getIndex1();
		remove(begin, end);
	}

	private void remove(int begin, int end) {
		int i = begin;
		do {
			tabbedPane.removeTabAt(i);
		} while (++i < end);
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		int begin = e.getIndex0();
		int end = e.getIndex1();
		remove(begin, end);
		ListModel model = (ListModel) e.getSource();
		insert(begin, end, model);
	}

	/**
	 * Sets a new tabbed pane to which we add or remove tabs.
	 * 
	 * @param pane
	 *            the new {@link JTabbedPane}.
	 */
	public void setTabbedPane(JTabbedPane pane) {
		this.tabbedPane = pane;
	}

	/**
	 * Remove old children from the tree model and adds new children.
	 * 
	 * @param model
	 *            the {@link ListModel} that contains the children.
	 */
	public void updateChildren(ListModel model) {
		int begin = 0;
		int end = model.getSize();
		removeAll();
		insert(begin, end, model);
	}

	private void removeAll() {
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			tabbedPane.removeTabAt(i);
		}
	}
}
