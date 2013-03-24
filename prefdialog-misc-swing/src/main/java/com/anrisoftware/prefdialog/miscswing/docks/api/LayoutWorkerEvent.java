package com.anrisoftware.prefdialog.miscswing.docks.api;

import java.util.EventObject;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class LayoutWorkerEvent extends EventObject {

	private final String name;

	/**
	 * @param name
	 *            the name of the layout.
	 * 
	 * @see EventObject#EventObject(Object)
	 */
	public LayoutWorkerEvent(Object source, String name) {
		super(source);
		this.name = name;
	}

	/**
	 * Returns the layout name.
	 * 
	 * @return the layout name
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.append(name).toString();
	}
}
