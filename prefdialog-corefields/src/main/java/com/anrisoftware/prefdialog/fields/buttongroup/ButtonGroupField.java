package com.anrisoftware.prefdialog.fields.buttongroup;

import java.awt.Container;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.JPanel;

import org.apache.commons.lang3.ArrayUtils;

import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.google.inject.assistedinject.Assisted;

class ButtonGroupField extends AbstractTitleField<JPanel, Container> {

	private final ButtonGroupFieldLogger log;

	private final RowPanel rowPanel;

	private final ButtonsRowPanel buttonsRowPanel;

	@Inject
	ButtonGroupField(ButtonGroupFieldLogger logger, RowPanel rowPanel,
			ButtonsRowPanel buttonsRowPanel, @Assisted Container container,
			@Assisted Object parentObject, @Assisted Field field) {
		super(rowPanel, container, parentObject, field);
		this.log = logger;
		this.rowPanel = rowPanel;
		this.buttonsRowPanel = buttonsRowPanel;
		setup();
	}

	private void setup() {
		rowPanel.setButtonsRowPanel(buttonsRowPanel);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		rowPanel.setName(name);
		buttonsRowPanel.setName(name);
	}

	@Override
	public void setValue(Object newValue) {
		super.setValue(newValue);
		final List<Action> actions = asList(getValue());
		@SuppressWarnings("serial")
		AbstractListModel model = new AbstractListModel() {
			@Override
			public int getSize() {
				return actions.size();
			}

			@Override
			public Object getElementAt(int i) {
				return actions.get(i);
			}
		};
		buttonsRowPanel.setModel(model);
	}

	private List<Action> asList(Object value) {
		if (ArrayUtils.isSameType(value, new Action[0])) {
			Action[] actionsArray = (Action[]) value;
			return Arrays.asList(actionsArray);
		} else if (value instanceof List) {
			@SuppressWarnings("unchecked")
			List<Action> list = (List<Action>) value;
			return list;
		}
		throw log.valueNotList(value);
	}
}
