/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.undomanager;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import javax.inject.Inject;
import javax.swing.DefaultListModel;
import javax.swing.undo.UndoableEdit;

import org.apache.commons.lang3.StringUtils;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Listens to project resource change and adds undoable edits to the undo
 * manager.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
public abstract class AbstractValueListener<ValueType, ProjectType> implements
        UndoableValue<ValueType> {

    @Inject
    private UndoableValueEditFactory undoableValueEditFactory;

    private ValueType resource;

    private DefaultListModel<ValueType> model;

    private UndoableEdit undoManager;

    private int index;

    private ProjectType project;

    public void setValueResource(int index, ValueType res) {
        this.index = index;
        this.resource = res;
    }

    public ValueType getValueResource() {
        return resource;
    }

    public int getValueIndex() {
        return index;
    }

    @SuppressWarnings("unchecked")
    public void setProject(ProjectType project) {
        this.project = project;
        this.model = (DefaultListModel<ValueType>) project;
        setValueResource(-1, null);
    }

    public DefaultListModel<ValueType> getModel() {
        return model;
    }

    public ProjectType getProject() {
        return project;
    }

    public void setUndoManager(UndoableEdit manager) {
        this.undoManager = manager;
    }

    public UndoableEdit getUndoManager() {
        return undoManager;
    }

    @Override
    @OnAwt
    public void redoValue(int index, ValueType newValue) {
        model.setElementAt(newValue, index);
        setSelectedResource(newValue);
        redoFieldValue(newValue);
    }

    @Override
    @OnAwt
    public void redoValue(ValueType newValue) {
        redoFieldValue(newValue);
    }

    @Override
    @OnAwt
    public void undoValue(int index, ValueType oldValue) {
        model.setElementAt(oldValue, index);
        setSelectedResource(oldValue);
        undoFieldValue(oldValue);
    }

    @Override
    @OnAwt
    public void undoValue(ValueType oldValue) {
        undoFieldValue(oldValue);
    }

    @OnAwt
    protected void addEdit(ValueType oldres, ValueType res) {
        model.setElementAt(res, index);
        setSelectedResource(res);
        undoManager.addEdit(undoableValueEditFactory.create(this, index,
                oldres, res));
    }

    protected void setSelectedResource(ValueType res) {
    }

    @OnAwt
    protected abstract void redoFieldValue(ValueType newValue);

    @OnAwt
    protected abstract void undoFieldValue(ValueType oldValue);

    /**
     * Checks if the specified object equals to the {@code old} object.
     */
    protected final boolean isValueEquals(Object value, Object old) {
        if (value == old) {
            return true;
        }
        if (value == null) {
            return false;
        }
        return value.equals(old);
    }

    /**
     * Checks if the specified string equals to the {@code old} string.
     */
    protected final boolean isStringEquals(Object value, String old) {
        if (value == null && old == null) {
            return true;
        }
        String a = null;
        if (value != null) {
            a = value.toString();
        }
        if (isEmpty(a) && isEmpty(old)) {
            return true;
        }
        return StringUtils.equals(a, old);
    }

}
