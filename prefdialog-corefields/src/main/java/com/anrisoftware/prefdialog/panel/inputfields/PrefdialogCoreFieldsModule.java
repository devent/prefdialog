package com.anrisoftware.prefdialog.panel.inputfields;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.anrisoftware.prefdialog.FieldHandlerFactory;
import com.anrisoftware.prefdialog.annotations.ButtonGroup;
import com.anrisoftware.prefdialog.annotations.Checkbox;
import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.annotations.ColorButton;
import com.anrisoftware.prefdialog.annotations.ComboBox;
import com.anrisoftware.prefdialog.annotations.FileChooser;
import com.anrisoftware.prefdialog.annotations.FontChooser;
import com.anrisoftware.prefdialog.annotations.FormattedTextField;
import com.anrisoftware.prefdialog.annotations.Group;
import com.anrisoftware.prefdialog.annotations.RadioButton;
import com.anrisoftware.prefdialog.annotations.Slider;
import com.anrisoftware.prefdialog.annotations.TextField;
import com.anrisoftware.prefdialog.panel.inputfields.api.ButtonGroupFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.api.CheckBoxFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.api.ChildFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.api.ColorButtonFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.api.ComboBoxFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.api.FileChooserFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.api.FontChooserFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.api.FormattedTextFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.api.GroupFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.api.RadioButtonFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.api.SliderFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.api.TextFieldHandlerFactory;
import com.anrisoftware.prefdialog.panel.inputfields.button.ButtonModule;
import com.anrisoftware.prefdialog.panel.inputfields.checkbox.CheckboxModule;
import com.anrisoftware.prefdialog.panel.inputfields.child.ChildModule;
import com.anrisoftware.prefdialog.panel.inputfields.colorbutton.ColorButtonModule;
import com.anrisoftware.prefdialog.panel.inputfields.combobox.ComboBoxModule;
import com.anrisoftware.prefdialog.panel.inputfields.filechooser.FileChooserModule;
import com.anrisoftware.prefdialog.panel.inputfields.fontchooser.FontChooserModule;
import com.anrisoftware.prefdialog.panel.inputfields.group.GroupModule;
import com.anrisoftware.prefdialog.panel.inputfields.radiobutton.RadioButtonModule;
import com.anrisoftware.prefdialog.panel.inputfields.slider.SliderModule;
import com.anrisoftware.prefdialog.panel.inputfields.textfield.TextFieldModule;
import com.anrisoftware.prefdialog.panel.inputfields.textfield.formattedtextfield.FormattedTextFieldModule;
import com.anrisoftware.prefdialog.reflection.ReflectionModule;
import com.anrisoftware.prefdialog.swingutils.SwingUtilsModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;

/**
 * Binds the core fields classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class PrefdialogCoreFieldsModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new SwingUtilsModule());
		install(new ButtonModule());
		install(new CheckboxModule());
		install(new ComboBoxModule());
		install(new FileChooserModule());
		install(new RadioButtonModule());
		install(new SliderModule());
		install(new TextFieldModule());
		install(new FormattedTextFieldModule());
		install(new ChildModule());
		install(new GroupModule());
		install(new ColorButtonModule());
		install(new FontChooserModule());
	}

	@Provides
	@Named("field_handler_factories_map")
	Map<Class<? extends Annotation>, FieldHandlerFactory> bindFactoriesMap() {
		Map<Class<? extends Annotation>, FieldHandlerFactory> fieldHandlerFactoriesMap = new HashMap<Class<? extends Annotation>, FieldHandlerFactory>();
		Injector injector = Guice.createInjector(
				new PrefdialogCoreFieldsModule(), new ReflectionModule());
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
		fieldHandlerFactoriesMap.put(ColorButton.class,
				injector.getInstance(ColorButtonFieldHandlerFactory.class));
		fieldHandlerFactoriesMap.put(FontChooser.class,
				injector.getInstance(FontChooserFieldHandlerFactory.class));
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
		annotations.add(ColorButton.class);
		annotations.add(FontChooser.class);
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
