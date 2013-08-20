/*
 * Copyright 2013 Japplis.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.anrisoftware.prefdialog.miscswing.spreadsheet.table;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

/**
 * Removes the border around the editor component.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class SheetTable extends JTable {

	public SheetTable(TableModel model) {
		super(model);
	}

	@Override
	public Component prepareEditor(TableCellEditor editor, int row, int column) {
		Component comp = super.prepareEditor(editor, row, column);
		if (comp instanceof JComponent) {
			JComponent jcomp = (JComponent) comp;
			jcomp.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		}
		return comp;
	}

}
