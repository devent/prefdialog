package com.anrisoftware.prefdialog.fields.buttongroup;

import java.awt.Component;
import java.awt.Container;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;

import com.anrisoftware.prefdialog.annotations.ButtonGroup;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldPlugin;
import com.anrisoftware.prefdialog.fields.FieldPluginError;
import com.google.inject.Injector;

public class ButtonGroupFieldPlugin implements FieldPlugin {

	private Injector parentInjector;

	private LazyInitializer<ButtonGroupFieldFactory> lazyCreateFactory;

	public ButtonGroupFieldPlugin() {
		this.lazyCreateFactory = new LazyInitializer<ButtonGroupFieldFactory>() {

			@Override
			protected ButtonGroupFieldFactory initialize()
					throws ConcurrentException {
				Injector injector = parentInjector
						.createChildInjector(new ButtonGroupModule());
				return injector.getInstance(ButtonGroupFieldFactory.class);
			}
		};
	}

	@Override
	public Class<? extends Annotation> getFieldAnnotation() {
		return ButtonGroup.class;
	}

	@Override
	public <ComponentType extends Component> FieldComponent<ComponentType> getField(
			Object injector, ComponentType component, Object bean, Field field) {
		parentInjector = (Injector) injector;
		Container container = (Container) component;
		try {
			return asType(lazyCreateFactory.get()
					.create(container, bean, field));
		} catch (ConcurrentException e) {
			throw new FieldPluginError(e.getCause()).addContextValue(
					"annotation", getFieldAnnotation());
		}
	}

	@SuppressWarnings("unchecked")
	private <ComponentType extends Component> FieldComponent<ComponentType> asType(
			FieldComponent<Container> fieldComponent) {
		return (FieldComponent<ComponentType>) fieldComponent;
	}

}
