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
package com.anrisoftware.prefdialog.miscswing.twolayoutspanel;

import static com.anrisoftware.prefdialog.miscswing.twolayoutspanel.ChangeLayoutBasedOnWidthPanel.PanelName.HORIZONTAL_PANEL_NAME;
import static com.anrisoftware.prefdialog.miscswing.twolayoutspanel.ChangeLayoutBasedOnWidthPanel.PanelName.VERTICAL_PANEL_NAME;
import static org.apache.commons.lang3.Validate.notNull;

import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Changes the layout based on the width of the panel.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.1
 */
public final class ChangeLayoutBasedOnWidthPanel {

    public enum PanelName {

        VERTICAL_PANEL_NAME,

        HORIZONTAL_PANEL_NAME,

    }

    private final ComponentAdapter panelSizeListener;

    @Inject
    private ChangeLayoutBasedOnWidthPanelUi panel;

    private JPanel verticalPanel;

    private JPanel horizontalPanel;

    private int verticalLayoutWidth;

    private CardLayout cardLayout;

    private Object currentVisiblePanel;

    ChangeLayoutBasedOnWidthPanel() {
        this.panelSizeListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateLayout();
            }

        };
    }

    @OnAwt
    public ChangeLayoutBasedOnWidthPanel createPanel() {
        panel.addComponentListener(panelSizeListener);
        this.cardLayout = panel.getCardLayout();
        return this;
    }

    @OnAwt
    public JPanel getPanel() {
        return panel;
    }

    public void setVerticalLayoutWidth(int width) {
        this.verticalLayoutWidth = width;
        updateLayout();
    }

    @OnAwt
    public void setVerticalPanel(JPanel verticalPanel) {
        notNull(verticalPanel);
        JPanel oldPanel = this.verticalPanel;
        this.verticalPanel = verticalPanel;
        if (oldPanel != null) {
            panel.remove(oldPanel);
        }
        panel.add(verticalPanel, VERTICAL_PANEL_NAME.name());
        updateLayout();
    }

    @OnAwt
    public void setHorizontalPanel(JPanel horizontalPanel) {
        notNull(horizontalPanel);
        JPanel oldPanel = this.horizontalPanel;
        this.horizontalPanel = horizontalPanel;
        if (oldPanel != null) {
            panel.remove(oldPanel);
        }
        panel.add(horizontalPanel, HORIZONTAL_PANEL_NAME.name());
        updateLayout();
    }

    @OnAwt
    public void setShowPanel(PanelName panelName) {
        notNull(panelName);
        cardLayout.show(panel, panelName.name());
    }

    @SuppressWarnings("unchecked")
    public <T> T getCurrentVisiblePanel() {
        return (T) currentVisiblePanel;
    }

    private void updateLayout() {
        int width = panel.getWidth();
        if (width <= verticalLayoutWidth) {
            this.currentVisiblePanel = verticalPanel;
            cardLayout.show(panel, VERTICAL_PANEL_NAME.name());
        } else {
            this.currentVisiblePanel = horizontalPanel;
            cardLayout.show(panel, HORIZONTAL_PANEL_NAME.name());
        }
    }

}
