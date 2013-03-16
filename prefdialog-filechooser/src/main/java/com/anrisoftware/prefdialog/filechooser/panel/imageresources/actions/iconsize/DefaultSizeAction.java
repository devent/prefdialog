package com.anrisoftware.prefdialog.filechooser.panel.imageresources.actions.iconsize;

import com.anrisoftware.resources.images.api.IconSize;

class DefaultSizeAction extends AbstractIconSizeAction {

	private IconSize size;

	public void setDefaultIconSize(IconSize size) {
		this.size = size;
	}

	@Override
	protected IconSize getIconSize() {
		return size;
	}

}
