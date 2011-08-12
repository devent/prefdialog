package com.globalscalingsoftware.prefdialog.panel.inputfields;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.FieldHandlerFactory;
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
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.ButtonGroupFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.CheckBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.ComboBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.FileChooserFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.FormattedTextFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.RadioButtonFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.SliderFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.api.TextFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.button.ButtonModule;
import com.globalscalingsoftware.prefdialog.panel.inputfields.checkbox.CheckboxModule;
import com.globalscalingsoftware.prefdialog.panel.inputfields.child.ChildFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfields.child.ChildFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.child.group.GroupFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfields.child.group.GroupFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfields.combobox.ComboBoxModule;
import com.globalscalingsoftware.prefdialog.panel.inputfields.filechooser.FileChooserModule;
import com.globalscalingsoftware.prefdialog.panel.inputfields.radiobutton.RadioButtonModule;
import com.globalscalingsoftware.prefdialog.panel.inputfields.slider.SliderModule;
import com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.TextFieldModule;
import com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.formattedtextfield.FormattedTextFieldModule;
import com.globalscalingsoftware.prefdialog.reflection.ReflectionModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;

public class PrefdialogCoreFieldsModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ButtonModule());
		install(new CheckboxModule());
		install(new ComboBoxModule());
		install(new FileChooserModule());
		install(new RadioButtonModule());
		install(new SliderModule());
		install(new TextFieldModule());
		install(new FormattedTextFieldModule());
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, ChildFieldHandler.class).build(
				ChildFieldHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, GroupFieldHandler.class).build(
				GroupFieldHandlerFactory.class));
	}

	@Provides
	@Named("field_handler_factories_map")
	Map<Class<? extends Annotation>, FieldHandlerFactory> bindFactoriesMap() {
		Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactoriesMap = new HashMap<Class<? extends Annotation>, FieldHandlerFactory>();
		Injector injector = Guice.createInjector(new PrefdialogCoreFieldsModule(),
				new ReflectionModule());
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
