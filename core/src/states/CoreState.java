package states;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import managers.GameStateManager;

public abstract class CoreState implements ApplicationListener {

    protected GameStateManager gsm;
    protected SpriteBatch batch;


    public CoreState(GameStateManager gsm) {
        this.gsm = gsm;
        batch = new SpriteBatch();
    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    public abstract void update(float delta);

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
