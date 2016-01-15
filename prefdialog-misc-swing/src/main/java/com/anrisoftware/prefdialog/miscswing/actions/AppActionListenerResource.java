/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.actions;

import java.util.Locale;

import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Application action listener resource.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.3
 */
public enum AppActionListenerResource {

    application_error_description,

    application_error_message;

    public static void loadTexts(Texts texts, Locale locale) {
        for (AppActionListenerResource value : values()) {
            value.setTexts(texts, locale);
        }
    }

    private TextResource textResource;

    public void setTexts(Texts texts, Locale locale) {
        this.textResource = texts.getResource(this.name(), locale);
    }

    @Override
    public String toString() {
        return textResource.getText();
    }
}
