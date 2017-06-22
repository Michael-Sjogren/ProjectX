package systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import components.BodyComponent;
import components.Mappers;
import components.PositionComponent;
import utils.Constants;

public class CameraSystem extends IteratingSystem {

    private OrthographicCamera camera;

    public CameraSystem(Family family , OrthographicCamera camera) {
        super(family);
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final float x = camera.position.x;
        final float y = camera.position.y;
        BodyComponent b = Mappers.BODY_M.get(entity);
        final float playerX =  b.body.getPosition().x * Constants.PPM;
        final float playerY =  b.body.getPosition().y * Constants.PPM;

        final float cx = MathUtils.lerp(x , playerX , 0.075f);
        final float cy = MathUtils.lerp(y , playerY , 0.075f);
        camera.position.set(cx , cy , 0f);
        camera.update();
    }
}
