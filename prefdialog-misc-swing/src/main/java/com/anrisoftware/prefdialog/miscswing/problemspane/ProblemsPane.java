package com.anrisoftware.prefdialog.miscswing.problemspane;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JPanel;

import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

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

	private ArrayList<String> columnNamesResources;

	@Inject
	ProblemsPane(UiPane pane, RootNode rootNode) {
		this.pane = pane;
		this.root = rootNode;
	}

	/**
	 * Sets the columns of the problems pane.
	 * 
	 * @param columnNames
	 *            the column names.
	 */
	public void setColumns(final List<Object> columnNames) {
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
	 * 
	 * @param texts
	 *            the {@link Texts}.
	 */
	public void setTexts(Texts texts) {
		this.texts = texts;
		root.setTexts(texts);
		updateTextsResource();
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

	public JPanel getAwtComponent() {
		return pane;
	}

	public void addCategory(CategoryNode category) {
		category.setColumnCount(root.getColumnCount());
		model.insertNodeInto(category, root, 0);
	}

	public void addMessage(MessageNode message) {
		model.insertNodeInto(message, message.getCategory(), 0);
		pane.getProblemsTable().expandAll();
	}
}
