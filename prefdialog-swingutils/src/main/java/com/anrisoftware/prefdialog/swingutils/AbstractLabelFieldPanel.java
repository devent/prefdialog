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
package com.anrisoftware.prefdialog.swingutils;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Adds on top of the {@link FieldType} a {@link JLabel} that displays the titel
 * of the field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public abstract class AbstractLabelFieldPanel<FieldType extends JComponent>
		extends AbstractPanelField<FieldType> {

	/**
	 * The prefix of the title label name.
	 */
	public static final String TITLE_LABEL = "titlelabel";

	private final JLabel label;

	private final TableLayout layout;

	/**
	 * Create and add the {@link JLabel} for the title of the component.
	 * 
	 * @param field
	 *            the {@link JComponent}.
	 */
	protected AbstractLabelFieldPanel(FieldType field) {
		super(field);
		this.label = new JLabel();
		this.layout = createLayout();
		setLayout(layout);
		setupPanel();
	}

	private TableLayout createLayout() {
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.PREFERRED, TableLayout.FILL };
		return new TableLayout(col, row);
	}

	private void setupPanel() {
		JPanel panel = (JPanel) getAWTComponent();
		panel.removeAll();
		panel.add(label, "0, 0");
		panel.add(getPanelField(), "0, 1");

		label.setLabelFor(getPanelField());
		getPanelField().requestFocus();
	}

	@Override
	public void setTitle(String title) {
		setLabelText(title);
	}

	/**
	 * Sets the label text.
	 */
	public void setLabelText(String text) {
		label.setText(text);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		label.setName(format("%s-%s", TITLE_LABEL, name));
	}

	/**
	 * Shows or hides the title of the field.
	 */
	public void setShowTitle(boolean show) {
		if (!show) {
			JPanel panel = (JPanel) getAWTComponent();
			panel.remove(label);
			layout.deleteRow(0);
		}
	}

	/**
	 * Sets the {@link Icon} for the title label.
	 */
	public void setIcon(Icon icon) {
		label.setIcon(icon);
	}

	/**
	 * Set the text of the title label under the icon.
	 * 
	 * @param underIcon
	 *            if <code>true</code> then set the text under the icon or left
	 *            from the icon otherwise.
	 */
	public void setTextUnderIcon(boolean underIcon) {
		if (underIcon) {
			label.setVerticalTextPosition(SwingConstants.BOTTOM);
			label.setHorizontalTextPosition(SwingConstants.CENTER);
		} else {
			label.setVerticalTextPosition(SwingConstants.CENTER);
			label.setHorizontalTextPosition(SwingConstants.TRAILING);
		}
	}

	/**
	 * Returns the title {@link JLabel}.
	 */
	protected JLabel getLabel() {
		return label;
	}
}
