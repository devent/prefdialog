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
package com.anrisoftware.prefdialog.appdialogs.registerdialog;

import static com.anrisoftware.prefdialog.appdialogs.registerdialog.RegisterDialogResource.day_text;
import static com.anrisoftware.prefdialog.appdialogs.registerdialog.RegisterDialogResource.days_text;
import static com.anrisoftware.prefdialog.appdialogs.registerdialog.RegisterDialogResource.email_text;
import static com.anrisoftware.prefdialog.appdialogs.registerdialog.RegisterDialogResource.registration_text;

import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.swing.JDialog;

import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialog;
import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialogFactory;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.editcontextmenu.EditContextMenu;
import com.anrisoftware.prefdialog.miscswing.editcontextmenu.EditContextMenuFactory;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status;
import com.anrisoftware.resources.images.api.ImageScalingWorkerFactory;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.images.api.ImagesFactory;
import com.anrisoftware.resources.templates.api.TemplateResource;
import com.anrisoftware.resources.templates.api.Templates;
import com.anrisoftware.resources.templates.api.TemplatesFactory;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

/**
 * Registration dialog.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public final class RegisterDialog {

    /**
     * Name text field name.
     */
    public static final String NAME_FIELD_NAME = "nameField";

    /**
     * Code text field name.
     */
    public static final String CODE_FIELD_NAME = "codeField";

    @Inject
    private UiRegisterPanel panel;

    @Inject
    private KeyLabelAction keyListAction;

    @Inject
    private CodeLabelAction codeListAction;

    @Inject
    private NameLabelAction nameListAction;

    @Inject
    private NameTextListener nameTextListener;

    @Inject
    private CodeTextListener codeTextListener;

    @Inject
    private ApprovalActionListener approvalActionListener;

    private EditContextMenu editContextMenu;

    private AppDialog appDialog;

    private Texts texts;

    private Templates templates;

    private int daysLeft;

    private String email;

    private Images images;

    private String key;

    private String code;

    private String name;

    @Inject
    @OnAwt
    void setAppDialog(AppDialogFactory factory) {
        AppDialog dialog = factory.create();
        dialog.setContent(panel);
        dialog.setShowRestoreButton(false);
        this.appDialog = dialog;
    }

    @Inject
    @OnAwt
    void setEditContextMenu(EditContextMenuFactory factory) {
        EditContextMenu menu = factory.create();
        menu.addTextField(panel.getKeyField());
        menu.addTextField(panel.getCodeField());
        menu.addTextField(panel.getNameField());
        this.editContextMenu = menu;
    }

    @Inject
    void setTextsFactory(TextsFactory factory) {
        this.texts = factory.create(RegisterDialog.class.getSimpleName());
        RegisterDialogResource.setTextsResource(texts);
    }

    @Inject
    void setTemplatesFactory(TemplatesFactory factory) {
        this.templates = factory.create(RegisterDialog.class.getSimpleName());
        RegisterDialogResource.setTemplatesResource(templates);
    }

    @Inject
    void setImagesFactory(ImagesFactory factory) {
        this.images = factory.create(RegisterDialog.class.getSimpleName());
        RegisterDialogResource.setImagesResource(images);
    }

    /**
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @return this {@link RegisterDialog}.
     *
     * @see AppDialog#createDialog()
     */
    @OnAwt
    public RegisterDialog createDialog() {
        setRegisterDialogTexts(texts);
        setTemplates(templates);
        setDialogHeaderImages(images);
        keyListAction.setLabel(panel.getKeyLabel());
        codeListAction.setLabel(panel.getCodeLabel());
        nameListAction.setLabel(panel.getNameLabel());
        nameTextListener.setTextField(panel.getNameField());
        codeTextListener.setTextField(panel.getCodeField());
        appDialog.createDialog();
        editContextMenu.createMenu();
        approvalActionListener.setTexts(texts);
        approvalActionListener.setAppDialog(appDialog);
        approvalActionListener.setRegisterDialog(this);
        return this;
    }

    /**
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @see AppDialog#setDialog(javax.swing.JDialog)
     */
    @OnAwt
    public void setDialog(JDialog dialog) {
        appDialog.setDialog(dialog);
    }

    /**
     * @see AppDialog#setImageScalingWorkerFactory(ImageScalingWorkerFactory)
     */
    public void setImageScalingWorkerFactory(ImageScalingWorkerFactory factory) {
        appDialog.setImageScalingWorkerFactory(factory);
    }

    /**
     * @see com.anrisoftware.prefdialog.appdialogs.dialog.AppDialog#setDialogHeaderImages(com.anrisoftware.resources.images.api.Images)
     */
    public void setDialogHeaderImages(Images images) {
        appDialog.setDialogHeaderImages(images);
    }

    /**
     * @see com.anrisoftware.prefdialog.appdialogs.dialog.AppDialog#setDialogImages(com.anrisoftware.resources.images.api.Images)
     */
    public void setDialogImages(Images images) {
        appDialog.setDialogImages(images);
    }

    /**
     * @see EditContextMenu#setImages(Images)
     */
    @OnAwt
    public void setEditContextMenuImages(Images images) {
        editContextMenu.setImages(images);
    }

    /**
     * @see AppDialog#setLocale(java.util.Locale)
     */
    @OnAwt
    public void setLocale(Locale locale) {
        appDialog.setLocale(locale);
        editContextMenu.setLocale(locale);
    }

    /**
     * @see AppDialog#setLogoImageName(java.lang.String)
     */
    @OnAwt
    public void setLogoImageName(String name) {
        appDialog.setLogoImageName(name);
    }

    /**
     * @see AppDialog#setLogoImage(java.awt.Image)
     */
    @OnAwt
    public void setLogoImage(Image image) {
        appDialog.setLogoImage(image);
    }

    /**
     * @see AppDialog#setLogoSize(java.awt.Dimension)
     */
    @OnAwt
    public void setLogoSize(Dimension size) {
        appDialog.setLogoSize(size);
    }

    /**
     * @see com.anrisoftware.prefdialog.appdialogs.dialog.AppDialog#setDialogHeadersTexts(com.anrisoftware.resources.texts.api.Texts)
     */
    public void setDialogHeadersTexts(Texts texts) {
        appDialog.setDialogHeadersTexts(texts);
    }

    /**
     * @see com.anrisoftware.prefdialog.appdialogs.dialog.AppDialog#setDialogTexts(com.anrisoftware.resources.texts.api.Texts)
     */
    public void setDialogTexts(Texts texts) {
        appDialog.setDialogTexts(texts);
    }

    /**
     * @see AppDialog#setTexts(Texts)
     */
    @OnAwt
    public void setRegisterDialogTexts(Texts texts) {
        keyListAction.setTexts(texts);
        codeListAction.setTexts(texts);
        nameListAction.setTexts(texts);
    }

    /**
     * @see EditContextMenu#setTexts(Texts)
     */
    @OnAwt
    public void setEditContextMenuTexts(Texts texts) {
        editContextMenu.setTexts(texts);
    }

    /**
     * @see AppDialog#setInfoTextName(java.lang.String)
     */
    @OnAwt
    public void setInfoTextName(String name) {
        appDialog.setInfoTextName(name);
    }

    /**
     * @see AppDialog#setInfoText(java.lang.String)
     */
    @OnAwt
    public void setInfoText(String text) {
        appDialog.setInfoText(text);
    }

    /**
     * @see AppDialog#setLinkTextName(java.lang.String)
     */
    @OnAwt
    public void setLinkTextName(String name) {
        appDialog.setLinkTextName(name);
    }

    /**
     * @see AppDialog#setLinkText(java.lang.String)
     */
    @OnAwt
    public void setLinkText(String text) {
        appDialog.setLinkText(text);
    }

    /**
     * @see AppDialog#getLocale()
     */
    public Locale getLocale() {
        return appDialog.getLocale();
    }

    /**
     * Sets the templates resources for the dialog.
     *
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @param templates
     *            the {@link Templates} resources.
     */
    @OnAwt
    public void setTemplates(Templates templates) {
        this.templates = templates;
        updateTexts();
    }

    @OnAwt
    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
        updateTexts();
    }

    @OnAwt
    public void setEmail(String email) {
        this.email = email;
        updateTexts();
    }

    @OnAwt
    public void setKey(String key) {
        this.key = key;
        updateTexts();
    }

    @OnAwt
    public void setCode(String code) {
        this.code = code;
        updateTexts();
    }

    @OnAwt
    public String getCode() {
        String code = codeTextListener.getText();
        this.code = code;
        return code;
    }

    @OnAwt
    public void setName(String name) {
        this.name = name;
        updateTexts();
    }

    @OnAwt
    public String getName() {
        String name = nameTextListener.getText();
        this.name = name;
        return name;
    }

    /**
     * @see com.anrisoftware.prefdialog.appdialogs.dialog.AppDialog#getStatus()
     */
    public Status getStatus() {
        return appDialog.getStatus();
    }

    private void updateTexts() {
        updateRegisterText();
        updateEmailText();
        updateKeyText();
        updateCodeText();
        updateNameText();
    }

    private void updateEmailText() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("email", email);
        TemplateResource resource = email_text.getTemplateResource();
        resource.invalidate();
        String text = resource.getText("emailText", "args", args);
        panel.getEmailLink().setText(text);
    }

    private void updateRegisterText() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("daysLeft", daysLeft);
        if (daysLeft > 1) {
            args.put("dayUnit", days_text.getTextResource().getText());
        } else {
            args.put("dayUnit", day_text.getTextResource().getText());
        }
        TemplateResource resource = registration_text.getTemplateResource();
        resource.invalidate();
        String text = resource.getText("registrationText", "args", args);
        panel.getTextLabel().setText(text);
    }

    private void updateKeyText() {
        panel.getKeyField().setText(key);
    }

    private void updateCodeText() {
        panel.getCodeField().setText(code);
    }

    private void updateNameText() {
        panel.getNameField().setText(name);
    }
}
