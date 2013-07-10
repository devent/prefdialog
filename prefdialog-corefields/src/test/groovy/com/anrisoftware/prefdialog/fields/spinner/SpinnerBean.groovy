/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.spinner

import javax.swing.SpinnerModel
import javax.swing.SpinnerNumberModel

import org.joda.time.DateTime

import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.Spinner

/**
 * Bean with text fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SpinnerBean {

	static final NULL_VALUE = "nullValue"

	static final INTEGER_VALUE = "integerValue"

	static final INTEGER_BOUNDS_VALUE = "integerBoundsValue"

	static final DOUBLE_BOUNDS_VALUE = "doubleBoundsValue"

	static final DATE_VALUE = "dateValue"

	static final DATE_BOUNDS_VALUE = "dateBoundsValue"

	static final LIST_VALUE = "listValue"

	static final CUSTOM_MODEL = "customModel"

	static final CUSTOM_MODEL_CLASS = "customModelClass"

	@FieldComponent
	@Spinner
	public Integer nullValue

	@FieldComponent
	@Spinner
	public int integerValue

	@FieldComponent
	@Spinner(minimum = 6d, maximum = 12d, stepSize = 2d)
	public int integerBoundsValue = 6

	@FieldComponent
	@Spinner(minimum = 6d, maximum = 12d, stepSize = 0.5d)
	public double doubleBoundsValue = 6

	@FieldComponent
	@Spinner
	public Date dateValue = DateTime.parse("2005-03-25").toDate()

	@FieldComponent
	@Spinner(start = 1111705200000l, end = 1116972000000l, calendarField = Calendar.MONTH)
	public Date dateBoundsValue = DateTime.parse("2005-03-25").toDate()

	@FieldComponent
	@Spinner(elements = "listValues")
	public Object listValue = "Aa"

	public List listValues = ["Aa", "Bb"]

	@FieldComponent
	@Spinner(model = "customModelModel")
	public Integer customModel = 2

	@FieldComponent
	@Spinner(modelClass = CustomSpinnerModel)
	public Integer customModelClass = 2

	public SpinnerModel customModelModel = new SpinnerNumberModel(2, 2, 10, 2)
}

