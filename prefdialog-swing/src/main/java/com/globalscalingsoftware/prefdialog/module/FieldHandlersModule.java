package com.globalscalingsoftware.prefdialog.module;

import static com.google.inject.assistedinject.FactoryProvider.newFactory;

import com.globalscalingsoftware.prefdialog.internal.inputfield.checkbox.CheckBoxFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.checkbox.CheckBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.group.GroupFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.group.GroupFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.radiobutton.RadioButtonFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.radiobutton.RadioButtonFieldHandlerFactory;
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
		bind(ChildFieldHandlerFactory.class).toProvider(
				newFactory(ChildFieldHandlerFactory.class,
						ChildFieldHandler.class));
		bind(GroupFieldHandlerFactory.class).toProvider(
				newFactory(GroupFieldHandlerFactory.class,
						GroupFieldHandler.class));
	}

}
