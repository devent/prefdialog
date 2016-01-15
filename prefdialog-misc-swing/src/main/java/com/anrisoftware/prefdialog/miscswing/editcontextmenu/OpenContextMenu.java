/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JPopupMenu;
import javax.swing.text.JTextComponent;

/**
 * Opens the edit context menu on the text field.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.1
 */
final class OpenContextMenu {

    private final MouseListener mouseListener;

    private List<ContextMenuAction> actions;

    private JPopupMenu menu;

    OpenContextMenu() {
        this.mouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

        };
    }

    public void addTextField(JTextComponent textField) {
        textField.addMouseListener(mouseListener);
    }

    public void setActions(List<ContextMenuAction> actions) {
        this.actions = actions;
    }

    public void setPopupMenu(JPopupMenu menu) {
        this.menu = menu;
    }

    private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            JTextComponent component = (JTextComponent) e.getComponent();
            menu.show(component, e.getX(), e.getY());
            for (ContextMenuAction action : actions) {
                action.setTextField(component);
            }
        }
    }
}
