package com.anrisoftware.prefdialog.filechooser.panel.defaults.filemodel;

import static org.apache.commons.io.comparator.DirectoryFileComparator.DIRECTORY_COMPARATOR;
import static org.apache.commons.io.comparator.ExtensionFileComparator.EXTENSION_COMPARATOR;
import static org.apache.commons.io.comparator.ExtensionFileComparator.EXTENSION_REVERSE;
import static org.apache.commons.io.comparator.LastModifiedFileComparator.LASTMODIFIED_COMPARATOR;
import static org.apache.commons.io.comparator.LastModifiedFileComparator.LASTMODIFIED_REVERSE;
import static org.apache.commons.io.comparator.NameFileComparator.NAME_COMPARATOR;
import static org.apache.commons.io.comparator.NameFileComparator.NAME_REVERSE;
import static org.apache.commons.io.comparator.SizeFileComparator.SIZE_COMPARATOR;
import static org.apache.commons.io.comparator.SizeFileComparator.SIZE_REVERSE;

import java.io.File;
import java.util.Comparator;

import org.apache.commons.io.comparator.CompositeFileComparator;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileSort;

class FileSorting {

	private final DefaultFileModel fileModel;

	private Comparator<File> comparator;

	private FileSort sort;

	private boolean descending;

	private boolean folderFirst;

	public FileSorting(DefaultFileModel fileModel) {
		this.fileModel = fileModel;
	}

	public void setFileSort(FileSort sort) {
		this.sort = sort;
		switch (sort) {
		case NAME:
			comparator = getNameComparator();
			break;
		case SIZE:
			comparator = getSizeComparator();
			break;
		case DATE:
			comparator = getLastModifiedComparator();
			break;
		case TYPE:
			comparator = getTypeComparator();
			break;
		}
		fileModel.updateDirectory(comparator);
	}

	public FileSort getFileSort() {
		return sort;
	}

	public void setDescending(boolean b) {
		this.descending = b;
		setFileSort(sort);
	}

	public boolean isDescending() {
		return descending;
	}

	public void setFolderFirst(boolean b) {
		this.folderFirst = b;
		setFileSort(sort);
	}

	public boolean isFolderFirst() {
		return folderFirst;
	}

	public Comparator<File> getComparator() {
		return comparator;
	}

	private Comparator<File> getNameComparator() {
		Comparator<File> comparator = NAME_COMPARATOR;
		if (descending) {
			comparator = NAME_REVERSE;
		}
		return getFileComparator(comparator);
	}

	private Comparator<File> getSizeComparator() {
		Comparator<File> comparator = SIZE_COMPARATOR;
		if (descending) {
			comparator = SIZE_REVERSE;
		}
		return getFileComparator(comparator);
	}

	private Comparator<File> getLastModifiedComparator() {
		Comparator<File> comparator = LASTMODIFIED_COMPARATOR;
		if (descending) {
			comparator = LASTMODIFIED_REVERSE;
		}
		return getFileComparator(comparator);
	}

	private Comparator<File> getTypeComparator() {
		Comparator<File> comparator = EXTENSION_COMPARATOR;
		if (descending) {
			comparator = EXTENSION_REVERSE;
		}
		return getFileComparator(comparator);
	}

	@SuppressWarnings("unchecked")
	private Comparator<File> getFileComparator(Comparator<File> comparator) {
		if (folderFirst) {
			return new CompositeFileComparator(DIRECTORY_COMPARATOR, comparator);
		} else {
			return comparator;
		}
	}

}
