package com.anrisoftware.prefdialog.panel.inputfields.group

import info.clearthought.layout.TableLayout

import java.awt.Container

import aurelienribon.tweenengine.TweenAccessor

class LayoutAccessor implements TweenAccessor<Container> {

	public static final int POSITION_Y = 0

	@Override
	int getValues(Container target, int type, float[] returnValues) {
		returnValues[0] = target.layout.getRow(1)
		return 1
	}

	@Override
	void setValues(Container target, int type, float[] returnValues) {
		if (returnValues[0] == 100.0f) {
			target.layout.setRow(1, TableLayout.FILL)
		}
		target.layout.setRow(1, returnValues[0])
		target.layout.layoutContainer target
		target.repaint()
	}
}
