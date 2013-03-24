package com.anrisoftware.prefdialog.miscswing.docks.api;

public interface LayoutListener {

	void layoutSaved(LayoutWorkerEvent event);

	void layoutLoaded(LayoutWorkerEvent layoutWorkerEvent);

	void layoutError(LayoutWorkerErrorEvent event);

}
