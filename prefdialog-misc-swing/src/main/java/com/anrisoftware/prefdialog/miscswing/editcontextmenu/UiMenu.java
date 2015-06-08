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

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Basic edit popup menu.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.1
 */
@SuppressWarnings("serial")
final class UiMenu extends JPopupMenu {

    private final JMenuItem copyMenu;
    private final JMenuItem cutMenu;
    private final JMenuItem pasteMenu;
    private final JMenuItem selectAllMenu;
    private final JMenuItem deleteMenu;

    @OnAwt
    UiMenu() {
        this.copyMenu = new JMenuItem("Copy");
        copyMenu.setName(EditContextMenu.COPY_ACTION_NAME);
        add(copyMenu);

        this.cutMenu = new JMenuItem("Cut");
        cutMenu.setName(EditContextMenu.CUT_ACTION_NAME);
        add(cutMenu);

        this.pasteMenu = new JMenuItem("Paste");
        pasteMenu.setName(EditContextMenu.PASTE_ACTION_NAME);
        add(pasteMenu);
        addSeparator();

        this.deleteMenu = new JMenuItem("Delete");
        deleteMenu.setName(EditContextMenu.DELETE_ACTION_NAME);
        add(deleteMenu);

        this.selectAllMenu = new JMenuItem("Select All");
        selectAllMenu.setName(EditContextMenu.SELECT_ALL_ACTION_NAME);
        add(selectAllMenu);
    }

    public JMenuItem getCopyMenu() {
        return copyMenu;
    }

    public JMenuItem getCutMenu() {
        return cutMenu;
    }

    public JMenuItem getPasteMenu() {
        return pasteMenu;
    }

    public JMenuItem getDeleteMenu() {
        return deleteMenu;
    }

    public JMenuItem getSelectAllMenu() {
        return selectAllMenu;
    }
}
