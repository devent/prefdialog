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
import com.globalscalingsoftware.prefdialog.internal.textfield.TextFieldGroupPreferencePanelTest.Group;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class PreferenceDialogWithNamedGroupsTest extends AbstractPreferenceTest {
	
	static class Preferences {
		
		@Child("General")
		General general = new General()
	}
	
	static class General {
		
		@TextField(validator=NotEmptyStringValidator, validatorText="Must not be empty")
		String name = ""
		
		@FormattedTextField(validator=FieldsValidator, validatorText="Must be a number and between 2 and 100")
		int fields = 4
		
		@com.globalscalingsoftware.prefdialog.annotations.fields.Group("Group One")
		Group group1 = new Group()
		
		@com.globalscalingsoftware.prefdialog.annotations.fields.Group("Group Two")
		Group group2 = new Group()
		
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
	
	static class Group {
		
		@TextField
		String textField1 = ""
		
		@TextField
		String textField2 = ""
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
