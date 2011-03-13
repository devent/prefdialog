package com.globalscalingsoftware.prefdialog;

public class InputChangedDelegateCallback implements InputChangedCallback {

	private InputChangedCallback delegateCallback;

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
