package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dock;

import java.io.Serializable;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.event.CDockableStateListener;
import bibliothek.gui.dock.common.event.CFocusListener;
import bibliothek.gui.dock.common.intern.CDockable;
import bibliothek.gui.dock.common.mode.ExtendedMode;

import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;

/**
 * Implements view dockable listeners.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractViewDockWindow implements ViewDockWindow,
        Serializable {

    private transient EventListenerSupport<ChangeListener> changeSupport;

    private transient CDockableStateListener stateListener;

    private transient CFocusListener focusListener;

    private transient DefaultSingleCDockable dockable;

    private String title;

    private boolean visible;

    private boolean focus;

    protected AbstractViewDockWindow() {
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
        this.dockable = (DefaultSingleCDockable) dock;
        dockable.addCDockableStateListener(stateListener);
        dockable.addFocusListener(focusListener);
    }

    @Override
    public DefaultSingleCDockable getDockable() {
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
        this.visible = visible;
        changeSupport.fire().stateChanged(new ChangeEvent(this));
    }

    private void updateFocus(boolean focus) {
        this.focus = focus;
        changeSupport.fire().stateChanged(new ChangeEvent(this));
    }
}
