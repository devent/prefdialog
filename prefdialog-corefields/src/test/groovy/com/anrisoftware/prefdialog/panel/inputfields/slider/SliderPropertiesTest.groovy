/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields.slider


import static com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel.TITLE_LABEL

import org.junit.Test

import com.anrisoftware.prefdialog.annotations.Slider
import com.anrisoftware.prefdialog.panel.inputfields.FieldFixtureHandler
import com.anrisoftware.prefdialog.panel.inputfields.api.SliderFieldHandlerFactory

class SliderPropertiesTest extends FieldFixtureHandler {

	static factory = injector.getInstance(SliderFieldHandlerFactory)

	static final String DEFAULT_SLIDER = "defaultSlider"

	static final String MAX = "max"

	static final String MIN = "min"

	static final String NO_PAINT_TRACK = "noPaintTrack"

	static final String PAINT_LABELS = "paintLabels"

	static final String PAINT_TICKS = "paintTicks"

	static final String READ_ONLY = "readOnly"

	static final String SNAP_TO_TICKS = "snapToTicks"

	static final String DEFAULT_TITLE = "defaultTitle"

	static final String CUSTOM_TITLE = "customTitle"

	static final String HIDE_TITLE = "hideTitle"

	static class General {

		@Slider
		int defaultSlider = 0

		@Slider(max=200)
		int max = 50

		@Slider(min=-50)
		int min = 50

		@Slider(paintTrack=false)
		int noPaintTrack = 50

		@Slider(paintLabels=true, majorTicks=10)
		int paintLabels = 50

		@Slider(paintTicks=true, majorTicks=20, minorTicks=5)
		int paintTicks = 100

		@Slider(readonly=true)
		int readOnly = 50

		@Slider(snapToTicks=true)
		int snapToTicks = 50

		@Slider
		int defaultTitle = 0

		@Slider(title="Some slider")
		int customTitle = 0

		@Slider(showTitle=false)
		int hideTitle = 0
	}

	@Test
	void "default slider set and apply input"() {
		createFieldFixture(new General(), DEFAULT_SLIDER, factory)
		beginFixture()
		fixture.slider(DEFAULT_SLIDER).slideTo 55
		inputField.applyInput parentObject
		assert parentObject.defaultSlider == 55
		endFixture()
	}

	@Test
	void "max slider set and apply input"() {
		createFieldFixture(new General(), MAX, factory)
		beginFixture()
		fixture.slider(MAX).slideToMaximum()
		inputField.applyInput parentObject
		assert parentObject.max == 200
		endFixture()
	}

	@Test
	void "choose minimum and apply input"() {
		createFieldFixture(new General(), MIN, factory)
		beginFixture()
		fixture.slider(MIN).slideToMinimum()
		inputField.applyInput parentObject
		assert parentObject.min == -50
		endFixture()
	}

	@Test
	void "paint track"() {
		createFieldFixture(new General(), NO_PAINT_TRACK, factory)
		beginFixture()
		fixture.slider(NO_PAINT_TRACK).slideToMaximum()
		assert fixture.slider(NO_PAINT_TRACK).component().paintTrack == false
		endFixture()
	}

	@Test
	void "paint labels and major tick spacing"() {
		createFieldFixture(new General(), PAINT_LABELS, factory)
		beginFixture()
		fixture.slider(PAINT_LABELS).slideToMaximum()
		assert fixture.slider(PAINT_LABELS).component().paintLabels == true
		assert fixture.slider(PAINT_LABELS).component().majorTickSpacing == 10
		endFixture()
	}

	@Test
	void "paint ticks with major and minor ticks"() {
		createFieldFixture(new General(), PAINT_TICKS, factory)
		beginFixture()
		assert fixture.slider(PAINT_TICKS).component().paintTicks == true
		assert fixture.slider(PAINT_TICKS).component().majorTickSpacing == 20
		assert fixture.slider(PAINT_TICKS).component().minorTickSpacing == 5
		endFixture()
	}

	@Test
	void "read only"() {
		createFieldFixture(new General(), READ_ONLY, factory)
		beginFixture()
		fixture.slider(READ_ONLY).requireDisabled()
		endFixture()
	}

	@Test
	void "snap to tick and apply input"() {
		createFieldFixture(new General(), SNAP_TO_TICKS, factory)
		beginFixture()
		assert fixture.slider(SNAP_TO_TICKS).component().snapToTicks == true
		fixture.slider(SNAP_TO_TICKS).slideTo 55
		inputField.applyInput parentObject
		assert parentObject.snapToTicks == 55
		endFixture()
	}

	@Test
	void "default title"() {
		createFieldFixture(new General(), DEFAULT_TITLE, factory)
		beginFixture()
		fixture.slider(DEFAULT_TITLE).slideTo 55
		assert fixture.label("$TITLE_LABEL-$DEFAULT_TITLE").text() == DEFAULT_TITLE
		endFixture()
	}

	@Test
	void "custom title"() {
		createFieldFixture(new General(), CUSTOM_TITLE, factory)
		beginFixture()
		fixture.slider(CUSTOM_TITLE).slideTo 55
		assert fixture.label("$TITLE_LABEL-$CUSTOM_TITLE").text() == "Some slider"
		endFixture()
	}

	@Test
	void "hide title"() {
		createFieldFixture(new General(), HIDE_TITLE, factory)
		beginFixture()
		fixture.slider(HIDE_TITLE).slideTo 55
		//assert fixture.label("$TITLE_LABEL-$HIDE_TITLE").requireNotVisible()
		endFixture()
	}
}