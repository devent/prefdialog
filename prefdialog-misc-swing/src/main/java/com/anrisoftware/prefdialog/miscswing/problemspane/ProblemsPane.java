/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.problemspane;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.resources.api.ResourcesException;
import com.anrisoftware.resources.texts.api.Texts;
import com.google.inject.Guice;

/**
 * Uses a tree table to display messages ordered in categories.
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
public class ProblemsPane {

	/**
	 * Creates the problems pane.
	 * 
	 * @return the {@link ProblemsPane}.
	 */
	public static ProblemsPane create() {
		return Guice.createInjector().getInstance(ProblemsPane.class);
	}

	private final UiPane pane;

	private final RootNode root;

	private DefaultTreeTableModel model;

	private Texts texts;

	private List<Object> columnNames;

	private List<String> columnNamesResources;

	private final List<CategoryNode> categories;

	private final List<MessageNode> messages;

	@Inject
	@OnAwt
	ProblemsPane(UiPane pane, RootNode rootNode) {
		this.pane = pane;
		this.root = rootNode;
		this.categories = new ArrayList<CategoryNode>();
		this.messages = new ArrayList<MessageNode>();
	}

	/**
	 * Sets the columns of the problems pane.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param columnNames
	 *            the column names.
	 */
	@OnAwt
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
	 * Sets the texts resources for the category. The name is looked up in the
	 * resources.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param texts
	 *            the {@link Texts}.
	 */
	@OnAwt
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

	private void updateTextsResource() {
		if (texts == null) {
			return;
		}
		for (int i = 0; i < columnNamesResources.size(); i++) {
			try {
				String name = columnNamesResources.get(i);
				name = texts.getResource(name).getText();
				columnNames.set(i, name);
			} catch (ResourcesException e) {
			}
		}
		this.model = new DefaultTreeTableModel(root, columnNames);
		this.pane.getProblemsTable().setTreeTableModel(model);
	}

	/**
	 * Returns the pane component.
	 * 
	 * @return the {@link Component}.
	 */
	public Component getAwtComponent() {
		return pane;
	}

	/**
	 * Adds a new category to the problems pane.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param category
	 *            the {@link CategoryNode}.
	 */
	@OnAwt
	public void addCategory(CategoryNode category) {
		categories.add(category);
		category.setColumnCount(root.getColumnCount());
		model.insertNodeInto(category, root, 0);
	}

	/**
	 * Adds the message to the list of problems.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param message
	 *            the {@link MessageNode}.
	 */
	@OnAwt
	public void addMessage(MessageNode message) {
		messages.add(message);
		model.insertNodeInto(message, message.getCategory(), 0);
		pane.getProblemsTable().expandAll();
	}
}
