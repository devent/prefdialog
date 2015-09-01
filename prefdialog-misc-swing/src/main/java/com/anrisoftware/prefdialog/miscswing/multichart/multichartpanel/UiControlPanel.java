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
package com.anrisoftware.prefdialog.miscswing.multichart.multichartpanel;

import java.awt.BorderLayout;

import javax.inject.Inject;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.anrisoftware.prefdialog.miscswing.actions.AbstractExecuteActions;
import com.anrisoftware.prefdialog.miscswing.actions.AbstractResourcesAction;
import com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions.ToolbarActions;
import com.anrisoftware.prefdialog.miscswing.toolbarmenu.ToolbarMenu;

/**
 * Panel containing the forecast data graph.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
final class UiControlPanel extends JPanel {

    private final JScrollBar horizontalScrollBar;

    private final JFormattedTextField rangeField;

    private final JToolBar toolBar;

    private final JButton zoomInButton;

    private final JButton zoomOutButton;

    private final JButton autoZoomDomainButton;

    private final JButton optionsButton;

    private final JScrollBar verticalScrollBar;

    private final JPanel graphsPanel;

    private ToolbarMenu toolbarMenu;

    private final JButton autoZoomRangeButton;

    /**
     * Create the panel.
     */
    UiControlPanel() {
        setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        setLayout(new BorderLayout(0, 0));

        toolBar = new JToolBar();
        add(toolBar, BorderLayout.NORTH);

        zoomInButton = new JButton("Zoom In");
        zoomInButton.setName(ToolbarActions.ZOOM_IN_NAME);
        toolBar.add(zoomInButton);

        zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.setName(ToolbarActions.ZOOM_OUT_NAME);
        toolBar.add(zoomOutButton);

        autoZoomDomainButton = new JButton("Auto Domain");
        autoZoomDomainButton.setName(ToolbarActions.AUTO_ZOOM_DOMAIN_NAME);
        toolBar.add(autoZoomDomainButton);

        autoZoomRangeButton = new JButton("Auto Range");
        autoZoomRangeButton.setName(ToolbarActions.AUTO_ZOOM_RANGE_NAME);
        toolBar.add(autoZoomRangeButton);

        optionsButton = new JButton("Options");
        optionsButton.setName(ToolbarActions.OPTIONS_NAME);
        toolBar.add(optionsButton);

        rangeField = new JFormattedTextField();
        toolBar.add(rangeField);
        rangeField.setValue(0);
        rangeField.setHorizontalAlignment(SwingConstants.TRAILING);

        verticalScrollBar = new JScrollBar();
        add(verticalScrollBar, BorderLayout.EAST);
        verticalScrollBar.setName(ToolbarActions.VERTICAL_SCROLLBAR_NAME);

        this.horizontalScrollBar = new JScrollBar();
        add(horizontalScrollBar, BorderLayout.SOUTH);
        horizontalScrollBar.setName(ToolbarActions.HORIZONTAL_SCROLLBAR_NAME);
        horizontalScrollBar.setOrientation(JScrollBar.HORIZONTAL);

        graphsPanel = new JPanel();
        add(graphsPanel, BorderLayout.CENTER);
    }

    @Inject
    void setToolbarMenu(ToolbarMenu toolbarMenu) {
        this.toolbarMenu = toolbarMenu;
        toolbarMenu.setToolBar(toolBar);
    }

    public ToolbarMenu getToolbarMenu() {
        return toolbarMenu;
    }

    public JScrollBar getGraphScrollBar() {
        return horizontalScrollBar;
    }

    public JFormattedTextField getRangeField() {
        return rangeField;
    }

    public JButton getAutoZoomDomainButton() {
        return autoZoomDomainButton;
    }

    public JButton getZoomInButton() {
        return zoomInButton;
    }

    public JButton getOptionsButton() {
        return optionsButton;
    }

    public JButton getZoomOutButton() {
        return zoomOutButton;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public JScrollBar getVerticalScrollBar() {
        return verticalScrollBar;
    }

    public JScrollBar getHorizontalScrollBar() {
        return horizontalScrollBar;
    }

    public JPanel getGraphsPanel() {
        return graphsPanel;
    }

    public void setAction(AbstractButton button, AbstractExecuteActions actions) {
        String name = button.getName();
        Action menuAction = actions.getActions().get(name);
        button.setAction(menuAction);
        toolbarMenu.addAction((AbstractResourcesAction) menuAction);
    }

    public JButton getAutoZoomRangeButton() {
        return autoZoomRangeButton;
    }

}
