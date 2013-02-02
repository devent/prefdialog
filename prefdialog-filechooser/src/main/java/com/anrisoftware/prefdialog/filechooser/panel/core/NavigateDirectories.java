package com.anrisoftware.prefdialog.filechooser.panel.core;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static sun.swing.SwingUtilities2.loc2IndexFileList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;

import sun.awt.shell.ShellFolder;

import com.anrisoftware.prefdialog.filechooser.panel.api.DirectoyModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;

/**
 * Navigate in the file chooser directories per mouse and keyboard.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("restriction")
class NavigateDirectories implements MouseListener, KeyListener {

	private FileSystemView systemView;

	private JList<?> list;

	private FileModel fileModel;

	private FileChooserPanel fileChooser;

	private DirectoyModel directoyModel;

	NavigateDirectories() {
	}

	public void setList(JList<?> list) {
		JList<?> oldValue = this.list;
		this.list = list;
		if (oldValue != null) {
			oldValue.removeMouseListener(this);
			oldValue.removeKeyListener(this);
		}
		list.addMouseListener(this);
		list.addKeyListener(this);
	}

	public void setFileSystemView(FileSystemView view) {
		this.systemView = view;
	}

	public void setFileModel(FileModel model) {
		this.fileModel = model;
	}

	public void setFileChooser(FileChooserPanel fileChooser) {
		this.fileChooser = fileChooser;
	}

	public void setDirectoryModel(DirectoyModel model) {
		this.directoyModel = model;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		if (isLeftMouseButton(evt)) {
			if (evt.getClickCount() % 2 == 0) {
				int index = loc2IndexFileList(list, evt.getPoint());
				if (index >= 0) {
					File f = (File) list.getModel().getElementAt(index);
					try {
						f = ShellFolder.getNormalizedFile(f);
					} catch (IOException ex) {
					}
					if (systemView.isTraversable(f)) {
						list.clearSelection();
						directoyModel.setCurrentDirectory(f);
						fileModel.setDirectory(f);
					} else {
						fileChooser.approveAction();
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
