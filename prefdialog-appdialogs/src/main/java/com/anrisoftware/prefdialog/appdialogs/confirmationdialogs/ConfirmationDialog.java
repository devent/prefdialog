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
package com.anrisoftware.prefdialog.appdialogs.confirmationdialogs;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.templates.api.Templates;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Simple two or three option confirmation dialog.
 * <p>
 * <h2>AWT Thread</h2>
 * Objects of that class should be used in the AWT event dispatch thread.
 * </p>
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
@OnAwt
public class ConfirmationDialog {

    private static final String CONFIRMATION_TEXT_NAME = "confirmationText";

    private static final String ARGS_PARAM = "args";

    @Inject
    private SimpleDialog simpleDialog;

    @Inject
    private UiConfirmationPanel panel;

    private Texts texts;

    private Images images;

    private String informationTextResourceName;

    private Map<String, Object> informationTextResourceArgs;

    private String approveActionName;

    private Templates templates;

    private String iconName;

    private String cancelActionName;

    private String dialogTitleText;

    private String restoreActionName;

    /**
     * Creates the confirmation dialog.
     *
     * @return this {@link ConfirmationDialog}.
     */
    public ConfirmationDialog createDialog() {
        setupSimpleDialog();
        setupPanel();
        return this;
    }

    public void setTexts(Texts texts) {
        this.texts = texts;
        simpleDialog.setTexts(texts);
    }

    public void setTemplates(Templates templates) {
        this.templates = templates;
    }

    public void setImages(Images images) {
        this.images = images;
        simpleDialog.setImages(images);
    }

    /**
     * Sets the locale of the components of the dialog.
     *
     * @param locale
     *            the {@link Locale}.
     */
    public void setLocale(Locale locale) {
        simpleDialog.setLocale(locale);
        panel.setLocale(locale);
        if (templates != null) {
            updateInformationText();
        }
    }

    public void setDialog(JDialog dialog) {
        simpleDialog.setDialog(dialog);
    }

    public void setRestoreActionName(String name) {
        this.restoreActionName = name;
        if (texts != null) {
            simpleDialog.setRestoreActionName(name);
        }
    }

    public void setApproveActionName(String name) {
        this.approveActionName = name;
        if (texts != null) {
            simpleDialog.setApproveActionName(name);
        }
    }

    public void setCancelActionName(String name) {
        this.cancelActionName = name;
        if (texts != null) {
            simpleDialog.setCancelActionName(name);
        }
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
        if (images != null) {
            updateInformationIcon();
        }
    }

    public void setInformationText(String informationTextResourceName,
            Map<String, Object> args) {
        this.informationTextResourceName = informationTextResourceName;
        this.informationTextResourceArgs = new HashMap<String, Object>(args);
        if (templates != null) {
            updateInformationText();
        }
    }

    public void setDialogTitleText(String dialogTitleText) {
        this.dialogTitleText = dialogTitleText;
        if (texts != null) {
            updateDialogTitle();
        }
    }

    public void addApprovalAction(ActionListener action) {
        simpleDialog.addApprovalAction(action);
    }

    public void addCancelAction(ActionListener action) {
        simpleDialog.addCancelAction(action);
    }

    public void addRestoreAction(ActionListener action) {
        simpleDialog.addRestoreAction(action);
    }

    public void openDialog() {
        simpleDialog.openDialog();
    }

    public void closeDialog() {
        simpleDialog.closeDialog();
    }

    public void packDialog() {
        simpleDialog.packDialog();
    }

    public void setLocationRelativeTo(Component owner) {
        simpleDialog.setLocationRelativeTo(owner);
    }

    public JDialog getDialog() {
        return simpleDialog.getDialog();
    }

    public void setShowRestoreButton(boolean show) {
        simpleDialog.setShowRestoreButton(show);
    }

    public Status getStatus() {
        return simpleDialog.getStatus();
    }

    private void setupPanel() {
        panel.getIconLabel().setText(null);
        updateDialogTitle();
        updateInformationText();
        updateInformationIcon();
    }

    private void setupSimpleDialog() {
        simpleDialog.setFieldsPanel(panel);
        simpleDialog.setTexts(texts);
        simpleDialog.setImages(images);
        simpleDialog.setRestoreActionName(restoreActionName);
        simpleDialog.setApproveActionName(approveActionName);
        simpleDialog.setCancelActionName(cancelActionName);
        simpleDialog.createDialog();
    }

    private void updateInformationText() {
        if (informationTextResourceName == null) {
            return;
        }
        Locale locale = simpleDialog.getLocale();
        String text = templates
                .getResource(informationTextResourceName, locale).getText(
                        CONFIRMATION_TEXT_NAME, ARGS_PARAM,
                        informationTextResourceArgs);
        panel.getTextLabel().setText(text);
    }

    private void updateDialogTitle() {
        if (dialogTitleText == null) {
            return;
        }
        Locale locale = simpleDialog.getLocale();
        String text = texts.getResource(dialogTitleText, locale).getText();
        Dialog dialog = simpleDialog.getDialog();
        dialog.setTitle(text);
    }

    private void updateInformationIcon() {
        if (iconName == null) {
            return;
        }
        Locale locale = simpleDialog.getLocale();
        ImageIcon icon = new ImageIcon(images.getResource(iconName, locale,
                IconSize.HUGE).getBufferedImage(TYPE_INT_ARGB));
        panel.getIconLabel().setIcon(icon);
    }

}
