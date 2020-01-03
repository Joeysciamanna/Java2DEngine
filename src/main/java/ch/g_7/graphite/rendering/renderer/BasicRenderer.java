package ch.g_7.graphite.rendering.renderer;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import java.util.List;

import org.joml.Matrix4f;

import ch.g_7.graphite.base.vao.VAO;
import ch.g_7.graphite.core.Camera;
import ch.g_7.graphite.core.window.Window;
import ch.g_7.graphite.rendering.BasicRenderable;
import ch.g_7.graphite.rendering.Dimension;
import ch.g_7.graphite.rendering.ITransformation;
import ch.g_7.graphite.rendering.Renderable;
import ch.g_7.graphite.rendering.shaderprogram.BasicShaderProgram;

public abstract class BasicRenderer<S extends BasicShaderProgram, R extends Renderable> implements IRenderer<R> {

	protected S shaderProgram;

	public BasicRenderer(S shaderProgram) {
		this.shaderProgram = shaderProgram;
	}


	@Override
	public final void render(List<R> renderClass, Dimension dimension, Window window, Camera camera) {
		
		before(renderClass, dimension, window, camera);
		
		renderAll(renderClass);

		after(renderClass, dimension, window, camera);
	}
	
	
	protected abstract void renderAll(List<R> renderables);

	protected <T extends BasicRenderable> void render(T r, ITransformation<T> transformation) {
		
		Matrix4f modelViewMatrix = transformation.getModelViewMatrix(r);

		shaderProgram.setModelViewMatrix(modelViewMatrix);

		shaderProgram.setColor(r.getColor());
		
		if (r.getTexture() != null) {
			glActiveTexture(GL_TEXTURE0);
			glBindTexture(GL_TEXTURE_2D, r.getTexture().getId());
			shaderProgram.setTextureEnabled(true);
		} else {
			shaderProgram.setTextureEnabled(false);
		}

		VAO vao = r.getMesh().getVAO();
		
		vao.bind();
		glDrawElements(GL_TRIANGLES, r.getMesh().getVerticesCount(), GL_UNSIGNED_INT, 0);
		vao.unbind();
		
	}

	protected void before(List<R> renderClass, Dimension dimension, Window window, Camera camera) {
		
		shaderProgram.bind();
		prepareTransformations(window, camera);
		shaderProgram.setTextureSampler(0);
		
	}

	protected void prepareTransformations(Window window, Camera camera) {};
	
	protected void after(List<R> renderClass, Dimension dimension, Window window, Camera camera) {
		shaderProgram.unbind();
		
	}


	@Override
	public void init() {
		shaderProgram.init();
	}
	
	@Override
	public void close() {
		shaderProgram.close();
	}
	


}
