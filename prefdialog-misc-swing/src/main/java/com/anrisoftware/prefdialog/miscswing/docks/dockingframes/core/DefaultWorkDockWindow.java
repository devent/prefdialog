package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.swing.JPanel;

import com.anrisoftware.prefdialog.miscswing.docks.api.DockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.PerspectivePosition;
import com.anrisoftware.prefdialog.miscswing.docks.api.WorkDockWindow;

public class DefaultWorkDockWindow implements WorkDockWindow {

	private String id;

	private String title;

	private PerspectivePosition position;

	private Color color;

	public DefaultWorkDockWindow() {
	}

	public DefaultWorkDockWindow(String id, String title,
			PerspectivePosition position, Color color) {
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
	public PerspectivePosition getPosition() {
		return position;
	}

	@Override
	public boolean match(DockWindow window) {
		return getId().equals(window.getId());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(id);
		out.writeUTF(title);
		out.writeObject(position);
		out.writeObject(color);
		out.flush();
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		this.id = in.readUTF();
		this.title = in.readUTF();
		this.position = (PerspectivePosition) in.readObject();
		this.color = (Color) in.readObject();
	}

}
