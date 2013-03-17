package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.workdockable;

import javax.inject.Inject;

import bibliothek.gui.dock.common.MultipleCDockableFactory;

import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DefaultWorkDockWindow;

/**
 * Factory for a window that is in the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class WorkDockableFactory implements
		MultipleCDockableFactory<WorkDockable, WorkDockableLayout> {

	public static String ID = "work_dockable_factory";

	private final WorkDockableLayoutFactory layoutFactory;

	@Inject
	WorkDockableFactory(WorkDockableLayoutFactory layoutFactory) {
		this.layoutFactory = layoutFactory;
	}

	@Override
	public WorkDockableLayout write(WorkDockable dockable) {
		return layoutFactory.createFor(dockable);
	}

	@Override
	public WorkDockable read(WorkDockableLayout layout) {
		return new WorkDockable(this, layout.getMeta());
	}

	@Override
	public boolean match(WorkDockable dockable, WorkDockableLayout layout) {
		return layout.match(dockable);
	}

	@Override
	public WorkDockableLayout create() {
		return layoutFactory.createFor(new DefaultWorkDockWindow());
	}

}
