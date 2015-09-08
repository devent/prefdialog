/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import static java.lang.String.format;
import static org.apache.commons.io.FilenameUtils.isExtension;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import org.apache.commons.io.FilenameUtils;

/**
 * Returns the extension for the forecast resource file.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3,2
 */
public abstract class FileFilterExtension extends FileFilter {

    /**
     * Returns the file extension of the filter.
     *
     * @return the extension {@link String}.
     */
    public abstract String getExtension();

    /**
     * Tests if the specified file have the extension of the file filter.
     *
     * @param file
     *            the {@link File}.
     *
     * @return {@code true} if the specified file have the extension of the file
     *         filter.
     */
    public boolean fileHaveExtension(File file) {
        return FilenameUtils.getExtension(file.getName())
                .equals(getExtension());
    }

    /**
     * Tests if the specified file path have the extension of the file filter.
     *
     * @param path
     *            the {@link String}.
     *
     * @return {@code true} if the specified path have the extension of the file
     *         filter.
     */
    public boolean fileHaveExtension(String path) {
        return FilenameUtils.getExtension(path).equals(getExtension());
    }

    /**
     * Appends the file extension if the file does not have the extension
     * already.
     *
     * @param file
     *            the {@link File}.
     *
     * @return the {@link File} with appended extension.
     */
    public File appendExtension(File file) {
        return appendExtensionToFile(file, getExtension());
    }

    /**
     * Appends the file extension if the file does not have the extension
     * already.
     *
     * @param file
     *            the {@link File}.
     *
     * @param extension
     *            the {@link String} extension.
     *
     * @return the {@link File} with appended extension.
     */
    public static File appendExtensionToFile(File file, String extension) {
        if (!isExtension(file.getName(), extension)) {
            file = new File(format("%s.%s", file.getAbsolutePath(), extension));
        }
        return file;
    }
}
