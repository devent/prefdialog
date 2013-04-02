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
package com.anrisoftware.prefdialog.fields;

import java.awt.Component;
import java.beans.PropertyVetoException;
import java.util.Locale;
import java.util.MissingResourceException;

import javax.swing.Icon;

import com.anrisoftware.prefdialog.annotations.TextPosition;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * The field component.
 * 
 * @param <ComponentType>
 *            the {@link Component} for this field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface FieldComponent<ComponentType extends Component> {

	/**
	 * Sets the name the field. The name should be a unique ID of the field
	 * inside the container.
	 * 
	 * @param name
	 *            the name.
	 * 
	 * @throws NullPointerException
	 *             if the new name is {@code null}.
	 */
	void setName(String name);

	/**
	 * Returns the name the field.
	 * 
	 * @return the name of the field.
	 */
	String getName();

	/**
	 * Sets the title of the field. The title can also be a resource name that
	 * is queried in the supplied texts resource.
	 * 
	 * @param title
	 *            the title.
	 */
	void setTitle(String title);

	/**
	 * Returns the title of the field.
	 * 
	 * @return the title of the field or {@code null} for no title was set.
	 */
	String getTitle();

	/**
	 * Sets if the title should be shown.
	 * 
	 * @param show
	 *            {@code true} if the title should be shown or {@code false} if
	 *            not.
	 */
	void setShowTitle(boolean show);

	/**
	 * Returns if the title should be shown.
	 * 
	 * @return {@code true} if the title should be shown or {@code false} if
	 *         not.
	 */
	boolean isShowTitle();

	/**
	 * Set the text of the tool-tip for the component. The tool-tip can also be
	 * a resource name that is queried in the supplied texts resource.
	 * 
	 * @param text
	 *            the text, empty string or {@code null} if the tool-tip should
	 *            be disabled.
	 */
	void setToolTipText(String text);

	/**
	 * Returns the text of the tool-tip for the component.
	 * 
	 * @return the tool-tip text or {@code null} if no tool-tip text was set.
	 */
	String getToolTipText();

	/**
	 * Show or hide the tool-tip for the component.
	 * 
	 * @param {@code true} to show the tool-tip, {@code false} to hide the
	 *        tool-tip.
	 */
	void setShowToolTip(boolean show);

	/**
	 * Sets the position of the title text and icon.
	 * 
	 * @param position
	 *            the {@link TextPosition}.
	 */
	void setTitlePosition(TextPosition position);

	/**
	 * Returns the position of the title text and icon.
	 * 
	 * @return the {@link TextPosition}.
	 */
	TextPosition getTitlePosition();

	/**
	 * Sets if the field is enabled or not. Read-only fields should be disabled
	 * so the user can not enter any input.
	 * 
	 * @param enabled
	 *            {@code true} if the field is enabled, {@code false} if not.
	 */
	void setEnabled(boolean enabled);

	/**
	 * Returns if the field is enabled or not.
	 * 
	 * @return {@code true} if the field is enabled, {@code false} if not.
	 */
	boolean isEnabled();

	/**
	 * Sets the physical width of the field inside the container. The width can
	 * be in pixels, a percentage or some special constant.
	 * 
	 * @param width
	 *            the width {@link Number}.
	 * 
	 * @throws NullPointerException
	 *             if the new width is {@code null}.
	 */
	void setWidth(Number width);

	/**
	 * Returns the physical width of the field inside the container. The width
	 * can be in pixels, a percentage or some special constant.
	 * 
	 * @return the {@link Number} width.
	 */
	Number getWidth();

	/**
	 * Sets the resource for the icon. The resource is loaded from the specified
	 * images resources.
	 * 
	 * @param name
	 *            the icon resource name or {@code null} or empty if the old
	 *            icon should be deleted.
	 * 
	 * @throws ResourcesException
	 *             if the icon resource is not available.
	 * 
	 * @throws MissingResourceException
	 *             if the icon resource could not be found.
	 */
	void setIconResource(String name);

	/**
	 * Sets the new icon for the field. No icon resource name is set that means
	 * that we can not change the locale or the size of the icon.
	 * 
	 * @param icon
	 *            the {@link Icon} for the field or {@code null} if the old icon
	 *            should be deleted.
	 */
	void setIcon(Icon icon);

	/**
	 * Returns the icon for this field.
	 * 
	 * @return the {@link Icon}
	 */
	Icon getIcon();

	/**
	 * Sets the size of the icon.
	 * 
	 * @param size
	 *            the {@link IconSize}.
	 * 
	 * @throws ResourcesException
	 *             if the icon resource is not available.
	 * 
	 * @throws MissingResourceException
	 *             if the icon resource could not be found.
	 */
	void setIconSize(IconSize size);

	/**
	 * Returns the size of the icon.
	 * 
	 * @return the {@link IconSize}.
	 */
	IconSize getIconSize();

	/**
	 * Sets the locale of the field.
	 * <p>
	 * If a texts resource is set then the title, tool-tip text and icon are
	 * retrieved with the new locale.
	 * 
	 * @param locale
	 *            the {@link Locale}.
	 */
	void setLocale(Locale locale);

	/**
	 * Returns the locale of the field.
	 * 
	 * @return the {@link Locale}.
	 */
	Locale getLocale();

	/**
	 * Sets the value of the field.
	 * 
	 * @param value
	 *            the value {@link Object}.
	 * 
	 * @throws PropertyVetoException
	 *             if the user input is not valid.
	 */
	void setValue(Object value) throws PropertyVetoException;

	/**
	 * Returns the value of the field.
	 * 
	 * @return the value {@link Object}.
	 */
	Object getValue();

	/**
	 * Sets the texts resource.
	 * 
	 * @param texts
	 *            the {@link Texts} resource.
	 * 
	 * @throws MissingResourceException
	 *             if the title or tool-tip resource could not be found.
	 */
	void setTexts(Texts texts);

	/**
	 * Returns the texts resource.
	 * 
	 * @return the {@link Texts} resource.
	 */
	Texts getTexts();

	/**
	 * Sets the images resource.
	 * 
	 * @param images
	 *            the {@link Images} resources.
	 * 
	 * @throws ResourcesException
	 *             if the icon resource is not available.
	 * 
	 * @throws MissingResourceException
	 *             if the icon resource could not be found.
	 */
	void setImages(Images images);

	/**
	 * Returns the images resource.
	 * 
	 * @return the {@link Images} resource.
	 */
	Images getImages();

	/**
	 * Tests if the current input for the field is valid.
	 * 
	 * @return {@code true} if the current input is valid or {@code false} if it
	 *         is not valid.
	 */
	boolean isInputValid();

	/**
	 * Applies the user input of the field.
	 * 
	 * @throws PropertyVetoException
	 *             if the user input is not valid.
	 */
	void applyInput() throws PropertyVetoException;

	/**
	 * Restores the user input of this field.
	 */
	void restoreInput();

	/**
	 * Returns the component to be added in the preferences container for this
	 * field.
	 * 
	 * @return the {@link Component}.
	 */
	ComponentType getComponent();

	/**
	 * Add a new child field component to this field.
	 * 
	 * @param field
	 *            the {@link FieldComponent} to add.
	 * 
	 * @throws NullPointerException
	 *             if the field is {@code null}.
	 */
	void addField(FieldComponent<?> field);

	/**
	 * Returns the field component that is in this field with the specified
	 * name.
	 * 
	 * @param name
	 *            the name of the field.
	 * 
	 * @param <K>
	 *            the type of the {@link Component}.
	 * 
	 * @param <T>
	 *            the type of the {@link FieldComponent}.
	 * 
	 * @return the {@link FieldComponent} with the specified name or
	 *         {@code null} if no such field was found.
	 */
	<K extends Component, T extends FieldComponent<K>> T findField(String name);

}
