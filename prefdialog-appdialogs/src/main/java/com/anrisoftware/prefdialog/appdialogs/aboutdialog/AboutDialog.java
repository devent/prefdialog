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
package com.anrisoftware.prefdialog.appdialogs.aboutdialog;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

import javax.inject.Inject;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialog;
import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialogFactory;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status;
import com.anrisoftware.resources.images.api.ImageScalingWorkerFactory;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

/**
 * About dialog.
 * <p>
 * <h2>AWT Thread</h2>
 * Objects of that class should be used in the AWT event dispatch thread.
 * </p>
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
@OnAwt
public class AboutDialog {

    /**
     * About tab pane name
     */
    public static final String ABOUT_PANE_NAME = "aboutPane";

    private final Map<Integer, String> aboutTexts;

    @Inject
    private UiAboutPanel panel;

    private AppDialog appDialog;

    private Texts texts;

    /**
     * @see AboutDialogFactory#create()
     */
    AboutDialog() {
        this.aboutTexts = new HashMap<Integer, String>();
    }

    @Inject
    void setTextsFactory(TextsFactory factory) {
        this.texts = factory.create("AboutDialog");
    }

    @Inject
    void setAppDialog(AppDialogFactory factory) {
        AppDialog dialog = factory.create();
        dialog.setContent(panel);
        dialog.setShowRestoreButton(false);
        this.appDialog = dialog;
        setDialogTexts(texts);
    }

    /**
     * Creates the about dialog.
     *
     * @return this {@link AboutDialog}.
     *
     * @see AppDialog#createDialog()
     */
    public AboutDialog createDialog() {
        appDialog.setShowApproveButton(false);
        appDialog.setShowRestoreButton(false);
        appDialog.setCancelActionName("close_action");
        appDialog.createDialog();
        return this;
    }

    public void setDialog(JDialog dialog) {
        appDialog.setDialog(dialog);
    }

    public void setImageScalingWorkerFactory(ImageScalingWorkerFactory factory) {
        appDialog.setImageScalingWorkerFactory(factory);
    }

    public void setDialogHeadersTexts(Texts texts) {
        appDialog.setDialogHeadersTexts(texts);
    }

    public void setDialogTexts(Texts texts) {
        appDialog.setDialogTexts(texts);
        this.texts = texts;
    }

    public void setDialogHeaderImages(Images images) {
        appDialog.setDialogHeaderImages(images);
    }

    public void setDialogImages(Images images) {
        appDialog.setDialogImages(images);
    }

    public void setLocale(Locale locale) {
        appDialog.setLocale(locale);
        panel.setLocale(locale);
        updateLocale(locale);
    }

    public Locale getLocale() {
        return appDialog.getLocale();
    }

    public void setLogoImageName(String name) {
        appDialog.setLogoImageName(name);
    }

    public void setLogoImage(Image image) {
        appDialog.setLogoImage(image);
    }

    public void setLogoSize(Dimension size) {
        appDialog.setLogoSize(size);
    }

    public void setInfoTextName(String name) {
        appDialog.setInfoTextName(name);
    }

    public void setInfoText(String text) {
        appDialog.setInfoText(text);
    }

    public void setLinkTextName(String name) {
        appDialog.setLinkTextName(name);
    }

    public void setLinkText(String text) {
        appDialog.setLinkText(text);
    }

    public Status getStatus() {
        return appDialog.getStatus();
    }

    public void addAboutText(String name, String text) {
        String title = getTitle(name);
        JTabbedPane pane = panel.getAboutPane();
        Component component = pane.add(title, panel.createAboutText(text));
        aboutTexts.put(pane.indexOfTabComponent(component), name);
    }

    private void updateLocale(Locale locale) {
        JTabbedPane pane = panel.getAboutPane();
        for (int i = 0; i < pane.getTabCount(); i++) {
            String name = aboutTexts.get(i);
            String title = getTitle(name);
            pane.setTitleAt(i, title);
        }
    }

    private String getTitle(String name) {
        try {
            return texts.getResource(name, getLocale()).getText();
        } catch (MissingResourceException e) {
            return name;
        }
    }

}
