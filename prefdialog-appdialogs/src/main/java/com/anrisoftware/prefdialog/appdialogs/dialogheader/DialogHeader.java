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
package com.anrisoftware.prefdialog.appdialogs.dialogheader;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Locale;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.resources.images.api.ImageScalingWorkerFactory;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Headers contains the logo image and the application text.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.1
 */
public class DialogHeader {

    private UiHeader header;

    @Inject
    private HeaderLogo headerLogo;

    @Inject
    private HeaderInfo headerInfo;

    /**
     * Injects the header user interface.
     *
     * @param header
     *            the {@link UiHeader}.
     */
    @Inject
    void setHeader(UiHeader header) {
        this.header = header;
        headerLogo.setHeader(header);
        headerInfo.setHeader(header);
    }

    /**
     * Sets the image scaler to scale the logo image if needed.
     *
     * @param factory
     *            the {@link ImageScalingWorkerFactory}.
     *
     * @throws NullPointerException
     *             if the specified factory is {@code null}.
     */
    public void setImageScalingWorkerFactory(ImageScalingWorkerFactory factory) {
        notNull(factory);
        headerLogo.setImageScalingWorkerFactory(factory);
    }

    /**
     * Sets the images resources and updates the header.
     *
     * <p>
     * <h2>AWT Thread</h2>
     * </p>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @param images
     *            the {@link Images}.
     *
     * @throws NullPointerException
     *             if the specified images is {@code null}.
     */
    @OnAwt
    public void setImages(Images images) {
        notNull(images);
        headerLogo.setImages(images);
    }

    /**
     * Sets the head locale and updates the header.
     *
     * <p>
     * <h2>AWT Thread</h2>
     * </p>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @param locale
     *            the {@link Locale}.
     *
     * @throws NullPointerException
     *             if the specified locale is {@code null}.
     */
    @OnAwt
    public void setLocale(Locale locale) {
        notNull(locale);
        headerLogo.setLocale(locale);
    }

    /**
     * Sets the head logo image resource name and updates the header.
     *
     * <p>
     * <h2>AWT Thread</h2>
     * </p>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @param name
     *            the {@link String} name.
     *
     * @throws NullPointerException
     *             if the specified name is {@code null}.
     */
    @OnAwt
    public void setLogoImageName(String name) {
        notBlank(name);
        headerLogo.setLogoImageName(name);
    }

    /**
     * Sets the head logo image and updates the header.
     *
     * <p>
     * <h2>AWT Thread</h2>
     * </p>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @param image
     *            the {@link Image} image.
     *
     * @throws NullPointerException
     *             if the specified image is {@code null}.
     */
    @OnAwt
    public void setLogoImage(Image image) {
        notNull(image);
        headerLogo.setLogoImage(image);
    }

    /**
     * Sets the head logo image size and updates the header.
     *
     * <p>
     * <h2>AWT Thread</h2>
     * </p>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @param size
     *            the {@link Dimension} size.
     *
     * @throws NullPointerException
     *             if the specified size is {@code null}.
     */
    @OnAwt
    public void setLogoSize(Dimension size) {
        notNull(size);
        headerLogo.setLogoSize(size);
    }

    @OnAwt
    public void setTexts(Texts texts) {
        headerInfo.setTexts(texts);
    }

    @OnAwt
    public void setInfoTextName(String name) {
        headerInfo.setInfoTextName(name);
    }

    @OnAwt
    public void setInfoText(String text) {
        headerInfo.setInfoText(text);
    }

    @OnAwt
    public void setLinkTextName(String name) {
        headerInfo.setLinkTextName(name);
    }

    @OnAwt
    public void setLinkText(String text) {
        headerInfo.setLinkText(text);
    }

    /**
     * Returns the component to be added.
     *
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @return the {@link Component}.
     */
    @OnAwt
    public Component getComponent() {
        return header;
    }

}
