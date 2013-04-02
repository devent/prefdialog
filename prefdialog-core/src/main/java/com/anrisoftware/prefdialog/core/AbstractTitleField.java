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
import static org.apache.commons.lang3.StringUtils.isEmpty;
import info.clearthought.layout.TableLayout;

import java.awt.Component;
import java.awt.Container;
import java.util.Locale;

import javax.swing.JLabel;

import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Sets a title label on top of the field. The title can show the icon if the
 * correct text position is set.
 * 
 * @see AbstractContainerField
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class AbstractTitleField<ComponentType extends Component, ContainerType extends Container>
		extends AbstractContainerField<ComponentType, ContainerType> {

	/**
	 * The post-fix of the title label name. The name of the title label will be
	 * set to <code>&lt;name&gt;-{@value #TITLE_NAME}</code>, with &lt;name&gt;
	 * being the name of the component.
	 */
	public static final String TITLE_NAME = "title";

	private final JLabel titleLabel;

	/**
	 * @see AbstractContainerField#AbstractContainerField(Component, Container,
	 *      Object, String)
	 */
	protected AbstractTitleField(ComponentType component,
			ContainerType container, Object parentObject, String fieldName) {
		super(component, container, parentObject, fieldName);
		this.titleLabel = new JLabel("(title):");
		setup();
	}

	private void setup() {
		setupContainer();
		setupTitleLabel();
	}

	private void setupContainer() {
		Container container = getContainer();
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

	private void setupTitleLabel() {
		titleLabel.setLabelFor(getContainerComponent());
	}

	@Override
	public void setTexts(Texts texts) {
		super.setTexts(texts);
		updateTextsResources();
	}

	private void updateTextsResources() {
		updateTitleResource();
	}

	private void updateTitleResource() {
		if (isShowTitle()) {
			titleLabel.setText(appendColumn(getTitle()));
		} else {
			titleLabel.setText(null);
		}
	}

	private String appendColumn(String text) {
		if (!isEmpty(text) && !text.endsWith(":")) {
			text = text + ":";
		}
		return text;
	}

	@Override
	public void setImages(Images images) {
		super.setImages(images);
		updateIconResource();
	}

	private void updateIconResource() {
		titleLabel.setIcon(getIcon());
	}

	/**
	 * Sets the name of the title label to
	 * <code>&lt;name&gt;-{@value #TITLE_NAME}</code>, with &lt;name&gt; being
	 * the name of the component.
	 */
	@Override
	public void setName(String name) {
		super.setName(name);
		titleLabel.setName(format("%s-%s", name, TITLE_NAME));
	}

	/**
	 * Enabled or disables the title label with the component.
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		titleLabel.setEnabled(enabled);
	}

	@Override
	public void setLocale(Locale locale) {
		super.setLocale(locale);
		updateTextsResources();
		updateIconResource();
	}

	@Override
	public void setIconSize(IconSize size) {
		super.setIconSize(size);
		updateIconResource();
	}

	/**
	 * Returns the title label for styling.
	 * 
	 * @return the {@link JLabel} title label.
	 */
	public JLabel getTitleLabel() {
		return titleLabel;
	}
}
