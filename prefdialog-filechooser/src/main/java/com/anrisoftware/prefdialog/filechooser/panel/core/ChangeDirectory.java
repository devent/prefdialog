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

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;

@SuppressWarnings("restriction")
class ChangeDirectory implements MouseListener, KeyListener {

	private FileSystemView systemView;

	private JList<?> list;

	private FileChooserPanel fileChooser;

	ChangeDirectory() {
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

	public void setFileChooserPanel(FileChooserPanel panel) {
		this.fileChooser = panel;
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
						fileChooser.getFileModel().setDirectory(f);
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
