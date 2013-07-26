package com.anrisoftware.prefdialog.miscswing.docks.problemsdock;

import java.awt.Component;
import java.util.List;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;
import com.anrisoftware.prefdialog.miscswing.problemspane.CategoryNode;
import com.anrisoftware.prefdialog.miscswing.problemspane.MessageNode;
import com.anrisoftware.prefdialog.miscswing.problemspane.ProblemsPane;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Problems dock. Uses a tree table to display messages ordered in categories.
 * 
 * @see ProblemsPane
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ProblemsDock implements ViewDockWindow {

	private final ProblemsPane pane;

	private String title;

	@Inject
	ProblemsDock(ProblemsPane pane) {
		this.pane = pane;
	}

	@Override
	public String getId() {
		return "problems-dock";
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setColumns(List<Object> columnNames) {
		pane.setColumns(columnNames);
	}

	public void setTexts(Texts texts) {
		pane.setTexts(texts);
	}

	public void addCategory(CategoryNode category) {
		pane.addCategory(category);
	}

	public void addMessage(MessageNode message) {
		pane.addMessage(message);
	}

	@Override
	public Component getComponent() {
		return pane.getAwtComponent();
	}

	@Override
	public DockPosition getPosition() {
		return DockPosition.SOUTH;
	}

	@Override
	public boolean isCloseable() {
		return true;
	}

	@Override
	public boolean isExternalizable() {
		return true;
	}

	@Override
	public boolean isMaximizable() {
		return true;
	}

	@Override
	public boolean isMinimizable() {
		return true;
	}

	@Override
	public boolean isStackable() {
		return true;
	}

}
