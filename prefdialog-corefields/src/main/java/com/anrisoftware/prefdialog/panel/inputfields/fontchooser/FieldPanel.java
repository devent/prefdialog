/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields.fontchooser;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel;
import com.google.inject.Inject;

/**
 * Sets a {@link FontChooserComboBox} with a {@link JButton}. the combo box will
 * show the current selected font and the button can open a font chooser dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class FieldPanel extends AbstractLabelFieldPanel<JPanel> {

	private FieldPanelLogger log;

	private final Panel slidingPanel;

	/**
	 * Set the {@link UiFontChooserPanel}.
	 */
	@Inject
	FieldPanel(Panel slidingPanel) {
		super(slidingPanel.getPanel());
		this.slidingPanel = slidingPanel;
	}

	@Override
	public Object getValue() {
		return slidingPanel.getFont();
	}

	@Override
	public void setValue(Object value) {
		if (value instanceof Font) {
			slidingPanel.setFont((Font) value);
		}
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		slidingPanel.setName(name);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		slidingPanel.setEnabled(enabled);
	}

	@Override
	public boolean isInputValid() {
		return true;
	}

	public void setMinimumFontChooserHeight(int height) {
		slidingPanel.setMinimumFontChooserHeight(height);
		log.setMinimumFontChooserHeight(height);
	}

	@Inject
	public void setLog(FieldPanelLogger log) {
		this.log = log;
	}
}
