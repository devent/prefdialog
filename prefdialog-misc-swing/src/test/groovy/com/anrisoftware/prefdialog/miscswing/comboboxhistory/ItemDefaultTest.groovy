package com.anrisoftware.prefdialog.miscswing.comboboxhistory

import static com.anrisoftware.prefdialog.miscswing.comboboxhistory.ComboBoxHistoryModule.*

import org.junit.Test

/**
 * @see ItemDefault
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ItemDefaultTest {

	@Test
	void "equals"() {
		String item = "default A"
		def defitem = getInjector().getInstance(ItemDefaultFactory).create(item)
		assert item.hashCode() == defitem.hashCode()
		assert defitem.equals(item)
		assert !item.equals(defitem)
		assert defitem == item
		assert item != defitem
	}
}
