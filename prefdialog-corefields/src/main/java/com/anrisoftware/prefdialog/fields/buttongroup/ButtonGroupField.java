package com.anrisoftware.prefdialog.fields.buttongroup;

import java.awt.Container;
import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.JPanel;

import org.apache.commons.lang3.ArrayUtils;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanFactory;
import com.anrisoftware.prefdialog.annotations.ButtonGroup;
import com.anrisoftware.prefdialog.annotations.HorizontalAlignment;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.google.inject.assistedinject.Assisted;

/**
 * Field with a group of buttons.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class ButtonGroupField extends
		AbstractTitleField<ButtonsGroupPanel, Container> {

	private static final String HORIZONTAL_ALIGNMENT_ELEMENT = "horizontalAlignment";

	private static final Class<? extends Annotation> ANNOTATION_CLASS = ButtonGroup.class;

	private final ButtonGroupFieldLogger log;

	private final ButtonsGroupPanel buttonsGroupPanel;

	private final ButtonsRowPanel buttonsRowPanel;

	private AnnotationAccess buttonGroupAnnotation;

	/**
	 * @see ButtonGroupFieldFactory#create(Object, String)
	 */
	@Inject
	ButtonGroupField(ButtonGroupFieldLogger logger,
			ButtonsGroupPanel buttonsGroupPanel,
			ButtonsRowPanel buttonsRowPanel, @Assisted Object parentObject,
			@Assisted String fieldName) {
		super(buttonsGroupPanel, new JPanel(), parentObject, fieldName);
		this.log = logger;
		this.buttonsGroupPanel = buttonsGroupPanel;
		this.buttonsRowPanel = buttonsRowPanel;
		setup();
	}

	private void setup() {
		buttonsGroupPanel.setButtonsRowPanel(buttonsRowPanel);
	}

	@Inject
	void setBeanAccessFactory(BeanAccessFactory beanAccessFactory,
			BeanFactory beanFactory,
			AnnotationAccessFactory annotationAccessFactory) {
		this.buttonGroupAnnotation = annotationAccessFactory.create(
				ANNOTATION_CLASS, getAccessibleObject());
		setupHorizontalAlignment();
	}

	private void setupHorizontalAlignment() {
		HorizontalAlignment alignment = buttonGroupAnnotation
				.getValue(HORIZONTAL_ALIGNMENT_ELEMENT);
		setHorizontalAlignment(alignment);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		buttonsGroupPanel.setName(name);
		buttonsRowPanel.setName(name);
	}

	@Override
	public void setValue(Object value) throws PropertyVetoException {
		super.setValue(value);
		List<Action> actions = asList(getValue());
		AbstractListModel<Action> model = createModel(actions);
		buttonsRowPanel.setModel(model);
	}

	private AbstractListModel<Action> createModel(final List<Action> actions) {
		return new AbstractListModel<Action>() {

			@Override
			public int getSize() {
				return actions.size();
			}

			@Override
			public Action getElementAt(int i) {
				return actions.get(i);
			}
		};
	}

	@SuppressWarnings("unchecked")
	private List<Action> asList(Object value) {
		if (ArrayUtils.isSameType(value, new Action[0])) {
			return Arrays.asList((Action[]) value);
		} else if (value instanceof Iterable) {
			return createList((Iterable<? extends Action>) value);
		}
		throw log.valueNotList(value);
	}

	private List<Action> createList(Iterable<? extends Action> it) {
		List<Action> list = new ArrayList<Action>();
		for (Action action : it) {
			list.add(action);
		}
		return list;
	}

	/**
	 * Sets the horizontal alignment of the button group.
	 * 
	 * @param alignment
	 *            the {@link HorizontalAlignment}.
	 */
	public void setHorizontalAlignment(HorizontalAlignment alignment) {
		buttonsGroupPanel.setHorizontalAlignment(alignment);
		log.horizontalAlignmentSet(this, alignment);
	}

	/**
	 * Returns the horizontal alignment of the button group.
	 * 
	 * @return the {@link HorizontalAlignment}.
	 */
	public HorizontalAlignment getHorizontalAlignment() {
		return buttonsGroupPanel.getHorizontalAlignment();
	}
}
