/*
 * Copyright (c) 2013 - 2014 FHNW & ETH Zurich (Stefan Muller Arisona & Simon Schubiger)
 * Copyright (c) 2013 - 2014 Stefan Muller Arisona & Simon Schubiger
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *  Neither the name of FHNW / ETH Zurich nor the names of its contributors may
 *   be used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package ch.fhnw.ether.render;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import javax.media.opengl.GL3;

import ch.fhnw.ether.render.attribute.IArrayAttributeProvider;
import ch.fhnw.ether.render.attribute.IUniformAttributeProvider;
import ch.fhnw.ether.render.attribute.IAttribute.ISuppliers;
import ch.fhnw.ether.render.shader.IShader;
import ch.fhnw.ether.view.IView;

public abstract class AbstractRenderer implements IRenderer, IUniformAttributeProvider {

	private final Renderables renderables = new Renderables();

	@Override
	public IRenderable createRenderable(Pass pass, IShader shader, IUniformAttributeProvider uniforms, IArrayAttributeProvider... providers) {
		return createRenderable(pass, EnumSet.noneOf(Flag.class), shader, uniforms, Arrays.asList(providers));
	}

	@Override
	public IRenderable createRenderable(Pass pass, IShader shader, IUniformAttributeProvider uniforms, List<? extends IArrayAttributeProvider> providers) {
		return createRenderable(pass, EnumSet.noneOf(Flag.class), shader, uniforms, providers);
	}

	@Override
	public IRenderable createRenderable(Pass pass, EnumSet<Flag> flags, IShader shader, IUniformAttributeProvider uniforms, IArrayAttributeProvider... providers) {
		return createRenderable(pass, flags, shader, uniforms, Arrays.asList(providers));
	}

	@Override
	public IRenderable createRenderable(Pass pass, EnumSet<Flag> flags, IShader shader, IUniformAttributeProvider uniforms, List<? extends IArrayAttributeProvider> providers) {
		IUniformAttributeProvider composed = new IUniformAttributeProvider() {
			@Override
			public void getAttributeSuppliers(ISuppliers dst) {
				if(uniforms != null) uniforms.getAttributeSuppliers(dst);
				AbstractRenderer.this.getAttributeSuppliers(dst);
			}
		};
		return new Renderable(pass, flags, shader, composed, providers);
	}

	@Override
	public void addRenderables(IRenderable renderable) {
		this.renderables.add(renderable);
	}

	@Override
	public void addRenderables(IRenderable... renderables) {
		addRenderables(Arrays.asList(renderables));
	}
	
	@Override
	public void addRenderables(List<IRenderable> renderables) {
		this.renderables.add(renderables);
	}

	@Override
	public void removeRenderables(IRenderable renderable) {
		this.renderables.remove(renderable);
	}

	@Override
	public void removeRenderables(IRenderable... renderables) {
		removeRenderables(Arrays.asList(renderables));
	}
	
	@Override
	public void removeRenderables(List<IRenderable> renderables) {
		this.renderables.remove(renderables);
	}

	protected void update(GL3 gl) {
		this.renderables.update(gl);
		
	}

	protected void renderPass(GL3 gl, IView view, RenderState state, Pass pass) {
		this.renderables.render(gl, view, state, pass);
	}
}
