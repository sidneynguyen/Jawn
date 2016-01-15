package com.carnagestudios.projectjawn.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> gameStates;


    public GameStateManager() {
        gameStates = new Stack<State>();
    }


    public void push(State gameState) {
        gameStates.push(gameState);
    }


    public void pop() {
        gameStates.pop().dispose();
    }


    public void set(State gameState) {
        gameStates.pop().dispose();
        gameStates.push(gameState);
    }


    public void update(float dt) {
        gameStates.peek().update(dt);
    }


    public void render(SpriteBatch sb) {
            gameStates.peek().render(sb);
        }


    public void dispose() {
        System.err.println("Disposing all States");
        for (State s: gameStates) {
            s.dispose();
        }
        System.err.println("All States disposed");
    }
}
