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

import java.util.Locale;

import com.anrisoftware.resources.templates.api.TemplateResource;
import com.anrisoftware.resources.templates.api.Templates;
import com.anrisoftware.resources.templates.api.TemplatesFactory;
import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

/**
 * The about dialog resources.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
public enum AboutDialogResource {

    /**
     * Default license tab title.
     */
    license_title,

    /**
     * Default about text template.
     */
    about_text,

    /**
     * GPLv3 license text.
     */
    gpl3_license,

    /**
     * Freepik Creative Commons BY 3.0
     */
    freepik_cc_by_3_0_license;

    public static Templates loadTemplates(TemplatesFactory factory) {
        return factory.create("AboutDialog");
    }

    public static Texts loadTexts(TextsFactory factory) {
        return factory.create("AboutDialog");
    }

    public TemplateResource getTemplate(Templates templates, Locale locale) {
        return templates.getResource(this.name(), locale);
    }

    public TextResource getText(Texts texts, Locale locale) {
        return texts.getResource(this.name(), locale);
    }

}
