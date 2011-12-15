package com.anrisoftware.prefdialog.panel.inputfields.textfield.formattedtextfield;

import static java.lang.String.format;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.panel.inputfields.api.FormattedTextFieldHandlerFactory;
import com.google.common.collect.Maps;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;

/**
 * Binds the formatted text field classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class FormattedTextFieldModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldHandler<?>>() {
				}, FormattedTextFieldHandler.class).build(
				FormattedTextFieldHandlerFactory.class));
	}

	@Provides
	@Named("ValidatorTexts")
	Map<Class<?>, String> getValidatorTexts() {
		Map<Class<?>, String> map = Maps.newHashMap();
		map.put(Object.class, "");
		map.put(Number.class, "Must be a number");
		map.put(int.class,
				format("Must be a number from %d to %d", Integer.MIN_VALUE,
						Integer.MAX_VALUE));
		map.put(Integer.class,
				format("Must be a number from %d to %d", Integer.MIN_VALUE,
						Integer.MAX_VALUE));
		map.put(long.class,
				format("Must be a number from %d to %d", Long.MIN_VALUE,
						Long.MAX_VALUE));
		map.put(Long.class,
				format("Must be a number from %d to %d", Long.MIN_VALUE,
						Long.MAX_VALUE));
		map.put(float.class,
				format("Must be a float point number from %e to %e",
						Float.MIN_VALUE, Float.MAX_VALUE));
		map.put(Float.class,
				format("Must be a float point number from %e to %e",
						Float.MIN_VALUE, Float.MAX_VALUE));
		map.put(double.class,
				format("Must be a float point number from %e to %e",
						Double.MIN_VALUE, Double.MAX_VALUE));
		map.put(Double.class,
				format("Must be a float point number from %e to %e",
						Double.MIN_VALUE, Double.MAX_VALUE));
		map.put(String.class, "Must be a string");
		map.put(File.class, "Must be a valid file name");
		return map;
	}

	@Provides
	@Named("ValidatorFormats")
	Map<Class<?>, AbstractFormatterFactory> getValidatorFormats() {
		Map<Class<?>, AbstractFormatterFactory> map = Maps.newHashMap();
		map.put(double.class, createDoubleFormatter());
		map.put(Double.class, createDoubleFormatter());
		map.put(float.class, createDoubleFormatter());
		map.put(Float.class, createDoubleFormatter());
		map.put(int.class, createNumberFormatter(Integer.class));
		map.put(Integer.class, createNumberFormatter(Integer.class));
		map.put(short.class, createNumberFormatter(Short.class));
		map.put(Short.class, createNumberFormatter(Short.class));
		map.put(long.class, createNumberFormatter(Long.class));
		map.put(Long.class, createNumberFormatter(Long.class));
		map.put(Date.class, new DefaultFormatterFactory(new DateFormatter()));
		map.put(Object.class, new DefaultFormatterFactory(
				new DefaultFormatter()));
		return map;
	}

	private AbstractFormatterFactory createNumberFormatter(Class<?> valueClass) {
		NumberFormatter display = new NumberFormatter();
		display.setValueClass(valueClass);
		DecimalFormat editFormat = new DecimalFormat("#.#");
		NumberFormatter edit = new NumberFormatter(editFormat);
		edit.setValueClass(valueClass);
		return new DefaultFormatterFactory(display, display, edit);
	}

	private AbstractFormatterFactory createDoubleFormatter() {
		Class<Double> valueClass = Double.class;
		NumberFormatter display = new NumberFormatter();
		display.setValueClass(valueClass);
		DecimalFormat editFormat = new DecimalFormat(
				"#.########################");
		NumberFormatter edit = new NumberFormatter(editFormat);
		edit.setValueClass(valueClass);
		return new DefaultFormatterFactory(display, display, edit);
	}
}
