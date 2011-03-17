package com.globalscalingsoftware.prefdialog.swingutils.actions.internal;

import com.globalscalingsoftware.prefdialog.InputChangedCallback;

public class InputChangedDelegateCallback implements InputChangedCallback {

	private InputChangedCallback delegateCallback;

	public interface InputChangedDelegateCallbackFactory {
		InputChangedDelegateCallback create();
	}

	public InputChangedDelegateCallback() {
		this.delegateCallback = new InputChangedCallback() {

			@Override
			public void inputChanged(Object source) {
			}
		};
	}

	public void setDelegateCallback(InputChangedCallback delegateCallback) {
		this.delegateCallback = delegateCallback;
	}

	@Override
	public void inputChanged(Object source) {
		delegateCallback.inputChanged(source);
	}

}
