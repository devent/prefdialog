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

import static com.anrisoftware.prefdialog.miscswing.filechoosers.CsvFilesFilter._.csv_extension;
import static com.anrisoftware.prefdialog.miscswing.filechoosers.CsvFilesFilter._.csv_filter_description;
import static org.apache.commons.io.FilenameUtils.isExtension;

import java.io.File;
import java.util.Locale;

import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Comma separated values (CSV) files filter.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.4
 */
public class CsvFilesFilter extends FileFilterExtension {

    /**
     * {@link CsvFilesFilter} texts resources.
     *
     * @author Erwin Mueller, erwin.mueller@deventm.org
     * @since 3.4
     */
    enum _ {

        /**
         * Comma separated values (CSV) files description.
         */
        csv_filter_description,

        /**
         * Comma separated values (CSV) files extension.
         */
        csv_extension;

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
        return f.isFile() && isExtension(f.getName(), csv_extension.toString());
    }

    @Override
    public String getDescription() {
        return csv_filter_description.toString();
    }

    @Override
    public String getExtension() {
        return csv_extension.toString();
    }

    private void retrieveTextResources() {
        for (_ value : _.values()) {
            value.setResource(texts.getResource(value.name(), locale));
        }
    }

}
