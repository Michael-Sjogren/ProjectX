package utils;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import components.*;

public class EntityFactory {

    private Engine engine;
    private World world;
    private Entity player;

    public EntityFactory(Engine engine , World world) {
        this.engine = engine;
        this.world = world;
    }

    public void createPlayerEntity(){
        player = new Entity();

        PositionComponent p = new PositionComponent(new Vector2(Gdx.graphics.getWidth() / 2  / Constants.PPM, Gdx.graphics.getHeight() / 2 / Constants.PPM));
        // box2d
        BodyDef def = createBodyDef(BodyDef.BodyType.DynamicBody , p.pos , true);
        Body body = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / Constants.PPM, 16 / Constants.PPM);
        body.createFixture(shape , 1);
        shape.dispose();

        player.add(new PlayerComponent())
                .add(p)
                .add(new VelocityComponent(1000))
                .add(new BodyComponent(body))
                .add(new AngleComponent(0f))
                .add(new MousePositionComponent(new Vector2(0 ,0)));
        engine.addEntity(player);
    }

    public void creatDynamiceBox(float w , float h){
        BodyDef def = createBodyDef(BodyDef.BodyType.DynamicBody , new Vector2(Gdx.graphics.getWidth() / 2 / Constants.PPM , Gdx.graphics.getHeight()  / 2 / Constants.PPM)  , true);
        Body body = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w / 2 / Constants.PPM, h / 2 / Constants.PPM);
        body.createFixture(shape , 100);
        shape.dispose();
    }

    public void createStaticBox(float w , float h , float x , float y){
        // need to add x with half of the width from the box same with y
        BodyDef def = createBodyDef(BodyDef.BodyType.StaticBody , new Vector2((x + w / 2) / Constants.PPM , (y + h / 2) / Constants.PPM)  , true);
        Body body = world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w / 2 / Constants.PPM, h / 2 / Constants.PPM);
        body.createFixture(shape , 1);
        shape.dispose();
    }

    public void createChain(Vector2[] v){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(Gdx.graphics.getWidth() / 2 / Constants.PPM , Gdx.graphics.getHeight() / 2 / Constants.PPM);
        ChainShape chainShape = new ChainShape();

        chainShape.createChain(v);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = chainShape;
        fdef.filter.categoryBits = 1;
        fdef.filter.maskBits = -1;
        fdef.isSensor = false;

        world.createBody(bodyDef).createFixture(fdef);
    }

    public Entity getPlayer(){
        return player;
    }

    public BodyDef createBodyDef(BodyDef.BodyType type , Vector2 position , boolean isFixedRotation){
        BodyDef def = new BodyDef();
        def.type = type;
        def.position.set(position);
        def.fixedRotation = isFixedRotation;
        return def;
    }
}
