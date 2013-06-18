package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import java.util.List;

import javax.swing.MutableComboBoxModel;

public interface HistoryComboBoxModelFactory {

	HistoryComboBoxModel<?> create(MutableComboBoxModel<?> model,
			List<?> defaultItems);
}
