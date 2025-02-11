/*
 * Copyright (c) 2013 - 2015 Stefan Muller Arisona, Simon Schubiger, Samuel von Stachelski
 * Copyright (c) 2013 - 2015 FHNW & ETH Zurich
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

package ch.fhnw.ether.scene.mesh.material;

import ch.fhnw.ether.scene.mesh.geometry.IGeometry;
import ch.fhnw.ether.scene.mesh.geometry.IGeometry.Primitive;
import ch.fhnw.util.color.RGBA;

public final class PointMaterial extends AbstractMaterial {
	
	private RGBA color;
	private float size;

	public PointMaterial(RGBA color, float size) {
		this(color, size, false, false);
	}

	public PointMaterial(RGBA color, float size, boolean perVertexColor, boolean perVertexSize) {
		super(material(IMaterial.COLOR, IMaterial.POINT_SIZE),
			  geometry(IGeometry.POSITION_ARRAY, perVertexColor ? IGeometry.COLOR_ARRAY : null, perVertexSize ? IGeometry.POINT_SIZE_ARRAY : null));		
				  
		this.color = color;
		this.size = size;
	}

	public final RGBA getColor() {
		return color;
	}

	public final void setColor(RGBA color) {
		this.color = color;
		updateRequest();
	}

	public final float getSize() {
		return size;
	}

	public final void setSize(float size) {
		this.size = size;
		updateRequest();
	}

	@Override
	public Primitive getType() {
		return Primitive.POINTS;
	}

	@Override
	public Object[] getData() {
		return data(color, size);
	}

	@Override
	public String toString() {
		return super.toString() + "[" + color + ", " + size + "]";
	}
}
