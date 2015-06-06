package com.anrisoftware.prefdialog.appdialogs.registerdialog;

import com.anrisoftware.globalpom.mnemonic.MnemonicModule;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule;
import com.anrisoftware.globalpom.reflection.beans.BeansModule;
import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialogFactory;
import com.anrisoftware.prefdialog.appdialogs.dialog.AppDialogModule;
import com.anrisoftware.prefdialog.appdialogs.dialogheader.DialogHeaderModule;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialogModule;
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the register dialog.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 * @see AppDialogFactory
 */
public class RegisterDialogModule extends AbstractModule {

    /**
     * Returns the register dialog factory.
     *
     * @return the {@link RegisterDialogFactory}.
     */
    public static RegisterDialogFactory getSimpleDialogFactory() {
        return SingletonHolder.dialogFactory;
    }

    private static class SingletonHolder {

        private static final Module[] modules = new Module[] {
                new RegisterDialogModule(), new AppDialogModule(),
                new DialogHeaderModule(), new SimpleDialogModule(),
                new MnemonicModule(), new AnnotationsModule(),
                new BeansModule(), new TextsResourcesDefaultModule() };

        public static final Injector injector = Guice.createInjector(modules);

        public static final RegisterDialogFactory dialogFactory = injector
                .getInstance(RegisterDialogFactory.class);
    }

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(RegisterDialog.class,
                RegisterDialog.class).build(RegisterDialogFactory.class));
    }

}
