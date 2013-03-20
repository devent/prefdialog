package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.viewdockable;

import javax.inject.Inject;

import bibliothek.gui.dock.common.MultipleCDockableFactory;

/**
 * Factory for a dock that is outside of the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ViewDockableFactory implements
		MultipleCDockableFactory<ViewDockable, ViewDockableLayout> {

	public static String ID = "single_dockable_factory";

	private final ViewDockableLayoutFactory layoutFactory;

	@Inject
	ViewDockableFactory(ViewDockableLayoutFactory layoutFactory) {
		this.layoutFactory = layoutFactory;
	}

	@Override
	public ViewDockableLayout write(ViewDockable dockable) {
		return layoutFactory.createFor(dockable);
	}

	@Override
	public ViewDockable read(ViewDockableLayout layout) {
		return new ViewDockable(this, layout.getMeta());
	}

	@Override
	public boolean match(ViewDockable dockable, ViewDockableLayout layout) {
		return layout.match(dockable);
	}

	@Override
	public ViewDockableLayout create() {
		return layoutFactory.createFor(new DefaultViewDockWindow());
	}

}
