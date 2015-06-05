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

import java.util.Locale;

import javax.swing.JLabel;

import org.jdesktop.swingx.JXHyperlink;

import com.anrisoftware.resources.texts.api.Texts;

/**
 * The header information text.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.1
 */
final class HeaderInfo {

    private UiHeader header;

    private Texts texts;

    private String infoTextName;

    private Locale locale;

    private String infoText;

    private String linkTextName;

    private String linkText;

    public void setHeader(UiHeader header) {
        this.header = header;
        this.infoText = header.getInfoText().getText();
        this.linkText = header.getLinkButton().getText();
        this.locale = header.getLocale();
    }

    public void setTexts(Texts texts) {
        this.texts = texts;
        updateTexts();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        updateTexts();
    }

    public void setInfoTextName(String name) {
        this.infoTextName = name;
        updateTexts();
    }

    public void setInfoText(String text) {
        this.infoText = text;
        updateTexts();
    }

    public void setLinkTextName(String name) {
        this.linkTextName = name;
        updateTexts();
    }

    public void setLinkText(String text) {
        this.linkText = text;
        updateTexts();
    }

    private void updateTexts() {
        JLabel infoLabel = header.getInfoText();
        infoLabel.setText(getInfoText());
        JXHyperlink linkButton = header.getLinkButton();
        linkButton.setText(getLinkText());
    }

    private String getInfoText() {
        if (infoTextName != null && texts != null) {
            String name = infoTextName;
            return texts.getResource(name, locale).getText();
        } else {
            return infoText;
        }
    }

    private String getLinkText() {
        if (linkTextName != null && texts != null) {
            String name = linkTextName;
            return texts.getResource(name, locale).getText();
        } else {
            return linkText;
        }
    }

}
