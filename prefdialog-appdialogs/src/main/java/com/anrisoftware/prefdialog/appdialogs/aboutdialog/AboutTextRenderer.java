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

import static com.anrisoftware.prefdialog.appdialogs.aboutdialog.AboutDialogResource.about_text;

import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import com.anrisoftware.resources.templates.api.TemplateResource;
import com.anrisoftware.resources.templates.api.Templates;
import com.anrisoftware.resources.templates.api.TemplatesFactory;

/**
 * Renders the default about text.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
public class AboutTextRenderer {

    private Templates templates;

    @Inject
    void setTemplatesFactory(TemplatesFactory factory) {
        this.templates = factory.create("AboutDialog");
    }

    /**
     * Renders and returns the about text.
     *
     * @param locale
     *            the {@link Locale}.
     *
     * @param args
     *            the {@link Map} of the arguments.
     *
     * @return the {@link String} text.
     */
    public String getText(Locale locale, Map<String, Object> args) {
        TemplateResource template = about_text.getTemplate(templates, locale);
        template.invalidate();
        return template.getText("aboutText", "args", args);
    }
}
