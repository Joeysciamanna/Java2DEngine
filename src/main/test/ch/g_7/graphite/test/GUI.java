package ch.g_7.graphite.test;

import ch.g_7.graphite.base.texture.Texture;
import ch.g_7.graphite.core.Application;
import ch.g_7.graphite.node.RenderCluster;
import ch.g_7.graphite.ui.UIButton;
import ch.g_7.graphite.ui.UIRootContainer;
import ch.g_7.graphite.ui.layout.CenterLayoutPanel;
import ch.g_7.graphite.ui.layout.ListLayoutPanel;
import ch.g_7.graphite.ui.layout.SpaceSharingLayoutPanel;
import ch.g_7.graphite.util.Color;
import ch.g_7.util.task.SecureRunner;

public class GUI extends Application {

	public GUI(String name) {
		super(name);
	}

	public static void main(String[] args) {
		new GUI("GUI Test").start();
	}
	
	@Override
	protected void init() {
		Texture square1 = new SecureRunner<Void, Texture>(() -> new Texture(
				"C:\\Users\\Joey Sciamanna\\git\\Graphite\\src\\main\\resources\\textures\\square.png")).get();

		UIRootContainer mainMenu = new UIRootContainer(getWindow());
		getDimension().addObj(mainMenu, RenderCluster.UI);
		
		
		SpaceSharingLayoutPanel sharingLayoutPanel = new SpaceSharingLayoutPanel();
		mainMenu.add(sharingLayoutPanel);
		
		ListLayoutPanel optionPanel = new ListLayoutPanel(ListLayoutPanel.X_AXIS);
		optionPanel.setColor(Color.getColor(0, 255, 0));
		optionPanel.getPreferedHeight().reset().addPF(5).addPixel(20);
		optionPanel.getPlaceHolder().reset().addPixel(5);
		sharingLayoutPanel.setMainPanel(optionPanel, SpaceSharingLayoutPanel.BUTTOM);

		UIButton settingsButton = new UIButton();
		settingsButton.getPreferedWidth().reset().add(settingsButton.getMaxHeight());
		settingsButton.setColor(Color.getColor(100, 100, 100));
		optionPanel.add(settingsButton);
		
		UIButton wikiButton = new UIButton();
		wikiButton.getPreferedWidth().reset().add(wikiButton.getMaxHeight());
		wikiButton.setColor(Color.getColor(100, 100, 100));
		optionPanel.add(wikiButton);
		
		CenterLayoutPanel buttonPanel = new CenterLayoutPanel();
		buttonPanel.setColor(Color.getColor(0, 255, 255));
		sharingLayoutPanel.setAdjustablePanel(buttonPanel);
		
		ListLayoutPanel buttonsPanel = new ListLayoutPanel(ListLayoutPanel.Y_AXIS);
		buttonsPanel.setColor(Color.getColor(0, 0, 255));
		buttonsPanel.getPlaceHolder().addPixel(20).addPF(5);
		buttonsPanel.getPreferedWidth().reset().addPixel(50).addPF(33);
		buttonsPanel.getPreferedHeight().reset().addPixel(50).addPF(33);
		buttonPanel.set(buttonsPanel);
		
		UIButton startGame = new UIButton();
		startGame.getPreferedHeight().addPixel(5).addPF(10);
		startGame.setColor(Color.getColor(255, 0, 0));
		buttonsPanel.add(startGame);
		
		
		
		mainMenu.setVisible(true);
		
		mainMenu.recalculate();
		
		getWindow().setVisible(true);
		getWindow().setSize(500, 500);
	
		
	}
}
