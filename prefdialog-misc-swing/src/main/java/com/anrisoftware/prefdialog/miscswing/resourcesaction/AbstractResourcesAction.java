/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.resourcesaction;

import static java.lang.String.format;

import java.util.Locale;
import java.util.MissingResourceException;

import javax.inject.Inject;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.apache.commons.lang3.StringUtils;

import com.anrisoftware.globalpom.mnemonic.Accelerator;
import com.anrisoftware.globalpom.mnemonic.AcceleratorFactory;
import com.anrisoftware.globalpom.mnemonic.Mnemonic;
import com.anrisoftware.globalpom.mnemonic.MnemonicFactory;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.ImageResource;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Sets the action name and mnemonic from text resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractResourcesAction extends AbstractAction {

	private static final String SHORT_DESCRIPTION_SUFFIX = "short";

	private static final String SMALL_ICON_SUFFIX = "small_icon";

	private static final String LARGE_ICON_SUFFIX = "large_icon";

	private static final String MNEMONIC_SUFFIX = "mnemonic";

	private static final String ACCELERATOR_SUFFIX = "accelerator";

	private String name;

	@Inject
	private AbstractResourcesActionLogger log;

	@Inject
	private MnemonicFactory mnemonicFactory;

	@Inject
	private AcceleratorFactory acceleratorFactory;

	private Texts texts;

	private Images images;

	private Locale locale;

	private IconSize largeIconSize;

	private IconSize smallIconSize;

	/**
	 * Sets the name of the action. The name is used as the text resource name
	 * for the action title and mnemonic.
	 * 
	 * @param name
	 *            the action name {@link String}.
	 */
	protected AbstractResourcesAction(String name) {
		this.name = name;
		this.locale = Locale.getDefault();
		this.largeIconSize = IconSize.SMALL;
		this.smallIconSize = IconSize.SMALL;
	}

	/**
	 * Sets the locale for the resources of this action.
	 * 
	 * @param locale
	 *            the {@link Locale}.
	 */
	public void setLocale(Locale locale) {
		Locale oldValue = this.locale;
		this.locale = locale;
		if (oldValue == locale) {
			return;
		}
		updateTextsResources();
		updateImagesResources();
	}

	/**
	 * Sets the icon size for the large icon resources of this action.
	 * 
	 * @param size
	 *            the {@link IconSize}.
	 */
	public void setLargeIconSize(IconSize size) {
		IconSize oldValue = this.largeIconSize;
		this.largeIconSize = size;
		if (oldValue == size) {
			return;
		}
		if (images != null) {
			updateLargeIcon();
		}
	}

	/**
	 * Sets the icon size for the large icon resources of this action.
	 * 
	 * @param size
	 *            the {@link IconSize}.
	 */
	public void setSmallIconSize(IconSize size) {
		IconSize oldValue = this.smallIconSize;
		this.smallIconSize = size;
		if (oldValue == size) {
			return;
		}
		if (images != null) {
			updateSmallIcon();
		}
	}

	/**
	 * Sets the texts resource for the action. The texts should contain the
	 * following resources:
	 * 
	 * <ul>
	 * <li>name
	 * <li>name_mnemonic
	 * <li>name_accelerator
	 * </ul>
	 * 
	 * @param texts
	 *            the {@link Texts}.
	 */
	public void setTexts(Texts texts) {
		this.texts = texts;
		updateTextsResources();
	}

	/**
	 * Sets the images resource for the action. The images should contain the
	 * following resources:
	 * 
	 * <ul>
	 * <li>name_large_icon
	 * </ul>
	 * 
	 * @param images
	 *            the {@link Images}.
	 */
	public void setImages(Images images) {
		this.images = images;
		if (images != null) {
			updateLargeIcon();
			updateSmallIcon();
		}
	}

	/**
	 * Sets the action name. The action name is used to look up the action
	 * resources.
	 * 
	 * @param name
	 *            the action name {@link String}.
	 */
	public void setActionName(String name) {
		String oldValue = this.name;
		this.name = name;
		if (StringUtils.equals(oldValue, name)) {
			return;
		}
		updateTextsResources();
		updateImagesResources();
	}

	private void updateImagesResources() {
		if (images == null) {
			return;
		}
		updateLargeIcon();
		updateSmallIcon();
	}

	private void updateLargeIcon() {
		ImageResource icon = loadImageSave(LARGE_ICON_SUFFIX, largeIconSize);
		if (!log.checkResource(this, icon, LARGE_ICON_SUFFIX)) {
			return;
		}
		putValue(LARGE_ICON_KEY, new ImageIcon(icon.getImage()));
	}

	private void updateSmallIcon() {
		ImageResource icon = loadImageSave(SMALL_ICON_SUFFIX, smallIconSize);
		if (!log.checkResource(this, icon, SMALL_ICON_SUFFIX)) {
			return;
		}
		putValue(SMALL_ICON, new ImageIcon(icon.getImage()));
	}

	private ImageResource loadImageSave(String suffix, IconSize size) {
		try {
			String resource = format("%s_%s", name, suffix);
			return images.getResource(resource, locale, size);
		} catch (MissingResourceException e) {
			return null;
		}
	}

	private void updateTextsResources() {
		if (texts == null) {
			return;
		}
		updateTitle();
		updateMnemonic();
		updateAcc();
		updateShortDescription();
	}

	private void updateTitle() {
		String text = texts.getResource(name).getText();
		putValue(NAME, text);
	}

	private void updateMnemonic() {
		TextResource resource = loadTextSave(MNEMONIC_SUFFIX);
		if (!log.checkResource(this, resource, MNEMONIC_SUFFIX)) {
			return;
		}
		Mnemonic m = mnemonicFactory.create(resource.getText());
		Integer code = m.getMnemonic();
		if (code != null) {
			putValue(MNEMONIC_KEY, code);
		}
		int index = m.getMnemonicIndex();
		if (index != -1) {
			putValue(DISPLAYED_MNEMONIC_INDEX_KEY, index);
		}
	}

	private void updateAcc() {
		TextResource resource = loadTextSave(ACCELERATOR_SUFFIX);
		if (!log.checkResource(this, resource, ACCELERATOR_SUFFIX)) {
			return;
		}
		Accelerator acc = acceleratorFactory.create(resource.getText());
		putValue(ACCELERATOR_KEY, acc.getAccelerator());
	}

	private void updateShortDescription() {
		TextResource resource = loadTextSave(SHORT_DESCRIPTION_SUFFIX);
		if (!log.checkResource(this, resource, SHORT_DESCRIPTION_SUFFIX)) {
			return;
		}
		putValue(SHORT_DESCRIPTION, resource.getText());
	}

	private TextResource loadTextSave(String suffix) {
		try {
			return texts.getResource(format("%s_%s", name, suffix));
		} catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * Returns the name of the action.
	 * 
	 * @return the {@link String} name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets show the text of the action.
	 * 
	 * @param b
	 *            set to {@code true} to show the text.
	 */
	public void setShowText(boolean b) {
		if (b) {
			updateTitle();
		} else {
			putValue(NAME, null);
		}
	}

	/**
	 * Sets show the large icon of the action.
	 * 
	 * @param b
	 *            set to {@code true} to show the icon.
	 */
	public void setShowLargeIcon(boolean b) {
		if (b) {
			updateLargeIcon();
		} else {
			putValue(LARGE_ICON_KEY, null);
		}
	}
}
