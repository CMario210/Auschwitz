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

import engine.core.Transform;
import engine.core.Vector3f;
import engine.rendering.RenderingEngine;
import engine.rendering.Shader;
import game.Level;

/**
 *
 * @author Carlos Rodriguez.
 * @version 1.1
 * @since 2018
 */
public abstract class GameComponent {
	
	private float distance;
	public String componentType = "";

	private Transform transform;
	public void input() {}
	public void render(Shader shader, RenderingEngine renderingEngine) {}
	public void update(double delta) {}
	public Transform getTransform() {return transform;}
	
	public float getDistance() {return distance;}
	public void setDistance(float distance) {this.distance = distance;}
	
    public void damage(int amt) {}
    
    /**
     * Checks the distance from the point of view.
     */
    public void checkDistance(Transform t) {
    	Vector3f playerDistance = t.getPosition().sub(Level.getPlayer().getCamera().getPos());
        setDistance(playerDistance.length());
    }
}
