package com.anrisoftware.prefdialog.miscswing.tables.spreadsheetnavigation;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

import com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingFormattedTextComponent;

/**
 * Navigation panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
class UiDataPanel extends JPanel {
	private final JFormattedTextField currentColumnField;
	private final JFormattedTextField maximumColumnField;
	private final JLabel separatorLabel;
	private final JLabel columnSeparatorLabel;
	private final JLabel rowSeparatorLabel;
	private final JFormattedTextField currentRowField;
	private final JFormattedTextField maximumRowField;
	private final ValidatingFormattedTextComponent<JFormattedTextField> currentColumnValidatingField;
	private final ValidatingFormattedTextComponent<JFormattedTextField> currentRowValidatingField;

	/**
	 * Create the panel.
	 */
	public UiDataPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new MigLayout("gap 0 0 fill ins 0",
				"0[grow][10%][][10%][][10%][][10%]0", "0[]0"));

		currentColumnField = new JFormattedTextField();
		currentColumnField.setHorizontalAlignment(SwingConstants.TRAILING);
		currentColumnField.setName("currentColumnField");
		currentColumnField.setText("0");
		add(currentColumnField, "cell 1 0,growx");
		currentColumnField.setColumns(10);
		currentColumnValidatingField = new ValidatingFormattedTextComponent<JFormattedTextField>(
				currentColumnField);

		columnSeparatorLabel = new JLabel("-");
		columnSeparatorLabel.setName("columnSeparatorLabel");
		add(columnSeparatorLabel, "cell 2 0,alignx trailing");

		maximumColumnField = new JFormattedTextField();
		maximumColumnField.setName("maximumColumnField");
		maximumColumnField.setEditable(false);
		maximumColumnField.setFocusable(false);
		maximumColumnField.setText("6");
		add(maximumColumnField, "cell 3 0,growx");
		maximumColumnField.setColumns(10);

		separatorLabel = new JLabel(":");
		separatorLabel.setName("separatorLabel");
		add(separatorLabel, "cell 4 0,alignx trailing");

		currentRowField = new JFormattedTextField();
		currentRowField.setHorizontalAlignment(SwingConstants.TRAILING);
		currentRowField.setName("currentRowField");
		currentRowField.setText("0");
		add(currentRowField, "cell 5 0,growx");
		currentRowValidatingField = new ValidatingFormattedTextComponent<JFormattedTextField>(
				currentRowField);

		rowSeparatorLabel = new JLabel("-");
		rowSeparatorLabel.setName("rowSeparatorLabel");
		add(rowSeparatorLabel, "cell 6 0");

		maximumRowField = new JFormattedTextField();
		maximumRowField.setEditable(false);
		maximumRowField.setName("maximumRowField");
		maximumRowField.setText("40");
		maximumRowField.setFocusable(false);
		add(maximumRowField, "cell 7 0,growx");

	}

	public JFormattedTextField getCurrentColumnField() {
		return currentColumnField;
	}

	public ValidatingFormattedTextComponent<JFormattedTextField> getCurrentColumnValidatingField() {
		return currentColumnValidatingField;
	}

	public JFormattedTextField getMaximumColumnField() {
		return maximumColumnField;
	}

	public JFormattedTextField getCurrentRowField() {
		return currentRowField;
	}

	public ValidatingFormattedTextComponent<JFormattedTextField> getCurrentRowValidatingField() {
		return currentRowValidatingField;
	}

	public JFormattedTextField getMaximumRowField() {
		return maximumRowField;
	}
}
