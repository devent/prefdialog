package com.anrisoftware.prefdialog.miscswing.docks.perspectives.dockingframes;

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CGrid;
import bibliothek.gui.dock.common.CWorkingArea;
import bibliothek.gui.dock.common.DefaultMultipleCDockable;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.SingleCDockable;

import com.anrisoftware.prefdialog.miscswing.docks.api.DockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.PerspectivePosition;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.DockingFramesPerspectiveTask;

/**
 * Sets the default perspective.
 * <p>
 * Arrange the docks according to their perspective position.
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

	@Override
	public void setupPerspective(final CControl control,
			final CWorkingArea workingArea,
			final Map<String, ViewDockWindow> docks) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				setupPerspectiveInAWT(control, workingArea, docks);
			}

		});
	}

	private void setupPerspectiveInAWT(CControl control,
			CWorkingArea workingArea, Map<String, ViewDockWindow> docks) {
		CGrid grid = new CGrid(control);
		grid.add(50, 50, 150, 150, workingArea);
		setupGrid(grid, docks);
		control.getContentArea().deploy(grid);
	}

	private void setupGrid(CGrid grid,
			Map<String, ? extends DockWindow> viewDocks) {
		for (DockWindow dock : viewDocks.values()) {
			SingleCDockable dockable = createSingleDock(dock);
			PerspectivePosition position = dock.getPosition();
			int x = getPerspectiveX(position);
			int y = getPerspectiveY(position);
			int w = getPerspectiveW(position);
			int h = getPerspectiveH(position);
			grid.add(x, y, w, h, dockable);
		}
	}

	private SingleCDockable createSingleDock(DockWindow dock) {
		String title = dock.getTitle();
		Component component = dock.getComponent();
		String id = dock.getId();
		DefaultSingleCDockable dockable = new DefaultSingleCDockable(id, title,
				component);
		dockable.setCloseable(dock.isCloseable());
		dockable.setExternalizable(dock.isExternalizable());
		dockable.setMaximizable(dock.isMaximizable());
		dockable.setMinimizable(dock.isMinimizable());
		dockable.setStackable(dock.isStackable());
		return dockable;
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
	public void addEditor(final CWorkingArea workingArea,
			final EditorDockWindow dock) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				addEditorInAWT(workingArea, dock);
			}

		});
	}

	private void addEditorInAWT(CWorkingArea workingArea, EditorDockWindow dock) {
		DefaultMultipleCDockable dockable = createMultipleDock(dock);
		workingArea.show(dockable);
		dockable.toFront();
	}

	private DefaultMultipleCDockable createMultipleDock(DockWindow dock) {
		String title = dock.getTitle();
		Component component = dock.getComponent();
		DefaultMultipleCDockable dockable = new DefaultMultipleCDockable(null,
				title, component);
		return dockable;
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
