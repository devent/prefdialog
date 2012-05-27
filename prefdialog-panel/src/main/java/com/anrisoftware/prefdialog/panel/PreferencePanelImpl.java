package com.anrisoftware.prefdialog.panel;

import javax.swing.JPanel;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.PreferencePanel;
import com.anrisoftware.prefdialog.PreferencePanelFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the input fields for the preferences panel.
 * 
 * @see PreferencePanelFactory
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class PreferencePanelImpl implements PreferencePanel {

	private final JPanel panel;

	private final ChildFieldWorker worker;

	private final FieldHandler<?> childFieldHandler;

	@Inject
	PreferencePanelImpl(ChildFieldWorkerFactory workerFactory,
			@Assisted JPanel panel, @Assisted Object preferences,
			@Assisted String childName) {
		this.panel = panel;
		this.worker = workerFactory.create(preferences, childName);
		this.childFieldHandler = worker.getChildFieldHandler();
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public String getChildName() {
		return worker.getChildName();
	}

	@Override
	public Object getPreferences() {
		return worker.getPreferences();
	}

	@Override
	public boolean isInputValid() {
		return childFieldHandler.isInputValid();
	}

	@Override
	public void applyInput() {
		Object value = childFieldHandler.getComponentValue();
		childFieldHandler.applyInput(value);
	}

	@Override
	public void restoreInput() {
		Object value = childFieldHandler.getComponentValue();
		childFieldHandler.restoreInput(value);
	}

}
