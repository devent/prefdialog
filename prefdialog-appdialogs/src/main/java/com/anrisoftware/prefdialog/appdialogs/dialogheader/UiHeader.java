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
package com.anrisoftware.prefdialog.appdialogs.dialogheader;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXHyperlink;

/**
 * Header panel.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@SuppressWarnings("serial")
final class UiHeader extends JPanel {

    private static final String LINK_TEXT = "https://anrisoftware.com";

    private static final String INFO_TEXT = "<html>\nGlobal Scaling Software<br/>\nVersion 1.0<br/>\nCopyright © 2015<br/>\nAdvanced Natural Research Institute<br/>\nErwin Müller\n</html>";

    private static final URL LOGO_RESOURCE = UiHeader.class
            .getResource("/com/anrisoftware/prefdialog/registrationdialog/iref_logo_transparent_128.png");

    private JLabel logoLabel;

    private JLabel infoText;

    private JXHyperlink linkButton;

    private ImageIcon logoImage;

    /**
     * Create the panel.
     */
    public UiHeader() {

        setLayout(new MigLayout("", "[center][grow]", "[center]"));
        {
            JPanel infoTextPanel = new JPanel();
            add(infoTextPanel, "cell 1 0,growx,aligny center");
            infoTextPanel.setLayout(new BoxLayout(infoTextPanel,
                    BoxLayout.PAGE_AXIS));
            infoText = new JLabel();
            infoText.setText(INFO_TEXT);
            infoTextPanel.add(infoText);
            linkButton = new JXHyperlink();
            linkButton.setText(LINK_TEXT);
            infoTextPanel.add(linkButton);
        }
        {
            logoLabel = new JLabel("");
            logoLabel.setMinimumSize(new Dimension(128, 128));
            logoLabel.setMaximumSize(new Dimension(128, 128));
            logoLabel.setPreferredSize(new Dimension(128, 128));
            logoLabel.setSize(new Dimension(128, 128));
            this.logoImage = new ImageIcon(LOGO_RESOURCE);
            logoLabel.setIcon(logoImage);
            add(logoLabel, "cell 0 0,alignx left,aligny top");
        }

    }

    public JLabel getLogoLabel() {
        return logoLabel;
    }

    public JLabel getInfoText() {
        return infoText;
    }

    public JXHyperlink getLinkButton() {
        return linkButton;
    }

    public Image getLogoImage() {
        return logoImage.getImage();
    }
}
