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

import static javax.swing.SwingUtilities.invokeLater;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.lang3.ObjectUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import bibliothek.gui.dock.common.SingleCDockable;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.logdock.LogDock;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Shows errors and problems of the application to the user.
 * <p>
 * <h2>AWT Thread</h2>
 * Objects of that class should be used in the AWT event dispatch thread.
 * </p>
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
@OnAwt
public class LogWindowDock {

    private enum ColumnName {

        logwindow_time_column,

        logwindow_title_column,

        logwindow_desciption_column,

        logwindow_type_column;

    }

    private final List<Object> columnNames;

    private final STGroup group;

    @Inject
    private ErrorNodeFactory errorNodeFactory;

    @Inject
    private InfoNodeFactory infoNodeFactory;

    @Inject
    private ErrorCategory errorCategory;

    @Inject
    private InfoCategory infoCategory;

    @Inject
    private LogDock dock;

    /**
     * @see LogWindowDockFactory#create()
     */
    @Inject
    LogWindowDock() {
        this.columnNames = new ArrayList<Object>(4);
        this.group = new STGroup();
    }

    /**
     * Sets the texts resources for the log window dock.
     *
     * @param texts
     *            the {@link Texts}.
     */
    public void setTexts(Texts texts) {
        dock.setTexts(texts);
    }

    /**
     * Sets the locale for the log window dock.
     *
     * @param locale
     *            the {@link Locale}.
     */
    public void setLocale(Locale locale) {
        dock.setLocale(locale);
    }

    /**
     * Creates the problems window.
     *
     * @return this {@link LogWindowDock}.
     */
    public LogWindowDock createWindowDock() {
        setupColumnNames();
        setupDock();
        return this;
    }

    private void setupDock() {
        dock.setColumns(columnNames);
        dock.addCategory(errorCategory);
        dock.addCategory(infoCategory);
    }

    private void setupColumnNames() {
        columnNames.add(ColumnName.logwindow_time_column);
        columnNames.add(ColumnName.logwindow_title_column);
        columnNames.add(ColumnName.logwindow_desciption_column);
        columnNames.add(ColumnName.logwindow_type_column);
    }

    /**
     * Returns the view dock window.
     *
     * @return the {@link ViewDockWindow}.
     */
    public ViewDockWindow getDock() {
        return dock;
    }

    /**
     * Returns the dockable.
     *
     * @return the {@link SingleCDockable}.
     */
    public SingleCDockable getDockable() {
        return dock.getDockable();
    }

    /**
     * Returns the fatal errors category.
     *
     * @return the {@link ErrorCategory}.
     */
    public ErrorCategory getErrorCategory() {
        return errorCategory;
    }

    /**
     * Returns the info category.
     *
     * @return the {@link InfoCategory}.
     */
    public InfoCategory getInfoCategory() {
        return infoCategory;
    }

    /**
     * Adds the info message to the problems.
     *
     * @param message
     *            the {@link InfoNode} message.
     */
    public void addInfoMessage(final InfoNode message) {
        invokeLater(new Runnable() {

            @Override
            public void run() {
                dock.addMessage(message);
            }
        });
    }

    public void addInfoMessage(final Object messageTitle,
            final Object messageDescr, final Object... args) {
        invokeLater(new Runnable() {

            @Override
            public void run() {
                InfoNode info;
                InfoCategory category = getInfoCategory();
                info = infoNodeFactory.create(category);
                info.setTitle(messageTitle.toString());
                String descr = renderMessage(messageDescr, args);
                info.setDescription(descr);
                addInfoMessage(info);
            }
        });
    }

    /**
     * Adds the error message to the problems.
     *
     * @param message
     *            the {@link ErrorNode} message.
     */
    public void addErrorMessage(final ErrorNode message) {
        invokeLater(new Runnable() {

            @Override
            public void run() {
                dock.addMessage(message);
            }
        });
    }

    public void addErrorMessage(final Object messageTitle,
            final Object messageDescr, final Throwable ex, final Object... args) {
        invokeLater(new Runnable() {

            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                ErrorNode error;
                ErrorCategory category = getErrorCategory();
                error = errorNodeFactory.create(category);
                error.setTitle(ObjectUtils.toString(messageTitle));
                String descr = renderMessage(messageDescr, args);
                error.setDescription(descr);
                error.setException(ex);
                addErrorMessage(error);
            }
        });
    }

    public void addErrorMessage(final Object messageTitle, final Throwable ex) {
        invokeLater(new Runnable() {

            @Override
            public void run() {
                ErrorNode error;
                ErrorCategory category = getErrorCategory();
                error = errorNodeFactory.create(category);
                error.setTitle(messageTitle.toString());
                error.setDescription(ex.getLocalizedMessage());
                error.setException(ex);
                addErrorMessage(error);
            }
        });
    }

    private String renderMessage(Object message, Object... args) {
        String messagestr = message.toString();
        notNull(messagestr, "message");
        ST st = new ST(group, messagestr);
        for (int i = 0; i < args.length; i += 2) {
            st.add(args[i].toString(), args[i + 1]);
        }
        String text = st.render();
        return text;
    }

}
