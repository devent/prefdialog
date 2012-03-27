package com.anrisoftware.prefdialog;

/**
 * A factory to create new {@link PreferencePanelHandler}.
 * 
 * <pre>
 * factory = injector.getInstance(PreferencePanelHandlerFactory)
 * handler = factory.create(preferences, panelName).createPanel()
 * </pre>
 * 
 * After you created the preference panel handler you can add it to a dialog:
 * 
 * <pre>
 * panel = handler.getAWTComponent()
 * dialog.add(panel, BorderLayout.CENTER)
 * </pre>
 * 
 * @see PreferencePanelHandler
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public interface PreferencePanelHandlerFactory {

	/**
	 * Creates a new {@link PreferencePanelHandler preference panel handler}.
	 * 
	 * @param preferences
	 *            the preferences from which the panel will be created.
	 * 
	 * @param panelName
	 *            the name of the field in the preferences from which the panel
	 *            will be created.
	 * 
	 * @return the new created {@link PreferencePanelHandler preference panel
	 *         handler}.
	 */
	PreferencePanelHandler create(Object preferences, String panelName);
}
