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
package com.anrisoftware.prefdialog.miscswing.statusbar;

import java.util.Locale;

import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Status bar texts.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public enum StatusBarTestMessage {

    non_message,

    status_bar_message_one,

    status_bar_message_two;

    /**
     * Retrieve the text resources.
     * 
     * @param texts
     *            the {@link Texts} texts resources.
     * 
     * @param locale
     *            the {@link Locale} locale.
     */
    public static void retrieveTextResources(Texts texts, Locale locale) {
        for (StatusBarTestMessage value : values()) {
            value.setResource(texts.getResource(value.name(), locale));
        }
    }

    private TextResource resource;

    /**
     * Retrieve the text resource.
     * 
     * @param resource
     *            the {@link TextResource} text resources.
     */
    public void setResource(TextResource resource) {
        this.resource = resource;
    }

    public TextResource getResource() {
        return resource;
    }

    @Override
    public String toString() {
        return resource.getText();
    }
}
