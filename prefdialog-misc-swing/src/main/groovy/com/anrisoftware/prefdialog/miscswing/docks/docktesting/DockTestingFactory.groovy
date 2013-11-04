package com.anrisoftware.prefdialog.miscswing.docks.docktesting

/**
 * Factory to create dock testing.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
interface DockTestingFactory {

	/**
	 * Creates a new dock testing with the specified arguments.
	 *
	 * @return the {@link DockTesting}.
	 *
	 * @see FrameTestingFactory#create(Map)
	 */
	DockTesting create(Map args)
}
