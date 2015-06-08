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
/**
 * Extends the Swing Action to pull the action's properties from the resources.
 *
 <h2>Resource Actions<a name="Resource_Actions"></a></h2>
 <ul>
 <li><tt>com.anrisoftware.prefdialog.miscswing.resourcesaction.AbstractResourcesAction</tt>
 <p>Sets the action name and mnemonic from text resource. It is expected that the user extends the <tt>AbstractResourcesAction</tt> in her own action class and sets the texts and images resources. The name of the action is used to look-up the resources for the action text, short text, mnemonic, accelerator, small and large icon images.</p></li>
 <li><tt>com.anrisoftware.prefdialog.miscswing.resourcesaction.LabelResourcesAction</tt>
 <p>Decorates a <tt>JLabel</tt> with the retrieved resources. The label text, icon and mnemonic can be set from the action.</p></li></ul>
 <p>The following suffixes are used to look-up the resources for the action, attached to the action name.</p>
 <ul>
 <li><tt>name</tt> the action name as the action text;</li>
 <li><tt>name_short</tt> the short text of the action;</li>
 <li><tt>name_mnemonic</tt> the mnemonic of the action;</li>
 <li><tt>name_accelerator</tt> the accelerator key of the action;</li>
 <li><tt>name_small_icon</tt> the small icon image of the action;</li>
 <li><tt>name_large_icon</tt> the large icon image of the action;</li></ul></div>
 <div class="section">
 <h2>Examples<a name="Examples"></a></h2>
 <p>For example, if the action is named &quot;cancel&quot; then the following resource should be used:</p>
 <p><tt>AppResources.properties</tt></p>
 <div>
 <pre>cancel = com/anrisoftware/app/resources/texts/en/cancel_action.txt
 cancel_short = com/anrisoftware/app/resources/texts/en/cancel_action_short.txt
 cancel_mnemonic = com/anrisoftware/app/resources/texts/en/cancel_action_mnemonic.txt
 cancel_accelerator = com/anrisoftware/app/resources/texts/en/cancel_action_accelerator.txt
 cancel_small_icon = com/anrisoftware/app/resources/images/%s/cancel_action_small.png
 cancel_large_icon = com/anrisoftware/app/resources/images/%s/cancel_action_large.png</pre></div>
 <div>
 <pre>@SuppressWarnings(&quot;serial&quot;)
 class CancelAction extends AbstractResourcesAction {

 private SimpleDialog dialog;

 CancelAction() {
 super(CANCEL_ACTION_NAME);
 }

 public void setDialog(SimpleDialog dialog) {
 this.dialog = dialog;
 }

 @Override
 public void actionPerformed(ActionEvent evt) {
 try {
 dialog.setStatus(CANCELED);
 dialog.closeDialog();
 } catch (PropertyVetoException e) {
 }
 }

 }</pre></div>
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.0
 * @version 3.1
 */
package com.anrisoftware.prefdialog.miscswing.resourcesaction;

