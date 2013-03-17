package com.anrisoftware.prefdialog.miscswing.docks.perspectives.dockingframes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import bibliothek.gui.dock.common.perspective.CControlPerspective;
import bibliothek.gui.dock.common.perspective.CGridPerspective;
import bibliothek.gui.dock.common.perspective.CMinimizePerspective;
import bibliothek.gui.dock.common.perspective.CPerspective;
import bibliothek.gui.dock.common.perspective.CWorkingPerspective;

import com.anrisoftware.prefdialog.miscswing.docks.api.DockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.PerspectivePosition;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockablePerspective;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesPerspectiveTask;

/**
 * Sets the default perspective.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class DefaultPerspectiveTask implements DockingFramesPerspectiveTask {

	private final PropertyChangeSupport propertySupport;

	private String name;

	public DefaultPerspectiveTask() {
		this.propertySupport = new PropertyChangeSupport(this);
	}

	@Override
	public void setName(String name) {
		String oldValue = name;
		this.name = name;
		propertySupport.firePropertyChange(NAME_PROPERTY, oldValue, name);
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Sets the default perspective.
	 * 
	 * @param perspectives
	 *            the {@link CControlPerspective}.
	 * 
	 * @param perspective
	 *            the {@link CPerspective}.
	 * 
	 * @param singleDockablePerspectives
	 *            the {@link Map} of the {@link DockablePerspective} for the
	 *            windows outside of the work area.
	 * 
	 * @param workDockablePerspectives
	 *            the {@link Map} of the {@link DockablePerspective} for the
	 *            windows in the work area.
	 * 
	 * @param workAreaId
	 *            the identifier of the work area.
	 */
	@Override
	public void setupPerspective(CControlPerspective perspectives,
			CPerspective perspective,
			Map<String, DockablePerspective> singleDockablePerspectives,
			Map<String, DockablePerspective> workDockablePerspectives,
			String workAreaId) {
		setupMinimizePerspective(singleDockablePerspectives, perspective);
		setupMinimizePerspective(workDockablePerspectives, perspective);
		perspective.storeLocations();
		CGridPerspective center = perspective.getContentArea().getCenter();
		CWorkingPerspective work = (CWorkingPerspective) perspective
				.getStation(workAreaId);
		setupGridPerspective(center, singleDockablePerspectives);
		setupGridPerspective(work, workDockablePerspectives);
		perspective.storeLocations();
		setupMinimizePerspective(singleDockablePerspectives, perspective);
		perspective.shrink();
		perspectives.setPerspective(perspective, true);
	}

	private void setupGridPerspective(CGridPerspective grid,
			Map<String, DockablePerspective> perspectives) {
		for (DockablePerspective perspective : perspectives.values()) {
			DockWindow window = perspective.window;
			PerspectivePosition position = window.getPosition();
			int x = getPerspectiveX(position);
			int y = getPerspectiveY(position);
			int w = getPerspectiveW(position);
			int h = getPerspectiveH(position);
			grid.gridAdd(x, y, w, h, perspective.perspective);
		}
	}

	private void setupMinimizePerspective(
			Map<String, DockablePerspective> dockablePerspectives,
			CPerspective perspective) {
		for (DockablePerspective dockablePerspective : dockablePerspectives
				.values()) {
			DockWindow window = dockablePerspective.window;
			PerspectivePosition position = window.getPosition();
			CMinimizePerspective min = getMinimizePerspective(position,
					perspective);
			min.add(dockablePerspective.perspective);
		}
	}

	private CMinimizePerspective getMinimizePerspective(
			PerspectivePosition position, CPerspective perspective) {
		switch (position) {
		case CENTER:
			return perspective.getContentArea().getSouth();
		case EAST:
			return perspective.getContentArea().getEast();
		case NORTH:
			return perspective.getContentArea().getNorth();
		case SOUTH:
			return perspective.getContentArea().getSouth();
		case WEST:
			return perspective.getContentArea().getWest();
		}
		throw new IllegalArgumentException();
	}

	private int getPerspectiveX(PerspectivePosition position) {
		switch (position) {
		case CENTER:
			return 0;
		case EAST:
			return 150;
		case NORTH:
			return 0;
		case SOUTH:
			return 0;
		case WEST:
			return 0;
		}
		throw new IllegalArgumentException();
	}

	private int getPerspectiveY(PerspectivePosition position) {
		switch (position) {
		case CENTER:
			return 0;
		case EAST:
			return 0;
		case NORTH:
			return 0;
		case SOUTH:
			return 150;
		case WEST:
			return 0;
		}
		throw new IllegalArgumentException();
	}

	private int getPerspectiveW(PerspectivePosition position) {
		switch (position) {
		case CENTER:
			return 200;
		case EAST:
			return 50;
		case NORTH:
			return 200;
		case SOUTH:
			return 200;
		case WEST:
			return 50;
		}
		throw new IllegalArgumentException();
	}

	private int getPerspectiveH(PerspectivePosition position) {
		switch (position) {
		case CENTER:
			return 200;
		case EAST:
			return 200;
		case NORTH:
			return 50;
		case SOUTH:
			return 50;
		case WEST:
			return 200;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.removePropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertySupport.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removePropertyChangeListener(String propertyName,
			PropertyChangeListener listener) {
		propertySupport.removePropertyChangeListener(propertyName, listener);
	}

}
