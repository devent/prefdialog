package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;
import javax.swing.MutableComboBoxModel;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Decorate a combo box as the history combo box. That is a combo box that
 * retains a history of added items in the model.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class HistoryComboBox {

	private final JComboBox comboBox;

	private final HistoryComboBoxModel model;

	private final ActionListener actionListener;

	private final ListCellRenderer renderer;

	/**
	 * @see HistoryComboBoxFactory#create(JComboBox)
	 */
	@AssistedInject
	HistoryComboBox(HistoryComboBoxModelFactory modelFactory,
			ItemsDefaultComboBoxRendererFactory rendererFactory,
			@Assisted JComboBox comboBox) {
		this(modelFactory, rendererFactory, comboBox,
				new DefaultComboBoxModel(), new DefaultListCellRenderer(),
				new ArrayList(0));
	}

	/**
	 * @see HistoryComboBoxFactory#create(JComboBox, Collection)
	 */
	@AssistedInject
	HistoryComboBox(HistoryComboBoxModelFactory modelFactory,
			ItemsDefaultComboBoxRendererFactory rendererFactory,
			@Assisted JComboBox comboBox, @Assisted Collection defaultItems) {
		this(modelFactory, rendererFactory, comboBox,
				new DefaultComboBoxModel(), new DefaultListCellRenderer(),
				defaultItems);
	}

	/**
	 * @see HistoryComboBoxFactory#create(JComboBox, MutableComboBoxModel,
	 *      Collection)
	 */
	@AssistedInject
	HistoryComboBox(HistoryComboBoxModelFactory modelFactory,
			ItemsDefaultComboBoxRendererFactory rendererFactory,
			@Assisted JComboBox comboBox, @Assisted MutableComboBoxModel model,
			@Assisted Collection defaultItems) {
		this(modelFactory, rendererFactory, comboBox, model,
				new DefaultListCellRenderer(), defaultItems);
	}

	/**
	 * @see HistoryComboBoxFactory#create(JComboBox, MutableComboBoxModel,
	 *      ListCellRenderer, Collection)
	 */
	@AssistedInject
	HistoryComboBox(HistoryComboBoxModelFactory modelFactory,
			ItemsDefaultComboBoxRendererFactory rendererFactory,
			@Assisted JComboBox comboBox, @Assisted MutableComboBoxModel model,
			@Assisted ListCellRenderer renderer,
			@Assisted Collection defaultItems) {
		this.comboBox = comboBox;
		this.model = modelFactory.create(model, defaultItems);
		this.renderer = rendererFactory.create(renderer);
		this.actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addItem();
			}
		};
		setupComboBox();
	}

	private void addItem() {
		model.addElement(comboBox.getSelectedItem());
	}

	private void setupComboBox() {
		comboBox.setModel(model);
		comboBox.setRenderer(renderer);
		comboBox.addActionListener(actionListener);
	}

	/**
	 * Returns the decorated combo box.
	 * 
	 * @return the {@link JComboBox}.
	 */
	public JComboBox getComboBox() {
		return comboBox;
	}
}
