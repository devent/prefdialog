package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import java.util.Set;

import javax.swing.MutableComboBoxModel;

public interface HistoryComboBoxModelFactory {

	HistoryComboBoxModel<?> create(MutableComboBoxModel<?> model,
			Set<?> defaultItems);
}
