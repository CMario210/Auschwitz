/*
 * Copyright 2017 Carlos Rodriguez.
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
package engine.core;

import org.lwjgl.opengl.Display;

import engine.rendering.RenderUtil;
import engine.rendering.Window;

/**
*
* @author Carlos Rodriguez
* @version 1.5
* @since 2017
*/
public class CoreEngine {
	
	private int width;
	private int height;
	private double frameTime;
	private Game game;
	private boolean isRunning;

    /**
     * Constructor for the core of the game to compile.
     */
    public CoreEngine() {
        RenderUtil.initGraphics();
        isRunning = false;
    }

	/**
	 * Constructor for the engine display.
	 * @param width of the display.
	 * @param height of the display.
	 * @param framerate of the display.
	 */
	public CoreEngine(int width, int height, double framerate, Game game) {
		this.width = width;
		this.height = height;
		this.frameTime = 1.0/framerate;
		this.game = game;
	}
	
	/**
	 * Method that creates the window for the program.
	 * @param title of the window.
	 * @param fullscreen If its windowed or full-screen.
	 */
	public void createWindow(String title, int aliasing, boolean fullscreen) {	
		Window.createWindow(width, height, aliasing, title, fullscreen);
	}

    /**
     * Method that declares that the game should run.
     */
    public void start() {

        if (isRunning) {
            return;
        }
        
        runGame();
    }

    /**
     * Method that declares that the game should stop.
     */
    public void stop() {

        if (!isRunning) {
            return;
        }

        isRunning = false;
    }

    /**
     * Method that sets everything to run, for example the engine, the frames
     * per second, the frame-times, the time of compiling, the processing
     * states, etc.
     */
    private void runGame() {
    	
    	isRunning = true;

		@SuppressWarnings("unused")
		int frames = 0;
        long frameCounter = 0;
        
        game.init();

        double lastTime = Time.getTime();
        double unprocessedTime = 0;

        while (isRunning) {

            boolean render = false;

            double startTime = Time.getTime();
            double passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime;
            frameCounter += passedTime;

            while (unprocessedTime > frameTime) {

                render = true;

                unprocessedTime -= frameTime;

                if (Window.isCloseRequested()) {
                    stop();
                }

                Time.setDelta(frameTime);

                game.input();
                Input.update();
                
                game.update();

                if (frameCounter >= 1.0) {
                    //System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }
            if (render) {
                render();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        cleanUp();
    }

    /**
     * Method that renders everything to render.
     */
    private void render() {
        RenderUtil.clearScreen();
        game.render();
        
        Display.update();
    }

	
	/**
	 * Method that sets to run everything when the program ask it for.
	 */
	public void run() {
		while(!Window.isCloseRequested()) {
			Window.updateMenu();
			Window.renderMenu();
			//Update Window
		}
		cleanUp();
	}
	
	/**
     * Method that cleans everything in the program's window.
     */
    private void cleanUp() {
        Window.dispose();
    }

}