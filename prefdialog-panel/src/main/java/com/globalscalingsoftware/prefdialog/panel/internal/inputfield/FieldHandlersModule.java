package com.globalscalingsoftware.prefdialog.panel.internal.inputfield;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.checkbox.CheckBoxFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.checkbox.CheckBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.ChildFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.ChildFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.group.GroupFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.group.GroupFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.combobox.ComboBoxFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.combobox.ComboBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.filechooser.FileChooserFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.filechooser.FileChooserFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.radiobutton.RadioButtonFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.radiobutton.RadioButtonFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.slider.SliderFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.slider.SliderFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.TextFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.TextFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.formattedtextfield.FormattedTextFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield.formattedtextfield.FormattedTextFieldHandlerFactory;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FieldHandlersModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, TextFieldHandler.class).build(TextFieldHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, FormattedTextFieldHandler.class).build(
				FormattedTextFieldHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, RadioButtonFieldHandler.class).build(
				RadioButtonFieldHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, CheckBoxFieldHandler.class).build(
				CheckBoxFieldHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, ComboBoxFieldHandler.class).build(
				ComboBoxFieldHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, FileChooserFieldHandler.class).build(
				FileChooserFieldHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, SliderFieldHandler.class).build(
				SliderFieldHandlerFactory.class));
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
