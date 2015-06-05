package com.anrisoftware.prefdialog.appdialogs.dialog;

import com.anrisoftware.globalpom.mnemonic.MnemonicModule;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule;
import com.anrisoftware.globalpom.reflection.beans.BeansModule;
import com.anrisoftware.prefdialog.appdialogs.dialogheader.DialogHeaderModule;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialogModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the application dialog.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 * @see AppDialogFactory
 */
public class AppDialogModule extends AbstractModule {

    /**
     * Returns the application dialog factory.
     *
     * @return the {@link AppDialogFactory}.
     */
    public static AppDialogFactory getSimpleDialogFactory() {
        return SingletonHolder.dialogFactory;
    }

    private static class SingletonHolder {

        private static final Module[] modules = new Module[] {
                new AppDialogModule(), new DialogHeaderModule(),
                new SimpleDialogModule(), new MnemonicModule(),
                new AnnotationsModule(), new BeansModule() };

        public static final Injector injector = Guice.createInjector(modules);

        public static final AppDialogFactory dialogFactory = injector
                .getInstance(AppDialogFactory.class);
    }

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(AppDialog.class,
                AppDialog.class).build(AppDialogFactory.class));
    }

}
