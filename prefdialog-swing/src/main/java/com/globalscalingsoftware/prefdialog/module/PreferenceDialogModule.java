/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.module;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.globalscalingsoftware.prefdialog.PreferenceDialogController;
import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.FileChooser;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.Group;
import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.annotations.Slider;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.internal.dialog.PreferenceDialogControllerImpl;
import com.globalscalingsoftware.prefdialog.internal.inputfield.checkbox.CheckBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.group.GroupFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.combobox.ComboBoxFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.filechooser.FileChooserFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.radiobutton.RadioButtonFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.slider.SliderFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.textfield.TextFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.textfield.formattedtextfield.FormattedTextFieldHandlerFactory;
import com.globalscalingsoftware.prefdialog.internal.reflection.AnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.internal.reflection.AnnotationFilter;
import com.globalscalingsoftware.prefdialog.internal.reflection.FactoriesMap;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Binds the default dependencies for the preference dialog.
 * 
 * We can use the module to create a new instance of
 * {@link PreferenceDialogController} with the help of an {@link Injector}. As
 * in the example:
 * 
 * <pre>
 * injector = Guice.createInjector(new PreferenceDialogModule());
 * controller = injector.getInstance(PreferenceDialogController.class);
 * controller.setPreferences(preferences);
 * controller.setup(owner);
 * controller.openDialog();
 * if (controller.getOption() == OK) {
 *     compute preferences
 * }
 * </pre>
 * 
 * We can use Guice to inject the preferences:
 * 
 * <pre>
 * In preferencesModule:
 * binding.bind(Object.class).annotatedWith(Names.named("preferences")).toInstance(preferences);
 * 
 * injector = Guice
 * 		.createInjector(new PreferenceDialogModule(), preferencesModule);
 * controller = injector.getInstance(PreferenceDialogController.class);
 * controller.setup(owner);
 * controller.openDialog();
 * if (controller.getOption() == OK) {
 *     compute preferences
 * }
 * </pre>
 */
public class PreferenceDialogModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AnnotationDiscovery.class).asEagerSingleton();
		bind(ReflectionToolbox.class).asEagerSingleton();
		bindPreferenceDialog();
		bindFields();
	}

	private void bindFields() {
		bindAnnotations();
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
		bind(AnnotationFilter.class).toInstance(
				new AnnotationFilter(annotations));
	}

	private void bindPreferenceDialog() {
		bind(PreferenceDialogController.class).to(
				PreferenceDialogControllerImpl.class);
	}

}
