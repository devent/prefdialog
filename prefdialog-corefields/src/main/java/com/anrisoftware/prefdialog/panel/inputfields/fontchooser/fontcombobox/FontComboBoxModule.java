package com.anrisoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox;

import javax.swing.ComboBoxEditor;
import javax.swing.ListCellRenderer;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;

/**
 * Binds the font combobox classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class FontComboBoxModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(FontComboBox.class,
				FontComboBox.class).build(FontComboBoxFactory.class));
		bind(ListCellRenderer.class).annotatedWith(
				Names.named("FontComboBoxRenderer")).to(
				FontComboBoxListCellRenderer.class);
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<ComboBoxEditor>() {
				}, FontComboBoxEditor.class).build(
				FontComboBoxEditorFactory.class));
	}

}
