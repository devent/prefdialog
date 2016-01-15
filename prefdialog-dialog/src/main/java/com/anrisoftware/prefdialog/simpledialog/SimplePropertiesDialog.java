/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-dialog.
 *
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.simpledialog;

import static com.anrisoftware.prefdialog.simpledialog.SimpleDialogModule.getSimplePropertiesDialogFactory;
import static java.util.ServiceLoader.load;

import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelField;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
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
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     *
     * @param dialog
     *            the {@link JDialog}.
     *
     * @see SimplePropertiesDialogFactory#create(Object, String)
     *
     * @return the {@link CsvImportDialog}.
     */
	@OnAwt
	public static SimplePropertiesDialog decorate(JDialog dialog,
			Object properties, String panelFieldName) {
		SimplePropertiesDialog simpleDialog = getSimplePropertiesDialogFactory()
				.create(properties, panelFieldName);
		simpleDialog.setDialog(dialog);
		return simpleDialog.createDialog();
	}

	private static final String PREFERENCES_PANEL_NAME = "VerticalPreferencesPanel";

	private final Object properties;

	private final String panelFieldName;

	@Inject
	private Injector parent;

	@Inject
	private SimplePropertiesDialogLogger log;

	private VerticalPreferencesPanelField panel;

	/**
	 * @see SimplePropertiesDialogFactory#create(Object, String)
	 */
	@Inject
	protected SimplePropertiesDialog(@Assisted("properties") Object properties,
			@Assisted("panelFieldName") String panelFieldName) {
		this.properties = properties;
		this.panelFieldName = panelFieldName;
	}

	@Override
	@OnAwt
	public void setTexts(Texts texts) {
		super.setTexts(texts);
		if (panel != null) {
			panel.setTexts(texts);
		}
	}

	@Override
	@OnAwt
	public void setImages(Images images) {
		super.setImages(images);
		if (panel != null) {
			panel.setImages(images);
		}
	}

	@Override
	@OnAwt
	public void setLocale(Locale locale) {
		super.setLocale(locale);
		if (panel != null) {
			panel.setLocale(locale);
		}
	}

	    /**
     * Sets the icon size for the properties dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     *
     * @param size
     *            the {@link IconSize}.
     */
	@OnAwt
	public void setIconSize(IconSize size) {
		getField().setIconSize(size);
	}

	    /**
     * Returns the icon size for the properties dialog.
     *
     * @return the {@link IconSize}.
     */
	public IconSize getIconSize() {
		return getField().getIconSize();
	}

	    /**
     * Sets the parent dependencies.
     *
     * @param parent
     *            the parent dependencies.
     *
     * @throws NullPointerException
     *             if the specified parent is {@code null}.
     */
	public void setParent(Object parent) {
		log.checkParent(this, parent);
		this.parent = (Injector) parent;
	}

	    /**
     * Returns the properties field.
     *
     * @return the {@link FieldComponent}.
     */
	public FieldComponent<JPanel> getField() {
		return panel;
	}

	    /**
     * Returns the properties of the dialog.
     *
     * @return the properties {@link Object}.
     */
	public Object getProperties() {
		return properties;
	}

	@Override
	public SimplePropertiesDialog createDialog() {
		this.panel = createPanel();
		panel.createPanel(parent);
		if (getTexts() != null) {
			panel.setTexts(getTexts());
		}
		if (getImages() != null) {
			panel.setImages(getImages());
		}
		setFieldsPanel(panel.getAWTComponent());
		return (SimplePropertiesDialog) super.createDialog();
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
	@OnAwt
    public void openDialog() throws PropertyVetoException {
		panel.requestFocus();
		super.openDialog();
	}

	@Override
	@OnAwt
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
