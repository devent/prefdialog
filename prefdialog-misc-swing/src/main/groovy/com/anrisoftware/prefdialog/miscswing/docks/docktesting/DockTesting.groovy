package com.anrisoftware.prefdialog.miscswing.docks.docktesting

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static javax.swing.SwingUtilities.invokeAndWait

import java.awt.BorderLayout
import java.awt.Dimension

import javax.inject.Inject
import javax.swing.JFrame
import javax.swing.UIManager

import org.fest.swing.edt.GuiActionRunner
import org.fest.swing.edt.GuiQuery
import org.fest.swing.fixture.FrameFixture

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
 * def testing = testingFactory.create([title: title]).createDock()
 * testing.withFixture({
 * 	// tests
 * })
 * </pre>
 *
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class DockTesting {

	/**
	 * Name of the system look&feel.
	 */
	public static String SYSTEM_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel"

	/**
	 * Name of the GTK look&feel.
	 */
	public static String GTK_LOOK_AND_FEEL = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel"

	/**
	 * Name of the nimbus look&feel.
	 */
	public static String NIMBUS_LOOK_AND_FEEL = "javax.swing.plaf.nimbus.NimbusLookAndFeel"

	/**
	 * Name of the substance business look&feel.
	 */
	public static String SUBSTANCE_BUSINESS_LOOK_AND_FEEL = "org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel"

	/**
	 * Sets the specified Look&Feel.
	 */
	public static setLookAndFeel(String lookAndFeel) {
		invokeAndWait { UIManager.setLookAndFeel(lookAndFeel) }
	}

	private final String title

	private final Dimension size

	@Inject
	private DockFactory dockFactory

	private JFrame frame

	private Dock dock

	private FrameFixture fixture

	/**
	 * @see DockTestingFactory#create(Map)
	 */
	@Inject
	DockTesting(@Assisted Map args) {
		args = defaultArgs(args)
		this.title = args.title
		this.size = args.size
	}

	private Map defaultArgs(Map args) {
		[
			title: "Dock Test",
			size: new Dimension(680, 480)
		] << args
	}

	/**
	 * Creates the frame, dock and frame fixture.
	 *
	 * @return this {@link DockTesting}.
	 */
	DockTesting createDock() {
		invokeAndWait {
			frame = createFrame()
			dock = createDock(frame)
			fixture = createFixture(frame)
		}
		this
	}

	/**
	 * Creates the dock frame.
	 *
	 * @return the {@link JFrame}.
	 */
	JFrame createFrame() {
		def frame = new JFrame(title)
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
		frame.setSize size
		frame.setPreferredSize size
		frame
	}

	/**
	 * Creates the dock with the specified frame.
	 *
	 * @param frame
	 * 			  the {@link JFrame}.
	 *
	 * @return the {@link Dock}.
	 */
	Dock createDock(JFrame frame) {
		def dock = dockFactory.create(frame)
		frame.add dock.getAWTComponent(), BorderLayout.CENTER
		dock
	}

	FrameFixture createFixture(JFrame frame) {
		def result = GuiActionRunner.execute([executeInEDT: { frame } ] as GuiQuery)
		new FrameFixture(result)
	}

	/**
	 * Returns the current title.
	 */
	String getTitle() {
		title
	}

	/**
	 * Returns the frame size.
	 */
	Dimension getSize() {
		size
	}

	/**
	 * Returns the dock.
	 */
	Dock getDock() {
		dock
	}

	/**
	 * Returns the dock frame.
	 */
	JFrame getFrame() {
		frame
	}

	/**
	 * The current {@link FrameFixture}.
	 */
	FrameFixture getFixture() {
		fixture
	}

	/**
	 * Runs the tests. The {@link FrameFixture} is passed
	 * to each specified test as the first argument.
	 *
	 * @param tests
	 * 			  the tests to run.
	 *
	 * @return this {@link DockTesting}
	 */
	DockTesting withFixture(Object... tests) {
		beginFixture()
		sequencedActionsWith(fixture, tests)
		endFixture()
		this
	}

	/**
	 * Creates and show the {@link FrameFixture}.
	 */
	void beginFixture() {
		fixture.show()
	}

	/**
	 * End the {@link FrameFixture}.
	 */
	void endFixture() {
		fixture.cleanUp()
		fixture = null
	}
}
