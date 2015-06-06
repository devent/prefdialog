package com.anrisoftware.prefdialog.appdialogs.registerdialog;

import java.awt.Dimension;
import java.awt.Image;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.JDialog;

import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialog;
import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialogFactory;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.resources.images.api.ImageScalingWorkerFactory;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

/**
 * Registration dialog.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public final class RegisterDialog {

    @Inject
    private UiRegisterPanel panel;

    private AppDialog appDialog;

    private Texts texts;

    @Inject
    @OnAwt
    void setAppDialogFactory(AppDialogFactory factory) {
        AppDialog dialog = factory.create();
        dialog.setContent(panel);
        dialog.setShowRestoreButton(false);
        this.appDialog = dialog;
    }

    @Inject
    void setTextsFactory(TextsFactory factory) {
        this.texts = factory.create(RegisterDialog.class.getSimpleName());
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
        setTexts(texts);
        appDialog.createDialog();
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
     * @see AppDialog#setImages(com.anrisoftware.resources.images.api.Images)
     */
    @OnAwt
    public void setImages(Images images) {
        appDialog.setImages(images);
    }

    /**
     * @see AppDialog#setLocale(java.util.Locale)
     */
    @OnAwt
    public void setLocale(Locale locale) {
        appDialog.setLocale(locale);
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
     * @see AppDialog#setTexts(com.anrisoftware.resources.texts.api.Texts)
     */
    @OnAwt
    public void setTexts(Texts texts) {
        appDialog.setTexts(texts);
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
     * @see AppDialog#getTexts()
     */
    public Texts getTexts() {
        return appDialog.getTexts();
    }

    /**
     * @see AppDialog#getImages()
     */
    public Images getImages() {
        return appDialog.getImages();
    }

    /**
     * @see AppDialog#getLocale()
     */
    public Locale getLocale() {
        return appDialog.getLocale();
    }
}
