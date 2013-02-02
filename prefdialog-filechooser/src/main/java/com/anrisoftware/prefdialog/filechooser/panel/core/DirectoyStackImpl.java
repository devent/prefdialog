package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.io.File;
import java.util.Stack;

import com.anrisoftware.prefdialog.filechooser.panel.api.DirectoyModel;

class DirectoyStackImpl implements DirectoyModel {

	private final Stack<File> currentDirectories;

	private final Stack<File> lastDirectories;

	DirectoyStackImpl() {
		this.currentDirectories = new Stack<File>();
		this.lastDirectories = new Stack<File>();
	}

	@Override
	public void setCurrentDirectory(File currentDirectory) {
		if (!currentDirectories.contains(currentDirectory)) {
			currentDirectories.push(currentDirectory);
		}
	}

	@Override
	public File getCurrentDirectory() {
		return currentDirectories.peek();
	}

	@Override
	public boolean canGoBack() {
		return currentDirectories.size() > 1;
	}

	@Override
	public File goBack() {
		File dir = currentDirectories.pop();
		lastDirectories.push(dir);
		return getCurrentDirectory();
	}

	@Override
	public boolean canGoForward() {
		return lastDirectories.size() > 0;
	}

	@Override
	public File goForward() {
		File dir = lastDirectories.pop();
		currentDirectories.push(dir);
		return dir;
	}

	@Override
	public boolean canGoUp() {
		File dir = getCurrentDirectory();
		return dir.getParentFile() != null;
	}

	@Override
	public File goUp() {
		File dir = getCurrentDirectory();
		File parent = dir.getParentFile();
		currentDirectories.push(parent);
		return parent;
	}

}
