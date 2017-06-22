package game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import utils.Assets;
import managers.GameStateManager;
import world.WorldGenerator;

public class Game implements ApplicationListener {
    /** pixels per meter **/
    public static final int PPM = 100;

    private GameStateManager gsm;
    private Assets assets;

    @Override
    public void create() {
        assets = new Assets();
        assets.loadAssets();
        assets.loadSkin();
        gsm = new GameStateManager(assets.getSkin());
    }

    @Override
    public void resize(int width, int height) {
        gsm.resize(width , height);
    }

    @Override
    public void render() {
        gsm.render();
        gsm.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void pause() {
        gsm.pause();
    }

    @Override
    public void resume() {
        gsm.resume();
    }

    @Override
    public void dispose() {
        gsm.dispose();
        assets.dispose();
    }
}
