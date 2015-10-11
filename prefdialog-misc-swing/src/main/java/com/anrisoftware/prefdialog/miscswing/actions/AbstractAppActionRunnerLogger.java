package com.anrisoftware.prefdialog.miscswing.actions;

import static com.anrisoftware.prefdialog.miscswing.actions.AbstractAppActionLogger._.error;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.anrisoftware.prefdialog.miscswing.logwindowdock.AbstractLogWindowLogger;
import com.anrisoftware.prefdialog.miscswing.logwindowdock.ErrorCategory;
import com.anrisoftware.prefdialog.miscswing.logwindowdock.ErrorNode;
import com.anrisoftware.prefdialog.miscswing.logwindowdock.ErrorNodeFactory;

/**
 * Logging for {@link AbstractAppActionRunner}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
final class AbstractAppActionRunnerLogger extends AbstractLogWindowLogger {

    /**
     * Sets the context of the logger to {@link AbstractAppActionRunner}.
     */
    public AbstractAppActionRunnerLogger() {
        super(AbstractAppActionRunner.class);
    }

    @Inject
    private ErrorNodeFactory errorNodeFactory;

    ErrorNode errorAction(ErrorCategory category, Throwable e) {
        logException(e, error, e.getLocalizedMessage());
        ErrorNode node = errorNodeFactory.create(category);
        node.setException(e);
        return node;
    }

}
