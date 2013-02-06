package com.anrisoftware.prefdialog.filechooser.panel.core.actions;

import java.awt.event.ActionListener;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;

public class SortActionsModel {

	private final SortDateAction sortDateAction;

	private final SortNameAction sortNameAction;

	private final SortSizeAction sortSizeAction;

	private final SortTypeAction sortTypeAction;

	@Inject
	SortActionsModel(SortDateAction sortDateAction,
			SortNameAction sortNameAction, SortSizeAction sortSizeAction,
			SortTypeAction sortTypeAction) {
		this.sortDateAction = sortDateAction;
		this.sortNameAction = sortNameAction;
		this.sortSizeAction = sortSizeAction;
		this.sortTypeAction = sortTypeAction;
	}

	public void setFileModel(FileModel model) {
		sortDateAction.setFileModel(model);
		sortNameAction.setFileModel(model);
		sortSizeAction.setFileModel(model);
		sortTypeAction.setFileModel(model);
	}

	public ActionListener getSortDateAction() {
		return sortDateAction;
	}

	public ActionListener getSortNameAction() {
		return sortNameAction;
	}

	public ActionListener getSortSizeAction() {
		return sortSizeAction;
	}

	public ActionListener getSortTypeAction() {
		return sortTypeAction;
	}
}
