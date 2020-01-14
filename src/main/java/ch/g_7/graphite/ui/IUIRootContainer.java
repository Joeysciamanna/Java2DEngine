package ch.g_7.graphite.ui;

import ch.g_7.graphite.core.window.ResizeListner;
import ch.g_7.graphite.node.Updatable;
import ch.g_7.graphite.ui.util.MouseManager;

public interface IUIRootContainer extends IUIContainer, ResizeListner, Updatable {

	void add(IUIPanel panel);
	
	void remove(IUIPanel panel);
	
	void clear();
	
	MouseManager getMouseManager();

}
