/**
 * Carnage Studios
 *
 * File: GameStateManager.java
 * Authors: Sidney Nguyen (creator), Vishu Yellisetty
 * Date Created: January 13, 2016
 * Date Modified: January 14, 2016
 */

package com.carnagestudios.projectjawn.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.carnagestudios.projectjawn.Driver;

import java.util.Stack;

/**
 * This class utilizes a stack to manage various states of our game, only using the "top"/peek as
 * the "current".
 */
public class GameStateManager {

    private Stack<State> gameStates;

    /**
     * This constructor initializes the game State stack.
     */
    public GameStateManager() {
        gameStates = new Stack<State>();
    }

    /**
     * This method pushes on and starts a given game State.
     * @param gameState The state to be pushed onto the top of the gameStates stack.
     */
    public void push(State gameState) {
        gameStates.push(gameState);
    }

    /**
     * This method destroys the currently running game State.
     */
    public void pop() {
        gameStates.pop().dispose();
    }

    /**
     * This method will destroy the current game State and push & start a new given game State.
     * @param gameState The new state will become the current/peek state
     */
    public void set(State gameState) {
        gameStates.pop().dispose();
        gameStates.push(gameState);
    }

    /**
     * This method calls the update method of the currently running game State and passes in the
     * parameter dt(delta time).
     * @param dt The delta time or interval of which update method is called.
     */
    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    /**
     * This method calls the render method of the currently running game State and gives it the
     * SpriteBatch.
     * @param sb The sprite batch or "assets" to render.
     */
    public void render(SpriteBatch sb) {
            gameStates.peek().render(sb);
        }

    /**
     * Disposes all states in the stack.
     */
    public void dispose() {
        Driver.print_debug ("Disposing all States");
        for (State s: gameStates) {
            s.dispose();
        }
        Driver.print_debug ("All States disposed");
    }
}
