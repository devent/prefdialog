package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.singledockable;

import javax.inject.Inject;

import bibliothek.gui.dock.common.MultipleCDockableFactory;

/**
 * Factory for a window that is outside of the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class SingleDockableFactory implements
		MultipleCDockableFactory<SingleDockable, SingleDockableLayout> {

	public static String ID = "single_dockable_factory";

	private final SingleDockableLayoutFactory layoutFactory;

	@Inject
	SingleDockableFactory(SingleDockableLayoutFactory layoutFactory) {
		this.layoutFactory = layoutFactory;
	}

	@Override
	public SingleDockableLayout write(SingleDockable dockable) {
		return layoutFactory.createFor(dockable);
	}

	@Override
	public SingleDockable read(SingleDockableLayout layout) {
		return new SingleDockable(this, layout.getMeta());
	}

	@Override
	public boolean match(SingleDockable dockable, SingleDockableLayout layout) {
		return layout.match(dockable);
	}

	@Override
	public SingleDockableLayout create() {
		return layoutFactory.createFor(new DefaultSingleDockWindow());
	}

}
