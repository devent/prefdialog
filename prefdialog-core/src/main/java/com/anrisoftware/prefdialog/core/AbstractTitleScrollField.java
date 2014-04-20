/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
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

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Sets a title label on top of the field that is embedded in a scroll pane. The
 * title can show the icon if the correct text position is set.
 * 
 * @see AbstractContainerField
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class AbstractTitleScrollField<ComponentType extends Component>
		extends AbstractTitleField<ComponentType> {

	private static final String FIELD_SCROLL_NAME = "scrollpane";

	private final JScrollPane fieldScroll;

	private final TableLayout layout;

	/**
	 * @see AbstractContainerField#AbstractContainerField(Component, Object,
	 *      String)
	 */
	protected AbstractTitleScrollField(ComponentType component,
			Object parentObject, String fieldName) {
		super(component, parentObject, fieldName);
		this.fieldScroll = new JScrollPane();
		this.layout = createLayout();
		setup();
	}

	private void setup() {
		setupContainer();
		setupFieldScroll();
	}

	private TableLayout createLayout() {
		double[] col = { FILL };
		double[] row = { PREFERRED, PREFERRED };
		return new TableLayout(col, row);
	}

	private void setupFieldScroll() {
		fieldScroll.setViewportView(getComponent());
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		fieldScroll.setName(format("%s-%s", name, FIELD_SCROLL_NAME));
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		fieldScroll.setEnabled(enabled);
	}

	@Override
	public void setContainer(JPanel container) {
		super.setContainer(container);
		setupContainer();
	}

	@Override
	public void setComponent(ComponentType component) {
		super.setComponent(component);
		setupContainer();
	}

	private void setupContainer() {
		Container container = getContainer();
		container.removeAll();
		container.setLayout(layout);
		container.add(getTitleLabel(), "0, 0");
		container.add(fieldScroll, "0, 1");
	}

}
