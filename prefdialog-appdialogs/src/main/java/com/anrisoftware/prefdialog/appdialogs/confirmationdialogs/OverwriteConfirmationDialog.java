/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-appdialogs.
 *
 * prefdialog-appdialogs is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-appdialogs is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-appdialogs. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.appdialogs.confirmationdialogs;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status;
import com.anrisoftware.resources.images.api.Images;
import com.anrisoftware.resources.images.api.ImagesFactory;
import com.anrisoftware.resources.texts.api.Texts;
import com.anrisoftware.resources.texts.api.TextsFactory;

/**
 * Overwrite confirmation dialog. The dialog is usually shown if the user
 * selects an already existing file to save a resource.
 * <p>
 * <h2>AWT Thread</h2>
 * Objects of that class should be used in the AWT event dispatch thread.
 * </p>
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.2
 */
@OnAwt
public class OverwriteConfirmationDialog extends ConfirmationDialog {

    /**
     * @see OverwriteConfirmationDialogFactory#create()
     */
    OverwriteConfirmationDialog() {
    }

    @Inject
    void setTextsFactory(TextsFactory factory) {
        Texts texts = factory.create(ConfirmationDialog.class.getSimpleName());
        setApproveActionName("overwrite_action");
        setCancelActionName("cancel_action");
        setShowRestoreButton(false);
        setTexts(texts);
    }

    @Inject
    void setImagesFactory(ImagesFactory factory) {
        Images images = factory
                .create(ConfirmationDialog.class.getSimpleName());
        setImages(images);
    }

    /**
     * Returns if the user closed the dialog by the Overwrite action.
     *
     * @return {@code true} if the user closed the dialog by the Overwrite
     *         action.
     */
    public boolean isOverwrite() {
        return getStatus() == Status.APPROVED;
    }

    /**
     * Returns if the user closed the dialog by the Cancel action.
     *
     * @return {@code true} if the user closed the dialog by the Cancel action.
     */
    public boolean isCancel() {
        return getStatus() == Status.CANCELED;
    }

}
