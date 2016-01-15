/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
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
