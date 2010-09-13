package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.globalscalingsoftware.prefdialog.IPreferencePanelAnnotationFilter;
import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.Group;
import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.annotations.TextField;

public class PreferencePanelAnnotationFilter extends AbstractAnnotationFilter
		implements IPreferencePanelAnnotationFilter {

	private static List<Class<? extends Annotation>> annotations;

	static {
		annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(Checkbox.class);
		annotations.add(FormattedTextField.class);
		annotations.add(TextField.class);
		annotations.add(RadioButton.class);
		annotations.add(ComboBox.class);
		annotations.add(Group.class);
		annotations.add(Child.class);
	}

	public static PreferencePanelAnnotationFilter createDefault() {
		return new PreferencePanelAnnotationFilter(annotations);
	}

	PreferencePanelAnnotationFilter(
			List<Class<? extends Annotation>> annotations) {
		setAnnotations(annotations);
	}

}
