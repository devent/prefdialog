/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-panel.
 *
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.verticalpanel;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.anrisoftware.prefdialog.annotations.FieldAnnotation;

/**
 * Vertical preferences panel. Each child is layout from top to bottom in one
 * panel.
 * 
 * <p>
 * <h2>Examples</h2>
 * <p>
 * a) create panel for the children:
 * 
 * <pre>
 * class ChildABean {
 * 
 * 	&#064;FieldComponent
 * 	&#064;TextField
 * 	public String childName = &quot;Child A&quot;
 * 
 * 	&#064;FieldComponent
 * 	&#064;TextField
 * 	public String childTitle;
 * }
 * 
 * class ChildBBean {
 * 
 * 	&#064;FieldComponent
 * 	&#064;TextField
 * 	public String childName = &quot;Child B&quot;
 * 
 * 	&#064;FieldComponent
 * 	&#064;TextField
 * 	public String childTitle;
 * }
 * 
 * &#064;FieldComponent
 * &#064;VerticalPreferencesPanel
 * public Object panelTab;
 * 	
 * &#064;FieldComponent
 * &#064;Child
 * public ChildABean childA;
 * 
 * &#064;FieldComponent
 * &#064;Child
 * public ChildBBean childB;
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface VerticalPreferencesPanel {
}
