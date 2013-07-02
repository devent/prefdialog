package com.anrisoftware.prefdialog.miscswing.actions;

import static java.lang.String.format;

import javax.inject.Inject;
import javax.swing.AbstractAction;

import com.anrisoftware.globalpom.mnemonic.Mnemonic;
import com.anrisoftware.globalpom.mnemonic.MnemonicFactory;
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

	private final String name;

	private Texts texts;

	private MnemonicFactory mnemonicFactory;

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
	 * Sets the texts resource for the action. The texts should contain the
	 * following resources:
	 * 
	 * <ul>
	 * <li>name
	 * <li>name_mnemonic
	 * </ul>
	 * 
	 * @param texts
	 *            the {@link Texts}.
	 */
	public void setTexts(Texts texts) {
		this.texts = texts;
		updateTitle();
		updateMnemonic();
	}

	private void updateTitle() {
		putValue(NAME, texts.getResource(name).getText());
	}

	private void updateMnemonic() {
		Mnemonic m = mnemonicFactory.create(texts.getResource(
				format("%s_%s", name, MNEMONIC_SUFFIX)).getText());
		Integer code = m.getMnemonic();
		if (code != null) {
			putValue(MNEMONIC_KEY, code);
		}
		int index = m.getMnemonicIndex();
		if (index != -1) {
			putValue(DISPLAYED_MNEMONIC_INDEX_KEY, index);
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
