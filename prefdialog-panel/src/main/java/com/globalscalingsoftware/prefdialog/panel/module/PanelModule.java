package com.globalscalingsoftware.prefdialog.panel.module;

import static com.google.inject.assistedinject.FactoryProvider.newFactory;
import static com.google.inject.name.Names.named;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.globalscalingsoftware.prefdialog.PreferencePanelHandlerFactory;
import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.FileChooser;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.Group;
import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.annotations.Slider;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.panel.internal.PreferencePanelHandlerImpl;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FieldHandlersModule;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.checkbox.CheckBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.ChildFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.group.GroupFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.combobox.ComboBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.filechooser.FileChooserFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.radiobutton.RadioButtonFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.slider.SliderFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.TextFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.formattedtextfield.FormattedTextFieldHandlerFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class PanelModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PreferencePanelHandlerFactory.class).toProvider(
				newFactory(PreferencePanelHandlerFactory.class,
						PreferencePanelHandlerImpl.class));
		bindFactoriesMap();
		bindAnnotations();
	}

	private void bindFactoriesMap() {
		new FieldHandlersModule().configure(binder());
		Injector injector = Guice.createInjector(new FieldHandlersModule());
		Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactoriesMap = createFactoriesMap(injector);
		bind(Map.class).annotatedWith(named("field_handler_factories_map"))
				.toInstance(fieldHandlerFactoriesMap);
	}

	private Map<Class<? extends Annotation>, FieldHandlerFactory> createFactoriesMap(
			Injector injector) {
		Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactoriesMap = new HashMap<Class<? extends Annotation>, FieldHandlerFactory>();
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
		return fieldHandlerFactoriesMap;
	}

	private void bindAnnotations() {
		List<Class<? extends Annotation>> annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(Checkbox.class);
		annotations.add(FormattedTextField.class);
		annotations.add(TextField.class);
		annotations.add(RadioButton.class);
		annotations.add(ComboBox.class);
		annotations.add(Slider.class);
		annotations.add(Group.class);
		annotations.add(Child.class);
		annotations.add(FileChooser.class);
		bind(Collection.class).annotatedWith(named("field_annotations"))
				.toInstance(annotations);
	}

}
