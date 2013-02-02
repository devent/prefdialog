package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.io.File;
import java.util.Stack;

import com.anrisoftware.prefdialog.filechooser.panel.api.DirectoyStack;

class DirectoyStackImpl implements DirectoyStack {

	private final Stack<File> currentDirectories;

	private final Stack<File> lastDirectories;

	DirectoyStackImpl() {
		this.currentDirectories = new Stack<File>();
		this.lastDirectories = new Stack<File>();
	}

	@Override
	public void setCurrentDirectory(File currentDirectory) {
		currentDirectories.push(currentDirectory);
	}

	@Override
	public File getCurrentDirectory() {
		return currentDirectories.peek();
	}

	@Override
	public File goBack() {
		File dir = currentDirectories.pop();
		lastDirectories.push(dir);
		return dir;
	}

	@Override
	public File goForward() {
		File dir = lastDirectories.pop();
		currentDirectories.push(dir);
		return dir;
	}

	@Override
	public File goUp() {
		File dir = getCurrentDirectory();
		File parent = dir.getParentFile();
		currentDirectories.push(parent);
		return parent;
	}

}
