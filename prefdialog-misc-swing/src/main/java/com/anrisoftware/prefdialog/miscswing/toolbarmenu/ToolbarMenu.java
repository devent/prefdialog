/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.toolbarmenu;

import static com.anrisoftware.globalpom.textposition.TextPosition.ICON_ONLY;
import static com.anrisoftware.globalpom.textposition.TextPosition.TEXT_ALONGSIDE_ICON;
import static com.anrisoftware.globalpom.textposition.TextPosition.TEXT_ONLY;
import static com.anrisoftware.prefdialog.miscswing.toolbarmenu.ToolbarMenuProperty.ICON_SIZE_PROPERTY;
import static com.anrisoftware.prefdialog.miscswing.toolbarmenu.ToolbarMenuProperty.TEXT_POSITION_PROPERTY;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import com.anrisoftware.globalpom.textposition.TextPosition;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Creates a pop-up menu to set text position and icon size of the added
 * actions.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ToolbarMenu {

    /**
     * Name of the icons only menu.
     */
    public static final String ICONS_ONLY_NAME = "iconsOnlyMenu";

    /**
     * Name of the text only menu.
     */
    public static final String TEXT_ONLY_NAME = "textOnlyMenu";

    /**
     * Name of the text alongside icons menu.
     */
    public static final String TEXT_ALONGSIDE_NAME = "textAlongsideMenu";

    /**
     * Name of the huge icon size menu.
     */
    public static final String HUGE_ICON_SIZE_NAME = "hugeIconSizeMenu";

    /**
     * Name of the large icon size menu.
     */
    public static final String LARGE_ICON_SIZE_NAME = "largeIconSizeMenu";

    /**
     * Name of the medium icon size menu.
     */
    public static final String MEDIUM_ICON_SIZE_NAME = "mediumIconSizeMenu";

    /**
     * Name of the small icon size menu.
     */
    public static final String SMALL_ICON_SIZE_NAME = "smallIconSizeMenu";

    private final List<AbstractResourcesAction> actions;

    private final MouseAdapter mouseListener;

    private final Map<String, AbstractResourcesAction> menuActions;

    private final PropertyChangeSupport p;

    @Inject
    private UiMenu menu;

    private TextPosition textPosition;

    private IconSize iconSize;

    ToolbarMenu() {
        this.p = new PropertyChangeSupport(this);
        this.menuActions = new HashMap<String, AbstractResourcesAction>();
        this.actions = new ArrayList<AbstractResourcesAction>();
        this.textPosition = TextPosition.TEXT_ALONGSIDE_ICON;
        this.iconSize = IconSize.SMALL;
        this.mouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                maybeShowPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                maybeShowPopup(e);
            }

        };
    }

    @Inject
    void setIconsOnlyAction(IconsOnlyAction action) {
        menuActions.put(ICONS_ONLY_NAME, action);
        action.setToolbarMenu(this);
        menu.getIconsOnlyMenu().setName(ICONS_ONLY_NAME);
        menu.getIconsOnlyMenu().setAction(action);
    }

    @Inject
    void setTextOnlyAction(TextOnlyAction action) {
        menuActions.put(TEXT_ONLY_NAME, action);
        action.setToolbarMenu(this);
        menu.getTextOnlyMenu().setName(TEXT_ONLY_NAME);
        menu.getTextOnlyMenu().setAction(action);
    }

    @Inject
    void setTextAlongsideOnlyAction(TextAlongsideAction action) {
        menuActions.put(TEXT_ALONGSIDE_NAME, action);
        action.setToolbarMenu(this);
        menu.getTextAlongsideIconsMenu().setName(TEXT_ALONGSIDE_NAME);
        menu.getTextAlongsideIconsMenu().setAction(action);
    }

    @Inject
    void setHugeIconSizeAction(HugeIconSizeAction action) {
        menuActions.put(HUGE_ICON_SIZE_NAME, action);
        action.setToolbarMenu(this);
        menu.getHugeMenu().setName(HUGE_ICON_SIZE_NAME);
        menu.getHugeMenu().setAction(action);
    }

    @Inject
    void setLargeIconSizeAction(LargeIconSizeAction action) {
        menuActions.put(LARGE_ICON_SIZE_NAME, action);
        action.setToolbarMenu(this);
        menu.getLargeMenu().setName(LARGE_ICON_SIZE_NAME);
        menu.getLargeMenu().setAction(action);
    }

    @Inject
    void setMediumIconSizeAction(MediumIconSizeAction action) {
        menuActions.put(MEDIUM_ICON_SIZE_NAME, action);
        action.setToolbarMenu(this);
        menu.getMediumMenu().setName(MEDIUM_ICON_SIZE_NAME);
        menu.getMediumMenu().setAction(action);
    }

    @Inject
    void setSmallIconSizeAction(SmallIconSizeAction action) {
        menuActions.put(SMALL_ICON_SIZE_NAME, action);
        action.setToolbarMenu(this);
        menu.getSmallMenu().setName(SMALL_ICON_SIZE_NAME);
        menu.getSmallMenu().setAction(action);
    }

    /**
     * Sets the pop-up menu for the tool-bar.
     * 
     * @param bar
     *            the {@link JToolBar}.
     */
    public void setToolBar(JToolBar bar) {
        bar.addMouseListener(mouseListener);
    }

    /**
     * Sets the locale for the resources of the actions.
     * 
     * @param locale
     *            the {@link Locale}.
     */
    public void setLocale(Locale locale) {
        for (AbstractResourcesAction action : menuActions.values()) {
            action.setLocale(locale);
        }
        for (AbstractResourcesAction action : actions) {
            action.setLocale(locale);
        }
    }

    /**
     * Sets the texts resource for the actions.
     * 
     * @param texts
     *            the {@link Texts}.
     */
    public void setTexts(Texts texts) {
        for (AbstractResourcesAction action : menuActions.values()) {
            action.setTexts(texts);
        }
        for (AbstractResourcesAction action : actions) {
            action.setTexts(texts);
        }
    }

    /**
     * Sets the images resource for the actions.
     * 
     * @param images
     *            the {@link Images}.
     */
    public void setImages(Images images) {
        for (AbstractResourcesAction action : menuActions.values()) {
            action.setImages(images);
        }
        for (AbstractResourcesAction action : actions) {
            action.setImages(images);
        }
    }

    /**
     * Adds the action so the text position and the icon size can be set.
     * 
     * @param action
     *            the {@link AbstractResourcesAction}.
     */
    public void addAction(AbstractResourcesAction action) {
        actions.add(action);
    }

    /**
     * Returns the pop-up menu.
     * 
     * @return the {@link JPopupMenu}.
     */
    public JPopupMenu getPopupMenu() {
        return menu;
    }

    /**
     * Sets show icons only for actions.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param b
     *            set to {@code true} to show only icons.
     */
    @OnAwt
    public void setIconsOnly(boolean b) {
        for (AbstractResourcesAction action : actions) {
            action.setShowText(!b);
        }
        if (b) {
            menu.getIconsOnlyMenu().setSelected(b);
            updateTextPosition(ICON_ONLY);
        }
    }

    /**
     * Sets show text only for actions.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param b
     *            set to {@code true} to show text icons.
     */
    @OnAwt
    public void setTextOnly(boolean b) {
        for (AbstractResourcesAction action : actions) {
            action.setShowLargeIcon(!b);
        }
        if (b) {
            updateTextPosition(TEXT_ONLY);
            menu.getTextOnlyMenu().setSelected(b);
        }
    }

    /**
     * Sets show text alongside icons for actions.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param b
     *            set to {@code true} to show text alongside icons.
     */
    @OnAwt
    public void setTextAlongsideIcons(boolean b) {
        for (AbstractResourcesAction action : actions) {
            action.setShowText(b);
            action.setShowLargeIcon(b);
        }
        if (b) {
            updateTextPosition(TEXT_ALONGSIDE_ICON);
            menu.getTextAlongsideIconsMenu().setSelected(b);
        }
    }

    /**
     * Sets text position for actions.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param position
     *            the {@link TextPosition}.
     */
    @OnAwt
    public void setTextPosition(TextPosition position) {
        this.textPosition = position;
        switch (position) {
        case ICON_ONLY:
            setIconsOnly(true);
            break;
        case TEXT_ONLY:
            setTextOnly(true);
            break;
        case TEXT_ALONGSIDE_ICON:
            setTextAlongsideIcons(true);
            break;
        default:
        }
    }

    /**
     * Returns the text position of the actions.
     * 
     * @return the {@link TextPosition}.
     */
    public TextPosition getTextPosition() {
        return textPosition;
    }

    /**
     * Sets the icon size for the actions.
     * <p>
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * 
     * @param size
     *            the {@link IconSize}.
     */
    @OnAwt
    public void setIconSize(IconSize size) {
        for (AbstractResourcesAction action : actions) {
            action.setLargeIconSize(size);
            action.setSmallIconSize(size);
        }
        switch (size) {
        case HUGE:
            menu.getHugeMenu().setSelected(true);
            break;
        case LARGE:
            menu.getLargeMenu().setSelected(true);
            break;
        case MEDIUM:
            menu.getMediumMenu().setSelected(true);
            break;
        case SMALL:
            menu.getSmallMenu().setSelected(true);
            break;
        }
        IconSize oldValue = this.iconSize;
        this.iconSize = size;
        p.firePropertyChange(ICON_SIZE_PROPERTY.toString(), oldValue, size);
    }

    /**
     * Returns the icon size of the actions.
     * 
     * @return the {@link IconSize}.
     */
    public IconSize getIconSize() {
        return iconSize;
    }

    /**
     * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
     * @see ToolbarMenuProperty
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        p.addPropertyChangeListener(listener);
    }

    /**
     * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
     * @see ToolbarMenuProperty
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        p.removePropertyChangeListener(listener);
    }

    /**
     * @see PropertyChangeSupport#addPropertyChangeListener(String,
     *      PropertyChangeListener)
     * @see ToolbarMenuProperty
     */
    public void addPropertyChangeListener(ToolbarMenuProperty property,
            PropertyChangeListener listener) {
        p.addPropertyChangeListener(property.toString(), listener);
    }

    /**
     * @see PropertyChangeSupport#removePropertyChangeListener(String,
     *      PropertyChangeListener)
     * @see ToolbarMenuProperty
     */
    public void removePropertyChangeListener(ToolbarMenuProperty property,
            PropertyChangeListener listener) {
        p.removePropertyChangeListener(property.toString(), listener);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private void updateTextPosition(TextPosition position) {
        TextPosition oldValue = this.textPosition;
        this.textPosition = position;
        p.firePropertyChange(TEXT_POSITION_PROPERTY.toString(), oldValue,
                position);
    }

}
