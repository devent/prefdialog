/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
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
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

import javax.inject.Inject;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.ToolTipManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.globalpom.mnemonic.Mnemonic;
import com.anrisoftware.globalpom.mnemonic.MnemonicFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanAccess;
import com.anrisoftware.globalpom.reflection.beans.BeanAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanFactory;
import com.anrisoftware.prefdialog.annotations.TextPosition;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Sets the component and sets the enumerated attributes of the component.
 * <p>
 * For the tool-tip to be set the component of this field must be of class
 * {@link JComponent}.
 * 
 * <ul>
 * <li>name</li>
 * <li>title</li>
 * <li>show title flag</li>
 * <li>mnemonic</li>
 * <li>tool-tip</li>
 * <li>invalidText</li>
 * <li>read-only flag</li>
 * <li>width</li>
 * <li>height</li>
 * <li>title position</li>
 * <li>icon</li>
 * <li>show icon flag</li>
 * <li>icon size</li>
 * <li>locale</li>
 * <li>order</li>
 * <li>value</li>
 * </ul>
 * 
 * @param <ComponentType>
 *            the type of the component of this field. Must be of class
 *            {@link Component}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class AbstractFieldComponent<ComponentType extends Component>
		implements FieldComponent<ComponentType>, Serializable {

	private static final Class<com.anrisoftware.prefdialog.annotations.FieldComponent> ANNOTATION_CLASS = com.anrisoftware.prefdialog.annotations.FieldComponent.class;

	private static final String TITLE_ELEMENT = "title";

	private static final String SHOW_TITLE_ELEMENT = "showTitle";

	private static final String MNEMONIC_ELEMENT = "mnemonic";

	private static final String TOOLTIP_ELEMENT = "toolTip";

	private static final String READ_ONLY_ELEMENT = "readOnly";

	private static final String WIDTH_ELEMENT = "width";

	private static final String HEIGHT_ELEMENT = "height";

	private static final String TITLE_POSITION_ELEMENT = "titlePosition";

	private static final String ICON_ELEMENT = "icon";

	private static final String ICON_SIZE_ELEMENT = "iconSize";

	private static final String LOCALE_ELEMENT = "locale";

	private static final String INVALID_TEXT_ELEMENT = "invalidText";

	private static final String ORDER_ELEMENT = "order";

	private final Object parentObject;

	private final String fieldName;

	private final List<FieldComponent<?>> childFields;

	private transient VetoableChangeSupport vetoableSupport;

	private transient PropertyChangeSupport propertySupport;

	private transient MnemonicFactory mnemonicFactory;

	private transient AnnotationAccess annotationAccess;

	private transient BeanAccess beanAccess;

	private transient BeanFactory beanFactory;

	private AbstractFieldComponentLogger log;

	private ComponentType component;

	private String titleResource;

	private String title;

	private boolean showTitle;

	private String mnemonicResource;

	private Integer mnemonic;

	private String toolTipResource;

	private String toolTip;

	private String invalidTextResource;

	private String invalidText;

	private boolean showToolTip;

	private boolean readOnly;

	private Number width;

	private Number height;

	private TextPosition titlePosition;

	private String iconResource;

	private Icon icon;

	private IconSize iconSize;

	private Locale locale;

	private Texts texts;

	private Images images;

	private Object originalValue;

	private Object value;

	private Object oldValue;

	private String name;

	private int order;

	private int mnemonicIndex;

	/**
	 * Sets the component of this field.
	 * 
	 * @param component
	 *            the {@link Component}.
	 * 
	 * @param parentObject
	 *            the parent object of this field.
	 * 
	 * @param fieldName
	 *            the name of the field in the parent object.
	 */
	protected AbstractFieldComponent(ComponentType component,
			Object parentObject, String fieldName) {
		this.component = component;
		this.parentObject = parentObject;
		this.fieldName = fieldName;
		this.childFields = new ArrayList<FieldComponent<?>>();
		this.mnemonic = null;
		this.mnemonicIndex = -1;
		this.vetoableSupport = new VetoableChangeSupport(this);
		this.propertySupport = new PropertyChangeSupport(this);
	}

	/**
	 * Setups the field component.
	 */
	@Inject
	void setupAbstractFieldComponent(AbstractFieldComponentLogger logger,
			BeanAccessFactory beanAccessFactory, BeanFactory beanFactory,
			AnnotationAccessFactory annotationAccessFactory,
			MnemonicFactory mnemonicFactory) {
		this.beanAccess = createBeanAccess(beanAccessFactory);
		this.annotationAccess = createAnnotationAccess(annotationAccessFactory);
		this.beanFactory = beanFactory;
		this.log = logger;
		this.mnemonicFactory = mnemonicFactory;
		setupField();
	}

	private AnnotationAccess createAnnotationAccess(
			AnnotationAccessFactory annotationAccessFactory) {
		return annotationAccessFactory.create(ANNOTATION_CLASS,
				beanAccess.getGettterObject());
	}

	private BeanAccess createBeanAccess(BeanAccessFactory beanAccessFactory) {
		return beanAccessFactory.create(fieldName, parentObject);
	}

	/**
	 * Returns the annotation access to access the elements of an annotation.
	 * 
	 * @return the {@link AnnotationAccess}.
	 */
	protected AnnotationAccess getAnnotationAccess() {
		return annotationAccess;
	}

	/**
	 * Returns the bean access to access the fields of a object.
	 * 
	 * @return the {@link BeanAccess}.
	 */
	protected BeanAccess getBeanAccess() {
		return beanAccess;
	}

	/**
	 * Returns the bean factory to create beans.
	 * 
	 * @return the {@link BeanFactory}.
	 */
	protected BeanFactory getBeanFactory() {
		return beanFactory;
	}

	private void setupField() {
		setupName();
		setupTitle();
		setupShowTitle();
		setupMnemonic();
		setupToolTip();
		setupInvalidText();
		setupReadOnly();
		setupWidth();
		setupHeight();
		setupTitlePosition();
		setupIcon();
		setupIconSize();
		setupLocale();
		setupOrder();
		setupValue();
	}

	private void setupName() {
		String name = fieldName;
		setName(name);
	}

	private void setupTitle() {
		String title = annotationAccess.getValue(TITLE_ELEMENT);
		title = isEmpty(title) ? fieldName : title;
		setTitle(title);
	}

	private void setupShowTitle() {
		boolean show = annotationAccess.getValue(SHOW_TITLE_ELEMENT);
		setShowTitle(show);
	}

	private void setupMnemonic() {
		String string = annotationAccess.getValue(MNEMONIC_ELEMENT);
		string = isEmpty(string) ? format("%s_mnemonic", fieldName) : string;
		setMnemonicString(string);
	}

	private void setupToolTip() {
		String text = annotationAccess.getValue(TOOLTIP_ELEMENT);
		setToolTipText(text);
	}

	private void setupInvalidText() {
		String text = annotationAccess.getValue(INVALID_TEXT_ELEMENT);
		setInvalidText(text);
	}

	private void setupReadOnly() {
		boolean readOnly = annotationAccess.getValue(READ_ONLY_ELEMENT);
		setEnabled(!readOnly);
	}

	private void setupWidth() {
		double width = annotationAccess.getValue(WIDTH_ELEMENT);
		setWidth(width);
	}

	private void setupHeight() {
		double height = annotationAccess.getValue(HEIGHT_ELEMENT);
		setHeight(height);
	}

	private void setupTitlePosition() {
		TextPosition position = annotationAccess
				.getValue(TITLE_POSITION_ELEMENT);
		setTitlePosition(position);
	}

	private void setupIcon() {
		String name = annotationAccess.getValue(ICON_ELEMENT);
		setIconResource(name);
	}

	private void setupIconSize() {
		IconSize size = annotationAccess.getValue(ICON_SIZE_ELEMENT);
		setIconSize(size);
	}

	private void setupLocale() {
		String localeName = annotationAccess.getValue(LOCALE_ELEMENT);
		Locale locale = isEmpty(localeName) ? Locale.getDefault() : new Locale(
				localeName);
		setLocale(locale);
	}

	private void setupOrder() {
		int order = annotationAccess.getValue(ORDER_ELEMENT);
		setOrder(order);
	}

	private void setupValue() {
		Object value = beanAccess.getValue();
		if (value == null) {
			value = beanFactory.create(beanAccess.getType());
		}
		this.originalValue = value;
		try {
			setValue(value);
		} catch (PropertyVetoException e) {
			throw log.errorSetupValue(this, e, value);
		}
	}

	/**
	 * Returns the field or getter method of this component.
	 * 
	 * @return the {@link AccessibleObject}.
	 */
	public AccessibleObject getAccessibleObject() {
		return beanAccess.getGettterObject();
	}

	/**
	 * Returns the parent object of this field.
	 * 
	 * @return the parent {@link Object}.
	 */
	protected Object getParentObject() {
		return parentObject;
	}

	/**
	 * Returns the field name.
	 * 
	 * @return the {@link String} field name.
	 */
	protected String getFieldName() {
		return fieldName;
	}

	@Override
	public void setName(String name) {
		log.checkName(this, name);
		this.name = name;
		component.setName(name);
		log.nameSet(this, name);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setTitle(String title) {
		titleResource = title;
		this.title = title;
		updateTitleResource();
		log.titleSet(this, title);
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setShowTitle(boolean show) {
		this.showTitle = show;
		log.showTitleSet(this, show);
	}

	@Override
	public boolean isShowTitle() {
		return showTitle;
	}

	@Override
	public void setMnemonicString(String string) {
		this.mnemonicResource = string;
		Mnemonic mnemonic = mnemonicFactory.create(string);
		Integer code = mnemonic(mnemonic);
		if (code != null) {
			setMnemonic(code);
			setMnemonicIndex(mnemonic.getMnemonicIndex());
		} else {
			updateMnemonicResource();
		}
		log.mnemonicSet(this, string);
	}

	private Integer mnemonic(Mnemonic mnemonic) {
		return mnemonic.isValid() ? mnemonic.getMnemonic() : null;
	}

	@Override
	public void setMnemonic(int mnemonics) {
		this.mnemonic = mnemonics;
	}

	@Override
	public Integer getMnemonic() {
		return mnemonic;
	}

	@Override
	public void setMnemonicIndex(int index) {
		this.mnemonicIndex = index;
	}

	@Override
	public int getMnemonicIndex() {
		return mnemonicIndex;
	}

	@Override
	public void setToolTipText(String text) {
		text = StringUtils.isEmpty(text) ? null : text;
		toolTipResource = text;
		toolTip = text;
		updateToolTipResource();
		setupToolTipText();
	}

	private void setupToolTipText() {
		if (component instanceof JComponent) {
			JComponent jcomponent = (JComponent) component;
			jcomponent.setToolTipText(toolTip);
		}
	}

	@Override
	public String getToolTipText() {
		return toolTip;
	}

	@Override
	public void setInvalidText(String text) {
		text = StringUtils.isEmpty(text) ? null : text;
		invalidTextResource = text;
		invalidText = text;
		updateInvalidTextResource();
	}

	@Override
	public String getInvalidText() {
		return invalidText;
	}

	@Override
	public void setShowToolTip(boolean show) {
		if (show && !showToolTip) {
			showToolTip();
		} else if (!show && showToolTip) {
			hideToolTip();
		}
		this.showToolTip = show;
		log.showToolTipSet(this, show);
	}

	private void showToolTip() {
		int id = 0;
		long when = 0;
		int modifiers = 0;
		int x = 0;
		int y = 0;
		int clickCount = 0;
		ToolTipManager.sharedInstance().mouseMoved(
				new MouseEvent(component, id, when, modifiers, x, y,
						clickCount, false));
	}

	private void hideToolTip() {
		int id = 0;
		long when = 0;
		int modifiers = 0;
		int x = 0;
		int y = 0;
		int clickCount = 0;
		ToolTipManager.sharedInstance().mouseExited(
				new MouseEvent(component, id, when, modifiers, x, y,
						clickCount, false));
	}

	@Override
	public void setEnabled(boolean enabled) {
		component.setEnabled(enabled);
		this.readOnly = !enabled;
		log.enabledSet(this, enabled);
	}

	@Override
	public boolean isEnabled() {
		return !readOnly;
	}

	@Override
	public void setWidth(Number width) {
		log.checkWidth(this, width);
		this.width = width;
		log.widthSet(this, width);
	}

	@Override
	public Number getWidth() {
		return width;
	}

	@Override
	public void setHeight(Number height) {
		log.checkHeight(this, height);
		this.height = height;
		log.heightSet(this, height);
	}

	@Override
	public Number getHeight() {
		return height;
	}

	@Override
	public void setTitlePosition(TextPosition position) {
		this.titlePosition = position;
		log.titlePositionSet(this, position);
	}

	@Override
	public TextPosition getTitlePosition() {
		return titlePosition;
	}

	@Override
	public void setIconResource(String name) {
		this.iconResource = name;
		if (isEmpty(iconResource)) {
			icon = null;
		} else {
			updateIconResources();
		}
		log.iconResourceSet(this, name);
	}

	@Override
	public void setIcon(Icon icon) {
		iconResource = null;
		this.icon = icon;
		log.iconSet(this, icon);
	}

	@Override
	public Icon getIcon() {
		return icon;
	}

	@Override
	public void setIconSize(IconSize newSize) {
		iconSize = newSize;
		updateIconResources();
		log.iconSizeSet(this, iconSize);
	}

	@Override
	public IconSize getIconSize() {
		return iconSize;
	}

	@Override
	public void setLocale(Locale locale) {
		log.checkLocale(this, locale);
		this.locale = locale;
		component.setLocale(locale);
		updateTextsResources();
		updateIconResources();
		log.localeSet(this, locale);
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	@Override
	public void setOrder(int order) {
		this.order = order;
		log.orderSet(this, order);
	}

	@Override
	public int getOrder() {
		return order;
	}

	/**
	 * Sets the original value of the field. That is the value that is restored.
	 * 
	 * @param value
	 *            the original value {@link Object}.
	 */
	protected void setOriginalValue(Object value) {
		this.originalValue = value;
	}

	/**
	 * Returns the original value of the field. That is the value that is
	 * restored.
	 * 
	 * @return the original value {@link Object}.
	 */
	protected Object getOriginalValue() {
		return originalValue;
	}

	@Override
	public void setValue(Object value) throws PropertyVetoException {
		if (this.value == value) {
			return;
		}
		trySetValue(value);
		vetoValueChanged(value);
		changeValue(value);
		fireValueChanged(value);
		log.valueSet(this, value);
	}

	private void fireValueChanged(Object value) {
		propertySupport.firePropertyChange(VALUE_PROPERTY, null, value);
	}

	private void vetoValueChanged(Object value) throws PropertyVetoException {
		try {
			vetoableSupport.fireVetoableChange(VALUE_PROPERTY, null, value);
		} catch (PropertyVetoException e) {
			trySetValue(oldValue);
			throw e;
		}
	}

	/**
	 * Sets the specified value as the new value without modifying the bean
	 * object field.
	 * 
	 * @param value
	 *            the {@link Object} value.
	 */
	protected void changeValue(Object value) {
		oldValue = this.value;
		this.value = value;
	}

	/**
	 * Sets the value to the bean object field.
	 * 
	 * @param value
	 *            the {@link Object} value.
	 * 
	 * @throws PropertyVetoException
	 *             if the value is unacceptable by the bean.
	 */
	protected void trySetValue(Object value) throws PropertyVetoException {
		try {
			beanAccess.setValue(value);
		} catch (Exception e) {
			throw log.errorTrySetValue(this, e, value, VALUE_PROPERTY);
		}
	}

	@Override
	public Object getValue() {
		return value;
	}

	/**
	 * Returns the old value of this field. That is the value that was
	 * previously set.
	 * 
	 * @return the old value {@link Object}.
	 */
	public Object getOldValue() {
		return oldValue;
	}

	@Override
	public void setImages(Images images) {
		log.checkImagesResource(this, images);
		this.images = images;
		updateIconResources();
		for (FieldComponent<?> child : childFields) {
			child.setImages(images);
		}
	}

	@Override
	public Images getImages() {
		return images;
	}

	private void updateIconResources() {
		if (isEmpty(iconResource) || images == null) {
			return;
		}
		icon = new ImageIcon(images.getResource(iconResource, getLocale(),
				iconSize).getImage());
	}

	@Override
	public void setTexts(Texts texts) {
		log.checkTextsResource(this, texts);
		this.texts = texts;
		updateTextsResources();
		for (FieldComponent<?> child : childFields) {
			child.setTexts(texts);
		}
	}

	@Override
	public Texts getTexts() {
		return texts;
	}

	private void updateTextsResources() {
		updateTitleResource();
		updateToolTipResource();
		updateInvalidTextResource();
		updateMnemonicResource();
	}

	private void updateToolTipResource() {
		if (haveTextResource(toolTipResource)) {
			toolTip = getTextResource(toolTipResource, toolTip);
			setupToolTipText();
		}
	}

	private void updateTitleResource() {
		if (haveTextResource(titleResource)) {
			title = getTextResource(titleResource, title);
		}
	}

	private void updateInvalidTextResource() {
		if (haveTextResource(invalidTextResource)) {
			invalidText = getTextResource(invalidTextResource, invalidText);
		}
	}

	private void updateMnemonicResource() {
		if (haveTextResource(mnemonicResource)) {
			String string = getTextResource(mnemonicResource, null);
			if (string != null) {
				setMnemonic(mnemonicFactory.create(string).getMnemonic());
				setMnemonicIndex(mnemonicFactory.create(string)
						.getMnemonicIndex());
			}
		}
	}

	/**
	 * Tests if the text resource is available.
	 * 
	 * @param name
	 *            the {@link String} name of the text resource.
	 * 
	 * @return {@code true} if the name is not {@code null} or empty and the
	 *         texts resources are available, {@code false} otherwise.
	 */
	protected boolean haveTextResource(String name) {
		return !isEmpty(name) && texts != null;
	}

	/**
	 * Returns the text resource with the specified name and the current locale.
	 * 
	 * @param name
	 *            the {@link String} resource name.
	 * 
	 * @param defaultText
	 *            the default text for the resource.
	 * 
	 * 
	 * @return the {@link String} text resource or the default text if the text
	 *         resource could not be found.
	 * 
	 * @see #getLocale()
	 */
	protected String getTextResource(String name, String defaultText) {
		try {
			return texts.getResource(name, getLocale()).getText();
		} catch (MissingResourceException e) {
			log.textResourceMissing(this, name);
			return defaultText;
		}
	}

	/**
	 * Tests if the image resource is available.
	 * 
	 * @param name
	 *            the {@link String} name of the image resource.
	 * 
	 * @return {@code true} if the name is not {@code null} or empty and the
	 *         texts resources are available, {@code false} otherwise.
	 */
	protected boolean haveImageResource(String name) {
		return !isEmpty(name) && images != null;
	}

	/**
	 * Returns the icon resource with the specified name and the current locale.
	 * 
	 * @param name
	 *            the {@link String} resource name.
	 * 
	 * @param size
	 *            the {@link IconSize} size of the icon.
	 * 
	 * @param defaultText
	 *            the default {@link Icon} icon for the resource.
	 * 
	 * @return the {@link Icon} icon resource or the default icon if the image
	 *         resource could not be found.
	 * 
	 * @see #getLocale()
	 */
	protected Icon getIconResource(String name, IconSize size, Icon defaultIcon) {
		try {
			return new ImageIcon(images.getResource(name, getLocale(), size)
					.getImage());
		} catch (MissingResourceException e) {
			log.iconResourceMissing(this, name);
			return defaultIcon;
		}
	}

	@Override
	public void restoreInput() throws PropertyVetoException {
		for (FieldComponent<?> component : childFields) {
			component.restoreInput();
		}
		setValue(originalValue);
		log.restoredInput(this);
	}

	@Override
	public void setComponent(ComponentType component) {
		log.checkComponent(this, component);
		this.component = component;
		setName(name);
		setupToolTipText();
		setEnabled(!readOnly);
		if (locale != null) {
			setLocale(locale);
		}
	}

	@Override
	public ComponentType getComponent() {
		return component;
	}

	@Override
	public Component getAWTComponent() {
		return component;
	}

	@Override
	public void addField(FieldComponent<?> field) {
		log.checkField(this, field);
		childFields.add(field);
		Collections.sort(childFields, new FieldComponentComparator());
	}

	@Override
	public <R extends Component, T extends FieldComponent<R>> T findField(
			String name) {
		if (getName().equals(name)) {
			return asField(this);
		} else {
			return findFieldRecursive(name);
		}
	}

	@Override
	public FieldComponent<?> getField(int index) {
		return childFields.get(index);
	}

	private <R extends Component, T extends FieldComponent<R>> T findFieldRecursive(
			String name) {
		T field = null;
		for (FieldComponent<?> component : childFields) {
			if (component.getName().equals(name)) {
				field = asField(component);
			} else {
				field = component.findField(name);
			}
			if (field != null) {
				return field;
			}
		}
		return field;
	}

	@SuppressWarnings("unchecked")
	private <R extends Component, T extends FieldComponent<R>> T asField(
			FieldComponent<?> component) {
		return (T) component;
	}

	@Override
	public int getFieldsCount() {
		return childFields.size();
	}

	@Override
	public Iterable<FieldComponent<?>> getFields() {
		return new ArrayList<FieldComponent<?>>(childFields);
	}

	@Override
	public void requestFocus() {
		component.requestFocusInWindow();
	}

	@Override
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		vetoableSupport.addVetoableChangeListener(listener);
		for (FieldComponent<?> field : childFields) {
			field.addVetoableChangeListener(listener);
		}
	}

	@Override
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		vetoableSupport.removeVetoableChangeListener(listener);
		for (FieldComponent<?> field : childFields) {
			field.removeVetoableChangeListener(listener);
		}
	}

	@Override
	public void addVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		vetoableSupport.addVetoableChangeListener(propertyName, listener);
		for (FieldComponent<?> field : childFields) {
			field.addVetoableChangeListener(propertyName, listener);
		}
	}

	@Override
	public void removeVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		vetoableSupport.removeVetoableChangeListener(propertyName, listener);
		for (FieldComponent<?> field : childFields) {
			field.removeVetoableChangeListener(propertyName, listener);
		}
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.addPropertyChangeListener(listener);
		for (FieldComponent<?> field : childFields) {
			field.addPropertyChangeListener(listener);
		}
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.removePropertyChangeListener(listener);
		for (FieldComponent<?> field : childFields) {
			field.removePropertyChangeListener(listener);
		}
	}

	@Override
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertySupport.addPropertyChangeListener(propertyName, listener);
		for (FieldComponent<?> field : childFields) {
			field.addPropertyChangeListener(propertyName, listener);
		}
	}

	@Override
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertySupport.removePropertyChangeListener(propertyName, listener);
		for (FieldComponent<?> field : childFields) {
			field.removePropertyChangeListener(propertyName, listener);
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", getName()).toString();
	}
}
