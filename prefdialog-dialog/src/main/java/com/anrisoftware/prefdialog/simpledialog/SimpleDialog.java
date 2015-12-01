/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import static com.anrisoftware.prefdialog.simpledialog.SimpleDialogModule.getSimpleDialogFactory;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import static javax.swing.KeyStroke.getKeyStroke;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import static org.apache.commons.lang3.Validate.notNull;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.ImageResource;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.images.api.ImagesFactory;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

/**
 * Simple dialog with approve, restore and cancel buttons.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class SimpleDialog {

    /**
     * Status of the dialog.
     *
     * @author Erwin Mueller, erwin.mueller@deventm.org
     * @since 3.0
     */
    public enum Status {

        /**
         * The dialog was opened. Initial state of the dialog.
         *
         * @since 3.1
         */
        OPENED,

        /**
         * The user approved the dialog.
         */
        APPROVED,

        /**
         * The user canceled the dialog.
         */
        CANCELED;
    }

    /**
     * Decorates the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param dialog
     *            the {@link JDialog}.
     *
     * @param fieldsPanel
     *            the fields panel {@link Component} for the dialog.
     *
     * @param texts
     *            the {@link Texts} resources.
     *
     * @see SimpleDialogFactory#create()
     *
     * @return the {@link SimpleDialog}.
     */
    public static SimpleDialog decorate(JDialog dialog, Component fieldsPanel) {
        SimpleDialog simpleDialog = getSimpleDialogFactory().create();
        simpleDialog.setFieldsPanel(fieldsPanel);
        simpleDialog.setDialog(dialog);
        return simpleDialog.createDialog();
    }

    /**
     * Status property.
     *
     * @see #setStatus(Status)
     */
    public static final String STATUS_PROPERTY = "status";

    /**
     * Approve button name.
     */
    public static final String APPROVE_BUTTON_NAME = "approveButton";

    /**
     * Restore button name.
     */
    public static final String RESTORE_BUTTON_NAME = "restoreButton";

    /**
     * Cancel button name.
     */
    public static final String CANCEL_BUTTON_NAME = "cancelButton";

    /**
     * Error text component name.
     *
     * @since 3.1
     */
    public static final String ERROR_TEXT_NAME = "errorText";

    static final String CANCEL_ACTION_NAME = "cancel_action";

    static final String APPROVE_ACTION_NAME = "approve_action";

    static final String RESTORE_ACTION_NAME = "restore_action";

    private final VetoableChangeSupport vetoableSupport;

    private CancelAction cancelAction;

    private ApproveAction approveAction;

    @Inject
    private UiDialogPanel dialogPanel;

    private RestoreAction restoreAction;

    private Component fieldsPanel;

    private JDialog dialog;

    private Status status;

    private Texts texts;

    private Images images;

    private boolean showRestoreButton;

    private ImageResource emptyIcon;

    private ImageResource errorIcon;

    private boolean showApproveButton;

    /**
     * @see SimpleDialogFactory#create()
     */
    @Inject
    protected SimpleDialog() {
        this.vetoableSupport = new VetoableChangeSupport(this);
        this.showApproveButton = true;
        this.showRestoreButton = true;
    }

    @Inject
    void setTextsFactory(TextsFactory factory) {
        this.texts = factory.create(SimpleDialog.class.getSimpleName());
    }

    @Inject
    void setImagesFactory(ImagesFactory factory) {
        this.images = factory.create(SimpleDialog.class.getSimpleName());
    }

    @Inject
    void setApproveAction(ApproveAction action) {
        this.approveAction = action;
    }

    @Inject
    void setCancelAction(CancelAction action) {
        this.cancelAction = action;
    }

    @Inject
    void setRestoreAction(RestoreAction action) {
        this.restoreAction = action;
    }

    /**
     * Sets the panel component with the fields of the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param panel
     *            the panel {@link Component}.
     */
    @OnAwt
    public void setFieldsPanel(Component panel) {
        this.fieldsPanel = panel;
    }

    /**
     * Sets the texts resources for the dialog. There should be the resources
     * available:
     * <ul>
     * <li>{@value #APPROVE_ACTION_NAME}
     * <li>{@value #CANCEL_ACTION_NAME}
     * <li>{@value #RESTORE_ACTION_NAME}
     * </ul>
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param texts
     *            the {@link Texts} resources.
     */
    @OnAwt
    public void setTexts(Texts texts) {
        this.texts = texts;
    }

    /**
     * Returns the texts resources for the dialog.
     *
     * @return the {@link Texts} resources.
     */
    public Texts getTexts() {
        return texts;
    }

    /**
     * Sets the images resources for the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param images
     *            the {@link Images} resources.
     */
    @OnAwt
    public void setImages(Images images) {
        this.images = images;
        loadImages();
    }

    /**
     * Returns the images resources for the dialog.
     *
     * @return the {@link Images} resources.
     */
    public Images getImages() {
        return images;
    }

    /**
     * Sets the locale of the components of the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param locale
     *            the {@link Locale}.
     */
    @OnAwt
    public void setLocale(Locale locale) {
        approveAction.setLocale(locale);
        restoreAction.setLocale(locale);
        cancelAction.setLocale(locale);
        dialogPanel.setLocale(locale);
        if (dialog != null) {
            dialog.setLocale(locale);
        }
        if (fieldsPanel != null) {
            fieldsPanel.setLocale(locale);
        }
    }

    /**
     * Returns the locale of the components of the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link Locale}.
     */
    @OnAwt
    public Locale getLocale() {
        return dialogPanel.getLocale();
    }

    /**
     * Sets to show the restore button.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param show
     *            set to {@code true} to show the restore button.
     *
     * @since 3.1
     */
    @OnAwt
    public void setShowRestoreButton(boolean show) {
        boolean oldValue = this.showRestoreButton;
        this.showRestoreButton = show;
        if (oldValue && !show) {
            if (dialogPanel != null) {
                removeRestoreButton();
            }
        }
        if (!oldValue && show) {
            readdRestoreButton();
        }
    }

    /**
     * Sets to show the approve button.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param show
     *            set to {@code true} to show the approve button.
     *
     * @since 3.2
     */
    @OnAwt
    public void setShowApproveButton(boolean show) {
        boolean oldValue = this.showApproveButton;
        this.showApproveButton = show;
        if (oldValue && !show) {
            if (dialogPanel != null) {
                removeApproveButton();
            }
        }
        if (!oldValue && show) {
            readdApproveButton();
        }
    }

    /**
     * Sets the approval action name. The action name is used to look up the
     * action resources.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the action name {@link String}.
     */
    @OnAwt
    public void setApproveActionName(String name) {
        approveAction.setActionName(name);
    }

    /**
     * Sets the cancel action name. The action name is used to look up the
     * action resources.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the action name {@link String}.
     */
    @OnAwt
    public void setCancelActionName(String name) {
        cancelAction.setActionName(name);
    }

    /**
     * Sets the restore action name. The action name is used to look up the
     * action resources.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the action name {@link String}.
     */
    @OnAwt
    public void setRestoreActionName(String name) {
        restoreAction.setActionName(name);
    }

    /**
     * Adds a new action listener to the approval dialog button.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param action
     *            the {@link ActionListener}.
     */
    @OnAwt
    public void addApprovalAction(ActionListener action) {
        getApproveButton().addActionListener(action);
    }

    /**
     * Adds a new action listener to the cancel dialog button.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param action
     *            the {@link ActionListener}.
     */
    @OnAwt
    public void addCancelAction(ActionListener action) {
        getCancelButton().addActionListener(action);
    }

    /**
     * Adds a new action listener to the restore dialog button.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param action
     *            the {@link ActionListener}.
     */
    @OnAwt
    public void addRestoreAction(ActionListener action) {
        getRestoreButton().addActionListener(action);
    }

    /**
     * Sets the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param dialog
     *            the {@link JDialog}.
     */
    @OnAwt
    public void setDialog(JDialog dialog) {
        this.dialog = dialog;
    }

    /**
     * Returns the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link JDialog}.
     */
    @OnAwt
    public JDialog getDialog() {
        return dialog;
    }

    /**
     * Sets the size for the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param size
     *            sets the {@link Dimension} size.
     *
     * @since 3.5
     */
    @OnAwt
    public void setSize(Dimension size) {
        dialog.setSize(size);
    }

    /**
     * Returns the size for the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return sets the {@link Dimension} size.
     *
     * @since 3.5
     */
    @OnAwt
    public Dimension getSize() {
        return dialog.getSize();
    }

    /**
     * Opens or closes the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param visible
     *            set to {@code true} to open the dialog.
     *
     * @throws PropertyVetoException
     *             if the opening of the dialog was vetoed.
     *
     * @since 3.5
     */
    @OnAwt
    public void setVisible(boolean visible) throws PropertyVetoException {
        if (visible) {
            openDialog();
        } else {
            closeDialog();
        }
    }

    /**
     * Creates the simple dialog and all child fields.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return this {@link SimpleDialog}.
     */
    @OnAwt
    public SimpleDialog createDialog() {
        loadImages();
        setupPanel();
        setupActions();
        setupDialog();
        setupKeyboardActions();
        return this;
    }

    /**
     * Opens the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @throws PropertyVetoException
     *             if the opening of the dialog was vetoed.
     *
     * @since 3.5
     */
    @OnAwt
    public void openDialog() throws PropertyVetoException {
        setStatus(Status.OPENED);
        dialog.setVisible(true);
    }

    /**
     * Closes the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     */
    @OnAwt
    public void closeDialog() {
        dialog.setVisible(false);
    }

    /**
     * Packs the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     */
    @OnAwt
    public void packDialog() {
        dialog.pack();
    }

    /**
     * Sets the location of the dialog relative to the owner.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param owner
     *            the {@link Component} owner.
     */
    @OnAwt
    public void setLocationRelativeTo(Component owner) {
        dialog.setLocationRelativeTo(owner);
    }

    /**
     * Restores the input of the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     */
    @OnAwt
    public void restoreDialog() {
    }

    /**
     * Returns the component to be added in a container. The component contains
     * the dialog button and fields.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link Component}.
     */
    @OnAwt
    public Component getAWTComponent() {
        return dialogPanel;
    }

    /**
     * Returns the approval dialog button.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the approval {@link JButton}.
     *
     * @since 3.2
     */
    @OnAwt
    public JButton getApproveButton() {
        return dialogPanel.getApproveButton();
    }

    /**
     * Returns the reset dialog button.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the reset {@link JButton}.
     */
    @OnAwt
    public JButton getRestoreButton() {
        return dialogPanel.getRestoreButton();
    }

    /**
     * Returns the cancel dialog button.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the cancel {@link JButton}.
     */
    @OnAwt
    public JButton getCancelButton() {
        return dialogPanel.getCancelButton();
    }

    /**
     * Sets the status of the dialog. The property change listeners are informed
     * of the status change.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param status
     *            the {@link Status}.
     *
     * @throws PropertyVetoException
     *             if the status was vetoed by one of the property change
     *             listener.
     *
     * @see #STATUS_PROPERTY
     */
    @OnAwt
    public void setStatus(Status status) throws PropertyVetoException {
        Status oldValue = this.status;
        vetoableSupport.fireVetoableChange(STATUS_PROPERTY, oldValue, status);
        this.status = status;
    }

    /**
     * Returns the status of the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link Status} or {@code null} if the dialog was not open
     *         yet.
     */
    @OnAwt
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the error text to show inside the dialog.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param text
     *            the error {@link String} text or {@code null}.
     *
     * @since 3.1
     */
    @OnAwt
    public void setErrorText(String text) {
        if (text != null) {
            dialogPanel.getErrorTextLabel().setIcon(
                    new ImageIcon(errorIcon.getImage()));
            dialogPanel.getErrorTextLabel().setText(text);
        } else {
            dialogPanel.getErrorTextLabel().setIcon(
                    new ImageIcon(emptyIcon.getImage()));
            dialogPanel.getErrorTextLabel().setText(null);
        }
    }

    /**
     * Sets the error text font.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param font
     *            the {@link Font}.
     * @since 3.1
     */
    @OnAwt
    public void setErrorTextFont(Font font) {
        dialogPanel.getErrorTextLabel().setFont(font);
    }

    /**
     * Sets the error text font color.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param color
     *            the {@link Color}.
     * @since 3.1
     */
    @OnAwt
    public void setErrorTextFontColor(Color color) {
        dialogPanel.getErrorTextLabel().setForeground(color);
    }

    /**
     * @see #STATUS_PROPERTY
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableSupport.addVetoableChangeListener(listener);
    }

    /**
     * @see #STATUS_PROPERTY
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableSupport.removeVetoableChangeListener(listener);
    }

    /**
     * @see #STATUS_PROPERTY
     */
    public void addVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        vetoableSupport.addVetoableChangeListener(propertyName, listener);
    }

    /**
     * @see #STATUS_PROPERTY
     */
    public void removeVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        vetoableSupport.removeVetoableChangeListener(propertyName, listener);
    }

    private void setupDialog() {
        dialog.add(getAWTComponent());
        dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cancelAction.actionPerformed(null);
            }
        });
    }

    private void setupPanel() {
        notNull(fieldsPanel, "fieldsPanel");
        dialogPanel.add(fieldsPanel, "cell 0 0");
        dialogPanel.getErrorTextLabel().setIcon(
                new ImageIcon(emptyIcon.getImage()));
        dialogPanel.getErrorTextLabel().setText(" ");
    }

    private void setupActions() {
        approveAction.setDialog(this);
        approveAction.setTexts(texts);
        cancelAction.setDialog(this);
        cancelAction.setTexts(texts);
        getCancelButton().setAction(cancelAction);
        getApproveButton().setAction(approveAction);
        if (showApproveButton) {
            addApproveButton();
        } else {
            removeApproveButton();
        }
        if (showRestoreButton) {
            addRestoreButton();
        } else {
            removeRestoreButton();
        }
    }

    private void setupKeyboardActions() {
        JRootPane rootPane = dialog.getRootPane();
        rootPane.setDefaultButton(getApproveButton());
        rootPane.registerKeyboardAction(cancelAction,
                getKeyStroke(VK_ESCAPE, 0), WHEN_IN_FOCUSED_WINDOW);
    }

    private void addRestoreButton() {
        JButton restoreButton = getRestoreButton();
        restoreAction.setDialog(this);
        restoreAction.setTexts(texts);
        restoreButton.setVisible(true);
        restoreButton.setAction(restoreAction);
    }

    private void readdRestoreButton() {
        JButton cancelButton = getCancelButton();
        JButton restoreButton = getRestoreButton();
        JPanel buttonsPanel = dialogPanel.getButtonsPanel();
        restoreAction.setDialog(this);
        restoreAction.setTexts(texts);
        restoreButton.setVisible(true);
        restoreButton.setAction(restoreAction);
        buttonsPanel.remove(cancelButton);
        buttonsPanel.add(restoreButton);
        buttonsPanel.add(dialogPanel.getRestoreStrut());
        buttonsPanel.add(cancelButton);
        buttonsPanel.validate();
    }

    private void removeRestoreButton() {
        JPanel buttonsPanel = dialogPanel.getButtonsPanel();
        JButton restoreButton = getRestoreButton();
        buttonsPanel.remove(restoreButton);
        buttonsPanel.remove(dialogPanel.getRestoreStrut());
        restoreButton.setVisible(false);
        buttonsPanel.validate();
    }

    private void addApproveButton() {
        JButton approveButton = getApproveButton();
        approveAction.setDialog(this);
        approveAction.setTexts(texts);
        approveButton.setVisible(true);
        approveButton.setAction(approveAction);
    }

    private void readdApproveButton() {
        JButton cancelButton = getCancelButton();
        JButton approveButton = getApproveButton();
        JPanel buttonsPanel = dialogPanel.getButtonsPanel();
        approveAction.setDialog(this);
        approveAction.setTexts(texts);
        approveButton.setVisible(true);
        approveButton.setAction(approveAction);
        buttonsPanel.remove(cancelButton);
        buttonsPanel.add(approveButton);
        buttonsPanel.add(dialogPanel.getApproveStrut());
        buttonsPanel.add(cancelButton);
        buttonsPanel.validate();
    }

    private void removeApproveButton() {
        JPanel buttonsPanel = dialogPanel.getButtonsPanel();
        JButton approveButton = getApproveButton();
        buttonsPanel.remove(approveButton);
        buttonsPanel.remove(dialogPanel.getApproveStrut());
        approveButton.setVisible(false);
        buttonsPanel.validate();
    }

    private void loadImages() {
        Locale locale = getLocale();
        this.emptyIcon = images.getResource("empty_icon", locale,
                IconSize.SMALL);
        this.errorIcon = images.getResource("error_icon", locale,
                IconSize.SMALL);
    }

}
