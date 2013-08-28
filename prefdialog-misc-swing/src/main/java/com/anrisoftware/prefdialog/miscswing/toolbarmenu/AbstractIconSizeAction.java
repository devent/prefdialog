package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import java.awt.event.ActionEvent;

import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;
import com.anrisoftware.resources.images.api.IconSize;

/**
 * Sets the icon size.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class AbstractIconSizeAction extends AbstractResourcesAction {

	private final IconSize size;

	private ToolbarMenu toolbar;

	protected AbstractIconSizeAction(String name, IconSize size) {
		super(name);
		this.size = size;
	}

	public void setToolbarMenu(ToolbarMenu toolbar) {
		this.toolbar = toolbar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		toolbar.setIconSize(size);
	}

}
