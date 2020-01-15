package ch.g_7.graphite.draw;

import ch.g_7.util.resource.Resource;

public abstract class BasicDrawable extends Resource implements Drawable {

	private DrawContext drawContext;

	@Override
	public DrawContext draw() {
		drawContext.clear();
		draw(drawContext);
		return drawContext;
	}

	@Override
	public final void initDrawContext(DrawContext drawContext) {
		this.drawContext = drawContext;
		draw(drawContext);
	}
	
	public abstract void draw(DrawContext drawContext);
	
	@Override
	public void update(float deltaMillis) {}


	protected void doInit() {}
	
	protected void doClose() {}
	
	

}
