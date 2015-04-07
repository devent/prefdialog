/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.table;

import static com.anrisoftware.prefdialog.fields.table.AbstractTableFieldLogger._.editor_set;
import static com.anrisoftware.prefdialog.fields.table.AbstractTableFieldLogger._.editor_type_null;
import static com.anrisoftware.prefdialog.fields.table.AbstractTableFieldLogger._.model_null;
import static com.anrisoftware.prefdialog.fields.table.AbstractTableFieldLogger._.model_set;
import static com.anrisoftware.prefdialog.fields.table.AbstractTableFieldLogger._.renderer_field_null;
import static com.anrisoftware.prefdialog.fields.table.AbstractTableFieldLogger._.renderer_set;
import static com.anrisoftware.prefdialog.fields.table.AbstractTableFieldLogger._.renderer_type_null;
import static org.apache.commons.lang3.Validate.notNull;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link AbstractTableField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("rawtypes")
class AbstractTableFieldLogger extends AbstractLogger {

    enum _ {

        field("field"),

        renderer_set("Renderer {} for column type {} set for {}."),

        model_set("Model {} set for {}."),

        model_null("Model cannot be null for %s."),

        error_custom_renderer_message("Error set custom renderer for %s"),

        error_custom_renderer("Error set custom renderer"),

        error_custom_model_message("Error set custom model for %s"),

        error_custom_model("Error set custom model"),

        editor_set("Editor {} for column type {} set for {}."),

        renderer_type_null("Column type cannot be null for %s."),

        editor_type_null("Column type cannot be null for %s."),

        renderer_field_null("Renderer field '%s' cannot be null for %s.");

        private String name;

        private _(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Creates logger for {@link AbstractTableField}.
     */
    AbstractTableFieldLogger() {
        super(AbstractTableField.class);
    }

    void checkModel(AbstractTableField field, TableModel model) {
        notNull(model, model_null.toString(), field);
    }

    void modelSet(AbstractTableField field, TableModel model) {
        debug(model_set, model, field);
    }

    void checkRenderer(AbstractTableField field, Class<?> type) {
        notNull(type, renderer_type_null.toString(), field);
    }

    void rendererSet(AbstractTableField field, Class<?> type,
            TableCellRenderer renderer) {
        debug(renderer_set, renderer, type, field);
    }

    void checkEditor(AbstractTableField field, Class<?> type) {
        notNull(type, editor_type_null.toString(), field);
    }

    void editorSet(AbstractTableField field, Class<?> type,
            TableCellEditor editor) {
        debug(editor_set, editor, type, field);
    }

    void checkRenderer(AbstractTableField field, Object renderer, String name) {
        notNull(renderer, renderer_field_null.toString(), name, field);
    }
}
