/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-panel.
 *
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel;

import static java.lang.String.format;

import java.util.ServiceLoader;

import javax.inject.Provider;
import javax.inject.Singleton;

import com.anrisoftware.prefdialog.fields.FieldService;

/**
 * Provides the preferences panel service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.6
 */
@Singleton
public class PreferencesPanelServiceProvider implements
        Provider<PreferencesPanelService> {

    private static final String ERROR_FIND_SERVICE_MESSAGE = "Error find service %s";

    @Override
    public synchronized PreferencesPanelService get() {
        for (FieldService service : ServiceLoader.load(FieldService.class)) {
            if (service.getInfo().equals(PreferencesPanelService.INFO)) {
                return (PreferencesPanelService) service;
            }
        }
        throw new RuntimeException(format(ERROR_FIND_SERVICE_MESSAGE,
                PreferencesPanelService.INFO));
    }

}
