/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-appdialogs.
 *
 * prefdialog-appdialogs is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-appdialogs is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-appdialogs. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.appdialogs.registerdialog;

import static com.anrisoftware.prefdialog.appdialogs.registerdialog.ApprovalActionListener._.code_not_set_message;
import static com.anrisoftware.prefdialog.appdialogs.registerdialog.ApprovalActionListener._.name_not_set_message;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialog;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Retrieves the entered registration name and code.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
class ApprovalActionListener implements ActionListener, VetoableChangeListener {

    enum _ {

        name_not_set_message,

        code_not_set_message;

        public static void loadTexts(Texts texts) {
            for (_ value : values()) {
                value.text = texts.getResource(value.name()).getText();
            }
        }

        private String text;

        @Override
        public String toString() {
            return text;
        }
    }

    private RegisterDialog dialog;

    public void setAppDialog(AppDialog dialog) {
        dialog.addApprovalAction(this);
        dialog.addVetoableChangeListener(this);
    }

    public void setRegisterDialog(RegisterDialog dialog) {
        this.dialog = dialog;
    }

    public void setTexts(Texts texts) {
        _.loadTexts(texts);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt)
            throws PropertyVetoException {
        Status status = (Status) evt.getNewValue();
        if (status == Status.APPROVED) {
            if (isBlank(dialog.getName())) {
                throw new PropertyVetoException(
                        name_not_set_message.toString(), evt);
            }
            if (isBlank(dialog.getCode())) {
                throw new PropertyVetoException(
                        code_not_set_message.toString(), evt);
            }
        }
    }

}
