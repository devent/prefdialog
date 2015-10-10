/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.appdialogs.aboutdialog;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JDialog;

import com.anrisoftware.prefdialog.miscswing.dialogsworker.AbstractCreateDialogWorker;
import com.anrisoftware.resources.images.api.ImageScalingWorkerFactory;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Creates the about dialog on the AWT event thread.
 *
 * @see AboutDialog
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.3
 */
public class AboutDialogCreateWorker extends
        AbstractCreateDialogWorker<JDialog> {

    private final List<AboutTextEntry> aboutTexts;

    @Inject
    private AboutDialogFactory aboutDialogFactory;

    private ImageScalingWorkerFactory imageScalingWorkerFactory;

    private Texts dialogHeadersTexts;

    private Texts dialogTexts;

    private Images dialogHeaderImages;

    private Images dialogImages;

    private String logoImageName;

    private Image logoImage;

    private Dimension logoSize;

    private String infoTextName;

    private String infoText;

    private String linkTextName;

    private String linkText;

    private Frame owner;

    @Inject
    AboutDialogCreateWorker() {
        this.aboutTexts = new ArrayList<AboutDialogCreateWorker.AboutTextEntry>();
    }

    public void setImageScalingWorkerFactory(ImageScalingWorkerFactory factory) {
        this.imageScalingWorkerFactory = factory;
    }

    public void setDialogHeadersTexts(Texts texts) {
        this.dialogHeadersTexts = texts;
    }

    public void setDialogTexts(Texts texts) {
        this.dialogTexts = texts;
    }

    public void setDialogHeaderImages(Images images) {
        this.dialogHeaderImages = images;
    }

    public void setDialogImages(Images images) {
        this.dialogImages = images;
    }

    public void setLogoImageName(String name) {
        this.logoImageName = name;
    }

    public void setLogoImage(Image image) {
        this.logoImage = image;
    }

    public void setLogoSize(Dimension size) {
        this.logoSize = size;
    }

    public void setInfoTextName(String name) {
        this.infoTextName = name;
    }

    public void setInfoText(String text) {
        this.infoText = text;
    }

    public void setLinkTextName(String name) {
        this.linkTextName = name;
    }

    public void setLinkText(String text) {
        this.linkText = text;
    }

    public void addAboutText(String name, String text) {
        aboutTexts.add(new AboutTextEntry(name, text));
    }

    @Override
    protected JDialog createDialog() {
        JDialog jdialog = new JDialog(owner);
        AboutDialog aboutDialog = aboutDialogFactory.create();
        aboutDialog.setDialog(jdialog);
        if (imageScalingWorkerFactory != null) {
            aboutDialog.setImageScalingWorkerFactory(imageScalingWorkerFactory);
        }
        if (dialogHeadersTexts != null) {
            aboutDialog.setDialogHeadersTexts(dialogHeadersTexts);
        }
        if (dialogTexts != null) {
            aboutDialog.setDialogTexts(dialogTexts);
        }
        if (dialogHeaderImages != null) {
            aboutDialog.setDialogHeaderImages(dialogHeaderImages);
        }
        if (dialogImages != null) {
            aboutDialog.setDialogImages(dialogImages);
        }
        if (logoImageName != null) {
            aboutDialog.setLogoImageName(logoImageName);
        }
        if (logoImage != null) {
            aboutDialog.setLogoImage(logoImage);
        }
        if (logoSize != null) {
            aboutDialog.setLogoSize(logoSize);
        }
        if (infoTextName != null) {
            aboutDialog.setInfoTextName(infoTextName);
        }
        if (infoText != null) {
            aboutDialog.setInfoText(infoText);
        }
        if (linkTextName != null) {
            aboutDialog.setLinkTextName(linkTextName);
        }
        if (linkText != null) {
            aboutDialog.setLinkText(linkText);
        }
        aboutDialog.setLocale(getLocale());
        for (AboutTextEntry entry : aboutTexts) {
            aboutDialog.addAboutText(entry.name, entry.text);
        }
        aboutDialog.createDialog();
        jdialog.setTitle(getDialogTitleFromResource());
        jdialog.pack();
        jdialog.setSize(new Dimension(580, 420));
        jdialog.setLocationRelativeTo(owner);
        return jdialog;
    }

    private static class AboutTextEntry {

        final String name;

        final String text;

        public AboutTextEntry(String name, String text) {
            this.name = name;
            this.text = text;
        }

    }

}
