package com.anrisoftware.prefdialog.classtask;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.Validate.isTrue;

import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;

import javax.inject.Inject;

import org.apache.commons.lang3.builder.Builder;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanAccess;
import com.anrisoftware.globalpom.reflection.beans.BeanAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanFactory;
import com.anrisoftware.prefdialog.annotations.FileChooserModel;
import com.google.inject.assistedinject.Assisted;

/**
 * Creates an instance from an annotation attribute.
 * <p>
 * If the parent object have a field that the attribute name references then the
 * field's value is returned. If the field have no instance, the instance is
 * created with the default constructor and the field is set. If the attribute
 * name is set to a class type, the class type is instantiated and returned.
 * <p>
 * The attribute is suffixed with "Class" so that it reference the class type
 * that should be instantiated.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ClassTask<ClassType> implements Builder<ClassType> {

	private static final String ONE_CLASS_TYPE_SET = "Must have one class type set for attribute '%s'.";

	private final String attributeName;

	private final BeanAccessFactory beanAccessFactory;

	private final AnnotationAccess fieldAnnotation;

	private final BeanFactory beanFactory;

	private Object parent;

	/**
	 * @see ClassTaskFactory#create(String, Class, AccessibleObject)
	 */
	@Inject
	ClassTask(AnnotationAccessFactory annotationAccessFactory,
			BeanAccessFactory beanAccessFactory, BeanFactory beanFactory,
			@Assisted String attributeName,
			@Assisted Class<? extends Annotation> annotationClass,
			@Assisted AccessibleObject accessibleObject) {
		this.fieldAnnotation = annotationAccessFactory.create(annotationClass,
				accessibleObject);
		this.beanAccessFactory = beanAccessFactory;
		this.beanFactory = beanFactory;
		this.attributeName = attributeName;
	}

	public ClassTask<ClassType> withParent(Object parent) {
		this.parent = parent;
		return this;
	}

	@Override
	public ClassType build() {
		return createInstance();
	}

	private ClassType createInstance() {
		String fieldName = fieldAnnotation.getValue(attributeName);
		if (!isEmpty(fieldName)) {
			return createFromField(fieldName);
		} else {
			return createModelClass();
		}
	}

	private ClassType createFromField(String fieldName) {
		BeanAccess access = beanAccessFactory.create(fieldName, parent);
		ClassType value = access.getValue();
		return value == null ? createModelFromField(access) : value;
	}

	@SuppressWarnings("unchecked")
	private ClassType createModelFromField(BeanAccess access) {
		Class<? extends ClassType> type;
		type = (Class<? extends ClassType>) access.getType();
		ClassType model = beanFactory.create(type);
		try {
			access.setValue(model);
			return model;
		} catch (PropertyVetoException e) {
			throw new NullPointerException();
		}
	}

	@SuppressWarnings("unchecked")
	private ClassType createModelClass() {
		Class<? extends FileChooserModel>[] type = fieldAnnotation
				.getValue(attributeName + "Class");
		isTrue(type.length > 0, ONE_CLASS_TYPE_SET, attributeName);
		return (ClassType) beanFactory.create(type[0]);
	}

}
