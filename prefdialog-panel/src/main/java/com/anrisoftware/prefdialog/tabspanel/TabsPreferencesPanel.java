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
package com.anrisoftware.prefdialog.tabspanel;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.anrisoftware.prefdialog.annotations.FieldAnnotation;

/**
 * Tabbed preferences panel. For each child creates a tab in the tabbed pane.
 * The value of the field will be the value of the field in the selected tab.
 * 
 * <p>
 * <h2>Examples</h2>
 * <p>
 * a) create tabbed pane for the children:
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
 * 	public String childTitle
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
 * 	public String childTitle
 * }
 * 
 * &#064;FieldComponent
 * &#064;TabsPreferencesPanel
 * public Object panelTab
 * 	
 * &#064;FieldComponent
 * &#064;Child
 * public ChildABean childA
 * 
 * &#064;FieldComponent
 * &#064;Child
 * public ChildBBean childB
 * </pre>
 * 
 * b) to set the tab title, tool-tip text and icon a tabs group renderer can be
 * set. The field name with an instance of the custom renderer can be set. If no
 * instance is set, the renderer must have a public standard constructor
 * available for instantiation, the new instance is set in the parent object.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;TabsPreferencesPanel(renderer = "customRenderer")
 * public Object panelTab
 * 	
 * &#064;FieldComponent
 * &#064;Child
 * public ChildABean childA
 * 
 * &#064;FieldComponent
 * &#064;Child
 * public ChildBBean childB
 * 
 * public TabsRenderer customRenderer;
 * </pre>
 * 
 * c) sets the custom renderer class. The custom renderer must have a public
 * standard constructor available for instantiation.
 * 
 * <pre>
 * &#064;FieldComponent
 * &#064;TabsPreferencesPanel(rendererClass = CustomTabsRenderer.class)
 * public Object panelTab
 * 	
 * &#064;FieldComponent
 * &#064;Child
 * public ChildABean childA
 * 
 * &#064;FieldComponent
 * &#064;Child
 * public ChildBBean childB
 * </pre>
 * 
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Target({ FIELD, METHOD })
@Retention(RUNTIME)
@FieldAnnotation
@Documented
public @interface TabsPreferencesPanel {

	/**
	 * The name of the field name to use for the custom {@link TabsRenderer}.
	 * The renderer will return the title, tool-tip text and the icon for each
	 * tab. Defaults to an empty name which means no field is set.
	 */
	String renderer() default "";

	/**
	 * The custom {@link TabsRenderer} to use with this tabs group. The renderer
	 * will return the title, tool-tip text and the icon for each tab. The
	 * renderer must have the default constructor available for instantiation.
	 * Defaults to the {@link DefaultTabsRenderer}.
	 */
	Class<? extends TabsRenderer>[] rendererClass() default DefaultTabsRenderer.class;
}
