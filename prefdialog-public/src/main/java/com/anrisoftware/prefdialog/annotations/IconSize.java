package com.anrisoftware.prefdialog.annotations;

/**
 * The icon sizes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public enum IconSize {

	/**
	 * Size 16x16.
	 */
	SMALL(16),

	/**
	 * Size 22x22.
	 */
	MEDIUM(22),

	/**
	 * Size 32x32.
	 */
	LARGE(32),

	/**
	 * 48x48.
	 */
	HUGE(48);

	private final int width;

	private IconSize(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}
}
