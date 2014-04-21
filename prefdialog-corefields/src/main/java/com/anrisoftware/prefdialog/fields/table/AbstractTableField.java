/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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

import java.awt.Component;
import java.lang.annotation.Annotation;

import javax.inject.Inject;
import javax.swing.ComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClass;
import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClassFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanAccess;
import com.anrisoftware.globalpom.reflection.beans.BeanAccessFactory;
import com.anrisoftware.prefdialog.annotations.Table;
import com.anrisoftware.prefdialog.annotations.TypedTableCellEditor;
import com.anrisoftware.prefdialog.annotations.TypedTableCellRenderer;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.core.AbstractTitleScrollField;

/**
 * Implements the table field.
 * 
 * @see Table
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractTableField<ComponentType extends JTable> extends
        AbstractTitleScrollField<ComponentType> {

    private static final Class<Table> TABLE_ANNOTATION_CLASS = Table.class;

    private static final String RENDERERS_ELEMENT = "renderers";

    private static final String RENDERER_CLASSES_ELEMENT = "rendererClasses";

    private static final String MODEL_ELEMENT = "model";

    private static final String EDITOR_ELEMENT = "editor";

    private AbstractTableFieldLogger log;

    private final Class<? extends Annotation> annotationType;

    private transient AnnotationClass<?> annotationClass;

    private BeanAccessFactory beanAccessFactory;

    private AnnotationAccess annotationAccess;

    /**
     * @see AbstractTitleField#AbstractTitleField(Component, Object, String)
     * 
     * @param annotationType
     *            the annotation {@link Class} type of the specific combo box
     *            field. The annotation must have the following attributes:
     *            <ul>
     *            <li>model</li>
     *            <li>modelClass</li>
     *            <li>renderers</li>
     *            <li>rendererClasses</li>
     *            <li>editor</li>
     *            <li>editorClass</li>
     *            </ul>
     */
    protected AbstractTableField(Class<? extends Annotation> annotationType,
            ComponentType component, Object parentObject, String fieldName) {
        super(component, parentObject, fieldName);
        this.annotationType = annotationType;
    }

    /**
     * Reads the attributes of the annotation and setups the field.
     */
    @Inject
    void setupField(AbstractTableFieldLogger logger,
            AnnotationClassFactory classFactory,
            AnnotationAccessFactory annotationAccessFactory,
            BeanAccessFactory beanAccessFactory) {
        this.log = logger;
        this.annotationClass = createAnnotationClass(classFactory);
        this.beanAccessFactory = beanAccessFactory;
        this.annotationAccess = createAnnotationAccess(annotationAccessFactory);
        setupModel();
        setupRenderersFields();
        setupRenderersClasses();
        setupEditor();
    }

    private AnnotationClass<?> createAnnotationClass(
            AnnotationClassFactory classFactory) {
        return classFactory.create(getParentObject(), annotationType,
                getAccessibleObject());
    }

    private AnnotationAccess createAnnotationAccess(
            AnnotationAccessFactory annotationAccessFactory) {
        return annotationAccessFactory.create(TABLE_ANNOTATION_CLASS,
                getBeanAccess().getGettterObject());
    }

    private void setupModel() {
        TableModel model = (TableModel) annotationClass.forAttribute(
                MODEL_ELEMENT).build();
        setModel(model);
    }

    private void setupEditor() {
        TypedTableCellEditor editor = (TypedTableCellEditor) annotationClass
                .forAttribute(EDITOR_ELEMENT).build();
        if (editor != null) {
            setDefaultEditor(editor.getType(), editor.getEditor());
        }
    }

    private void setupRenderersFields() {
        String[] renderers = annotationAccess.getValue(RENDERERS_ELEMENT);
        for (String name : renderers) {
            BeanAccess access = beanAccessFactory.create(name,
                    getParentObject());
            TypedTableCellRenderer renderer = access.getValue();
            log.checkRenderer(this, renderer, name);
            setDefaultRenderer(renderer.getType(), renderer.getRenderer());
        }
    }

    private void setupRenderersClasses() {
        Class<? extends TypedTableCellRenderer>[] renderers = annotationAccess
                .getValue(RENDERER_CLASSES_ELEMENT);
        for (Class<? extends TypedTableCellRenderer> type : renderers) {
            TypedTableCellRenderer renderer = getBeanFactory().create(type);
            setDefaultRenderer(renderer.getType(), renderer.getRenderer());
        }
    }

    /**
     * Sets the specified model for the combo box field.
     * 
     * @param model
     *            the {@link ComboBoxModel}.
     * 
     * @throws NullPointerException
     *             if the specified model is {@code null}.
     * 
     * @see JTable#setModel(TableModel)
     */
    public void setModel(TableModel model) {
        log.checkModel(this, model);
        getComponent().setModel(model);
        log.modelSet(this, model);
    }

    /**
     * Returns the model of the table field.
     * 
     * @return the {@link TableModel}.
     * 
     * @see JTable#getModel()
     */
    public TableModel getModel() {
        return getComponent().getModel();
    }

    /**
     * Sets the specified renderer for the table field.
     * 
     * @param type
     *            the column {@link Class} type.
     * 
     * @param renderer
     *            the {@link TableCellRenderer} or {@code null}.
     * 
     * @throws NullPointerException
     *             if the specified type is {@code null}.
     * 
     * @see JTable#setDefaultRenderer(Class, TableCellRenderer)
     */
    public void setDefaultRenderer(Class<?> type, TableCellRenderer renderer) {
        log.checkRenderer(this, type);
        getComponent().setDefaultRenderer(type, renderer);
        log.rendererSet(this, type, renderer);
    }

    /**
     * Returns the renderer of the table field.
     * 
     * @param type
     *            the column {@link Class} type.
     * 
     * @return the {@link TableCellRenderer}.
     * 
     * @see JTable#getDefaultRenderer(Class)
     */
    public TableCellRenderer getDefaultRenderer(Class<?> type) {
        return getComponent().getDefaultRenderer(type);
    }

    /**
     * Sets the specified editor for the table field.
     * 
     * @param type
     *            the column {@link Class} type.
     * 
     * @param editor
     *            the {@link TableCellEditor} editor or {@code null}.
     * 
     * @throws NullPointerException
     *             if the specified type is {@code null}.
     */
    public void setDefaultEditor(Class<?> type, TableCellEditor editor) {
        log.checkEditor(this, type);
        getComponent().setDefaultEditor(type, editor);
        log.editorSet(this, type, editor);
    }

    /**
     * Returns the editor of the combo box field.
     * 
     * @param type
     *            the column {@link Class} type.
     * 
     * @return the {@link TableCellEditor}.
     */
    public TableCellEditor getEditor(Class<?> type) {
        return getComponent().getDefaultEditor(type);
    }

}
