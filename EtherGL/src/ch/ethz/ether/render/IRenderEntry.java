package ch.ethz.ether.render;

import javax.media.opengl.GL3;

import ch.ethz.ether.geom.Mat4;
import ch.ethz.ether.gl.Program;
import ch.ethz.ether.render.util.FloatList;
import ch.ethz.ether.view.IView;

public interface IRenderEntry {

    void dispose(GL3 gl);

    void update(GL3 gl, IRenderer renderer, IView view, FloatList data);

    void render(GL3 gl, IRenderer renderer, IView view, Mat4 projMatrix, Mat4 viewMatrix);

    IRenderGroup getGroup();

    Program getProgram();
}
