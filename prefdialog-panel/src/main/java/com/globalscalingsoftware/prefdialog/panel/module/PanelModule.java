package com.globalscalingsoftware.prefdialog.panel.module;

import static com.google.inject.assistedinject.FactoryProvider.newFactory;

import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
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
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.FactoriesMap;
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
						PreferencePanelHandler.class));
		bindFactories();
	}

	private void bindFactories() {
		Injector injector = Guice.createInjector(new FieldHandlersModule());
		FactoriesMap factories = new FactoriesMap();
		factories.put(TextField.class,
				injector.getInstance(TextFieldHandlerFactory.class));
		factories.put(FormattedTextField.class,
				injector.getInstance(FormattedTextFieldHandlerFactory.class));
		factories.put(RadioButton.class,
				injector.getInstance(RadioButtonFieldHandlerFactory.class));
		factories.put(Checkbox.class,
				injector.getInstance(CheckBoxFieldHandlerFactory.class));
		factories.put(ComboBox.class,
				injector.getInstance(ComboBoxFieldHandlerFactory.class));
		factories.put(FileChooser.class,
				injector.getInstance(FileChooserFieldHandlerFactory.class));
		factories.put(Slider.class,
				injector.getInstance(SliderFieldHandlerFactory.class));
		factories.put(Child.class,
				injector.getInstance(ChildFieldHandlerFactory.class));
		factories.put(Group.class,
				injector.getInstance(GroupFieldHandlerFactory.class));
		bind(FactoriesMap.class).toInstance(factories);
	}

}
