package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.inject.Inject;
import javax.swing.JComboBox;
import javax.swing.MutableComboBoxModel;

import com.google.inject.assistedinject.Assisted;

public class HistoryComboBox<E> {

	private final JComboBox<E> comboBox;

	private final HistoryComboBoxModel<E> model;

	private final ActionListener actionListener;

	@Inject
	HistoryComboBox(HistoryComboBoxModelFactory modelFactory,
			@Assisted JComboBox<E> comboBox,
			@Assisted MutableComboBoxModel<E> model,
			@Assisted Set<E> defaultItems) {
		this.comboBox = comboBox;
		this.model = createModel(modelFactory, model, defaultItems);
		this.actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		};
		setupComboBox();
	}

	@SuppressWarnings("unchecked")
	private void addItem() {
		model.addElement((E) comboBox.getSelectedItem());
	}

	@SuppressWarnings("unchecked")
	private HistoryComboBoxModel<E> createModel(
			HistoryComboBoxModelFactory modelFactory,
			MutableComboBoxModel<E> model, Set<E> defaultItems) {
		return (HistoryComboBoxModel<E>) modelFactory.create(model,
				defaultItems);
	}

	private void setupComboBox() {
		comboBox.setModel(model);
		comboBox.addActionListener(actionListener);
	}
}
