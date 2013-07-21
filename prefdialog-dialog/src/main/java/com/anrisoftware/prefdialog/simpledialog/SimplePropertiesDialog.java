package com.anrisoftware.prefdialog.simpledialog;

import static com.anrisoftware.prefdialog.simpledialog.SimpleDialogModule.getSimplePropertiesDialogFactory;
import static java.util.ServiceLoader.load;

import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.inject.Inject;
import javax.swing.JDialog;

import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelField;
import com.anrisoftware.resources.texts.api.Texts;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

/**
 * Simple dialog with approve, restore and cancel buttons.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class SimplePropertiesDialog extends SimpleDialog {

	/**
	 * Decorates the dialog.
	 * 
	 * @param dialog
	 *            the {@link JDialog}.
	 * 
	 * @param texts
	 *            the {@link Texts} resources.
	 * 
	 * @param parent
	 *            the parent {@link Injector}.
	 * 
	 * @see SimplePropertiesDialogFactory#create(Object, String)
	 * 
	 * @return the {@link CsvImportDialog}.
	 */
	public static SimplePropertiesDialog decorate(JDialog dialog,
			Object properties, String panelFieldName, Texts texts,
			Injector parent) {
		SimplePropertiesDialog simpleDialog = getSimplePropertiesDialogFactory(
				parent).create(properties, panelFieldName);
		simpleDialog.setParent(parent);
		simpleDialog.setTexts(texts);
		simpleDialog.setDialog(dialog);
		return simpleDialog;
	}

	private static final String PREFERENCES_PANEL_NAME = "VerticalPreferencesPanel";

	private final SimplePropertiesDialogLogger log;

	private final Object properties;

	private final String panelFieldName;

	private VerticalPreferencesPanelField panel;

	private Injector parent;

	/**
	 * @see SimplePropertiesDialogFactory#create(Object, String)
	 */
	@Inject
	SimplePropertiesDialog(SimplePropertiesDialogLogger logger,
			@Assisted Object properties, @Assisted String panelFieldName) {
		this.log = logger;
		this.properties = properties;
		this.panelFieldName = panelFieldName;
	}

	/**
	 * @see SimpleDialog#setTexts(Texts)
	 */
	@Override
	public void setTexts(Texts texts) {
		super.setTexts(texts);
		if (panel != null) {
			panel.setTexts(texts);
		}
	}

	/**
	 * Sets the parent dependencies.
	 * 
	 * @param parent
	 *            the parent dependencies or {@code null}.
	 */
	public void setParent(Object parent) {
		this.parent = (Injector) parent;
	}

	@Override
	public SimpleDialog createDialog() {
		this.panel = createPanel();
		panel.createPanel(parent);
		panel.setTexts(getTexts());
		setFieldsPanel(panel.getAWTComponent());
		return super.createDialog();
	}

	private VerticalPreferencesPanelField createPanel() {
		return (VerticalPreferencesPanelField) findPanelFactory().create(
				properties, panelFieldName);
	}

	private FieldFactory<?> findPanelFactory() {
		for (FieldService service : load(FieldService.class)) {
			if (isPreferencesPanelService(service)) {
				return service.getFactory(parent);
			}
		}
		throw log.errorFindService(this, PREFERENCES_PANEL_NAME);
	}

	private boolean isPreferencesPanelService(FieldService service) {
		return service.getInfo().getAnnotationType().getSimpleName()
				.equals(PREFERENCES_PANEL_NAME);
	}

	@Override
	public void openDialog() {
		panel.requestFocus();
		super.openDialog();
	}

	@Override
	public void restoreDialog() {
		try {
			panel.restoreInput();
		} catch (PropertyVetoException e) {
		}
	}

	/**
	 * @see FieldComponent#addVetoableChangeListener(VetoableChangeListener)
	 * @see SimpleDialog#addVetoableChangeListener(VetoableChangeListener)
	 */
	@Override
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		panel.addVetoableChangeListener(listener);
		super.addVetoableChangeListener(listener);
	}

	/**
	 * @see FieldComponent#removeVetoableChangeListener(VetoableChangeListener)
	 * @see SimpleDialog#removeVetoableChangeListener(VetoableChangeListener)
	 */
	@Override
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		panel.removeVetoableChangeListener(listener);
		super.removeVetoableChangeListener(listener);
	}

	/**
	 * @see FieldComponent#addVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see SimpleDialog#addVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 */
	@Override
	public void addVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		panel.addVetoableChangeListener(propertyName, listener);
		super.addVetoableChangeListener(propertyName, listener);
	}

	/**
	 * @see FieldComponent#removeVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 * @see SimpleDialog#removeVetoableChangeListener(String,
	 *      VetoableChangeListener)
	 */
	@Override
	public void removeVetoableChangeListener(String propertyName,
			VetoableChangeListener listener) {
		panel.removeVetoableChangeListener(propertyName, listener);
		super.removeVetoableChangeListener(propertyName, listener);
	}

}
