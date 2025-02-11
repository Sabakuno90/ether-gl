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

package ch.fhnw.ether.formats.obj;

import ch.fhnw.ether.image.Frame;
import ch.fhnw.ether.scene.mesh.material.Texture;
import ch.fhnw.util.color.RGB;

final class Material {
	private final String name;
	private RGB    Ka;
	private RGB    Kd;
	private RGB    Ks;
	private float  shininess;
	private Frame  texture;
	private String textureName;

	public Material(String name) {
		this.name = name;
		Ka = new RGB(1, 1, 1);
		Kd = new RGB(1, 1, 1);
		Ks = new RGB(0.5f, 0.5f, 0.5f);
		shininess = 0;
	}

	public String getName() {
		return name;
	}

	public RGB getKa() {
		return Ka;
	}

	public void setKa(RGB ka) {
		Ka = ka;
	}

	public RGB getKd() {
		return Kd;
	}

	public void setKd(RGB kd) {
		Kd = kd;
	}

	public RGB getKs() {
		return Ks;
	}

	public void setKs(RGB ks) {
		Ks = ks;
	}

	public float getShininess() {
		return shininess;
	}

	public void setShininess(float s) {
		shininess = s;
	}

	public Texture getTexture() {
		return texture.getTexture();
	}

	public void setTexture(Frame texture) {
		this.texture = texture;
	}

	public String getTextureName() {
		return textureName;
	}
	
	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}
}
