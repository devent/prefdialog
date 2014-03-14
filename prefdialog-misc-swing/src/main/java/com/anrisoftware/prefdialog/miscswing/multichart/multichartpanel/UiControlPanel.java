/*
 * Copyright 2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of forecast-suite-main. All rights reserved.
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

import com.anrisoftware.globalpom.textposition.TextPosition;
import com.anrisoftware.prefdialog.miscswing.actions.AbstractMenuActions;
import com.anrisoftware.prefdialog.miscswing.actions.MenuAction;
import com.anrisoftware.prefdialog.miscswing.multichart.toolbaractions.ToolbarActions;
import com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction;
import com.anrisoftware.prefdialog.miscswing.toolbarmenu.ToolbarMenu;
import com.anrisoftware.resources.images.api.IconSize;

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

    public void setAction(AbstractButton button, AbstractMenuActions actions) {
        String name = button.getName();
        MenuAction menuAction = actions.getActions().get(name);
        button.setAction((Action) menuAction);
        toolbarMenu.addAction((AbstractResourcesAction) menuAction);
    }

    public void setIconsOnly(boolean b) {
        toolbarMenu.setIconsOnly(b);
    }

    public void setTextOnly(boolean b) {
        toolbarMenu.setTextOnly(b);
    }

    public void setTextAlongsideIcons(boolean flag) {
        toolbarMenu.setTextAlongsideIcons(flag);
    }

    public void setTextPosition(TextPosition position) {
        toolbarMenu.setTextPosition(position);
    }

    public void setIconSize(IconSize size) {
        toolbarMenu.setIconSize(size);
    }

    public JButton getAutoZoomRangeButton() {
        return autoZoomRangeButton;
    }

}
