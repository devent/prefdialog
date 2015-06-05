package com.anrisoftware.prefdialog.appdialogs.dialog;

import static org.apache.commons.lang3.Validate.notNull;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JDialog;

import com.anrisoftware.prefdialog.appdialogs.dialogheader.DialogHeader;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialogFactory;
import com.anrisoftware.resources.images.api.ImageScalingWorkerFactory;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * General application dialog, contains a header panel that shows the
 * application logo and application information.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public final class AppDialog {

    @Inject
    private UiPanel panel;

    @Inject
    private DialogHeader dialogHeader;

    private SimpleDialog simpleDialog;

    private Component content;

    /**
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @see AppDialogFactory#create()
     */
    @OnAwt
    AppDialog() {
    }

    @Inject
    @OnAwt
    void setSimpleDialogFactory(SimpleDialogFactory factory) {
        SimpleDialog dialog = factory.create();
        dialog.setFieldsPanel(panel);
        this.simpleDialog = dialog;
    }

    /**
     * Creates the dialog.
     *
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @return this {@link AppDialog}.
     *
     * @throws NullPointerException
     *             if the content of the dialog was not set.
     *
     * @see #setContent(Component)
     */
    @OnAwt
    public AppDialog createDialog() {
        notNull(content);
        panel.add(dialogHeader.getComponent(), BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);
        simpleDialog.createDialog();
        simpleDialog.getRestoreButton().setVisible(false);
        return this;
    }

    /**
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @see SimpleDialog#setDialog(javax.swing.JDialog)
     */
    @OnAwt
    public void setDialog(JDialog dialog) {
        simpleDialog.setDialog(dialog);
    }

    /**
     * Sets the content of the dialog. The content in the center of the dialog,
     * between the header and the buttons.
     *
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @param content
     *            the content {@link Component}.
     *
     * @throws NullPointerException
     *             if the specified content is {@code null}.
     */
    @OnAwt
    public void setContent(Component content) {
        notNull(content);
        this.content = content;
    }

    /**
     * @see DialogHeader#setImageScalingWorkerFactory(ImageScalingWorkerFactory)
     */
    public void setImageScalingWorkerFactory(ImageScalingWorkerFactory factory) {
        dialogHeader.setImageScalingWorkerFactory(factory);
    }

    /**
     * @see DialogHeader#setImages(com.anrisoftware.resources.images.api.Images)
     */
    @OnAwt
    public void setImages(Images images) {
        dialogHeader.setImages(images);
        simpleDialog.setImages(images);
    }

    /**
     * @see DialogHeader#setLocale(java.util.Locale)
     */
    @OnAwt
    public void setLocale(Locale locale) {
        dialogHeader.setLocale(locale);
        simpleDialog.setLocale(locale);
    }

    /**
     * @see DialogHeader#setLogoImageName(java.lang.String)
     */
    @OnAwt
    public void setLogoImageName(String name) {
        dialogHeader.setLogoImageName(name);
    }

    /**
     * @see DialogHeader#setLogoImage(java.awt.Image)
     */
    @OnAwt
    public void setLogoImage(Image image) {
        dialogHeader.setLogoImage(image);
    }

    /**
     * @see DialogHeader#setLogoSize(java.awt.Dimension)
     */
    @OnAwt
    public void setLogoSize(Dimension size) {
        dialogHeader.setLogoSize(size);
    }

    /**
     * @see DialogHeader#setTexts(com.anrisoftware.resources.texts.api.Texts)
     */
    @OnAwt
    public void setTexts(Texts texts) {
        dialogHeader.setTexts(texts);
        simpleDialog.setTexts(texts);
    }

    /**
     * @see DialogHeader#setInfoTextName(java.lang.String)
     */
    @OnAwt
    public void setInfoTextName(String name) {
        dialogHeader.setInfoTextName(name);
    }

    /**
     * @see DialogHeader#setInfoText(java.lang.String)
     */
    @OnAwt
    public void setInfoText(String text) {
        dialogHeader.setInfoText(text);
    }

    /**
     * @see DialogHeader#setLinkTextName(java.lang.String)
     */
    @OnAwt
    public void setLinkTextName(String name) {
        dialogHeader.setLinkTextName(name);
    }

    /**
     * @see DialogHeader#setLinkText(java.lang.String)
     */
    @OnAwt
    public void setLinkText(String text) {
        dialogHeader.setLinkText(text);
    }

    /**
     * @see SimpleDialog#getTexts()
     */
    public Texts getTexts() {
        return simpleDialog.getTexts();
    }

    /**
     * @see SimpleDialog#getImages()
     */
    public Images getImages() {
        return simpleDialog.getImages();
    }

    /**
     * @see SimpleDialog#getLocale()
     */
    public Locale getLocale() {
        return simpleDialog.getLocale();
    }

    /**
     * @see SimpleDialog#setApproveActionName(java.lang.String)
     */
    @OnAwt
    public void setApproveActionName(String name) {
        simpleDialog.setApproveActionName(name);
    }

    /**
     * @see SimpleDialog#setCancelActionName(java.lang.String)
     */
    @OnAwt
    public void setCancelActionName(String name) {
        simpleDialog.setCancelActionName(name);
    }

    /**
     * @see SimpleDialog#setRestoreActionName(java.lang.String)
     */
    @OnAwt
    public void setRestoreActionName(String name) {
        simpleDialog.setRestoreActionName(name);
    }

    /**
     * @see SimpleDialog#getDialog()
     */
    @OnAwt
    public JDialog getDialog() {
        return simpleDialog.getDialog();
    }

    /**
     * @see SimpleDialog#addApprovalAction(java.awt.event.ActionListener)
     */
    @OnAwt
    public void addApprovalAction(ActionListener action) {
        simpleDialog.addApprovalAction(action);
    }

    /**
     * @see SimpleDialog#addCancelAction(java.awt.event.ActionListener)
     */
    @OnAwt
    public void addCancelAction(ActionListener action) {
        simpleDialog.addCancelAction(action);
    }

    /**
     * @see SimpleDialog#addRestoreAction(java.awt.event.ActionListener)
     */
    @OnAwt
    public void addRestoreAction(ActionListener action) {
        simpleDialog.addRestoreAction(action);
    }

    /**
     * @see SimpleDialog#setVisible(boolean)
     */
    @OnAwt
    public void setVisible(boolean visible) {
        simpleDialog.setVisible(visible);
    }

    /**
     * @see SimpleDialog#openDialog()
     */
    @OnAwt
    public void openDialog() {
        simpleDialog.openDialog();
    }

    /**
     * @see SimpleDialog#closeDialog()
     */
    @OnAwt
    public void closeDialog() {
        simpleDialog.closeDialog();
    }

    /**
     * @see SimpleDialog#restoreDialog()
     */
    @OnAwt
    public void restoreDialog() {
        simpleDialog.restoreDialog();
    }

    /**
     * @see SimpleDialog#getAWTComponent()
     */
    @OnAwt
    public Component getAWTComponent() {
        return simpleDialog.getAWTComponent();
    }

    /**
     * @see SimpleDialog#getApprovalButton()
     */
    @OnAwt
    public JButton getApprovalButton() {
        return simpleDialog.getApprovalButton();
    }

    /**
     * @see SimpleDialog#getRestoreButton()
     */
    @OnAwt
    public JButton getRestoreButton() {
        return simpleDialog.getRestoreButton();
    }

    /**
     * @see SimpleDialog#getCancelButton()
     */
    @OnAwt
    public JButton getCancelButton() {
        return simpleDialog.getCancelButton();
    }

    /**
     * @see SimpleDialog#setStatus(com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status)
     */
    @OnAwt
    public void setStatus(Status status) throws PropertyVetoException {
        simpleDialog.setStatus(status);
    }

    /**
     * @see SimpleDialog#getStatus()
     */
    @OnAwt
    public Status getStatus() {
        return simpleDialog.getStatus();
    }

    /**
     * @see SimpleDialog#addVetoableChangeListener(java.beans.VetoableChangeListener)
     */
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        simpleDialog.addVetoableChangeListener(listener);
    }

    /**
     * @see SimpleDialog#removeVetoableChangeListener(java.beans.VetoableChangeListener)
     */
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        simpleDialog.removeVetoableChangeListener(listener);
    }

    /**
     * @see SimpleDialog#addVetoableChangeListener(java.lang.String,
     *      java.beans.VetoableChangeListener)
     */
    public void addVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        simpleDialog.addVetoableChangeListener(propertyName, listener);
    }

    /**
     * @see SimpleDialog#removeVetoableChangeListener(java.lang.String,
     *      java.beans.VetoableChangeListener)
     */
    public void removeVetoableChangeListener(String propertyName,
            VetoableChangeListener listener) {
        simpleDialog.removeVetoableChangeListener(propertyName, listener);
    }

}
