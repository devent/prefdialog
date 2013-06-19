package com.anrisoftware.prefdialog.fields.combobox;

import static com.anrisoftware.prefdialog.miscswing.components.validating.AbstractValidatingComponent.VALUE_PROPERTY;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Vector;

import javax.inject.Inject;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanAccess;
import com.anrisoftware.globalpom.reflection.beans.BeanAccessFactory;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.miscswing.components.validating.ValidatingComboBoxComponent;

/**
 * Implements the combo box field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class AbstractComboBoxField extends
		AbstractTitleField<JComboBox<?>> {

	private static final String EDITABLE_ELEMENT = "editable";

	private static final String RENDERER_CLASS_ELEMENT = "rendererClass";

	private static final String MODEL_CLASS_ELEMENT = "modelClass";

	private static final String RENDERER_ELEMENT = "renderer";

	private static final String MODEL_ELEMENT = "model";

	private static final String ELEMENTS_ELEMENT = "elements";

	private AbstractComboBoxFieldLogger log;

	private final ValidatingComboBoxComponent<Object, JComboBox<?>> validating;

	private final VetoableChangeListener valueVetoListener;

	private AnnotationAccess fieldAnnotation;

	private BeanAccessFactory beanAccessFactory;

	protected AbstractComboBoxField(Object parentObject, String fieldName) {
		super(new JComboBox<Object>(), parentObject, fieldName);
		this.validating = new ValidatingComboBoxComponent<Object, JComboBox<?>>(
				getComponent());
		this.valueVetoListener = new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				changeVetoableValue(evt);
			}

		};
		setupValidating();
	}

	private void changeVetoableValue(PropertyChangeEvent evt)
			throws PropertyVetoException {
		super.trySetValue(evt.getNewValue());
		changeValue(evt.getNewValue());
	}

	private void setupValidating() {
		validating.addVetoableChangeListener(VALUE_PROPERTY, valueVetoListener);
	}

	/**
	 * Returns the annotation class of the specific combo box field. The
	 * annotation must have the following attributes:
	 * <ul>
	 * <li>model</li>
	 * <li>modelClass</li>
	 * <li>renderer</li>
	 * <li>rendererClass</li>
	 * <li>elements</li>
	 * <li>editable</li>
	 * </ul>
	 * 
	 * @return the {@link Class}.
	 */
	protected abstract Class<? extends Annotation> getAnnotationClass();

	@Inject
	void setupField(AbstractComboBoxFieldLogger logger,
			AnnotationAccessFactory annotationAccessFactory,
			BeanAccessFactory beanAccessFactory) {
		this.log = logger;
		this.fieldAnnotation = annotationAccessFactory.create(
				getAnnotationClass(), getAccessibleObject());
		this.beanAccessFactory = beanAccessFactory;
		setupModel();
		setupModelClass();
		setupRenderer();
		setupRendererClass();
		setupElements();
		setupEditable();
		getComponent().setSelectedItem(getValue());
	}

	private void setupEditable() {
		boolean editable = fieldAnnotation.getValue(EDITABLE_ELEMENT);
		setEditable(editable);
	}

	private void setupRendererClass() {
		Class<? extends ListCellRenderer<?>> type = fieldAnnotation
				.getValue(RENDERER_CLASS_ELEMENT);
		if (type != DefaultListCellRenderer.class) {
			setRendererFromClass(type);
		}
	}

	private void setRendererFromClass(Class<? extends ListCellRenderer<?>> type) {
		ListCellRenderer<?> renderer = getBeanFactory().create(type);
		setRenderer(renderer);
	}

	private void setupModelClass() {
		Class<? extends ComboBoxModel<?>> type = fieldAnnotation
				.getValue(MODEL_CLASS_ELEMENT);
		if (type != DefaultComboBoxModel.class) {
			setModelFromClass(type);
		}
	}

	private void setModelFromClass(Class<? extends ComboBoxModel<?>> type) {
		ComboBoxModel<?> model = getBeanFactory().create(type);
		setModel(model);
	}

	private void setupModel() {
		String fieldName = fieldAnnotation.getValue(MODEL_ELEMENT);
		if (!isEmpty(fieldName)) {
			setModelFromField(fieldName);
		}
	}

	private void setModelFromField(String fieldName) {
		Object parent = getParentObject();
		BeanAccess access = beanAccessFactory.create(fieldName, parent);
		ComboBoxModel<?> value = access.getValue();
		value = value == null ? createModelFromField(access) : value;
		setModel(value);
	}

	@SuppressWarnings("unchecked")
	private ComboBoxModel<?> createModelFromField(BeanAccess access) {
		Class<? extends ComboBoxModel<?>> type;
		type = (Class<? extends ComboBoxModel<?>>) access.getType();
		ComboBoxModel<?> model = getBeanFactory().create(type);
		try {
			access.setValue(model);
			return model;
		} catch (PropertyVetoException e) {
			throw log.errorSetModel(this, e);
		}
	}

	private void setupRenderer() {
		String fieldName = fieldAnnotation.getValue(RENDERER_ELEMENT);
		if (!isEmpty(fieldName)) {
			setRendererFromField(fieldName);
		}
	}

	private void setRendererFromField(String fieldName) {
		Object parent = getParentObject();
		BeanAccess access = beanAccessFactory.create(fieldName, parent);
		ListCellRenderer<?> value = access.getValue();
		value = value == null ? createRendererFromField(access) : value;
		setRenderer(value);
	}

	@SuppressWarnings("unchecked")
	private ListCellRenderer<?> createRendererFromField(BeanAccess access) {
		Class<? extends ListCellRenderer<?>> type;
		type = (Class<? extends ListCellRenderer<?>>) access.getType();
		ListCellRenderer<?> renderer = getBeanFactory().create(type);
		try {
			access.setValue(renderer);
			return renderer;
		} catch (PropertyVetoException e) {
			throw log.errorSetRenderer(this, e);
		}
	}

	private void setupElements() {
		String fieldName = fieldAnnotation.getValue(ELEMENTS_ELEMENT);
		if (isEmpty(fieldName)) {
			return;
		}
		Object parent = getParentObject();
		BeanAccess access = beanAccessFactory.create(fieldName, parent);
		Object value = access.getValue();
		setElements(value);
	}

	@Override
	protected void trySetValue(Object value) throws PropertyVetoException {
		validating.setValue(value);
	}

	/**
	 * Sets the specified model for the combo box field.
	 * 
	 * @param model
	 *            the {@link ComboBoxModel}.
	 * 
	 * @throws NullPointerException
	 *             if the specified model is {@code null}.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setModel(ComboBoxModel<?> model) {
		log.checkModel(this, model);
		getComponent().setModel((ComboBoxModel) model);
		log.modelSet(this, model);
	}

	/**
	 * Returns the model of the combo box field.
	 * 
	 * @return the {@link ComboBoxModel}.
	 */
	public ComboBoxModel<?> getModel() {
		return getComponent().getModel();
	}

	/**
	 * Sets the specified renderer for the combo box field.
	 * 
	 * @param renderer
	 *            the {@link ListCellRenderer}.
	 * 
	 * @throws NullPointerException
	 *             if the specified renderer is {@code null}.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setRenderer(ListCellRenderer<?> renderer) {
		log.checkRenderer(this, renderer);
		getComponent().setRenderer((ListCellRenderer) renderer);
		log.rendererSet(this, renderer);
	}

	/**
	 * Returns the renderer of the combo box field.
	 * 
	 * @return the {@link ListCellRenderer}.
	 */
	public ListCellRenderer<?> getRenderer() {
		return getComponent().getRenderer();
	}

	/**
	 * Sets the specified elements for this combo box fields.
	 * 
	 * @param elements
	 *            the elements to set.
	 * 
	 * @throws NullPointerException
	 *             if the specified elements are {@code null}.
	 * 
	 * @throws IllegalArgumentException
	 *             if the elements type is not of array or {@link Iterable}.
	 */
	public void setElements(Object elements) {
		log.checkElements(this, elements);
		if (elements.getClass().isArray()) {
			setElements((Object[]) elements);
		} else if (elements instanceof Iterable) {
			setElements((Iterable<?>) elements);
		} else {
			throw log.unsupportedType(this, elements);
		}
	}

	/**
	 * Sets the specified elements for this combo box fields.
	 * 
	 * @param newElements
	 *            the elements array to set.
	 * 
	 * @throws NullPointerException
	 *             if the specified elements are {@code null}.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setElements(Object[] elements) {
		log.checkElements(this, elements);
		getComponent().setModel(new DefaultComboBoxModel(elements));
		log.elementsSet(this, elements);
	}

	/**
	 * Sets the specified elements for this combo box fields.
	 * 
	 * @param elements
	 *            the {@link List} elements to set.
	 * 
	 * @throws NullPointerException
	 *             if the specified elements are {@code null}.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setElements(Iterable<?> elements) {
		log.checkElements(this, elements);
		getComponent().setModel(new DefaultComboBoxModel(asList(elements)));
		log.elementsSet(this, elements);
	}

	private Vector<Object> asList(Iterable<?> elements) {
		Vector<Object> list = new Vector<Object>();
		for (Object object : elements) {
			list.add(object);
		}
		return list;
	}

	/**
	 * Sets if the field should be editable.
	 * 
	 * @param editable
	 *            {@code true} if the combo box should be editable or
	 *            {@code false} if not.
	 */
	public void setEditable(boolean editable) {
		getComponent().setEditable(editable);
	}

	/**
	 * Returns if the field should is editable.
	 * 
	 * @return {@code true} if the combo box is editable or {@code false} if
	 *         not.
	 */
	public boolean isEditable() {
		return getComponent().isEditable();
	}

}
