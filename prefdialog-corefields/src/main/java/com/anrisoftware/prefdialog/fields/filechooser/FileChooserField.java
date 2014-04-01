/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.filechooser;

import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedPropertyChangeListener.lockedPropertyChangeListener;
import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedVetoableChangeListener.lockedVetoableChangeListener;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.lang.annotation.Annotation;

import javax.inject.Inject;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.anrisoftware.globalpom.mnemonic.Mnemonic;
import com.anrisoftware.globalpom.mnemonic.MnemonicFactory;
import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClassFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.globalpom.textposition.TextPosition;
import com.anrisoftware.prefdialog.annotations.FileChooser;
import com.anrisoftware.prefdialog.annotations.FileChooserModel;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.miscswing.filetextfield.FileTextField;
import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedPropertyChangeListener;
import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedVetoableChangeListener;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;
import com.google.inject.assistedinject.Assisted;

/**
 * Field to show and select a file. Opens a dialog to select the file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class FileChooserField extends AbstractTitleField<JPanel> {

    /**
     * Suffix to the name of the file field panel.
     */
    public static final String FILE_FIELD_PANEL_NAME = "fileFieldPanel";

    /**
     * Suffix to the name of the file text field.
     */
    public static final String FILE_FIELD_NAME = "fileField";

    /**
     * Suffix to the name of the open file chooser button.
     */
    public static final String OPEN_FILE_CHOOSER_NAME = "openFileChooser";

    private static final Class<? extends Annotation> ANNOTATION_CLASS = FileChooser.class;

    private static final String MODEL_ELEMENT = "model";

    private static final String BUTTON_TEXT_ELEMENT = "buttonText";

    private static final String BUTTON_MNEMONIC_ELEMENT = "buttonMnemonic";

    private static final String BUTTON_TEXT_POSITION_ELEMENT = "buttonTextPosition";

    private static final String BUTTON_ICON_ELEMENT = "buttonIcon";

    private static final String BUTTON_ICON_SIZE_ELEMENT = "buttonIconSize";

    private final FileChooserFieldLogger log;

    private final FileTextField fileTextField;

    private final OpenDialogAction openDialogAction;

    private final LockedVetoableChangeListener fileListener;

    private final UiPanel panel;

    private final ActionListener fileAction;

    private transient AnnotationClassFactory annotationClassFactory;

    private String buttonTextResource;

    private String buttonText;

    private FileChooserModel model;

    private AnnotationAccess annotationAccess;

    private String buttonMnemonicResource;

    private Integer buttonMnemonic;

    private MnemonicFactory mnemonicFactory;

    private int buttonMnemonicIndex;

    private TextPosition buttonTextPosition;

    private String buttonIconResource;

    private Icon buttonIcon;

    private IconSize buttonIconSize;

    private final LockedPropertyChangeListener fileTextFieldValueListener;

    /**
     * @see FileChooserFieldFactory#create(Object, String)
     */
    @Inject
    FileChooserField(FileChooserFieldLogger logger, UiPanel panel,
            FileTextField fileTextField, OpenDialogAction openDialogAction,
            @Assisted Object parentObject, @Assisted String fieldName) {
        super(panel, parentObject, fieldName);
        this.panel = panel;
        this.log = logger;
        this.fileTextField = fileTextField;
        this.openDialogAction = openDialogAction;
        this.fileListener = lockedVetoableChangeListener(new VetoableChangeListener() {

            @Override
            public void vetoableChange(PropertyChangeEvent evt)
                    throws PropertyVetoException {
                try {
                    fileListener.lock();
                    setValue(evt.getNewValue());
                } finally {
                    fileListener.unlock();
                }
            }
        });
        this.fileTextFieldValueListener = lockedPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    setValue(evt.getNewValue());
                } catch (PropertyVetoException e) {
                }
            }
        });
        this.fileAction = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setValue(FileChooserField.this.fileTextField.getValue());
                } catch (PropertyVetoException e1) {
                }
            }
        };
        setupPanel();
    }

    private void setupPanel() {
        panel.setFileField(fileTextField);
        panel.getOpenFileChooser().addActionListener(openDialogAction);
        fileTextField.addActionListener(fileAction);
        fileTextField.addPropertyChangeListener("value",
                fileTextFieldValueListener);
    }

    @Inject
    void setupFileChooserField(AnnotationClassFactory annotationClassFactory,
            AnnotationAccessFactory annotationAccessFactory,
            MnemonicFactory mnemonicFactory) {
        this.annotationClassFactory = annotationClassFactory;
        this.annotationAccess = annotationAccessFactory.create(
                ANNOTATION_CLASS, getAccessibleObject());
        this.mnemonicFactory = mnemonicFactory;
        setupModel();
        setupButtonText();
        setupButtonMnemonic();
        setupButtonIcon();
        setupButtonTextPosition();
        setupButtonIconSize();
        setupButton();
    }

    private void setupButton() {
        JButton button = getOpenFileChooser();
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        button.setMargin(new Insets(0, 4, 0, 4));
    }

    private void setupButtonIconSize() {
        IconSize size = annotationAccess.getValue(BUTTON_ICON_SIZE_ELEMENT);
        setButtonIconSize(size);
    }

    private void setupButtonIcon() {
        String name = annotationAccess.getValue(BUTTON_ICON_ELEMENT);
        setButtonIconResource(name);
    }

    private void setupButtonTextPosition() {
        TextPosition position = annotationAccess
                .getValue(BUTTON_TEXT_POSITION_ELEMENT);
        setButtonTextPosition(position);
    }

    private void setupButtonText() {
        String title = annotationAccess.getValue(BUTTON_TEXT_ELEMENT);
        setButtonText(title);
    }

    private void setupButtonMnemonic() {
        String mnemonic = annotationAccess.getValue(BUTTON_MNEMONIC_ELEMENT);
        setButtonMnemonicString(mnemonic);
    }

    private void setupModel() {
        FileChooserModel model = (FileChooserModel) annotationClassFactory
                .create(getParentObject(), ANNOTATION_CLASS,
                        getAccessibleObject()).forAttribute(MODEL_ELEMENT)
                .build();
        setModel(model);
    }

    @Override
    public void setIconSize(IconSize size) {
        super.setIconSize(size);
        setButtonIconSize(size);
    }

    @Override
    public void setValue(Object value) throws PropertyVetoException {
        File file = (File) value;
        if (model == null) {
            super.setValue(file);
            fileTextField.setValue(file);
            return;
        }

        File oldFile = model.getFile();
        try {
            model.setFile(file);
        } catch (PropertyVetoException e) {
            fileTextField.setValue(file);
            fileTextField.setInputValid(false);
            throw e;
        }
        try {
            super.setValue(file);
            fileTextFieldValueListener.lock();
            fileTextField.setValue(file);
            fileTextFieldValueListener.unlock();
            fileTextField.setInputValid(true);
        } catch (PropertyVetoException e) {
            model.setFile(oldFile);
            fileTextField.setInputValid(false);
            throw e;
        }
    }

    /**
     * Sets the file chooser model.
     * 
     * @param model
     *            the {@link FileChooserModel}.
     * 
     * @throws NullPointerException
     *             if the specified model is {@code null}.
     */
    public void setModel(FileChooserModel model) {
        log.checkModel(this, model);
        removeOldModel();
        this.model = model;
        try {
            model.setFile((File) getValue());
        } catch (PropertyVetoException e) {
        }
        model.addVetoableChangeListener(fileListener);
        openDialogAction.setFileChooserModel(model);
        log.modelSet(this, model);
    }

    private void removeOldModel() {
        if (model != null) {
            model.removeVetoableChangeListener(fileListener);
        }
    }

    /**
     * Returns the file chooser model.
     * 
     * @return the {@link FileChooserModel}.
     */
    public FileChooserModel getModel() {
        return model;
    }

    /**
     * Sets the open file chooser action.
     * 
     * @param action
     *            the {@link Action}.
     */
    public void setOpenFileChooserAction(Action action) {
        panel.getOpenFileChooser().setAction(action);
    }

    /**
     * Sets the text of the button that opens the file chooser dialog. The text
     * can also be a resource name that is queried in the supplied texts
     * resource.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param buttonText
     *            the button text or the resource name.
     */
    public void setButtonText(String buttonText) {
        this.buttonTextResource = buttonText;
        this.buttonText = buttonText;
        log.buttonTextSet(buttonText, this);
        updateButtonTextResource();
    }

    /**
     * Returns the text of the button that opens the file chooser dialog.
     * 
     * @return the button text.
     */
    public String getButtonText() {
        return buttonText;
    }

    /**
     * Sets the mnemonic character with an optimal mnemonic index for the button
     * that opens the file chooser dialog. The string can contain a key code
     * name or the character. The mnemonic can also be a resource name that is
     * queried in the supplied texts resource.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param string
     *            the mnemonic string or the resource name.
     */
    public void setButtonMnemonicString(String string) {
        this.buttonMnemonicResource = string;
        Mnemonic mnemonic = mnemonicFactory.create(string);
        Integer code = mnemonic(mnemonic);
        if (code != null) {
            setButtonMnemonic(code);
            setButtonMnemonicIndex(mnemonic.getMnemonicIndex());
        } else {
            updateButtonMnemonicResource();
        }
        log.buttonMnemonicSet(this, string);
    }

    private Integer mnemonic(Mnemonic mnemonic) {
        return mnemonic.isValid() ? mnemonic.getMnemonic() : null;
    }

    /**
     * Sets the mnemonic character code for the open the file chooser dialog
     * button.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param mnemonic
     *            the mnemonic character code.
     */
    public void setButtonMnemonic(int mnemonic) {
        this.buttonMnemonic = mnemonic;
        getOpenFileChooser().setMnemonic(mnemonic);
    }

    /**
     * Returns the mnemonic character code for the open the file chooser dialog
     * button.
     * 
     * @return the mnemonic character code or {@code null} if no mnemonic was
     *         set.
     */
    public Integer getButtonMnemonic() {
        return buttonMnemonic;
    }

    /**
     * Sets the mnemonic index for the open the file chooser dialog button.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param index
     *            the mnemonic index.
     */
    public void setButtonMnemonicIndex(int index) {
        this.buttonMnemonicIndex = index;
        getOpenFileChooser().setDisplayedMnemonicIndex(index);
    }

    /**
     * Returns the mnemonic index for the open the file chooser dialog button.
     * 
     * @param index
     *            the mnemonic index.
     */
    public int getButtonMnemonicIndex() {
        return buttonMnemonicIndex;
    }

    /**
     * Sets the position of the button text and icon.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param position
     *            the {@link TextPosition}.
     */
    public void setButtonTextPosition(TextPosition position) {
        this.buttonTextPosition = position;
        switch (position) {
        case ICON_ONLY:
            getOpenFileChooser().setIcon(buttonIcon);
            getOpenFileChooser().setText(null);
            break;
        case TEXT_ALONGSIDE_ICON:
            getOpenFileChooser().setText(buttonText);
            getOpenFileChooser().setIcon(buttonIcon);
            break;
        case TEXT_UNDER_ICON:
            getOpenFileChooser().setText(buttonText);
            getOpenFileChooser().setIcon(buttonIcon);
            break;
        case TEXT_ONLY:
            getOpenFileChooser().setText(buttonText);
            getOpenFileChooser().setIcon(null);
            break;
        }
        log.buttonTextPositionSet(this, position);
    }

    /**
     * Returns the position of the button text and icon.
     * 
     * @return the {@link TextPosition}.
     */
    public TextPosition getButtonTextPosition() {
        return buttonTextPosition;
    }

    /**
     * Sets the resource for the button icon. The resource is loaded from the
     * specified images resources.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param name
     *            the icon resource name or {@code null} or empty if the old
     *            icon should be removed.
     */
    public void setButtonIconResource(String name) {
        this.buttonIconResource = name;
        if (isEmpty(name)) {
            buttonIcon = null;
            getOpenFileChooser().setIcon(null);
        } else {
            updateButtonIconResources();
        }
        log.buttonIconResourceSet(this, name);
    }

    /**
     * Sets the new button icon for the field.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param icon
     *            the {@link Icon} for the field or {@code null} if the old icon
     *            should be deleted.
     * 
     * @see #getOpenFileChooser()
     */
    public void setButtonIcon(Icon icon) {
        this.buttonIconResource = null;
        this.buttonIcon = icon;
        getOpenFileChooser().setIcon(icon);
        log.buttonIconSet(this, icon);
    }

    /**
     * Returns the button icon for this field.
     * 
     * @return the {@link Icon}
     * 
     * @see #getOpenFileChooser()
     */
    public Icon getButtonIcon() {
        return buttonIcon;
    }

    /**
     * Sets the size of the button icon.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param size
     *            the {@link IconSize}.
     */
    public void setButtonIconSize(IconSize size) {
        this.buttonIconSize = size;
        updateButtonIconResources();
        log.buttonIconSizeSet(this, buttonIconSize);
    }

    /**
     * Returns the button size of the icon.
     * 
     * @return the {@link IconSize}.
     */
    public IconSize getButtonIconSize() {
        return buttonIconSize;
    }

    @Override
    public void setTexts(Texts texts) {
        super.setTexts(texts);
        updateTextsResources();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        panel.setEnabled(enabled);
    }

    @Override
    public void setImages(Images images) {
        super.setImages(images);
        updateImagesResources();
    }

    private void updateTextsResources() {
        updateButtonTextResource();
        updateButtonMnemonicResource();
    }

    private void updateButtonTextResource() {
        if (haveTextResource(buttonTextResource)) {
            buttonText = getTextResource(buttonTextResource, buttonText);
        }
        getOpenFileChooser().setText(buttonText);
        updateButtonMnemonicResource();
    }

    private void updateButtonMnemonicResource() {
        if (!haveTextResource(buttonMnemonicResource)) {
            return;
        }
        String string = getTextResource(buttonMnemonicResource, null);
        if (string == null) {
            return;
        }
        Mnemonic mnemonic = mnemonicFactory.create(string);
        setButtonMnemonic(mnemonic.getMnemonic());
        int index = mnemonic.getMnemonicIndex();
        if (index != -1) {
            setButtonMnemonicIndex(index);
        }
    }

    private void updateImagesResources() {
        updateButtonIconResources();
    }

    private void updateButtonIconResources() {
        if (haveImageResource(buttonIconResource)) {
            buttonIcon = getIconResource(buttonIconResource, buttonIconSize,
                    buttonIcon);
            getOpenFileChooser().setIcon(buttonIcon);
        }
    }

    /**
     * Returns the open file chooser button.
     * 
     * @return the {@link JButton}.
     */
    public JButton getOpenFileChooser() {
        return panel.getOpenFileChooser();
    }

    @Override
    public void requestFocus() {
        panel.getFileField().requestFocusInWindow();
    }

}
