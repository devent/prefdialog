package com.globalscalingsoftware.prefdialog.internal


import com.globalscalingsoftware.prefdialog.annotations.Child;

class Preferences {
	
	@Child
	General general = new General()
	
	@Child
	Device device = new Device()
}
