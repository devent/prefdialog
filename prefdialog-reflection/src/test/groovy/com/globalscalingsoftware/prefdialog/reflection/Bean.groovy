package com.globalscalingsoftware.prefdialog.reflection

import com.google.inject.Inject
import com.google.inject.assistedinject.Assisted

class Bean {

	@Assisted
	public assistedField = 5

	@Inject
	public injectField = 5
}
