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
