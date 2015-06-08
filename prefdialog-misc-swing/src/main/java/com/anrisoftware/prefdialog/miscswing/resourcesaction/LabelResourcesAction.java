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
package com.anrisoftware.prefdialog.miscswing.resourcesaction;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Decorates a {@link JLabel} with the retrieved resources. The label text, icon
 * and mnemonic can be set from the action.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.1
 */
@SuppressWarnings("serial")
public class LabelResourcesAction extends AbstractResourcesAction {

    /**
     * Sets the name of the label. The name is used as the text resource name
     * for the action title and mnemonic.
     *
     * @param name
     *            the action name {@link String}.
     */
    protected LabelResourcesAction(String name) {
        super(name);
    }

    /**
     * Do nothing.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * Sets the label to decorate.
     *
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @param label
     *            the {@link JLabel} label.
     */
    @OnAwt
    public void setLabel(JLabel label) {
        label.setText(getValue(NAME).toString());
        Integer mnemonic = getMnemonic();
        if (mnemonic != null) {
            label.setDisplayedMnemonic(mnemonic);
        }
        Integer index = getDisplayedMnemonicIndex();
        if (index != null) {
            label.setDisplayedMnemonicIndex(index);
        }
        ImageIcon icon = getSmallIcon();
        if (icon != null) {
            label.setIcon(icon);
        }
    }
}
