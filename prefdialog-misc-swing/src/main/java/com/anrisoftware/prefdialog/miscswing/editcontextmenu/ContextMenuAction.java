package com.anrisoftware.prefdialog.miscswing.editcontextmenu;

import javax.swing.text.JTextComponent;

/**
 * Base interface of the context menu action.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.1
 */
interface ContextMenuAction {

    void setTextField(JTextComponent textField);

}
