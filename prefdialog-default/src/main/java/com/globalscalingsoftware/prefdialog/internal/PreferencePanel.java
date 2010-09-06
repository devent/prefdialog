package com.globalscalingsoftware.prefdialog.internal;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.IPreferencePanel;
import com.google.inject.Inject;

public class PreferencePanel implements IPreferencePanel {

	private final TableLayout layout;
	private final UiPreferencePanel uiPreferencePanel;
	private final RunnableActionEvent applyEvent;

	@Inject
	PreferencePanel() {
		uiPreferencePanel = new UiPreferencePanel();
		applyEvent = new RunnableActionEvent();

		double[] cols = { TableLayout.FILL };
		double[] rows = {};
		layout = new TableLayout(cols, rows);
		uiPreferencePanel.getBottomPanel().setLayout(layout);
		setupActions();
	}

	@Override
	public JPanel getPanel() {
		return uiPreferencePanel;
	}

	private void setupActions() {
		uiPreferencePanel.getApplyButton().addActionListener(applyEvent);
	}

	@Override
	public void setTitle(String title) {
		uiPreferencePanel.getChildTitleLabel().setText(title);
	}

	@Override
	public void addField(Component field) {
		int index = layout.getNumRow();
		layout.insertRow(index, TableLayout.PREFERRED);
		uiPreferencePanel.getBottomPanel().add(field, format("0, %d", index));
	}

	@Override
	public void setApplyAction(Action a) {
		uiPreferencePanel.getApplyButton().setAction(a);
	}

	@Override
	public void setApplyEvent(Runnable applyEvent) {
		this.applyEvent.setEvent(applyEvent);
	}

	@Override
	public void setRestoreAction(Action a) {
		uiPreferencePanel.getRestoreButton().setAction(a);
	}

}
