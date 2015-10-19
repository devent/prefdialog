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

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.lang.ref.SoftReference;

import javax.inject.Inject;
import javax.swing.JDialog;

import org.joda.time.DateTime;

import com.anrisoftware.prefdialog.miscswing.dialogsworker.AbstractCreateDialogWorker;
import com.anrisoftware.prefdialog.miscswing.dialogsworker.CreateDialogInterrupedException;
import com.anrisoftware.prefdialog.miscswing.dialogsworker.CreateDialogWorkerException;
import com.anrisoftware.resources.images.api.ImageScalingWorkerFactory;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Creates the register dialog on the AWT event thread.
 *
 * @see RegisterDialog
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.4
 */
public class RegisterDialogCreateWorker extends
        AbstractCreateDialogWorker<JDialog> {

    @Inject
    private RegisterDialogFactory registerDialogFactory;

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

    private long daysLeft;

    private String email;

    private String key;

    private String code;

    private String name;

    private SoftReference<RegisterDialog> registerDialog;

    private DateTime registrationDate;

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

    public void setDaysLeft(long daysLeft) {
        this.daysLeft = daysLeft;
        forceRecreationDialog();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setKey(String key) {
        this.key = key;
        forceRecreationDialog();
    }

    public void setCode(String code) {
        this.code = code;
        forceRecreationDialog();
    }

    public void setName(String name) {
        this.name = name;
        forceRecreationDialog();
    }

    public void setRegistrationDate(DateTime registrationDate) {
        this.registrationDate = registrationDate;
        forceRecreationDialog();
    }

    /**
     * Returns the created registration dialog.
     *
     * @return the {@link RegisterDialog}.
     *
     * @throws CreateDialogWorkerException
     *             if there was an error creating the dialog.
     *
     * @throws CreateDialogInterrupedException
     *             if the dialog creation on the AWT event thread was
     *             interrupted.
     */
    public RegisterDialog getRegisterDialog()
            throws CreateDialogInterrupedException, CreateDialogWorkerException {
        if (registerDialog == null || registerDialog.get() == null) {
            forceRecreationDialog();
        }
        getDialog();
        return registerDialog.get();
    }

    @Override
    protected JDialog createDialog() {
        JDialog jdialog = new JDialog(owner, true);
        RegisterDialog dialog = registerDialogFactory.create();
        dialog.setDialog(jdialog);
        if (imageScalingWorkerFactory != null) {
            dialog.setImageScalingWorkerFactory(imageScalingWorkerFactory);
        }
        if (dialogHeadersTexts != null) {
            dialog.setDialogHeadersTexts(dialogHeadersTexts);
        }
        if (dialogTexts != null) {
            dialog.setDialogTexts(dialogTexts);
        }
        if (dialogHeaderImages != null) {
            dialog.setDialogHeaderImages(dialogHeaderImages);
        }
        if (dialogImages != null) {
            dialog.setDialogImages(dialogImages);
        }
        if (logoImageName != null) {
            dialog.setLogoImageName(logoImageName);
        }
        if (logoImage != null) {
            dialog.setLogoImage(logoImage);
        }
        if (logoSize != null) {
            dialog.setLogoSize(logoSize);
        }
        if (infoTextName != null) {
            dialog.setInfoTextName(infoTextName);
        }
        if (infoText != null) {
            dialog.setInfoText(infoText);
        }
        if (linkTextName != null) {
            dialog.setLinkTextName(linkTextName);
        }
        if (linkText != null) {
            dialog.setLinkText(linkText);
        }
        dialog.setEmail(email);
        dialog.setKey(key);
        dialog.setName(name);
        dialog.setDaysLeft(daysLeft);
        dialog.setCode(code);
        dialog.setRegistrationDate(registrationDate);
        dialog.setLocale(getLocale());
        dialog.createDialog();
        jdialog.setTitle(getDialogTitleFromResource());
        jdialog.pack();
        jdialog.setSize(new Dimension(580, 420));
        jdialog.setLocationRelativeTo(owner);
        this.registerDialog = new SoftReference<RegisterDialog>(dialog);
        return jdialog;
    }

}
