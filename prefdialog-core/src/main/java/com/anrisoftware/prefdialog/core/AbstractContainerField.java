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

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstants;

import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;

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
 * class TextField extends AbstractContainerField&lt;JTextField, JPanel&gt; {
 * 
 * 	public TextField(JPanel panel, Object parentObject, Field field) {
 * 		super(new JTextField(), panel, parentObject, field);
 * 	}
 * }
 * </pre>
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
@SuppressWarnings("serial")
public abstract class AbstractContainerField<ComponentType extends Component, ContainerType extends Container>
		extends AbstractFieldComponent<ContainerType> {

	/**
	 * The post-fix of the container name. The name of the container will be set
	 * to <code>&lt;name&gt;-{@value #CONTAINER_NAME}</code>, with &lt;name&gt;
	 * being the name of the component.
	 */
	public static final String CONTAINER_NAME = "container";

	private ComponentType component;

	private LayoutManager layout;

	/**
	 * Sets the component and the container of this field.
	 * 
	 * @param container
	 *            the {@link Container} of this field.
	 * 
	 * @see AbstractFieldComponent#AbstractFieldComponent(Component, Object,
	 *      String)
	 */
	protected AbstractContainerField(ComponentType component,
			ContainerType container, Object parentObject, String fieldName) {
		super(container, parentObject, fieldName);
		this.component = component;
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
		container.add(component, "0, 0");
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
		container.setLayout(layout);
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
		super.setName(format("%s-%s", name, CONTAINER_NAME));
		component.setName(name);
	}

	/**
	 * Enabled or disables this container and the component inside the
	 * container.
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		component.setEnabled(enabled);
	}

	@Override
	public void setComponent(ContainerType component) {
		super.setComponent(component);
		Container container = getContainer();
		container.setLayout(layout);
		container.add(component, "0, 0");
	}

	/**
	 * Sets the container.
	 * 
	 * @param container
	 *            the {@link Container}.
	 */
	public void setContainer(ContainerType container) {
		setComponent(container);
	}

	/**
	 * Returns the container.
	 * 
	 * @return the {@link Container} container.
	 */
	public ContainerType getContainer() {
		return getComponent();
	}

	/**
	 * Returns the component in this container.
	 * 
	 * @return the {@link Component}.
	 */
	public void setContainerComponent(ComponentType component) {
		ComponentType old = this.component;
		Container container = getContainer();
		if (old != null) {
			container.remove(component);
		}
		this.component = component;
		container.add(component, "0, 0");
		setName(getName());
		setEnabled(isEnabled());
	}

	/**
	 * Returns the component in this container.
	 * 
	 * @return the {@link Component}.
	 */
	public ComponentType getContainerComponent() {
		return component;
	}

}
