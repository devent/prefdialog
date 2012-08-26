package com.anrisoftware.prefdialog.core;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstants;

import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.lang.reflect.Field;

/**
 * Put the field in a container. The container will use a {@link TableLayout} to
 * dynamically set the width.
 * <p>
 * If set a different {@link LayoutManager} then a {@link TableLayout} with
 * {@link #setLayout(LayoutManager)} then the method {@link #setWidth(Number)}
 * must be adapted to this layout.
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
public abstract class AbstractContainerField<ComponentType extends Component, ContainerType extends Container>
		extends AbstractFieldComponent<ContainerType> {

	/**
	 * The postfix of the container name. The {@link #setName(String)} method
	 * sets the name of this container to "name-container".
	 */
	public static final String CONTAINER_NAME = "container";

	private final ComponentType component;

	private LayoutManager layout;

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
	protected AbstractContainerField(ComponentType component,
			ContainerType container, Object parentObject, Field field) {
		super(container, parentObject, field);
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
		Container container = getAWTComponent();
		container.setLayout(layout);
		container.add(component, "0, 0");
	}

	/**
	 * Returns the container to be added in the container of the preferences.
	 */
	@Override
	public ContainerType getAWTComponent() {
		return super.getAWTComponent();
	}

	/**
	 * Returns the component in this container.
	 * 
	 * @return the {@link ComponentType}.
	 */
	public ComponentType getComponent() {
		return component;
	}

	/**
	 * Sets the new layout for the container to use.
	 * 
	 * @param layout
	 *            the new {@link LayoutManager}.
	 */
	public void setLayout(LayoutManager layout) {
		this.layout = layout;
		Container container = getAWTComponent();
		container.setLayout(layout);
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
	 *            {@link TableLayout}:
	 *            <ul>
	 *            <li>{@link TableLayoutConstants#PREFERRED}</li>
	 *            <li>{@link TableLayoutConstants#FILL}</li>
	 *            </ul>
	 */
	@Override
	public void setWidth(Number width) {
		Container container = getAWTComponent();
		TableLayout tableLayout = (TableLayout) layout;
		tableLayout.setColumn(0, width.doubleValue());
		tableLayout.layoutContainer(container);
		container.repaint();
	}

	/**
	 * Sets the name of this container to "{@value #CONTAINER_NAME}-name".
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

}
