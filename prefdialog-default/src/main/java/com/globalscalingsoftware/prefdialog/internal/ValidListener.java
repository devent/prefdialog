package com.globalscalingsoftware.prefdialog.internal;

import java.util.EventListener;

interface ValidListener extends EventListener {

	void validChanged(ValidEvent validEvent);

}
