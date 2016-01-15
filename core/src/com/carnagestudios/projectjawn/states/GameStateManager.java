package com.carnagestudios.projectjawn.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Utilizes a stack to manage various states of our game, only using the "top"/peek as the "current".
 */
public class GameStateManager {

    private Stack<State> gameStates;

    /**
     * Constructor
     * Initializes the gameStates stack.
     */
    public GameStateManager() {
        gameStates = new Stack<State>();
    }

    /**
     * Pushes the passed in state onto the GameStateManager stack.
     * @param gameState The state to be pushed onto the top of the gameStates stack.
     */
    public void push(State gameState) {
        gameStates.push(gameState);
    }

    /**
     * Pops the top state on the GameStateManager stack.
     */
    public void pop() {
        gameStates.pop().dispose();
    }

    /**
     * This method will remove the top state on the gamestack stack
     *  and replace it with the passed in state (pushed on in place of
     *  previoius peek state).
     * @param gameState The new state will become the current/peek state
     */
    public void set(State gameState) {
        gameStates.pop().dispose();
        gameStates.push(gameState);
    }

    /**
     * Calls the update method of the peek state and passes in the parameter dt(delta time).
     * @param dt The delta time or interval of which update method is called.
     */
    public void update(float dt) {
        gameStates.peek().update(dt);
    }

    /**
     * Calls the render method of the peek state and gives it the sprite batch.
     * @param sb The sprite batch or "assets" to render.
     */
    public void render(SpriteBatch sb) {
            gameStates.peek().render(sb);
        }

    /**
     * Disposes all states in the stack.
     */
    public void dispose() {
        System.err.println("Disposing all States");
        for (State s: gameStates) {
            s.dispose();
        }
        System.err.println("All States disposed");
    }
}
