package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;

import com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;

public class ColorViewDock implements ViewDockWindow {

	private String id;

	private String title;

	private DockPosition position;

	private Color color;

	public ColorViewDock() {
	}

	public ColorViewDock(String id, String title, DockPosition position,
			Color color) {
		this.id = id;
		this.title = title;
		this.position = position;
		this.color = color;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Component getComponent() {
		JPanel panel = new JPanel();
		panel.setBackground(color);
		return panel;
	}

	@Override
	public DockPosition getPosition() {
		return position;
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
		return false;
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
