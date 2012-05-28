package com.anrisoftware.prefdialog.dialog.childrenpanels;

import static java.awt.BorderLayout.CENTER;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.anrisoftware.prefdialog.ChildrenPanel;
import com.anrisoftware.prefdialog.ChildrenPanels;
import com.google.common.collect.Maps;

/**
 * Returns a panel with a label that shows the selected child. Is used as a
 * place holder until a real {@link ChildrenPanels} is set.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public class EmptyChildrenPanels implements ChildrenPanels {

	private final HashMap<Object, JPanel> panels;

	EmptyChildrenPanels() {
		panels = Maps.newHashMap();
	}

	@Override
	public JPanel getChildPanel(ChildrenPanel panel, Object child) {
		JPanel childPanel = panels.get(child);
		childPanel = lazyCreateChildPanel(child, childPanel);
		return childPanel;
	}

	private JPanel lazyCreateChildPanel(Object child, JPanel childPanel) {
		if (childPanel == null) {
			childPanel = new JPanel(new BorderLayout());
			JLabel label = new JLabel(child.toString());
			childPanel.add(label, CENTER);
			panels.put(child, childPanel);
		}
		return childPanel;
	}

	@Override
	public boolean isInputValid() {
		return true;
	}

	@Override
	public void restoreAllInput() {
	}

	@Override
	public void applyAllInput() {
	}

}
