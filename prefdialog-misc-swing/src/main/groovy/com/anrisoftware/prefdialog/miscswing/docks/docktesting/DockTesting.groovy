package com.anrisoftware.prefdialog.miscswing.docks.docktesting

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static javax.swing.SwingUtilities.invokeAndWait

import java.awt.Component
import java.awt.Dimension

import javax.inject.Inject
import javax.swing.JFrame

import org.fest.swing.fixture.FrameFixture

import com.anrisoftware.globalpom.utils.frametesting.FrameTesting
import com.anrisoftware.prefdialog.miscswing.docks.api.Dock
import com.anrisoftware.prefdialog.miscswing.docks.api.DockFactory
import com.google.inject.assistedinject.Assisted

/**
 * Dock testing.
 * <p>
 * <h2>Example:</h2>
 * <p>
 * <pre>
 * def injector = Guice.createInjector(new DockTestingModule())
 * def testingFactory = injector.getInstance(DockTestingFactory)
 * def title = "Dock Test"
 * def testing = testingFactory.create([title: title])()
 * testing.withFixture({
 * 	// tests
 * })
 * </pre>
 *
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class DockTesting extends FrameTesting {

	private Dock dock

	/**
	 * @see DockTestingFactory#create(Map)
	 */
	@Inject
	DockTesting(@Assisted Map args) {
		super(args)
	}

	@Inject
	void setDockFactory(DockFactory factory) {
		this.dock = factory.create()
	}

	@Override
	DockTesting call() {
		super.call()
	}

	/**
	 * Creates the dock with the specified frame.
	 */
	Component createComponent(JFrame frame) {
		def dock = dock.createDock(frame)
		this.dock = dock
		dock.getAWTComponent()
	}

	/**
	 * Returns the dock.
	 *
	 * @return the {@link Dock}.
	 */
	Dock getDock() {
		dock
	}
}
