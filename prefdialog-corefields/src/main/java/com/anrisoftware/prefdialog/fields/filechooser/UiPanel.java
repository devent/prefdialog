package com.anrisoftware.prefdialog.fields.filechooser;

import static com.anrisoftware.prefdialog.fields.filechooser.FileChooserField.FILE_FIELD_NAME;
import static com.anrisoftware.prefdialog.fields.filechooser.FileChooserField.FILE_FIELD_PANEL_NAME;
import static com.anrisoftware.prefdialog.fields.filechooser.FileChooserField.OPEN_FILE_CHOOSER_NAME;
import static java.lang.String.format;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
class UiPanel extends JPanel {

	private JComponent fileField;

	private final JButton openFileChooser;

	private String name;

	/**
	 * Create the panel.
	 */
	UiPanel() {
		setLayout(new MigLayout("", "[grow][]", "[]"));

		fileField = new JTextField();
		fileField.setName(FileChooserField.FILE_FIELD_NAME);
		add(fileField, "cell 0 0,growx");

		openFileChooser = new JButton("...");
		openFileChooser.setName(OPEN_FILE_CHOOSER_NAME);
		add(openFileChooser, "cell 1 0");

	}

	@Override
	public void setName(String name) {
		super.setName(format("%s-%s", name, FILE_FIELD_PANEL_NAME));
		this.name = name;
		fileField.setName(format("%s-%s", name, FILE_FIELD_NAME));
		openFileChooser.setName(format("%s-%s", name, OPEN_FILE_CHOOSER_NAME));
	}

	public void setFileField(JComponent field) {
		field.setName(format("%s-%s", name, FILE_FIELD_NAME));
		remove(fileField);
		add(field, "cell 0 0,growx");
		this.fileField = field;
	}

	public JComponent getFileField() {
		return fileField;
	}

	public JButton getOpenFileChooser() {
		return openFileChooser;
	}
}
