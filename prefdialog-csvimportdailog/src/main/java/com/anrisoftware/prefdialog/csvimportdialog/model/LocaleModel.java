package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class LocaleModel extends DefaultComboBoxModel<Locale> {

	LocaleModel() {
		super(new Vector<Locale>(getLocaleDefaults()));
	}

	private static Collection<Locale> getLocaleDefaults() {
		List<Locale> locales = new ArrayList<Locale>();
		locales.addAll(Arrays.asList(Locale.getAvailableLocales()));
		Collections.sort(locales, new Comparator<Locale>() {

			@Override
			public int compare(Locale o1, Locale o2) {
				return o1.getDisplayName().compareTo(o2.getDisplayName());
			}
		});
		return locales;
	}

}
