package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.editordockable;

import javax.inject.Inject;

import bibliothek.gui.dock.common.MultipleCDockableFactory;

import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DefaultWorkDockWindow;

/**
 * Factory for a dock that is in the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class EditorDockableFactory implements
		MultipleCDockableFactory<EditorDockable, EditorDockableLayout> {

	public static String ID = "editor_dockable_factory";

	private final EditorDockableLayoutFactory layoutFactory;

	@Inject
	EditorDockableFactory(EditorDockableLayoutFactory layoutFactory) {
		this.layoutFactory = layoutFactory;
	}

	@Override
	public EditorDockableLayout write(EditorDockable dockable) {
		return layoutFactory.createFor(dockable);
	}

	@Override
	public EditorDockable read(EditorDockableLayout layout) {
		return new EditorDockable(this, layout.getMeta());
	}

	@Override
	public boolean match(EditorDockable dockable, EditorDockableLayout layout) {
		return layout.match(dockable);
	}

	@Override
	public EditorDockableLayout create() {
		return layoutFactory.createFor(new DefaultWorkDockWindow());
	}

}
