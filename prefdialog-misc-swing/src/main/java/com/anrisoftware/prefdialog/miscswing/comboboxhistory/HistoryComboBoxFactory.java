package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.MutableComboBoxModel;

public interface HistoryComboBoxFactory {

	HistoryComboBox<?> create(JComboBox<?> comboBox,
			MutableComboBoxModel<?> model, Set<?> defaultItems);
}
