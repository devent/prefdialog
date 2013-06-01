package com.anrisoftware.prefdialog.miscswing.lists;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Using rubber band selection in a swing list.
 * 
 * @author <a
 *         href=http://java-swing-tips.blogspot.de/2008/10/using-rubber-band-selection
 *         -in-jlist.html">2008/10/09 using rubber band selection in JList
 *         Posted by TERAI Atsuhiro </a>
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class RubberBandingList<E> extends JList<E> {

	private static final AlphaComposite ALPHA = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, 0.1f);

	private Path2D rubberBand;

	private Color selectionColor;

	private Point srcPoint;

	private MouseAdapter rubberBandMouseListener;

	private MouseMotionAdapter rubberBandMouseMotionListener;

	/**
	 * @see JList#JList()
	 */
	public RubberBandingList() {
		super();
		init();
	}

	/**
	 * @see JList#JList(Object[])
	 */
	public RubberBandingList(E[] listData) {
		super(listData);
		init();
	}

	/**
	 * @see JList#JList(ListModel)
	 */
	public RubberBandingList(ListModel<E> dataModel) {
		super(dataModel);
		init();
	}

	/**
	 * @see JList#JList(Vector)
	 */
	public RubberBandingList(Vector<? extends E> listData) {
		super(listData);
		init();
	}

	private void init() {
		this.srcPoint = new Point();
		this.selectionColor = createSelectionColor();
		this.rubberBandMouseListener = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int index = locationToIndex(e.getPoint());
				Rectangle rect = getCellBounds(index, index);
				if (!rect.contains(e.getPoint())) {
					clearSelection();
					getSelectionModel().setAnchorSelectionIndex(-1);
					getSelectionModel().setLeadSelectionIndex(-1);
					setFocusable(false);
				} else {
					setFocusable(true);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setFocusable(true);
				rubberBand = null;
				repaint();
			}
		};
		this.rubberBandMouseMotionListener = new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setFocusable(true);
				if (rubberBand == null) {
					srcPoint.setLocation(e.getPoint());
				}
				Point destPoint = e.getPoint();
				rubberBand = new Path2D.Double();
				rubberBand.moveTo(srcPoint.x, srcPoint.y);
				rubberBand.lineTo(destPoint.x, srcPoint.y);
				rubberBand.lineTo(destPoint.x, destPoint.y);
				rubberBand.lineTo(srcPoint.x, destPoint.y);
				rubberBand.closePath();
				setSelectedIndices(getIntersectsIdices(rubberBand));
				repaint();
			}
		};
		addMouseListener(rubberBandMouseListener);
		addMouseMotionListener(rubberBandMouseMotionListener);
	}

	private int[] getIntersectsIdices(Shape shape) {
		ListModel<E> model = getModel();
		List<Integer> list = new ArrayList<Integer>(model.getSize());
		for (int i = 0; i < model.getSize(); i++) {
			Rectangle r = getCellBounds(i, i);
			if (shape.intersects(r)) {
				list.add(i);
			}
		}
		int[] indices = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			indices[i] = list.get(i);
		}
		return indices;
	}

	@Override
	public void setSelectionBackground(Color selectionBackground) {
		super.setSelectionBackground(selectionBackground);
		this.selectionColor = createSelectionColor();
	}

	private Color createSelectionColor() {
		Color c = getSelectionBackground();
		int r = c.getRed(), g = c.getGreen(), b = c.getBlue();
		return r > g ? r > b ? new Color(r, 0, 0) : new Color(0, 0, b)
				: g > b ? new Color(0, g, 0) : new Color(0, 0, b);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (rubberBand != null) {
			drawSelectionRubberBand((Graphics2D) g);
		}
	}

	private void drawSelectionRubberBand(Graphics2D g) {
		g.setPaint(getSelectionBackground());
		g.draw(rubberBand);
		g.setComposite(ALPHA);
		g.setPaint(selectionColor);
		g.fill(rubberBand);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.toString();
	}
}
