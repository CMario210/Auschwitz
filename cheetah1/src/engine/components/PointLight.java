/*
 * Copyright 2018 Carlos Rodriguez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package engine.components;

import engine.core.Vector3f;
import engine.rendering.Shader;
import game.Level;

/**
 *
 * @author Carlos Rodriguez
 * @version 1.1
 * @since 2018
 */
public class PointLight extends BaseLight {
	
	private static final int COLOR_DEPTH = 256;

	private Attenuation attenuation;
	private Vector3f 	position;
	private float 		range;
	
	/**
	 * Point of light object instance.
	 * @param color of the light.
	 * @param intensity of the light.
	 * @param atten Attenuation of the light
	 * @param position of the light
	 */
	public PointLight(Vector3f color, float intensity, Attenuation atten, Vector3f position) {
		super(color, intensity);
		this.attenuation = atten;
		this.position = position;
		
		float a = atten.getExponent();
        float b = atten.getLinear();
        float c = atten.getConstant() - COLOR_DEPTH * getIntensity() * getColor().max();
		
		this.range = (float) (-b + Math.sqrt(b * b - 4 * a * c))/(2 * a);
		
		setShader(new Shader("forward-point"));
	}
	
	/**
	 * Returns the attenuation of the point-light.
	 * @return Attenuation
	 */
	public Attenuation getAtten() { return attenuation;}
	
	/**
	 * Sets a new attenuation to the point-light.
	 * @param Attenuation to set
	 */
	public void setAtten(Attenuation atten) {this.attenuation = atten;}
	
	/**
	 * Returns the position of the point-light.
	 * @return Position
	 */
	public Vector3f getPosition() {return position;}
	
	/**
	 * Sets a new position to the point-light.
	 * @param Position to set
	 */
	public void setPosition(Vector3f position) {this.position = position;}

	/**
	 * Returns the range of the point-light.
	 * @return Range
	 */
	public float getRange() {return range;}
	
	/**
	 * Sets a new range to the point-light.
	 * @param Range to set
	 */
	public void setRange(float range) {this.range = range;}
	
	/**
	 * Returns the point-light's distance to the camera.
	 * @return point-light's distance
	 */
	public float getDistance() {return getPosition().sub(Level.getPlayer().getCamera().getPos()).length();}
	
}