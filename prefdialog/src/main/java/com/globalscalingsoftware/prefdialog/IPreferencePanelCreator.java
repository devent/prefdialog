package com.globalscalingsoftware.prefdialog;

import java.lang.reflect.Field;

public interface IPreferencePanelCreator {

	IPreferencePanel createPanel(Object parentValue, Field field);
}