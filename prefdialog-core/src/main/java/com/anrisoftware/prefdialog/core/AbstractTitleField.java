/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-core.
 * 
 * prefdialog-core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-core is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-core. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.core;

import static info.clearthought.layout.TableLayoutConstants.FILL;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.Field;

import javax.swing.JLabel;

/**
 * Sets a title label on top of the field.
 * 
 * @param <ComponentType>
 *            the type of the component that is added to this container. Must be
 *            of class {@link Component}.
 * 
 * @param <ContainerType>
 *            the type of the container of this field. Must be of class
 *            {@link Container}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class AbstractTitleField<ComponentType extends Component, ContainerType extends Container>
		extends AbstractContainerField<ComponentType, ContainerType> {

	/**
	 * The postfix of the title label name. The {@link #setName(String)} method
	 * sets the name of the title label to "name-{@value #TITLE_NAME}".
	 */
	public static final String TITLE_NAME = "titleLabel";

	private final JLabel titleLabel;

	/**
	 * Sets the component and the container of this field.
	 * 
	 * @param component
	 *            the {@link ComponentType} of this field.
	 * 
	 * @param container
	 *            the {@link ContainerType} of this field.
	 * 
	 * @param parentObject
	 *            the parent object of this field.
	 * 
	 * @param field
	 *            the {@link Field}.
	 */
	protected AbstractTitleField(ComponentType component,
			ContainerType container, Object parentObject, Field field) {
		super(component, container, parentObject, field);
		this.titleLabel = new JLabel("(title):");
		setup();
	}

	private void setup() {
		setupContainer();
	}

	private void setupContainer() {
		Container container = getAWTComponent();
		container.removeAll();
		container.setLayout(createLayout());
		container.add(titleLabel, "0, 0");
		container.add(getComponent(), "0, 1");
	}

	private TableLayout createLayout() {
		double[] col = { FILL };
		double[] row = { PREFERRED, PREFERRED };
		return new TableLayout(col, row);
	}

	/**
	 * Sets the name of the title label to "name-{@value #TITLE_NAME}".
	 */
	@Override
	public void setName(String name) {
		super.setName(name);
		titleLabel.setName(format("%s-%s", name, TITLE_NAME));
	}

	/**
	 * Enabled or disables this container and the component inside the
	 * container.
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		titleLabel.setEnabled(enabled);
	}

	@Override
	public void setTitle(String newTitle) {
		super.setTitle(newTitle);
		titleLabel.setText(appendColumn(newTitle));
	}

	private String appendColumn(String text) {
		if (!text.endsWith(":")) {
			text = text + ":";
		}
		return text;
	}
}
