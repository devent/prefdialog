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
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
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
	 * Value property.
	 * 
	 * @see #setValue(Object)
	 */
	public static final String VALUE_PROPERTY = "value";

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
	 * Sets the mnemonic character with an optimal mnemonic index. The string
	 * can contain a key code name or the character. The mnemonic can also be a
	 * resource name that is queried in the supplied texts resource.
	 * 
	 * @param mnemonics
	 *            the mnemonic string or the text resource name.
	 */
	void setMnemonicString(String mnemonics);

	/**
	 * Sets the mnemonic key code.
	 * 
	 * @param mnemonics
	 *            the mnemonic key code.
	 * 
	 * @see KeyEvent
	 */
	void setMnemonic(int mnemonics);

	/**
	 * Returns the mnemonic key code.
	 * 
	 * @return the mnemonic key code or {@code null} if no such code was set.
	 * 
	 * @see KeyEvent
	 */
	Integer getMnemonic();

	/**
	 * Sets the mnemonic displayed index.
	 * 
	 * @param index
	 *            the mnemonic index.
	 */
	void setMnemonicIndex(int index);

	/**
	 * Returns the mnemonic displayed index.
	 * 
	 * @return the mnemonic displayed index.
	 * 
	 * @see KeyEvent
	 */
	int getMnemonicIndex();

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
	 * Set the text that is shown if the user input is invalid. Defaults to
	 * empty string. The text can also be a resource name that is queried in the
	 * supplied texts resource.
	 * 
	 * @param text
	 *            the text or the texts resource name.
	 */
	void setInvalidText(String text);

	/**
	 * Returns the text that is shown if the user input is invalid.
	 * 
	 * @return the text.
	 */
	String getInvalidText();

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
	 * Sets the physical height of the field inside the container. The height
	 * can be in pixels, a percentage or some special constant.
	 * 
	 * @param height
	 *            the height {@link Number}.
	 * 
	 * @throws NullPointerException
	 *             if the new height is {@code null}.
	 */
	void setHeight(Number height);

	/**
	 * Returns the physical height of the field inside the container. The height
	 * can be in pixels, a percentage or some special constant.
	 * 
	 * @return the {@link Number} height.
	 */
	Number getHeight();

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
	 * Sets the order number of the field.
	 * 
	 * @param order
	 *            the order.
	 * 
	 * @see com.anrisoftware.prefdialog.annotations.FieldComponent#order()
	 */
	void setOrder(int order);

	/**
	 * Returns the order number of the field.
	 * 
	 * @return the order.
	 * 
	 * @see com.anrisoftware.prefdialog.annotations.FieldComponent#order()
	 */
	int getOrder();

	/**
	 * Sets the value of the field. The added property change listeners are
	 * informed of the value change. If one of the listeners veto the value then
	 * the value is reset to the previos value of the field.
	 * 
	 * @param value
	 *            the value {@link Object}.
	 * 
	 * @throws PropertyVetoException
	 *             if the user input is not valid.
	 * 
	 * @see #VALUE_PROPERTY
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
	 * Restores the user input of this field.
	 * 
	 * @throws PropertyVetoException
	 *             if the old user input is not valid.
	 */
	void restoreInput() throws PropertyVetoException;

	/**
	 * Sets the Swing component of the field.
	 * 
	 * @param component
	 *            the {@link Component}.
	 */
	void setComponent(ComponentType component);

	/**
	 * Returns the Swing component of the field.
	 * 
	 * @return the {@link Component}.
	 */
	ComponentType getComponent();

	/**
	 * Returns the Swing component of the field to be added in a container.
	 * 
	 * @return the {@link Component}.
	 * 
	 * @since 3.0
	 */
	Component getAWTComponent();

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
	 * Returns the field component that was added to this field with the
	 * specified index.
	 * 
	 * @param index
	 *            the index.
	 * 
	 * @return the {@link FieldComponent}.
	 */
	FieldComponent<?> getField(int index);

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

	/**
	 * Returns the count of field components that were added to this field.
	 * 
	 * @return the count of fields.
	 */
	int getFieldsCount();

	/**
	 * Returns the field components that were added to this field.
	 * 
	 * @return the {@link Iterable} of {@link FieldComponent}.
	 */
	Iterable<FieldComponent<?>> getFields();

	/**
	 * @see VetoableChangeSupport#addVetoableChangeListener(VetoableChangeListener)
	 * @see #VALUE_PROPERTY
	 */
	void addVetoableChangeListener(VetoableChangeListener listener);

	/**
	 * @see VetoableChangeSupport#removeVetoableChangeListener(VetoableChangeListener)
	 * @see #VALUE_PROPERTY
	 */
	void removeVetoableChangeListener(VetoableChangeListener listener);

	/**
	 * @see VetoableChangeSupport#addVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see #VALUE_PROPERTY
	 */
	void addVetoableChangeListener(String propertyName,
			VetoableChangeListener listener);

	/**
	 * @see VetoableChangeSupport#removeVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see #VALUE_PROPERTY
	 */
	void removeVetoableChangeListener(String propertyName,
			VetoableChangeListener listener);

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
	 * @see #VALUE_PROPERTY
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
	 * @see #VALUE_PROPERTY
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @see PropertyChangeSupport#addPropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see #VALUE_PROPERTY
	 */
	void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

	/**
	 * @see PropertyChangeSupport#removePropertyChangeListener(String,
	 *      PropertyChangeListener)
	 * @see #VALUE_PROPERTY
	 */
	void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener);

}
