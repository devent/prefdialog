package com.anrisoftware.prefdialog.miscswing.docks.api;

import javax.swing.event.ChangeEvent;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Event that the showing state of a dock have been changed.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ShowingChangedEvent extends ChangeEvent {

	private final boolean showing;

	/**
	 * @see ChangeEvent#ChangeEvent(Object)
	 * 
	 * @param source
	 *            the {@link DockWindow} source.
	 * 
	 * @param showing
	 *            if the dock is showing or is hidden.
	 */
	public ShowingChangedEvent(DockWindow source, boolean showing) {
		super(source);
		this.showing = showing;
	}

	/**
	 * Returns the dock window.
	 * 
	 * @return the {@link DockWindow}.
	 */
	public DockWindow getDock() {
		return (DockWindow) getSource();
	}

	/**
	 * Returns if the dock is showing or is hidden.
	 * 
	 * @return {@code true} if it is showing.
	 */
	public boolean isShowing() {
		return showing;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.append("showing", showing).toString();
	}
}
