/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.editcontextmenu;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.swing.text.JTextComponent;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.images.api.ImagesFactory;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

/**
 * Basic text context menu, with the cut, copy and paste menu items.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.1
 */
public class EditContextMenu {

    public static final String COPY_ACTION_NAME = "copy_action";

    public static final String CUT_ACTION_NAME = "cut_action";

    public static final String PASTE_ACTION_NAME = "paste_action";

    public static final String DELETE_ACTION_NAME = "delete_action";

    public static final String SELECT_ALL_ACTION_NAME = "select_all_action";

    private final List<JTextComponent> textFields;

    @Inject
    private UiMenu menu;

    @Inject
    private CopyAction copyAction;

    @Inject
    private CutAction cutAction;

    @Inject
    private PasteAction pasteAction;

    @Inject
    private SelectAllAction selectAllAction;

    @Inject
    private DeleteAction deleteAction;

    private OpenContextMenu openContextMenu;

    private List<ContextMenuAction> actionsList;

    EditContextMenu() {
        this.textFields = new ArrayList<JTextComponent>();
    }

    @Inject
    void setTextsFactory(TextsFactory factory) {
        Texts texts = factory.create(EditContextMenu.class.getSimpleName());
        setTexts(texts);
    }

    @Inject
    void setImagesFactory(ImagesFactory factory) {
        Images images = factory.create(EditContextMenu.class.getSimpleName());
        setImages(images);
    }

    @Inject
    void setOpenContextMenu(OpenContextMenu menu) {
        List<ContextMenuAction> actions = new ArrayList<ContextMenuAction>();
        actions.add(copyAction);
        actions.add(cutAction);
        actions.add(pasteAction);
        actions.add(selectAllAction);
        actions.add(deleteAction);
        this.actionsList = Collections.unmodifiableList(actions);
        menu.setActions(actionsList);
        this.openContextMenu = menu;
    }

    /**
     * Sets the texts resource for the actions.
     *
     * @param texts
     *            the {@link Texts}.
     *
     * @throws NullPointerException
     *             if the specified texts are {@code null}.
     */
    public void setTexts(Texts texts) {
        notNull(texts);
        copyAction.setTexts(texts);
        cutAction.setTexts(texts);
        pasteAction.setTexts(texts);
        selectAllAction.setTexts(texts);
        deleteAction.setTexts(texts);
    }

    /**
     * Sets the images resource for the actions.
     *
     * @param images
     *            the {@link Images}.
     *
     * @throws NullPointerException
     *             if the specified images are {@code null}.
     */
    public void setImages(Images images) {
        notNull(images);
        copyAction.setImages(images);
        cutAction.setImages(images);
        pasteAction.setImages(images);
        selectAllAction.setImages(images);
        deleteAction.setImages(images);
    }

    /**
     * Adds the text field so that the edit context menu can be used on this
     * field.
     *
     * @param textField
     *            the {@link JTextComponent}.
     *
     * @throws NullPointerException
     *             if the specified text field is {@code null}.
     */
    public void addTextField(JTextComponent textField) {
        notNull(textField);
        textFields.add(textField);
        openContextMenu.addTextField(textField);
    }

    /**
     * Creates the menu.
     *
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @return this {@link EditContextMenu}.
     */
    @OnAwt
    public EditContextMenu createMenu() {
        menu.getCopyMenu().setAction(copyAction);
        menu.getCutMenu().setAction(cutAction);
        menu.getPasteMenu().setAction(pasteAction);
        menu.getSelectAllMenu().setAction(selectAllAction);
        menu.getDeleteMenu().setAction(deleteAction);
        openContextMenu.setPopupMenu(menu);
        return this;
    }

    /**
     * @see javax.swing.JPopupMenu#setLightWeightPopupEnabled(boolean)
     */
    public void setLightWeightPopupEnabled(boolean aFlag) {
        menu.setLightWeightPopupEnabled(aFlag);
    }

    /**
     * Sets the locale of the menu.
     *
     * @param locale
     *            the {@link Locale} locale.
     */
    public void setLocale(Locale locale) {
        menu.setLocale(locale);
        copyAction.setLocale(locale);
        cutAction.setLocale(locale);
        pasteAction.setLocale(locale);
        selectAllAction.setLocale(locale);
        deleteAction.setLocale(locale);
    }

}
