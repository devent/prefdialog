package com.anrisoftware.prefdialog.filechooser.panel.core;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Before the value changed message is done make sure that the list is not only
 * adjusting.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AdjustingAwareListSelectionListener implements
		ListSelectionListener {

	@Override
	public void valueChanged(ListSelectionEvent event) {
		if (event.getValueIsAdjusting()) {
			return;
		}
		doValueChanged(event);
	}

	/**
	 * Do the value changed message.
	 * 
	 * @param event
	 *            the {@link ListSelectionEvent}.
	 */
	protected abstract void doValueChanged(ListSelectionEvent event);
}
