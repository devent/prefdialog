package com.anrisoftware.prefdialog;

import javax.swing.JPanel;

/**
 * <p>
 * A factory to create new preference panel.
 * </p>
 * <p>
 * Basic example which will show the preference panel in a dialog:
 * </p>
 * 
 * <pre>
 * static class ChildOne {
 * 
 *     &#64;TextField
 *     String text = ""
 * }
 * 
 * static class Preferences {
 * 
 *     &#64;Child
 *     ChildOne childOne = new ChildOne()
 * }
 * 
 * injector = Guice.createInjector(new PrefdialogPanelModule(), new PrefdialogCoreFieldsModule());
 * factory = injector.getInstance(PreferencePanelFactory.class);
 * panel = new JPanel();
 * preferences = new Preferences();
 * preferencePanel = factory.create(panel, preferences, "childOne");
 * 
 * dialog.add(panel, BorderLayout.CENTER);
 * </pre>
 * 
 * @see PreferencePanel
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface PreferencePanelFactory {

	/**
	 * Creates a new {@link PreferencePanel preference panel handler}.
	 * 
	 * @param panel
	 *            the {@link JPanel} to which the input fields are added.
	 * 
	 * @param preferences
	 *            the preferences {@link Object}.
	 * 
	 * @param childName
	 *            the {@link String} name of the child preference.
	 * 
	 * @since 2.2
	 */
	PreferencePanel create(JPanel panel, Object preferences, String childName);
}
