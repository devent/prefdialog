package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dock;

import java.io.Serializable;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.DefaultMultipleCDockable;
import bibliothek.gui.dock.common.event.CDockableStateListener;
import bibliothek.gui.dock.common.event.CFocusListener;
import bibliothek.gui.dock.common.intern.CDockable;
import bibliothek.gui.dock.common.mode.ExtendedMode;

import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;

/**
 * Implements editor dockable listeners.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractEditorDockWindow implements EditorDockWindow,
        Serializable {

    private transient EventListenerSupport<ChangeListener> changeSupport;

    private transient CDockableStateListener stateListener;

    private transient CFocusListener focusListener;

    private transient DefaultMultipleCDockable dockable;

    private String title;

    private boolean visible;

    private boolean focus;

    protected AbstractEditorDockWindow() {
        readResolve();
    }

    public final Object readResolve() {
        this.changeSupport = new EventListenerSupport<ChangeListener>(
                ChangeListener.class);
        this.stateListener = new CDockableStateListener() {

            @Override
            public void visibilityChanged(CDockable d) {
                updateVisible(dockable.isVisible());
            }

            @Override
            public void extendedModeChanged(CDockable d, ExtendedMode mode) {
            }
        };
        this.focusListener = new CFocusListener() {

            @Override
            public void focusLost(CDockable dockable) {
                updateFocus(false);
            }

            @Override
            public void focusGained(CDockable dockable) {
                updateFocus(true);
            }
        };
        return this;
    }

    @Override
    public void setDockable(Object dock) {
        this.dockable = (DefaultMultipleCDockable) dock;
        this.visible = dockable.isVisible();
        this.focus = dockable.isVisible();
        dockable.addCDockableStateListener(stateListener);
        dockable.addFocusListener(focusListener);
    }

    @Override
    public DefaultMultipleCDockable getDockable() {
        return dockable;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public boolean isFocus() {
        return focus;
    }

    @Override
    public void addStateChangedListener(ChangeListener listener) {
        changeSupport.addListener(listener);
    }

    @Override
    public void removeStateChangedListener(ChangeListener listener) {
        changeSupport.removeListener(listener);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(title).toString();
    }

    private void updateVisible(boolean visible) {
        boolean oldValue = this.visible;
        this.visible = visible;
        if (oldValue != visible) {
            changeSupport.fire().stateChanged(new ChangeEvent(this));
        }
    }

    private void updateFocus(boolean focus) {
        boolean oldValue = this.focus;
        this.focus = focus;
        if (oldValue != visible) {
            changeSupport.fire().stateChanged(new ChangeEvent(this));
        }
    }
}
