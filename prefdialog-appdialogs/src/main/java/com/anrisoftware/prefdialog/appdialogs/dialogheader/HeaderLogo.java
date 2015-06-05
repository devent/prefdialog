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

import java.awt.Dimension;
import java.awt.Image;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.anrisoftware.resources.images.api.ImageScalingWorkerFactory;
import com.anrisoftware.resources.images.api.Images;

/**
 * The header logo image.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
final class HeaderLogo {

    @Inject
    private HeaderLogoLogger log;

    private UiHeader header;

    private ImageScalingWorkerFactory imageScalingWorkerFactory;

    private Images images;

    private Image logoImage;

    private Dimension logoSize;

    private Locale locale;

    private String logoImageName;

    public void setHeader(UiHeader header) {
        this.header = header;
        this.logoImage = header.getLogoImage();
        this.locale = header.getLocale();
    }

    public void setImageScalingWorkerFactory(ImageScalingWorkerFactory factory) {
        this.imageScalingWorkerFactory = factory;
    }

    public void setImages(Images images) {
        this.images = images;
        updateImages();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        updateImages();
    }

    public void setLogoImageName(String name) {
        this.logoImageName = name;
        updateImages();
    }

    public void setLogoSize(Dimension size) {
        this.logoSize = new Dimension(size);
        updateImages();
    }

    public void setLogoImage(Image image) {
        this.logoImage = image;
        updateImages();
    }

    private void updateImages() {
        JLabel logoLabel = header.getLogoLabel();
        logoLabel.setIcon(new ImageIcon(getLogoImage()));
        if (logoSize != null) {
            logoLabel.setMinimumSize(logoSize);
            logoLabel.setMaximumSize(logoSize);
            logoLabel.setPreferredSize(logoSize);
            logoLabel.setSize(logoSize);
        }
    }

    private Image getLogoImage() {
        if (logoImageName != null && images != null && logoSize != null) {
            String name = logoImageName;
            return images.getResource(name, locale, logoSize).getImage();
        } else if (logoSize != null && imageScalingWorkerFactory != null) {
            return resizeImage(logoImage);
        } else {
            return logoImage;
        }
    }

    private Image resizeImage(Image image) {
        try {
            return imageScalingWorkerFactory.create(image, logoSize).call();
        } catch (Exception e) {
            throw log.errorScaleImage(this, e);
        }
    }

}
