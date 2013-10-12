package com.anrisoftware.prefdialog.miscswing.fadingpane;

import static com.anrisoftware.prefdialog.miscswing.fadingpane.FadingColor.ALPHA;
import aurelienribon.tweenengine.TweenAccessor;

/**
 * Color with alpha fading accessor.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FadingColorAccessor implements TweenAccessor<FadingColor> {

	@Override
	public int getValues(FadingColor target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case ALPHA:
			returnValues[0] = target.getAlpha();
			return 1;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(FadingColor target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case ALPHA:
			target.setAlpha(newValues[0]);
			break;
		default:
			assert false;
			break;
		}
	}
}
