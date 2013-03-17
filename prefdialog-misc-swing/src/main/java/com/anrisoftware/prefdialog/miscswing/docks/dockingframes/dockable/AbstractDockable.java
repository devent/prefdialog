package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable;

import java.awt.Component;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import bibliothek.gui.dock.common.DefaultMultipleCDockable;
import bibliothek.gui.dock.common.MultipleCDockable;
import bibliothek.gui.dock.common.MultipleCDockableFactory;
import bibliothek.gui.dock.common.MultipleCDockableLayout;

import com.anrisoftware.prefdialog.miscswing.docks.api.DockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.PerspectivePosition;

public abstract class AbstractDockable<WindowType extends DockWindow, DockableType extends MultipleCDockable, LayoutType extends MultipleCDockableLayout, DockableFactoryType extends MultipleCDockableFactory<DockableType, LayoutType>>
		extends DefaultMultipleCDockable implements DockWindow {

	private final WindowType window;

	protected AbstractDockable(DockableFactoryType factory, WindowType window) {
		super(factory, window.getTitle(), window.getComponent());
		this.window = window;
	}

	@Override
	public String getId() {
		return window.getId();
	}

	@Override
	public String getTitle() {
		return window.getTitle();
	}

	@Override
	public Component getComponent() {
		return window.getComponent();
	}

	@Override
	public PerspectivePosition getPosition() {
		return window.getPosition();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		window.writeExternal(out);
		out.flush();
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		window.readExternal(in);
	}

	@Override
	public boolean match(DockWindow window) {
		return getId().equals(window.getId());
	}

}
