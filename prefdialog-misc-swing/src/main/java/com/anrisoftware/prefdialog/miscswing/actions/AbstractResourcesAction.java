/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.actions;

import static java.lang.String.format;

import java.util.MissingResourceException;

import javax.inject.Inject;
import javax.swing.AbstractAction;

import com.anrisoftware.globalpom.mnemonic.Accelerator;
import com.anrisoftware.globalpom.mnemonic.AcceleratorFactory;
import com.anrisoftware.globalpom.mnemonic.Mnemonic;
import com.anrisoftware.globalpom.mnemonic.MnemonicFactory;
import com.anrisoftware.resources.texts.api.TextResource;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Sets the action name and mnemonic from text resource.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractResourcesAction extends AbstractAction {

	private static final String MNEMONIC_SUFFIX = "mnemonic";

	private static final String ACCELERATOR_SUFFIX = "accelerator";

	private final String name;

	private AbstractResourcesActionLogger log;

	private Texts texts;

	private MnemonicFactory mnemonicFactory;

	private AcceleratorFactory acceleratorFactory;

	/**
	 * Sets the name of the action. The name is used as the text resource name
	 * for the action title and mnemonic.
	 * 
	 * @param name
	 *            the action name {@link String}.
	 */
	protected AbstractResourcesAction(String name) {
		this.name = name;
	}

	@Inject
	void setAbstractResourcesActionLogger(AbstractResourcesActionLogger logger) {
		this.log = logger;
	}

	/**
	 * Injects the mnemonic factory.
	 * 
	 * @param mnemonicFactory
	 *            the {@link MnemonicFactory}.
	 */
	@Inject
	public void setMnemonicFactory(MnemonicFactory mnemonicFactory) {
		this.mnemonicFactory = mnemonicFactory;
	}

	/**
	 * Injects the accelerator factory.
	 * 
	 * @param acceleratorFactory
	 *            the {@link AcceleratorFactory}.
	 */
	@Inject
	public void setMnemonicFactory(AcceleratorFactory acceleratorFactory) {
		this.acceleratorFactory = acceleratorFactory;
	}

	/**
	 * Sets the texts resource for the action. The texts should contain the
	 * following resources:
	 * 
	 * <ul>
	 * <li>name
	 * <li>name_mnemonic
	 * <li>name_accelerator
	 * </ul>
	 * 
	 * @param texts
	 *            the {@link Texts}.
	 */
	public void setTexts(Texts texts) {
		this.texts = texts;
		updateTitle();
		updateMnemonic();
		updateAcc();
	}

	private void updateTitle() {
		putValue(NAME, texts.getResource(name).getText());
	}

	private void updateMnemonic() {
		TextResource resource = loadResourceSave(MNEMONIC_SUFFIX);
		if (resource == null) {
			log.noResource(this, MNEMONIC_SUFFIX);
			return;
		}
		Mnemonic m = mnemonicFactory.create(resource.getText());
		Integer code = m.getMnemonic();
		if (code != null) {
			putValue(MNEMONIC_KEY, code);
		}
		int index = m.getMnemonicIndex();
		if (index != -1) {
			putValue(DISPLAYED_MNEMONIC_INDEX_KEY, index);
		}
	}

	private void updateAcc() {
		TextResource resource = loadResourceSave(ACCELERATOR_SUFFIX);
		if (resource == null) {
			log.noResource(this, ACCELERATOR_SUFFIX);
			return;
		}
		Accelerator acc = acceleratorFactory.create(resource.getText());
		putValue(ACCELERATOR_KEY, acc.getAccelerator());
	}

	private TextResource loadResourceSave(String suffix) {
		try {
			return texts.getResource(format("%s_%s", name, suffix));
		} catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * Returns the name of the action.
	 * 
	 * @return the {@link String} name.
	 */
	public String getName() {
		return name;
	}
}
