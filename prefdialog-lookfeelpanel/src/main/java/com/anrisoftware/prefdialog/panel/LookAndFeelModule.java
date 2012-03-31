package com.anrisoftware.prefdialog.panel;

import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.anrisoftware.prefdialog.panel.api.Appearance;
import com.anrisoftware.prefdialog.panel.api.LookAndFeelFont;
import com.google.inject.AbstractModule;

/**
 * Binds the buttons group field classes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class LookAndFeelModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(LookAndFeelInfoListItem.class).toInstance(
				new LookAndFeelInfoListItem(new LookAndFeelInfo("Metal",
						MetalLookAndFeel.class.getName())));
		bind(LookAndFeelFont.class).to(LookAndFeelFontImpl.class);
		bind(Appearance.class).to(AppearanceImpl.class);
	}

}
