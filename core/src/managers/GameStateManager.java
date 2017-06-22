package managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import states.CoreState;
import states.GameState;
import states.MenuState;
import states.States;

public class GameStateManager {

    private static CoreState currentState;
    private Skin skin;

    public GameStateManager(Skin skin) {
        this.skin = skin;
        setState(States.GAME);

    }

    public void setState(States state){
        switch (state){
            case GAME: currentState =  new GameState(this); break;
            case MENU: currentState = new MenuState(this); break;
            default: break;
        }
        currentState.create();
    }

    public void update(float delta){
        currentState.update(delta);
    }

    public void render(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        currentState.render();
    }

    public void resize(int width , int height){
        currentState.resize(width , height);
    }

    public void pause(){
        currentState.pause();
    }

    public void resume(){
        currentState.resume();
    }

    public void dispose() {
        currentState.dispose();
    }

    public Skin getSkin(){
        return skin;
    }
}
