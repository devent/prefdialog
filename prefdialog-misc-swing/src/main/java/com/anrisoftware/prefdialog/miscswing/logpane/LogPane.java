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
package com.anrisoftware.prefdialog.miscswing.logpane;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.texts.api.Texts;
import com.google.inject.Guice;

/**
 * Uses a tree table to display messages ordered in categories.
 * <p>
 * <h2>AWT Thread</h2>
 * Objects of that class should be used in the AWT event dispatch thread.
 * </p>
 *
 * <pre>
 * pane.setColumns(columnNames);
 * 
 * errors = new CategoryNode();
 * errors.setName(&quot;Errors&quot;);
 * pane.addCategory(errors);
 * 
 * info = new CategoryNode();
 * info.setName(&quot;Info&quot;);
 * pane.addCategory(info);
 * 
 * message = new MessageNode(errors);
 * message.setValueAt(DateTime.now(), 0);
 * message.setValueAt(&quot;Exception&quot;, 1);
 * message.setValueAt(&quot;Some message&quot;, 2);
 * message.setValueAt(ex, 3);
 * pane.addMessage(message);
 * </pre>
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@OnAwt
public class LogPane {

    /**
     * Creates the problems pane.
     *
     * @return the {@link LogPane}.
     */
    public static LogPane createLogPane() {
        return Guice.createInjector().getInstance(LogPane.class);
    }

    private final UiPane pane;

    private final RootNode root;

    private DefaultTreeTableModel model;

    private Texts texts;

    private List<Object> columnNames;

    private List<String> columnNamesResources;

    private final List<CategoryNode> categories;

    private final List<MessageNode> messages;

    private Locale locale;

    @Inject
    LogPane(UiPane pane, RootNode rootNode) {
        this.pane = pane;
        this.root = rootNode;
        this.categories = new ArrayList<CategoryNode>();
        this.messages = new ArrayList<MessageNode>();
    }

    /**
     * Sets the columns of the problems pane.
     *
     * @param columnNames
     *            the column names.
     */
    public void setColumns(List<Object> columnNames) {
        root.setName("root");
        root.setColumnCount(columnNames.size());
        this.columnNames = columnNames;
        this.columnNamesResources = new ArrayList<String>(columnNames.size());
        for (Object object : columnNames) {
            columnNamesResources.add(object.toString());
        }
        this.model = new DefaultTreeTableModel(root, columnNames);
        this.pane.getProblemsTable().setTreeTableModel(model);
    }

    /**
     * Sets the texts resources for the categories and messages.
     *
     * @param texts
     *            the {@link Texts}.
     */
    public void setTexts(Texts texts) {
        this.texts = texts;
        updateTextsResource();
        for (CategoryNode node : categories) {
            node.setTexts(texts);
        }
        for (MessageNode node : messages) {
            node.setTexts(texts);
        }
    }

    /**
     * Sets the locale for the log pane.
     *
     * @param locale
     *            the {@link Locale}.
     *
     * @since 3.2
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        updateTextsResource();
        for (CategoryNode node : categories) {
            node.setLocale(locale);
        }
        for (MessageNode node : messages) {
            node.setLocale(locale);
        }
    }

    private void updateTextsResource() {
        if (texts == null) {
            return;
        }
        if (locale == null) {
            return;
        }
        for (int i = 0; i < columnNamesResources.size(); i++) {
            try {
                String name = columnNamesResources.get(i);
                name = texts.getResource(name, locale).getText();
                columnNames.set(i, name);
            } catch (ResourcesException e) {
            }
        }
        this.model = new DefaultTreeTableModel(root, columnNames);
        this.pane.getProblemsTable().setTreeTableModel(model);
    }

    /**
     * Returns the pane component to be added in the container.
     *
     * @return the {@link Component}.
     */
    public Component getComponent() {
        return pane;
    }

    /**
     * Adds a new category to the problems pane.
     *
     * @param category
     *            the {@link CategoryNode}.
     */
    public void addCategory(CategoryNode category) {
        categories.add(category);
        category.setColumnCount(root.getColumnCount());
        model.insertNodeInto(category, root, 0);
    }

    /**
     * Adds the message to the list of problems.
     *
     * @param message
     *            the {@link MessageNode}.
     */
    public void addMessage(MessageNode message) {
        messages.add(message);
        model.insertNodeInto(message, message.getCategory(), 0);
        pane.getProblemsTable().expandAll();
    }
}
