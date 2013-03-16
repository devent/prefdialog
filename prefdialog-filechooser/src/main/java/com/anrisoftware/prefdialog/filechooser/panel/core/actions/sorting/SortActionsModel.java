package com.anrisoftware.prefdialog.filechooser.panel.core.actions.sorting;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;

/**
 * Returns the sorting menu actions.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class SortActionsModel {

	private final ActionListener sortDateAction;

	private final ActionListener sortNameAction;

	private final ActionListener sortSizeAction;

	private final ActionListener sortTypeAction;

	private final ActionListener sortDescendingAction;

	private final ActionListener sortFolderFirstAction;

	private final ArrayList<AbstractSortingAction> sortingActions;

	@Inject
	SortActionsModel(SortDateAction sortDateAction,
			SortNameAction sortNameAction, SortSizeAction sortSizeAction,
			SortTypeAction sortTypeAction,
			SortDescendingAction sortDescendingAction,
			SortFolderFirstAction sortFolderFirstAction) {
		this.sortDateAction = sortDateAction;
		this.sortNameAction = sortNameAction;
		this.sortSizeAction = sortSizeAction;
		this.sortTypeAction = sortTypeAction;
		this.sortDescendingAction = sortDescendingAction;
		this.sortFolderFirstAction = sortFolderFirstAction;
		this.sortingActions = new ArrayList<AbstractSortingAction>();
		sortingActions.add(sortDateAction);
		sortingActions.add(sortNameAction);
		sortingActions.add(sortTypeAction);
		sortingActions.add(sortDescendingAction);
		sortingActions.add(sortFolderFirstAction);
	}

	public void setFileModel(FileModel model) {
		for (AbstractSortingAction action : sortingActions) {
			action.setFileModel(model);
		}
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

	public ActionListener getSortDescendingAction() {
		return sortDescendingAction;
	}

	public ActionListener getSortFolderFirstAction() {
		return sortFolderFirstAction;
	}
}
