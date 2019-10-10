package ch.g_7.graphite.base.ui.layout;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;

import ch.g_7.graphite.base.ui.IUIPanel;
import ch.g_7.graphite.base.ui.UIPanel;

public class GridAreaMergeLayoutPanel extends UIPanel {

	private Vector2i gridSize;

	private final IUIPanel[][] childs;
	private List<IUIPanel> childList;

	public GridAreaMergeLayoutPanel(int colums, int rows) {
		this.gridSize = new Vector2i(colums, rows);
		this.childs = new IUIPanel[colums][rows];
		this.childList = new ArrayList<>();
	}

	public void add(IUIPanel panel, int x, int y, int width, int height) {
		for (int i = x; i < x + width; i++) {
			for (int j = y; j < y + height; j++) {
				childs[i][j] = panel;
			}
		}
		childList.add(panel);
		panel.setFather(this);
		place(panel, x, y, width, height);
	}

	private void place(IUIPanel panel, int x, int y, int width, int height) {
		panel.getWidth().reset().addPF((float) 100 / gridSize.x * width);
		panel.getHeight().reset().addPF((float) 100 / gridSize.y * height);

		panel.getX().reset().addPF((float) 100 / gridSize.x * x);
		panel.getY().reset().addPF((float) 100 / gridSize.y * y);

		requestRecalculation(this);
	}

	public void remove(int x, int y) {
		IUIPanel panel = null;
		for (int i = x; i < childs.length; i++) {
			for (int j = y; j < childs[i].length; j++) {
				if (panel == null || panel.equals(childs[i][j])) {
					panel = childs[i][j];
					childs[i][j] = null;
				}

			}
		}
		childList.remove(panel);
	}

	@Override
	public List<IUIPanel> getChilds() {
		return childList;
	}

}
