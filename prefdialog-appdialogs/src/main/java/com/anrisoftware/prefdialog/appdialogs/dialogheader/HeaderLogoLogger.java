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

import static com.anrisoftware.prefdialog.appdialogs.dialogheader.HeaderLogoLogger._.error_scale_header_logo_image;
import static com.anrisoftware.prefdialog.appdialogs.dialogheader.HeaderLogoLogger._.error_scale_header_logo_image_message;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging for {@link HeaderLogo}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class HeaderLogoLogger extends AbstractLogger {

    enum _ {

        error_scale_header_logo_image("Error scaling header logo image"),

        error_scale_header_logo_image_message(
                "Error scaling header logo image: {}");

        private String name;

        private _(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Sets the context of the logger to {@link HeaderLogo}.
     */
    public HeaderLogoLogger() {
        super(HeaderLogo.class);
    }

    RuntimeException errorScaleImage(HeaderLogo header, Exception e) {
        return logException(new ContextedRuntimeException(
                error_scale_header_logo_image.toString(), e),
                error_scale_header_logo_image_message.toString(),
                e.getLocalizedMessage());
    }

}
