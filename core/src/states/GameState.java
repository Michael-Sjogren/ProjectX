package states;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import components.BodyComponent;
import managers.GameStateManager;
import systems.CameraSystem;
import systems.Families;
import systems.PlayerInputSystem;
import utils.Constants;
import utils.EntityFactory;
import world.WorldGenerator;

public class GameState extends CoreState {


    private Engine engine;
    private EntityFactory factory;
    private OrthographicCamera camera;
    // box2d
    private World box2dWorld;
    private Box2DDebugRenderer box2dRenderer;
    private WorldGenerator gameWorld;

    public GameState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void create() {
        engine = new Engine();
        camera = new OrthographicCamera(Gdx.graphics.getWidth() , Gdx.graphics.getHeight());
        // dispose
        box2dWorld = new World(Vector2.Zero,false);
        factory = new EntityFactory(engine , box2dWorld);
        // dispose
        box2dRenderer = new Box2DDebugRenderer();
        factory.createPlayerEntity();
        factory.creatDynamiceBox(32 ,32);
        gameWorld = new WorldGenerator(factory);
        gameWorld.createWorldChunks();
        createEntitySystem();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false , width , height);
    }

    @Override
    public void render() {
        gameWorld.render(camera);
        box2dRenderer.render(box2dWorld , camera.combined.cpy().scl(Constants.PPM) );
    }

    @Override
    public void update(float delta) {
        box2dWorld.step(1/60f , 6 , 2);
        engine.update(delta);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        box2dWorld.dispose();
        box2dRenderer.dispose();
    }

    private void createEntitySystem(){
        PlayerInputSystem inputSystem = new PlayerInputSystem(Families.player , camera , batch);
        engine.addSystem(inputSystem);
        engine.addSystem(new CameraSystem(Families.player , camera));
        Gdx.input.setInputProcessor(inputSystem);
    }
}
