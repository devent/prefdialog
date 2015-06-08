package com.anrisoftware.prefdialog.appdialogs.registerdialog;

import java.awt.Dimension;
import java.util.Locale;

import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.ImageResource;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.templates.api.TemplateResource;
import com.anrisoftware.resources.templates.api.Templates;
import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Register dialog resource.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
enum RegisterDialogResource {

    key_label,

    name_label,

    code_label,

    email_text,

    registration_text,

    day_text,

    days_text;

    private Texts texts;

    private Templates templates;

    private Images images;

    /**
     * Sets the texts resources.
     *
     * @param texts
     *            the {@link Texts} resources.
     */
    public static void setTextsResource(Texts texts) {
        RegisterDialogResource[] values = RegisterDialogResource.values();
        for (int i = 0; i < values.length; i++) {
            values[i].setTexts(texts);
        }
    }

    /**
     * Sets the templates resources.
     *
     * @param templates
     *            the {@link Templates} resources.
     */
    public static void setTemplatesResource(Templates templates) {
        RegisterDialogResource[] values = RegisterDialogResource.values();
        for (int i = 0; i < values.length; i++) {
            values[i].setTemplates(templates);
        }
    }

    /**
     * Sets the images resources.
     *
     * @param images
     *            the {@link Templates} resources.
     */
    public static void setImagesResource(Images images) {
        RegisterDialogResource[] values = RegisterDialogResource.values();
        for (int i = 0; i < values.length; i++) {
            values[i].setImages(images);
        }
    }

    /**
     * Sets the texts resources.
     *
     * @param texts
     *            the {@link Texts} resources.
     */
    public void setTexts(Texts texts) {
        this.texts = texts;
    }

    /**
     * Sets the templates resources.
     *
     * @param templates
     *            the {@link Templates} resources.
     */
    public void setTemplates(Templates templates) {
        this.templates = templates;
    }

    /**
     * Sets the images resources.
     *
     * @param images
     *            the {@link Images} resources.
     */
    public void setImages(Images images) {
        this.images = images;
    }

    /**
     * Returns the text resource.
     *
     * @return the {@link TextResource} resource.
     */
    public TextResource getTextResource() {
        return texts.getResource(name());
    }

    /**
     * Returns the template resource.
     *
     * @return the {@link TemplateResource} resource.
     */
    public TemplateResource getTemplateResource() {
        return templates.getResource(name());
    }

    /**
     * Returns the image resource.
     *
     * @return the {@link ImageResource} resource.
     * @see Images#getResource(String, Locale, Dimension)
     */
    public ImageResource getImageResource(Locale locale, Dimension sizePx) {
        return images.getResource(name(), locale, sizePx);
    }

    /**
     * Returns the image resource.
     *
     * @return the {@link ImageResource} resource.
     * @see Images#getResource(String, Locale, IconSize)
     */
    public ImageResource getImageResource(Locale locale, IconSize size) {
        return images.getResource(name(), locale, size);
    }
}
