/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-panel.
 *
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.tabspanel;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Return the properties of a tab. Can use text and image resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class DefaultTabsRenderer implements TabsRenderer,
		Serializable {

	private Texts texts;

	private List<String> titles;

	private Locale locale;

	private List<String> tips;

	private List<String> iconResources;

	private List<Icon> icons;

	private Images images;

	private IconSize iconSize;

	public DefaultTabsRenderer() {
		this.titles = new ArrayList<String>(0);
		this.tips = new ArrayList<String>(0);
		this.icons = new ArrayList<Icon>(0);
		this.iconResources = new ArrayList<String>(0);
	}

	/**
	 * Sets the texts resource.
	 * 
	 * @param texts
	 *            the {@link Texts} resource.
	 */
	public void setTexts(Texts texts) {
		this.texts = texts;
	}

	/**
	 * Sets the images resource.
	 * 
	 * @param images
	 *            the {@link Images} resource.
	 */
	public void setImages(Images images) {
		this.images = images;
	}

	/**
	 * Sets the titles for the tabs. Can be resource names.
	 * 
	 * @param titles
	 *            the {@link List} of the titles.
	 */
	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	/**
	 * Sets the tool-tips for the tabs. Can be resource names.
	 * 
	 * @param tips
	 *            the {@link List} of the tool-tip texts.
	 */
	public void setToolTips(List<String> tips) {
		this.tips = tips;
	}

	/**
	 * Sets the icon resource names the tabs.
	 * 
	 * @param titles
	 *            the {@link List} of the icon resource names.
	 */
	public void setIconResources(List<String> icons) {
		this.iconResources = icons;
	}

	/**
	 * Sets the icons for the tabs.
	 * 
	 * @param titles
	 *            the {@link List} of the icons.
	 */
	public void setIcons(List<Icon> icons) {
		this.icons = icons;
	}

	/**
	 * Sets the icon size of the tabs.
	 * 
	 * @param titles
	 *            the {@link IconSize} size.
	 */
	public void setIconSize(IconSize iconSize) {
		this.iconSize = iconSize;
	}

	/**
	 * Sets the locale for the tabs. Is used to look up the text resources.
	 * 
	 * @param locale
	 *            the {@link Locale} of the tabs.
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public String getTitle(int index) {
		if (titles.size() <= index) {
			return null;
		}
		String title = titles.get(index);
		if (haveTextResource(title)) {
			title = getTextResource(title);
		}
		return title;
	}

	@Override
	public String getTip(int index) {
		if (tips.size() <= index) {
			return null;
		}
		String tip = tips.get(index);
		if (haveTextResource(tip)) {
			tip = getTextResource(tip);
		}
		return tip;
	}

	protected boolean haveTextResource(String name) {
		return !isEmpty(name) && texts != null;
	}

	protected String getTextResource(String name) {
		return texts.getResource(name, locale).getText();
	}

	@Override
	public Icon getIcon(int index) {
		Icon icon = icon(index);
		return icon == null ? iconResource(index) : icon;
	}

	private Icon iconResource(int index) {
		if (iconResources.size() <= index) {
			return null;
		}
		String resource = iconResources.get(index);
		return new ImageIcon(images.getResource(resource, locale, iconSize)
				.getImage());
	}

	private Icon icon(int index) {
		if (icons.size() > index) {
			return icons.get(index);
		}
		return null;
	}

}
