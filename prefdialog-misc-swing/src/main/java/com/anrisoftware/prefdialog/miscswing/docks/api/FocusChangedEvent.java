package com.anrisoftware.prefdialog.miscswing.docks.api;

import javax.swing.event.ChangeEvent;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Event that the focus state of a dock have been changed.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FocusChangedEvent extends ChangeEvent {

	private final boolean focus;

	/**
	 * @see ChangeEvent#ChangeEvent(Object)
	 * 
	 * @param source
	 *            the {@link DockWindow} source.
	 * 
	 * @param focus
	 *            if the dock have the focus.
	 */
	public FocusChangedEvent(DockWindow source, boolean focus) {
		super(source);
		this.focus = focus;
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
	 * Returns if the dock have the focus.
	 * 
	 * @return {@code true} if it have the focus.
	 */
	public boolean isFocus() {
		return focus;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.append("focus", focus).toString();
	}
}
