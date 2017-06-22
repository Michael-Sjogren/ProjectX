package states;

import managers.GameStateManager;

public class MenuState extends CoreState {
    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void create() {
        super.create();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render() {
        System.out.println("menu");
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void update(float delta) {

    }
}
