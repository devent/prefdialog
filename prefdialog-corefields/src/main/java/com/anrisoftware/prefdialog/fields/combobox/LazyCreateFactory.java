package com.anrisoftware.prefdialog.fields.combobox;

import java.awt.Container;
import java.lang.reflect.Field;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;

import com.anrisoftware.prefdialog.fields.FieldPluginError;
import com.google.inject.Injector;

/**
 * Lazy initialize the combo box factory for the color button field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class LazyCreateFactory extends LazyInitializer<ComboBoxFieldFactory> {

	private Injector parentInjector;

	public void setParentInjector(Injector parentInjector) {
		this.parentInjector = parentInjector;
	}

	/**
	 * Returns the combo box field for the specified bean, field and container.
	 * 
	 * @param bean
	 *            the bean {@link Object} where the field is defined.
	 * 
	 * @param field
	 *            the {@link Field} of the child field.
	 * 
	 * @param container
	 *            the {@link Container} that will contain the color button
	 *            field.
	 * 
	 * @return the {@link ComboBoxField}.
	 */
	public ComboBoxField lazyCreateField(Object bean, Field field,
			Container container) {
		try {
			return get().create(container, bean, field);
		} catch (ConcurrentException e) {
			throw createError(e, bean, field);
		}
	}

	private FieldPluginError createError(Exception e, Object bean, Field field) {
		return new FieldPluginError("Error create combo box field",
				e.getCause()).addContextValue("bean", bean).addContextValue(
				"field", field);
	}

	@Override
	protected ComboBoxFieldFactory initialize() throws ConcurrentException {
		Injector injector = parentInjector
				.createChildInjector(new ComboBoxModule());
		return injector.getInstance(ComboBoxFieldFactory.class);
	}

}
