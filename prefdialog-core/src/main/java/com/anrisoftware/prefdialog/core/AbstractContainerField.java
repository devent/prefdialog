/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstants;

import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * Put the field in a container. The container will use a {@link TableLayout} to
 * dynamically set the width.
 * <p>
 * If set a different {@link LayoutManager} then a {@link TableLayout} with
 * {@link #setLayout(LayoutManager)} then the method {@link #setWidth(Number)}
 * must be adapted to this layout.
 * <p>
 * Example:
 * 
 * <pre>
 * class TextField extends AbstractContainerField&lt;JTextField&gt; {
 * 
 * 	public TextField(Object parentObject, Field field) {
 * 		super(new JTextField(), parentObject, field);
 * 	}
 * }
 * </pre>
 * 
 * @param <ComponentType>
 *            the type of the component that is added to this container. Must be
 *            of class {@link Component}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class AbstractContainerField<ComponentType extends Component>
		extends AbstractFieldComponent<ComponentType> {

	/**
	 * Suffix for the container panel that contains the input field. The name of
	 * the panel will be {@code <name>-container}, {@code <name>} is the name of
	 * the field.
	 */
	public static final String CONTAINER_NAME = "container";

	private LayoutManager layout;

	private JPanel container;

	/**
	 * @see AbstractFieldComponent#AbstractFieldComponent(Component, Object,
	 *      String)
	 */
	protected AbstractContainerField(ComponentType component,
			Object parentObject, String fieldName) {
		super(component, parentObject, fieldName);
		this.container = new JPanel();
		this.layout = createLayout();
		setup();
	}

	private TableLayout createLayout() {
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.PREFERRED };
		return new TableLayout(col, row);
	}

	private void setup() {
		setupContainer();
	}

	private void setupContainer() {
		Container container = getContainer();
		container.setLayout(layout);
		container.add(getComponent(), "0, 0");
	}

	/**
	 * Sets the new layout for the container to use.
	 * 
	 * @param layout
	 *            the new {@link LayoutManager}.
	 */
	public void setLayout(LayoutManager layout) {
		this.layout = layout;
		Container container = getContainer();
		container.removeAll();
		container.setLayout(layout);
		container.add(getComponent(), "0, 0");
		container.repaint();
	}

	/**
	 * Returns the layout the the container is using.
	 * 
	 * @return the {@link LayoutManager}.
	 */
	public LayoutManager getLayout() {
		return layout;
	}

	/**
	 * Adjust the layout of the container to the specified width.
	 * 
	 * @param width
	 *            if with is at least 0 then the width in pixels is set. In
	 *            addition the special constants are used by the
	 *            {@link TableLayout} layout:
	 *            <ul>
	 *            <li>{@link TableLayoutConstants#PREFERRED}</li>
	 *            <li>{@link TableLayoutConstants#FILL}</li>
	 *            </ul>
	 */
	@Override
	public void setWidth(Number width) {
		super.setWidth(width);
		if (layout instanceof TableLayout) {
			Container container = getContainer();
			TableLayout tableLayout = (TableLayout) layout;
			tableLayout.setColumn(0, width.doubleValue());
			tableLayout.layoutContainer(container);
			container.repaint();
		}
	}

	/**
	 * Sets the name of this container to
	 * <code>&lt;name&gt;-{@value #CONTAINER_NAME}</code>, with &lt;name&gt;
	 * being the name of the component.
	 */
	@Override
	public void setName(String name) {
		super.setName(name);
		container.setName(format("%s-%s", name, CONTAINER_NAME));
	}

	/**
	 * Enabled or disables this container and the component inside the
	 * container.
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		container.setEnabled(enabled);
	}

	@Override
	public void setComponent(ComponentType component) {
		Container container = getContainer();
		ComponentType old = getComponent();
		if (old != null) {
			container.remove(old);
		}
		super.setComponent(component);
		container.add(component, "0, 0");
	}

	/**
	 * Sets the container.
	 * 
	 * @param container
	 *            the {@link Container}.
	 * 
	 * @since 3.0
	 */
	public void setContainer(JPanel container) {
		JPanel old = this.container;
		if (old != null) {
			old.remove(getComponent());
		}
		this.container = container;
		setName(getName());
		setEnabled(isEnabled());
		setLayout(layout);
		setWidth(getWidth());
	}

	/**
	 * Returns the container.
	 * 
	 * @return the {@link Container} container.
	 * 
	 * @since 3.0
	 */
	public JPanel getContainer() {
		return container;
	}

	@Override
	public Component getAWTComponent() {
		return getContainer();
	}
}
