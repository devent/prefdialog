package com.anrisoftware.prefdialog.miscswing.lists;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * List with check box items.
 * 
 * @see CheckListItem
 * @see CheckBoxListRenderer
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class CheckBoxList<E extends CheckListItem<?>> {

	/**
	 * @see #decorate(JList)
	 */
	public static <E extends CheckListItem<?>> CheckBoxList<E> createCheckBoxList(
			JList<E> list) {
		return decorate(list);
	}

	/**
	 * @see #decorate(CheckBoxListRenderer, JList)
	 */
	public static <E extends CheckListItem<?>> CheckBoxList<E> createCheckBoxList(
			CheckBoxListRenderer<E> renderer, JList<E> list) {
		return decorate(renderer, list);
	}

	/**
	 * @see #decorate(CheckBoxListRenderer, JList)
	 */
	public static <E extends CheckListItem<?>> CheckBoxList<E> decorate(
			JList<E> list) {
		return new CheckBoxList<E>(new CheckBoxListRenderer<E>(), list);
	}

	/**
	 * Decorate the specified list as check box list.
	 * 
	 * @param renderer
	 *            the {@link CheckBoxListRenderer}.
	 * 
	 * @param list
	 *            the {@link JList}.
	 * 
	 * @return the {@link CheckBoxList}.
	 */
	public static <E extends CheckListItem<?>> CheckBoxList<E> decorate(
			CheckBoxListRenderer<E> renderer, JList<E> list) {
		return new CheckBoxList<E>(renderer, list);
	}

	private final JList<E> list;

	private final CheckBoxListRenderer<E> renderer;

	private final ListSelectionListener selectionListener;

	/**
	 * @see #decorate(CheckBoxListRenderer, JList)
	 */
	protected CheckBoxList(CheckBoxListRenderer<E> renderer, JList<E> list) {
		this.list = list;
		this.renderer = renderer;
		this.selectionListener = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				updateSelected(e.getFirstIndex(), e.getLastIndex());
			}
		};
		setupList();
	}

	private void updateSelected(int index0, int index1) {
		for (int i = index0; i < index1 + 1; i++) {
			E value = list.getModel().getElementAt(i);
			if (list.isSelectedIndex(i)) {
				value.setSelected(true);
			} else {
				value.setSelected(false);
			}
		}
	}

	private void setupList() {
		list.setCellRenderer(renderer);
		list.addListSelectionListener(selectionListener);
	}

	/**
	 * Returns the decorated list.
	 * 
	 * @return the {@link JList}.
	 */
	public JList<E> getList() {
		return list;
	}
}
