package com.anrisoftware.prefdialog.miscswing.docks.layouts.dockingframes;

import static bibliothek.gui.dock.common.CLocation.normalized;
import static bibliothek.gui.dock.common.mode.ExtendedMode.MINIMIZED;
import static javax.swing.SwingUtilities.invokeLater;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javax.inject.Inject;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CGrid;
import bibliothek.gui.dock.common.CGridArea;
import bibliothek.gui.dock.common.CLocation;
import bibliothek.gui.dock.common.CWorkingArea;
import bibliothek.gui.dock.common.DefaultMultipleCDockable;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.MultipleCDockable;
import bibliothek.gui.dock.common.intern.AbstractCDockable;
import bibliothek.gui.dock.common.location.CFlapIndexLocation;

import com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesLayoutTask;

/**
 * Sets the default layout.
 * <p>
 * Arrange the docks according to their positions in a grid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class DefaultLayoutTask implements DockingFramesLayoutTask {

	private transient PropertyChangeSupport propertySupport;

	private final DefaultLayoutTaskLogger log;

	private String name;

	@Inject
	DefaultLayoutTask(DefaultLayoutTaskLogger logger) {
		this.log = logger;
		readResolve();
	}

	private Object readResolve() {
		if (propertySupport == null) {
			propertySupport = new PropertyChangeSupport(this);
		}
		return this;
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
	public void setupLayout(final CControl control,
			final CWorkingArea workingArea,
			final Map<String, ViewDockWindow> docks) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				setupGridInAWT(control, workingArea, docks);
			}

		});
	}

	private void setupGridInAWT(CControl control, CWorkingArea workingArea,
			Map<String, ViewDockWindow> docks) {
		removeDocks(control, docks);
		CGrid grid = new CGrid(control);
		grid.add(50, 50, 150, 150, workingArea);
		setupGrid(grid, docks);
		control.getContentArea().deploy(grid);
	}

	private void removeDocks(CControl control, Map<String, ViewDockWindow> docks) {
		for (DockWindow dock : docks.values()) {
			String id = dock.getId();
			control.removeDockable(control.getSingleDockable(id));
		}
	}

	private void setupGrid(CGrid grid, Map<String, ViewDockWindow> docks) {
		for (DockWindow dock : docks.values()) {
			DefaultSingleCDockable dockable = createSingleDock(dock);
			setupDefaultMinizedLocation(dockable, dock.getPosition());
			DockPosition position = dock.getPosition();
			int x = getPerspectiveX(position);
			int y = getPerspectiveY(position);
			int w = getPerspectiveW(position);
			int h = getPerspectiveH(position);
			grid.add(x, y, w, h, dockable);
		}
	}

	private void setupDefaultMinizedLocation(AbstractCDockable dockable,
			DockPosition position) {
		CFlapIndexLocation location = getMinimizedLocation(position);
		dockable.setDefaultLocation(MINIMIZED, location);
	}

	private CFlapIndexLocation getMinimizedLocation(DockPosition position) {
		switch (position) {
		case CENTER:
			return CLocation.base().minimalSouth();
		case EAST:
			return CLocation.base().minimalEast();
		case NORTH:
			return CLocation.base().minimalNorth();
		case SOUTH:
			return CLocation.base().minimalSouth();
		case WEST:
			return CLocation.base().minimalWest();
		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void addView(final CControl control, final ViewDockWindow dock) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				addViewInAWT(control, dock);
			}

		});
	}

	private void addViewInAWT(CControl control, ViewDockWindow dock) {
		DefaultSingleCDockable dockable = createSingleDock(dock);
		CGridArea center = control.getContentArea().getCenterArea();
		double x = getPerspectiveX(dock.getPosition()) / 200.0;
		double y = getPerspectiveY(dock.getPosition()) / 200.0;
		double w = getPerspectiveW(dock.getPosition()) / 200.0;
		double h = getPerspectiveH(dock.getPosition()) / 200.0;
		setupDefaultMinizedLocation(dockable, dock.getPosition());
		dockable.setLocation(normalized(center).rectangle(x, y, w, h));
		control.addDockable(dockable);
		dockable.setVisible(true);
	}

	private DefaultSingleCDockable createSingleDock(DockWindow dock) {
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

	private int getPerspectiveX(DockPosition position) {
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

	private int getPerspectiveY(DockPosition position) {
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

	private int getPerspectiveW(DockPosition position) {
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

	private int getPerspectiveH(DockPosition position) {
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
	public MultipleCDockable addEditor(final CWorkingArea workingArea,
			final EditorDockWindow dock) {
		FutureTask<MultipleCDockable> task = new FutureTask<MultipleCDockable>(
				new Callable<MultipleCDockable>() {

					@Override
					public MultipleCDockable call() throws Exception {
						return addEditorInAWT(workingArea, dock);
					}
				});
		invokeLater(task);
		try {
			return task.get();
		} catch (InterruptedException e) {
			log.addEditorInterrupted(this, e, dock);
			return null;
		} catch (ExecutionException e) {
			log.addEditorError(this, e.getCause(), dock);
			return null;
		}
	}

	private MultipleCDockable addEditorInAWT(CWorkingArea workingArea,
			EditorDockWindow dock) {
		DefaultMultipleCDockable dockable = createMultipleDock(dock);
		dockable.setCloseable(dock.isCloseable());
		dockable.setExternalizable(dock.isExternalizable());
		dockable.setMaximizable(dock.isMaximizable());
		dockable.setMinimizable(dock.isMinimizable());
		dockable.setStackable(dock.isStackable());
		DockPosition position = dock.getPosition();
		setupDefaultMinizedLocation(dockable, position);
		workingArea.show(dockable);
		dockable.toFront();
		return dockable;
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
