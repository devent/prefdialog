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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import com.anrisoftware.resources.templates.api.TemplateResource;
import com.anrisoftware.resources.templates.api.Templates;
import com.anrisoftware.resources.templates.api.TemplatesFactory;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

/**
 * Renders the default about text.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
public class AboutTextRenderer {

    private Templates templates;

    private Texts texts;

    @Inject
    void setTemplatesFactory(TemplatesFactory factory) {
        this.templates = factory.create("AboutDialog");
    }

    public Templates getTemplates() {
        return templates;
    }

    @Inject
    void setTextsFactory(TextsFactory factory) {
        this.texts = factory.create("AboutDialog");
    }

    public Texts getTexts() {
        return texts;
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
        setupDefaultArgs(args);
        TemplateResource template = about_text.getTemplate(templates, locale);
        template.invalidate();
        return template.getText("aboutText", "args", args);
    }

    private void setupDefaultArgs(Map<String, Object> args) {
        putValueIfNotExists("body", new HashMap<String, Object>(), args);
        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) args.get("body");
        putValueIfNotExists("fontFamily", "sans-serif", body);
        putValueIfNotExists("fontStyle", "normal", body);
        putValueIfNotExists("fontSize", "12pt", body);
        putValueIfNotExists("width", "400px", body);
    }

    private void putValueIfNotExists(String key, Object value,
            Map<String, Object> args) {
        if (!args.containsKey(key)) {
            args.put(key, value);
        }
    }
}
