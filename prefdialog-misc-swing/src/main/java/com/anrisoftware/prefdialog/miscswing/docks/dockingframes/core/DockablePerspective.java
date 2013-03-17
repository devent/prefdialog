package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import org.apache.commons.lang3.builder.ToStringBuilder;

import bibliothek.gui.dock.common.perspective.CDockablePerspective;

import com.anrisoftware.prefdialog.miscswing.docks.api.DockWindow;

/**
 * Stores the window with the perspective.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class DockablePerspective {

	/**
	 * The {@link DockWindow}.
	 */
	public final DockWindow window;

	/**
	 * The {@link CDockablePerspective}.
	 */
	public final CDockablePerspective perspective;

	/**
	 * Sets the window with the perspective.
	 * 
	 * @param window
	 *            the {@link DockWindow}.
	 * 
	 * @param perspective
	 *            the {@link CDockablePerspective}.
	 */
	public DockablePerspective(DockWindow window,
			CDockablePerspective perspective) {
		this.window = window;
		this.perspective = perspective;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(window).append(perspective)
				.toString();
	}

}
