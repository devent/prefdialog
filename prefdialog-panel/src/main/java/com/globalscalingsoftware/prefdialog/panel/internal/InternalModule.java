package com.globalscalingsoftware.prefdialog.panel.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.globalscalingsoftware.prefdialog.PreferencePanelHandlerFactory;
import com.globalscalingsoftware.prefdialog.annotations.ButtonGroup;
import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.FileChooser;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.Group;
import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.annotations.Slider;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.panel.internal.ChildFieldHandlerWorker.ChildFieldHandlerWorkerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FactoriesMap;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FactoriesMap.FactoriesMapFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldHandlersModule;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.button.ButtonGroupFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.checkbox.CheckBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.ChildFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.group.GroupFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.combobox.ComboBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.filechooser.FileChooserFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.radiobutton.RadioButtonFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.slider.SliderFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.TextFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.formattedtextfield.FormattedTextFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.reflection.ReflectionModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;

public class InternalModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<PreferencePanelHandler>() {
				}, PreferencePanelHandlerImpl.class).build(
				PreferencePanelHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(FactoriesMap.class,
				FactoriesMap.class).build(FactoriesMapFactory.class));
		install(new FactoryModuleBuilder().implement(
				ChildFieldHandlerWorker.class, ChildFieldHandlerWorker.class)
				.build(ChildFieldHandlerWorkerFactory.class));
		install(new ReflectionModule());
		install(new FieldHandlersModule());
	}

	@Provides
	@Named("field_handler_factories_map")
	Map<Class<? extends Annotation>, FieldHandlerFactory> bindFactoriesMap() {
		Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactoriesMap = new HashMap<Class<? extends Annotation>, FieldHandlerFactory>();
		Injector injector = Guice.createInjector(new FieldHandlersModule());
		fieldHandlerFactoriesMap.put(TextField.class,
				injector.getInstance(TextFieldHandlerFactory.class));
		fieldHandlerFactoriesMap.put(FormattedTextField.class,
				injector.getInstance(FormattedTextFieldHandlerFactory.class));
		fieldHandlerFactoriesMap.put(RadioButton.class,
				injector.getInstance(RadioButtonFieldHandlerFactory.class));
		fieldHandlerFactoriesMap.put(Checkbox.class,
				injector.getInstance(CheckBoxFieldHandlerFactory.class));
		fieldHandlerFactoriesMap.put(ComboBox.class,
				injector.getInstance(ComboBoxFieldHandlerFactory.class));
		fieldHandlerFactoriesMap.put(FileChooser.class,
				injector.getInstance(FileChooserFieldHandlerFactory.class));
		fieldHandlerFactoriesMap.put(Slider.class,
				injector.getInstance(SliderFieldHandlerFactory.class));
		fieldHandlerFactoriesMap.put(Child.class,
				injector.getInstance(ChildFieldHandlerFactory.class));
		fieldHandlerFactoriesMap.put(Group.class,
				injector.getInstance(GroupFieldHandlerFactory.class));
		fieldHandlerFactoriesMap.put(ButtonGroup.class,
				injector.getInstance(ButtonGroupFieldHandlerFactory.class));
		return fieldHandlerFactoriesMap;
	}

	@Provides
	@Named("field_annotations")
	Collection<Class<? extends Annotation>> bindFieldAnnotations() {
		Collection<Class<? extends Annotation>> annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(Checkbox.class);
		annotations.add(FormattedTextField.class);
		annotations.add(TextField.class);
		annotations.add(RadioButton.class);
		annotations.add(ComboBox.class);
		annotations.add(Slider.class);
		annotations.add(Group.class);
		annotations.add(Child.class);
		annotations.add(FileChooser.class);
		annotations.add(ButtonGroup.class);
		return annotations;
	}

	@Provides
	@Named("child_annotations")
	Collection<Class<? extends Annotation>> bindChildAnnotations() {
		Collection<Class<? extends Annotation>> annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(Child.class);
		return annotations;
	}

}
