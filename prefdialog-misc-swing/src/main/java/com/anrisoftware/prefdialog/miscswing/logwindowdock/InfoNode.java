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
package com.anrisoftware.prefdialog.miscswing.logwindowdock;

import static com.anrisoftware.prefdialog.miscswing.logwindowdock.LogWindowDockResource.logwindow_info;

import java.util.Locale;

import javax.inject.Inject;

import org.joda.time.DateTime;

import com.anrisoftware.prefdialog.miscswing.logpane.MessageNode;
import com.anrisoftware.resources.texts.api.Texts;
import com.google.inject.assistedinject.Assisted;

/**
 * Exception info node.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public class InfoNode extends MessageNode {

    private final DateTime time;

    private String title;

    private String description;

    private String type;

    @Inject
    InfoNode(@Assisted InfoCategory category) {
        super(category);
        this.time = DateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Object getValueAt(int column) {
        switch (column) {
        case 0:
            return time;
        case 1:
            return title;
        case 2:
            return description;
        case 3:
            return type;
        default:
            return null;
        }
    }

    @Override
    protected void doUpdateTextsResource() {
        super.doUpdateTextsResource();
        Texts texts = getTexts();
        Locale locale = getLocale();
        type = texts.getResource(logwindow_info.name(), locale).getText();
    }
}
