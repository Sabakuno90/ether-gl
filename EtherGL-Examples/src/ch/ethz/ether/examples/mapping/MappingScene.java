/*
Copyright (c) 2013, ETH Zurich (Stefan Mueller Arisona, Eva Friedrich)
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, 
  this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.
 * Neither the name of ETH Zurich nor the names of its contributors may be 
  used to endorse or promote products derived from this software without
  specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package ch.ethz.ether.examples.mapping;

import java.awt.event.KeyEvent;

import ch.ethz.ether.mapping.BoxCalibrationModel;
import ch.ethz.ether.mapping.CalibrationTool;
import ch.ethz.ether.mapping.FillTool;
import ch.ethz.ether.render.forward.ForwardRenderer;
import ch.ethz.ether.scene.AbstractScene;
import ch.ethz.ether.scene.AbstractTool;
import ch.ethz.ether.scene.ITool;
import ch.ethz.ether.view.IView;

// XXX light position currently not implemented
public class MappingScene extends AbstractScene {
	private float[] lightPosition = { 10.0f, 6.0f, 8.0f };

	private final ITool defaultTool = new AbstractTool(this) {
		// @formatter:off
		private final String[] help = {
			"Simple Mapping Example (Without Content)",
			"",
			"[1] Default Tool / View",
			"[2] Mapping Calibration",
			"[3] Projector Adjustment",
			"",
			"Use Mouse Buttons + Shift or Mouse Wheel to Navigate"
		};
		// @formatter:on
	};
	
	private final CalibrationTool calibrationTool = new CalibrationTool(this, new BoxCalibrationModel(0.5f, 0.5f, 0.5f, 0.8f, 0.8f));
	private final FillTool fillTool = new FillTool(this);
	
	public MappingScene() {
		super(new ForwardRenderer());
		setLightPosition(lightPosition);
		setCurrentTool(defaultTool);
	}

	public void modelChanged() {
		repaintViews();
	}

	@Override
	public void keyPressed(KeyEvent e, IView view) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_0:
		case KeyEvent.VK_1:
			setCurrentTool(defaultTool);
			break;
		case KeyEvent.VK_2:
			setCurrentTool(calibrationTool);
			break;
		case KeyEvent.VK_3:
			setCurrentTool(fillTool);
			break;
		default:
			super.keyPressed(e, view);
		}
		repaintViews();
	}

	public float[] getLightPosition() {
		return lightPosition.clone();
	}

	public void setLightPosition(float[] position) {
		lightPosition = position;
	}
	
	@Override
	public MappingTriangleModel getModel() {
		return (MappingTriangleModel)super.getModel();
	}
}
