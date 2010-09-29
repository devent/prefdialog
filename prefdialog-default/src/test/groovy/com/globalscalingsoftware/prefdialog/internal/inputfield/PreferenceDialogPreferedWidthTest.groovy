package com.globalscalingsoftware.prefdialog.internal.inputfield

import java.util.List;



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.annotations.fields.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.fields.Child;
import com.globalscalingsoftware.prefdialog.annotations.fields.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.fields.ComboBoxElements;
import com.globalscalingsoftware.prefdialog.annotations.fields.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.fields.RadioButton;
import com.globalscalingsoftware.prefdialog.annotations.fields.TextField;
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest;
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 
import com.globalscalingsoftware.prefdialog.internal.radiobutton.Colors;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class PreferenceDialogPreferedWidthTest extends AbstractPreferenceTest {
	
	static class Preferences {
		
		@Child
		General general = new General()
	}
	
	static class General {
		
		@TextField(width=-2.0d, validator=NotEmptyStringValidator, validatorText="Must not be empty")
		String name = ""
		
		@FormattedTextField(width=-2.0d, validator=FieldsValidator, validatorText="Must be a number and between 2 and 100")
		int fields = 4
		
		@Checkbox(width=-2.0d)
		boolean automaticSave = false
		
		@RadioButton(width=-2.0d, columns=2)
		Colors colors = Colors.BLACK
		
		@ComboBoxElements("combobox1")
		List<String> comboBoxElements = ["first element", "second element", "third element"]
		
		@ComboBox(value="combobox1", width=-2.0d)
		String comboBox
		
		@Override
		public String toString() {
			"General"
		}
	}
	
	def injector
	
	def preferences
	
	@Before
	void beforeTest() {
		preferences = new Preferences()
		injector = new PreferencesDialogInjectorFactory().create(preferences)
	}
	
	@Test
	void testDialogClickOk() {
		def controller = injector.getInstance(IPreferenceDialogController)
		controller.openDialog()
	}
}
