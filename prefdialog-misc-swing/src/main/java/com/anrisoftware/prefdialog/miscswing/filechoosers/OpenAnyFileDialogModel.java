/*
 * Copyright 2011-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of gscalculator-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.filechoosers;

import javax.swing.JFileChooser;

/**
 * Open any file dialog model.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
@SuppressWarnings("serial")
public class OpenAnyFileDialogModel extends OpenFileDialogModel {

    @Override
    public void setFileChooser(JFileChooser chooser) {
        super.setFileChooser(chooser);
        chooser.setAcceptAllFileFilterUsed(false);
    }

}
