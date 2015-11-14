/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.indicestextfield;

import java.text.ParseException;

import javax.swing.JFormattedTextField;

import com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingFormattedTextField;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Custom text field to enter column or row indices. The indices are entered as
 * <ul>
 * <li><code>""</code>, empty;
 * <li><code>"n"</code>, one index;
 * <li><code>"{n1,n2,n3}"</code>, indices set;
 * <li><code>"[n,k]", "]n,k]", "(n,k]", </code>etc. indices range as stated in
 * ISO 31-11;
 * </ul>
 * <p>
 * See also <a href="https://en.wikipedia.org/wiki/Interval_%28mathematics%29#
 * Including_or_excluding_endpoints">Including or excluding endpoints
 * [wikipedia.org]</a>
 * </p>
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
@SuppressWarnings("serial")
public class IndicesTextField extends ValidatingFormattedTextField {

    /**
     * Factory to create the column or row indices text field.
     *
     * @author Erwin Müller, erwin.mueller@deventm.de
     * @since 3.5
     */
    public interface IndicesTextFieldFactory {

        /**
         * Creates the column or row indices text field.
         *
         * @return the {@link JFormattedTextField}.
         */
        JFormattedTextField create();

    }

    /**
     * Installs the column or row indices text field.
     *
     * @see IndicesTextFieldFactory
     *
     * @author Erwin Müller, erwin.mueller@deventm.de
     * @since 3.5
     */
    public static class IndicesTextFieldModule extends AbstractModule {

        @Override
        protected void configure() {
            install(new FactoryModuleBuilder().implement(
                    JFormattedTextField.class, IndicesTextField.class).build(
                    IndicesTextFieldFactory.class));
        }

    }

    /**
     * @see #create()
     */
    public static JFormattedTextField createIndicesTextField() {
        return create();
    }

    /**
     * Creates the indices text field.
     *
     * @return the indices text field {@link JFormattedTextField}.
     */
    public static JFormattedTextField create() {
        JFormattedTextField field = Instance.createFactory().create();
        return field;
    }

    private static class Instance {

        private static Injector injector = Guice
                .createInjector(new IndicesTextFieldModule());

        public static IndicesTextFieldFactory createFactory() {
            return injector.getInstance(IndicesTextFieldFactory.class);
        }

    }

    @Inject
    IndicesTextField(IndicesTextFieldFormatterFactory formatterFactory) {
        super(formatterFactory);
        setupField();
    }

    @Override
    public ArrayRange getValue() {
        Object value = super.getValue();
        if (value instanceof String) {
            try {
                String str = (String) value;
                return (ArrayRange) getFormatterFactory().getFormatter(this)
                        .stringToValue(str);
            } catch (ParseException e) {
                return null;
            }
        }
        return (ArrayRange) value;
    }

    private void setupField() {
        setValue("");
    }

}
