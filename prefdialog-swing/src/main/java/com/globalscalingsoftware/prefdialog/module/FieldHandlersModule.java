package com.globalscalingsoftware.prefdialog.module;

import static com.google.inject.assistedinject.FactoryProvider.newFactory;

import com.globalscalingsoftware.prefdialog.internal.inputfield.checkbox.CheckBoxFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.checkbox.CheckBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandlerImpl;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.group.GroupFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.group.GroupFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.combobox.ComboBoxFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.combobox.ComboBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.filechooser.FileChooserFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.filechooser.FileChooserFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.radiobutton.RadioButtonFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.radiobutton.RadioButtonFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.slider.SliderFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.slider.SliderFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.textfield.TextFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.textfield.TextFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.textfield.formattedtextfield.FormattedTextFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.textfield.formattedtextfield.FormattedTextFieldHandlerFactory;
import com.google.inject.AbstractModule;

public class FieldHandlersModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TextFieldHandlerFactory.class).toProvider(
				newFactory(TextFieldHandlerFactory.class,
						TextFieldHandler.class));
		bind(FormattedTextFieldHandlerFactory.class).toProvider(
				newFactory(FormattedTextFieldHandlerFactory.class,
						FormattedTextFieldHandler.class));
		bind(RadioButtonFieldHandlerFactory.class).toProvider(
				newFactory(RadioButtonFieldHandlerFactory.class,
						RadioButtonFieldHandler.class));
		bind(CheckBoxFieldHandlerFactory.class).toProvider(
				newFactory(CheckBoxFieldHandlerFactory.class,
						CheckBoxFieldHandler.class));
		bind(ComboBoxFieldHandlerFactory.class).toProvider(
				newFactory(ComboBoxFieldHandlerFactory.class,
						ComboBoxFieldHandler.class));
		bind(FileChooserFieldHandlerFactory.class).toProvider(
				newFactory(FileChooserFieldHandlerFactory.class,
						FileChooserFieldHandler.class));
		bind(SliderFieldHandlerFactory.class).toProvider(
				newFactory(SliderFieldHandlerFactory.class,
						SliderFieldHandler.class));
		bind(ChildFieldHandlerFactory.class).toProvider(
				newFactory(ChildFieldHandlerFactory.class,
						ChildFieldHandlerImpl.class));
		bind(GroupFieldHandlerFactory.class).toProvider(
				newFactory(GroupFieldHandlerFactory.class,
						GroupFieldHandler.class));
	}

}
