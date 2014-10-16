/*
 * Copyright (c) 2013 - 2014 Stefan Muller Arisona, Simon Schubiger, Samuel von Stachelski
 * Copyright (c) 2013 - 2014 FHNW & ETH Zurich
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

package ch.fhnw.ether.controller.tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.fhnw.ether.camera.DefaultCameraControl;
import ch.fhnw.ether.controller.IController;
import ch.fhnw.ether.render.IRenderable;
import ch.fhnw.ether.render.IRenderer.Pass;
import ch.fhnw.ether.render.attribute.IArrayAttributeProvider;
import ch.fhnw.ether.render.attribute.IAttribute.PrimitiveType;
import ch.fhnw.ether.render.shader.IShader;
import ch.fhnw.ether.render.shader.builtin.LineShader;
import ch.fhnw.ether.scene.mesh.GenericMesh;
import ch.fhnw.ether.scene.mesh.IMesh;
import ch.fhnw.ether.scene.mesh.material.ColorMaterial;
import ch.fhnw.ether.view.IView;
import ch.fhnw.util.color.RGBA;
import ch.fhnw.util.math.Vec3;
import ch.fhnw.util.math.geometry.Primitives;

import com.jogamp.newt.event.MouseEvent;

// FIXME: do not allow createRenderable anymore, go through scene

public class NavigationTool extends AbstractTool {
	public static final RGBA GRID_COLOR = RGBA.GRAY;

	private int button;
	private int mouseX;
	private int mouseY;

	private IRenderable renderable;
	
	// TODO: make grid dynamic/configurable

	public NavigationTool(IController controller) {
		super(controller);
		// XXX hack: currently grid is always enabled
		IMesh gridMesh = makeGrid();
		List<IArrayAttributeProvider> l = Collections.singletonList(gridMesh.getGeometry());
		IShader s = new LineShader(false);
		renderable = controller.getRenderer().createRenderable(Pass.DEPTH, s, gridMesh.getMaterial(), l);
		activate();
	}

	@Override
	public void activate() {
		getController().getRenderer().addRenderables(renderable);
	}

	@Override
	public void deactivate() {
		getController().getRenderer().removeRenderables(renderable);
	}
	
	@Override
	public void mousePressed(MouseEvent e, IView view) {
		mouseX = e.getX();
		mouseY = e.getY();
		button = e.getButton();
		view.repaint();
	}
	
	@Override
	public void mouseMoved(MouseEvent e, IView view) {
		mouseX = e.getX();
		mouseY = e.getY();
		button = e.getButton();
	}

	@Override
	public void mouseDragged(MouseEvent e, IView view) {
		DefaultCameraControl control = new DefaultCameraControl(view.getCamera());
		float dx = e.getX() - mouseX;
		float dy = e.getY() - mouseY;
		float moveFactor = -0.001f * control.getDistance();
		float turnFactor = -0.2f;
		if (button == MouseEvent.BUTTON1) {
			control.addToAzimuth(turnFactor * dx);
			control.addToElevation(turnFactor * dy);
		} else if (button == MouseEvent.BUTTON2 || button == MouseEvent.BUTTON3) {
			control.track(moveFactor * dx, -moveFactor * dy);
		}
		mouseX = e.getX();
		mouseY = e.getY();
		view.refresh();
	}

	@Override
	public void mouseWheelMoved(MouseEvent e, IView view) {
		DefaultCameraControl control = new DefaultCameraControl(view.getCamera());
		float zoomFactor = -0.1f;
		if (e.isControlDown()) {
			control.dolly(e.getRotation()[1] * zoomFactor);
		} else {
			control.addToDistance(e.getRotation()[1] * zoomFactor);
		}
		view.refresh();
	}

	private static GenericMesh makeGrid() {
		GenericMesh mesh = new GenericMesh(PrimitiveType.LINE);
		List<Vec3> lines = new ArrayList<>();

		mesh.setMaterial(new ColorMaterial(RGBA.WHITE));

		int gridNumLines = 12;
		float gridSpacing = 0.1f;

		// add axis lines
		float e = 0.5f * gridSpacing * (gridNumLines + 1);
		Primitives.addLine(lines, -e, 0, e, 0);
		Primitives.addLine(lines, 0, -e, 0, e);

		// add grid lines
		int n = gridNumLines / 2;
		for (int i = 1; i <= n; ++i) {
			Primitives.addLine(lines, i * gridSpacing, -e, i * gridSpacing, e);
			Primitives.addLine(lines, -i * gridSpacing, -e, -i * gridSpacing, e);
			Primitives.addLine(lines, -e, i * gridSpacing, e, i * gridSpacing);
			Primitives.addLine(lines, -e, -i * gridSpacing, e, -i * gridSpacing);
		}

		mesh.setGeometry(Vec3.toArray(lines));
		return mesh;
	}
}
