package com.anrisoftware.prefdialog.appdialogs.registerdialog;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.text.JTextComponent;

/**
 * Retrieves the entered text from the text field.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
class AbstractTextListener implements FocusListener {

    private JTextComponent textField;

    private String text;

    public void setTextField(JTextComponent textField) {
        this.textField = textField;
        this.text = textField.getText();
        textField.addFocusListener(this);
    }

    public String getText() {
        return text;
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        this.text = textField.getText();
    }
}
