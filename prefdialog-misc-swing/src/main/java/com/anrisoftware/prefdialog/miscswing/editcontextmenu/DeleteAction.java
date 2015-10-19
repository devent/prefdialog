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

import java.awt.event.ActionEvent;

import javax.swing.text.JTextComponent;

import org.apache.commons.lang3.StringUtils;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractResourcesAction;

/**
 * Delete text action.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@SuppressWarnings("serial")
class DeleteAction extends AbstractResourcesAction implements ContextMenuAction {

    private JTextComponent textField;

    DeleteAction() {
        super(EditContextMenu.DELETE_ACTION_NAME);
    }

    @Override
    public void setTextField(JTextComponent textField) {
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = textField.getText();
        String selected = textField.getSelectedText();
        String deleted = StringUtils.remove(text, selected);
        textField.setText(deleted);
    }

}
