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
package com.anrisoftware.prefdialog.miscswing.filechoosers;

import static com.anrisoftware.prefdialog.miscswing.filechoosers.AllFilesFilter._.all_extension;
import static com.anrisoftware.prefdialog.miscswing.filechoosers.AllFilesFilter._.all_filter_description;

import java.io.File;
import java.util.Locale;

import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * All files filter.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.4
 */
public class AllFilesFilter extends FileFilterExtension {

    /**
     * {@link AllFilesFilter} texts resources.
     *
     * @author Erwin Mueller, erwin.mueller@deventm.org
     * @since 3.4
     */
    enum _ {

        /**
         * All files description.
         */
        all_filter_description,

        /**
         * All files extension.
         */
        all_extension;

        private TextResource resource;

        /**
         * Retrieve the text resource.
         *
         * @param resource
         *            the text {@link TextResource} resource.
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

    private Locale locale;

    private Texts texts;

    public void setLocale(Locale locale) {
        this.locale = locale;
        if (texts != null) {
            retrieveTextResources();
        }
    }

    public void setTexts(Texts texts) {
        this.texts = texts;
        if (locale != null) {
            retrieveTextResources();
        }
    }

    @Override
    public boolean accept(File f) {
        return f.isDirectory() || acceptFile(f);
    }

    private boolean acceptFile(File f) {
        return f.isFile();
    }

    @Override
    public String getDescription() {
        return all_filter_description.toString();
    }

    @Override
    public String getExtension() {
        return all_extension.toString();
    }

    private void retrieveTextResources() {
        for (_ value : _.values()) {
            value.setResource(texts.getResource(value.name(), locale));
        }
    }

}
