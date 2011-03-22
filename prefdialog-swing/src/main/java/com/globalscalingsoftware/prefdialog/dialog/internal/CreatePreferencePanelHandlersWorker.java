package com.globalscalingsoftware.prefdialog.dialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globalscalingsoftware.prefdialog.InputChangedCallback;
import com.globalscalingsoftware.prefdialog.PreferencePanelHandler;
import com.globalscalingsoftware.prefdialog.PreferencePanelHandlerFactory;
import com.globalscalingsoftware.prefdialog.dialog.internal.PreferencePanelsCollection.PreferencePanelsCollectionFactory;
import com.globalscalingsoftware.prefdialog.reflection.AnnotationDiscoveryCallback;
import com.globalscalingsoftware.prefdialog.reflection.AnnotationDiscoveryFactory;
import com.globalscalingsoftware.prefdialog.reflection.AnnotationFilterFactory;
import com.globalscalingsoftware.prefdialog.reflection.internal.AnnotationFilter;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.internal.Maps;
import com.google.inject.name.Named;

public class CreatePreferencePanelHandlersWorker {

	public interface CreatePreferencePanelHandlersWorkerFactory {
		CreatePreferencePanelHandlersWorker create(
				@Assisted Object preferences,
				@Assisted InputChangedCallback inputChangedCallback);
	}

	private final Logger l = LoggerFactory
			.getLogger(CreatePreferencePanelHandlersWorker.class);

	private final PreferencePanelHandlerFactory preferencePanelHandlerFactory;

	private final Map<Object, PreferencePanelHandler> panelHandlers;

	private final Map<Object, TreeNode[]> treeNodes;

	private final DefaultMutableTreeNode rootNode;

	private final Object preferences;

	private PreferencePanelHandler firstPreferencePanelHandler;

	private final AnnotationFilter annotationFilter;

	private final PreferencePanelsCollectionFactory preferencePanelsCollectionFactory;

	private PreferencePanelsCollection preferencePanelsCollection;

	private final InputChangedCallback inputChangedCallback;

	@Inject
	CreatePreferencePanelHandlersWorker(
			AnnotationFilterFactory annotationFilterFactory,
			AnnotationDiscoveryFactory annotationDiscoveryFactory,
			@Named("childAnnotations") Collection<Class<? extends Annotation>> childAnnotations,
			PreferencePanelsCollectionFactory preferencePanelsCollectionFactory,
			PreferencePanelHandlerFactory preferencePanelHandlerFactory,
			@Assisted Object preferences,
			@Assisted InputChangedCallback inputChangedCallback) {
		this.preferencePanelHandlerFactory = preferencePanelHandlerFactory;
		this.annotationFilter = annotationFilterFactory
				.create(childAnnotations);
		this.preferencePanelsCollectionFactory = preferencePanelsCollectionFactory;
		this.panelHandlers = Maps.newHashMap();
		this.treeNodes = Maps.newHashMap();
		this.rootNode = new DefaultMutableTreeNode();
		this.preferences = preferences;
		this.firstPreferencePanelHandler = null;
		this.inputChangedCallback = inputChangedCallback;
		annotationDiscoveryFactory.create(annotationFilter, preferences,
				new AnnotationDiscoveryCallback() {

					@Override
					public void fieldAnnotationDiscovered(Field field,
							Object value, Annotation a) {
						CreatePreferencePanelHandlersWorker.this
								.fieldAnnotationDiscovered(field, value, a);
					}
				}).discoverAnnotations();
	}

	private void fieldAnnotationDiscovered(Field field, Object value,
			Annotation a) {
		createPreferencePanelHandler(preferences, value.toString());
		createNodePath(value);
	}

	private void createPreferencePanelHandler(Object preferences,
			String panelName) {
		PreferencePanelHandler handler = preferencePanelHandlerFactory.create(
				preferences, panelName).createPanel();
		setFirstPreferencePanelHandler(handler);
		l.debug("New preference panel handler created for {} panel in preferences {}.",
				panelName, preferences);
		panelHandlers.put(handler.getPreferences(), handler);
		handler.setInputChangedCallback(new InputChangedCallback() {

			@Override
			public void inputChanged(Object source) {
				inputChangedCallback.inputChanged(source);
			}
		});
		preferencePanelsCollection = preferencePanelsCollectionFactory.create(
				panelHandlers, handler, treeNodes, rootNode);
	}

	private void setFirstPreferencePanelHandler(PreferencePanelHandler handler) {
		if (firstPreferencePanelHandler == null) {
			firstPreferencePanelHandler = handler;
		}
	}

	private void createNodePath(Object value) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(value);
		rootNode.add(node);
		TreeNode[] path = node.getPath();
		treeNodes.put(value, path);
		l.debug("New node path {} created for {}.", path, value);
	}

	public PreferencePanelHandler getFirstPreferencePanelHandler() {
		return firstPreferencePanelHandler;
	}

	public PreferencePanelsCollection getPreferencePanelsCollection() {
		return preferencePanelsCollection;
	}
}
