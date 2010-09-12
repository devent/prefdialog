package com.globalscalingsoftware.prefdialog.internal.inputfield

import java.util.List;



import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

import com.globalscalingsoftware.prefdialog.IPreferenceDialogController;
import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.ComboBox 
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements 
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.HelpText 
import com.globalscalingsoftware.prefdialog.annotations.RadioButton 
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.annotations.Validated 
import com.globalscalingsoftware.prefdialog.internal.AbstractPreferenceTest;
import com.globalscalingsoftware.prefdialog.internal.FieldsValidator 
import com.globalscalingsoftware.prefdialog.internal.PreferencesDialogInjectorFactory 
import com.globalscalingsoftware.prefdialog.internal.radiobutton.Colors;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class PreferenceDialogTest extends AbstractPreferenceTest {
	
	class Preferences {
		
		@Child
		General general = new General()
	}
	
	class General {
		
		@TextField
		@HelpText("Must not be empty")
		@Validated(NotEmptyStringValidator)
		String name = ""
		
		@FormattedTextField
		@HelpText("Must be a number and between 2 and 100")
		@Validated(FieldsValidator)
		int fields = 4
		
		@Checkbox
		boolean automaticSave = false
		
		@RadioButton(columns=2)
		Colors colors = Colors.BLACK
		
		@ComboBoxElements("combobox1")
		List<String> comboBoxElements = ["first element", "second element", "third element"]
		
		@ComboBox("combobox1")
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
		assertThat preferences.general.name, is("name")
		assertThat preferences.general.fields, is(10)
		assertThat preferences.general.automaticSave, is(true)
	}
}
