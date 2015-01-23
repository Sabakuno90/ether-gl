package ch.fhnw.ether.render.gl;

import javax.media.opengl.GL3;

public class FrameBuffer {
	private GLObject fbo;

	public FrameBuffer() {	
	}
		
	public int checkStatus(GL3 gl) {
		return gl.glCheckFramebufferStatus(GL3.GL_DRAW_FRAMEBUFFER);
	}
	
	public void bind(GL3 gl) {
		gl.glBindFramebuffer(GL3.GL_DRAW_FRAMEBUFFER, fbo.id());
	}
	
	public static void unbind(GL3 gl) {
		gl.glBindFramebuffer(GL3.GL_DRAW_FRAMEBUFFER, 0);
	}
	
	public void attach(GL3 gl, int attachment, RenderBuffer buffer) {
		gl.glFramebufferRenderbuffer(GL3.GL_FRAMEBUFFER, attachment, GL3.GL_RENDERBUFFER, buffer.id());
	}
	
	public void detach(GL3 gl, int attachment) {
		gl.glFramebufferRenderbuffer(GL3.GL_FRAMEBUFFER, attachment, GL3.GL_RENDERBUFFER, 0);
	}
	
	// to add: clear & blit
}
