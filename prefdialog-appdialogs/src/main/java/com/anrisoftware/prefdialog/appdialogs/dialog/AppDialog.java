/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-appdialogs.
 *
 * prefdialog-appdialogs is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-appdialogs is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-appdialogs. If not, see <http://www.gnu.org/licenses/>.
 */
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
@OnAwt
public class AppDialog {

    @Inject
    private UiPanel panel;

    @Inject
    private DialogHeader dialogHeader;

    private SimpleDialog simpleDialog;

    private Component content;

    /**
     * @see AppDialogFactory#create()
     */
    AppDialog() {
    }

    /**
     * Injects and creates the simple dialog.
     *
     * @param factory
     *            the {@link SimpleDialogFactory}.
     */
    @Inject
    void setSimpleDialogFactory(SimpleDialogFactory factory) {
        SimpleDialog dialog = factory.create();
        dialog.setFieldsPanel(panel);
        this.simpleDialog = dialog;
    }

    /**
     * Creates the dialog.
     *
     * @return this {@link AppDialog}.
     *
     * @throws NullPointerException
     *             if the content of the dialog was not set.
     *
     * @see #setContent(Component)
     */
    public AppDialog createDialog() {
        notNull(content);
        panel.add(dialogHeader.getComponent(), BorderLayout.NORTH);
        panel.add(content, BorderLayout.CENTER);
        simpleDialog.createDialog();
        simpleDialog.getRestoreButton().setVisible(false);
        return this;
    }

    /**
     * Sets the dialog.
     *
     * @param dialog
     *            the {@link JDialog}.
     */
    public void setDialog(JDialog dialog) {
        simpleDialog.setDialog(dialog);
    }

    /**
     * Sets the content of the dialog. The content in the center of the dialog,
     * between the header and the buttons.
     *
     * @param content
     *            the content {@link Component}.
     *
     * @throws NullPointerException
     *             if the specified content is {@code null}.
     */
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
    public void setDialogHeaderImages(Images images) {
        dialogHeader.setImages(images);
    }

    /**
     * @see SimpleDialog#setImages(com.anrisoftware.resources.images.api.Images)
     */
    public void setDialogImages(Images images) {
        simpleDialog.setImages(images);
    }

    /**
     * @see DialogHeader#setLocale(java.util.Locale)
     */
    public void setLocale(Locale locale) {
        dialogHeader.setLocale(locale);
        simpleDialog.setLocale(locale);
    }

    /**
     * @see DialogHeader#setLogoImageName(java.lang.String)
     */
    public void setLogoImageName(String name) {
        dialogHeader.setLogoImageName(name);
    }

    /**
     * @see DialogHeader#setLogoImage(java.awt.Image)
     */
    public void setLogoImage(Image image) {
        dialogHeader.setLogoImage(image);
    }

    /**
     * @see DialogHeader#setLogoSize(java.awt.Dimension)
     */
    public void setLogoSize(Dimension size) {
        dialogHeader.setLogoSize(size);
    }

    /**
     * @see DialogHeader#setTexts(com.anrisoftware.resources.texts.api.Texts)
     */
    public void setDialogHeadersTexts(Texts texts) {
        dialogHeader.setTexts(texts);
    }

    /**
     * @see SimpleDialog#setTexts(com.anrisoftware.resources.texts.api.Texts)
     */
    public void setDialogTexts(Texts texts) {
        simpleDialog.setTexts(texts);
    }

    /**
     * @see DialogHeader#setInfoTextName(java.lang.String)
     */
    public void setInfoTextName(String name) {
        dialogHeader.setInfoTextName(name);
    }

    /**
     * @see DialogHeader#setInfoText(java.lang.String)
     */
    public void setInfoText(String text) {
        dialogHeader.setInfoText(text);
    }

    /**
     * @see DialogHeader#setLinkTextName(java.lang.String)
     */
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
    public void setApproveActionName(String name) {
        simpleDialog.setApproveActionName(name);
    }

    /**
     * @see SimpleDialog#setCancelActionName(java.lang.String)
     */
    public void setCancelActionName(String name) {
        simpleDialog.setCancelActionName(name);
    }

    /**
     * @see SimpleDialog#setRestoreActionName(java.lang.String)
     */
    public void setRestoreActionName(String name) {
        simpleDialog.setRestoreActionName(name);
    }

    /**
     * @see SimpleDialog#getDialog()
     */
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
    public void addCancelAction(ActionListener action) {
        simpleDialog.addCancelAction(action);
    }

    /**
     * @see SimpleDialog#addRestoreAction(java.awt.event.ActionListener)
     */
    public void addRestoreAction(ActionListener action) {
        simpleDialog.addRestoreAction(action);
    }

    /**
     * @see SimpleDialog#setVisible(boolean)
     */
    public void setVisible(boolean visible) {
        simpleDialog.setVisible(visible);
    }

    /**
     * @see SimpleDialog#setShowRestoreButton(boolean)
     */
    public void setShowRestoreButton(boolean show) {
        simpleDialog.setShowRestoreButton(show);
    }

    /**
     * @see com.anrisoftware.prefdialog.simpledialog.SimpleDialog#setShowApproveButton(boolean)
     */
    public void setShowApproveButton(boolean show) {
        simpleDialog.setShowApproveButton(show);
    }

    /**
     * @see SimpleDialog#openDialog()
     */
    public void openDialog() {
        simpleDialog.openDialog();
    }

    /**
     * @see SimpleDialog#closeDialog()
     */
    public void closeDialog() {
        simpleDialog.closeDialog();
    }

    /**
     * @see SimpleDialog#restoreDialog()
     */
    public void restoreDialog() {
        simpleDialog.restoreDialog();
    }

    /**
     * @see SimpleDialog#getAWTComponent()
     */
    public Component getAWTComponent() {
        return simpleDialog.getAWTComponent();
    }

    /**
     * @see SimpleDialog#getApproveButton()
     */
    public JButton getApproveButton() {
        return simpleDialog.getApproveButton();
    }

    /**
     * @see SimpleDialog#getRestoreButton()
     */
    public JButton getRestoreButton() {
        return simpleDialog.getRestoreButton();
    }

    /**
     * @see SimpleDialog#getCancelButton()
     */
    public JButton getCancelButton() {
        return simpleDialog.getCancelButton();
    }

    /**
     * @see SimpleDialog#setStatus(com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status)
     */
    public void setStatus(Status status) throws PropertyVetoException {
        simpleDialog.setStatus(status);
    }

    /**
     * @see SimpleDialog#getStatus()
     */
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
