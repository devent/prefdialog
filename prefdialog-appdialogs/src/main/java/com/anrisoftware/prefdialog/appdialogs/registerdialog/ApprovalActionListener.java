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
