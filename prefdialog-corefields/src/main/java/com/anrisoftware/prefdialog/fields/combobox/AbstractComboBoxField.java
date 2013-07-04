package com.anrisoftware.prefdialog.fields.combobox;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Vector;

import javax.inject.Inject;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;

import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClass;
import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClassFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanAccess;
import com.anrisoftware.globalpom.reflection.beans.BeanAccessFactory;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingComboBoxUi;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Implements the combo box field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractComboBoxField<ComponentType extends JComboBox<?>>
		extends AbstractTitleField<ComponentType> {

	private static final String EDITABLE_ELEMENT = "editable";

	private static final String RENDERER_ELEMENT = "renderer";

	private static final String MODEL_ELEMENT = "model";

	private static final String ELEMENTS_ELEMENT = "elements";

	private static final String EDITOR_ELEMENT = "editor";

	private AbstractComboBoxFieldLogger log;

	private ValidatingComboBoxUi validating;

	private final Class<? extends Annotation> annotationType;

	private transient AnnotationAccess fieldAnnotation;

	private transient AnnotationClass<?> annotationClass;

	private transient BeanAccessFactory beanAccessFactory;

	private ActionListener dataListener;

	/**
	 * @see AbstractTitleField#AbstractTitleField(java.awt.Component, Object,
	 *      String)
	 * 
	 * @param annotationType
	 *            the annotation {@link Class} type of the specific combo box
	 *            field. The annotation must have the following attributes:
	 *            <ul>
	 *            <li>model</li>
	 *            <li>modelClass</li>
	 *            <li>renderer</li>
	 *            <li>rendererClass</li>
	 *            <li>elements</li>
	 *            <li>editable</li>
	 *            <li>editor</li>
	 *            <li>editorClass</li>
	 *            </ul>
	 */
	protected AbstractComboBoxField(Class<? extends Annotation> annotationType,
			ComponentType component, Object parentObject, String fieldName) {
		super(component, parentObject, fieldName);
		this.annotationType = annotationType;
	}

	/**
	 * Reads the attributes of the annotation and setups the field.
	 */
	@Inject
	void setupField(AbstractComboBoxFieldLogger logger,
			AnnotationAccessFactory annotationAccessFactory,
			AnnotationClassFactory classFactory,
			BeanAccessFactory beanAccessFactory) {
		this.log = logger;
		this.annotationClass = classFactory.create(getParentObject(),
				annotationType, getAccessibleObject());
		this.fieldAnnotation = annotationAccessFactory.create(annotationType,
				getAccessibleObject());
		this.beanAccessFactory = beanAccessFactory;
		this.dataListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setValue(getComponent().getSelectedItem());
				} catch (PropertyVetoException e1) {
				}
			}
		};
		setupModel();
		setupRenderer();
		setupElements();
		setupEditable();
		setupEditor();
		setupComboBox();
	}

	private void setupComboBox() {
		ComponentType component = getComponent();
		component.setSelectedItem(getValue());
		component.addActionListener(dataListener);
		validating = ValidatingComboBoxUi.decorate(component);
		validating.setInvalidText(getInvalidText());
	}

	private void setupEditor() {
		ComboBoxEditor editor = (ComboBoxEditor) annotationClass.forAttribute(
				EDITOR_ELEMENT).build();
		if (editor != null) {
			setEditor(editor);
		}
	}

	private void setupEditable() {
		boolean editable = fieldAnnotation.getValue(EDITABLE_ELEMENT);
		setEditable(editable);
	}

	private void setupModel() {
		ComboBoxModel<?> model = (ComboBoxModel<?>) annotationClass
				.forAttribute(MODEL_ELEMENT).build();
		if (model != null) {
			setModel(model);
		}
	}

	private void setupRenderer() {
		ListCellRenderer<?> renderer = (ListCellRenderer<?>) annotationClass
				.forAttribute(RENDERER_ELEMENT).build();
		if (renderer != null) {
			setRenderer(renderer);
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
	public void setValue(Object value) throws PropertyVetoException {
		try {
			super.setValue(value);
			if (validating != null) {
				validating.setValid(true);
			}
			getComponent().setSelectedItem(value);
		} catch (PropertyVetoException e) {
			if (validating != null) {
				validating.setValid(false);
			}
			throw e;
		}
	}

	@Override
	public void setInvalidText(String text) {
		super.setInvalidText(text);
		if (validating != null) {
			validating.setInvalidText(getInvalidText());
		}
	}

	@Override
	public void setTexts(Texts texts) {
		super.setTexts(texts);
		if (validating != null) {
			validating.setInvalidText(getInvalidText());
		}
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
	 * Sets the specified editor for the combo box field.
	 * 
	 * @param editor
	 *            the {@link ComboBoxEditor}.
	 * 
	 * @throws NullPointerException
	 *             if the specified editor is {@code null}.
	 */
	public void setEditor(ComboBoxEditor editor) {
		log.checkEditor(this, editor);
		getComponent().setEditor(editor);
		log.editorSet(this, editor);
	}

	/**
	 * Returns the editor of the combo box field.
	 * 
	 * @return the {@link ComboBoxEditor}.
	 */
	public ComboBoxEditor getEditor() {
		return getComponent().getEditor();
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
