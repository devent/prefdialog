package com.globalscalingsoftware.prefdialog.panel.inputfield;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfield.LoggerFactory.Logger;
import com.globalscalingsoftware.prefdialog.panel.inputfield.button.ButtonModule;
import com.globalscalingsoftware.prefdialog.panel.inputfield.checkbox.CheckboxModule;
import com.globalscalingsoftware.prefdialog.panel.inputfield.child.ChildFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfield.child.ChildFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfield.child.group.GroupFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfield.child.group.GroupFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.inputfield.combobox.ComboBoxModule;
import com.globalscalingsoftware.prefdialog.panel.inputfield.filechooser.FileChooserModule;
import com.globalscalingsoftware.prefdialog.panel.inputfield.radiobutton.RadioButtonModule;
import com.globalscalingsoftware.prefdialog.panel.inputfield.slider.SliderModule;
import com.globalscalingsoftware.prefdialog.panel.inputfield.textfield.TextFieldModule;
import com.globalscalingsoftware.prefdialog.panel.inputfield.textfield.formattedtextfield.FormattedTextFieldModule;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FieldHandlersModule extends AbstractModule {

	@Override
	protected void configure() {
		requestStaticInjection(AbstractLabelFieldHandler.class);
		install(new ButtonModule());
		install(new CheckboxModule());
		install(new ComboBoxModule());
		install(new FileChooserModule());
		install(new RadioButtonModule());
		install(new SliderModule());
		install(new TextFieldModule());
		install(new FormattedTextFieldModule());
		install(new FactoryModuleBuilder()
				.implement(Logger.class, Logger.class).build(
						LoggerFactory.class));
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, ChildFieldHandler.class).build(
				ChildFieldHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, GroupFieldHandler.class).build(
				GroupFieldHandlerFactory.class));
	}

}
