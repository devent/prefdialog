package com.anrisoftware.prefdialog.swingutils;

import info.clearthought.layout.TableLayout;

import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;

/**
 * Encloses the {@link FieldType} in a {@link JPanel} container. We add methods
 * to set and get the layout of the panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public abstract class AbstractPanelField<FieldType extends JComponent> extends
		AbstractFieldComponent<JPanel> {

	private final FieldType field;

	private TableLayout layout;

	/**
	 * Create the panel that will contain the component.
	 * 
	 * @param field
	 *            the {@link JComponent}.
	 */
	public AbstractPanelField(FieldType field) {
		super(new JPanel());
		this.field = field;
		this.layout = createLayout();
		setupPanel();
	}

	private TableLayout createLayout() {
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.PREFERRED };
		return new TableLayout(col, row);
	}

	private void setupPanel() {
		JPanel panel = (JPanel) getAWTComponent();
		panel.setLayout(layout);
		panel.add(field, "0, 0");
	}

	/**
	 * Sets the new {@link TableLayout} for the container to use.
	 */
	public void setLayout(TableLayout layout) {
		this.layout = layout;
		JPanel panel = getPanel();
		panel.setLayout(layout);
	}

	/**
	 * Returns the {@link TableLayout} the the container is using.
	 */
	public TableLayout getLayout() {
		return layout;
	}

	/**
	 * Returns the {@link FieldType} that the container is enclosing.
	 */
	public FieldType getPanelField() {
		return field;
	}

	/**
	 * Returns the {@link JPanel} container that we are using.
	 */
	public JPanel getPanel() {
		return (JPanel) getAWTComponent();
	}

	@Override
	public void setWidth(double width) {
		JPanel panel = getPanel();
		layout.setColumn(0, width);
		layout.layoutContainer(panel);
		panel.repaint();
	}

	@Override
	public void setName(String name) {
		super.setName("panel-" + name);
		field.setName(name);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		field.setEnabled(enabled);
	}

	/**
	 * Set the text of the tool-tip for the field.
	 */
	public void setToolTipText(String text) {
		field.setToolTipText(text);
	}

	/**
	 * Show or hide the tool-tip for the field.
	 */
	public void setShowToolTip(boolean show) {
		if (show) {
			showToolTip();
		} else {
			hideToolTip();
		}
	}

	private void showToolTip() {
		int id = 0;
		long when = 0;
		int modifiers = 0;
		int x = 0;
		int y = 0;
		int clickCount = 0;
		ToolTipManager.sharedInstance().mouseMoved(
				new MouseEvent(field, id, when, modifiers, x, y, clickCount,
						false));
	}

	private void hideToolTip() {
		int id = 0;
		long when = 0;
		int modifiers = 0;
		int x = 0;
		int y = 0;
		int clickCount = 0;
		ToolTipManager.sharedInstance().mouseExited(
				new MouseEvent(field, id, when, modifiers, x, y, clickCount,
						false));
	}

}