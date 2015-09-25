/*
 * Copyright 2011-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of gscalculator-suite-main. All rights reserved.
 */
package com.anrisoftware.prefdialog.miscswing.undomanager;

/**
 * Value editing that can be undo.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public interface UndoableValue<ValyeType> {

    void redoValue(int index, ValyeType newValue);

    void redoValue(ValyeType newValue);

    void undoValue(int index, ValyeType oldValue);

    void undoValue(ValyeType oldValue);

}
