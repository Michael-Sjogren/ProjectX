package systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import components.Mappers;
import components.PositionComponent;

public class EntityRenderSystem extends IteratingSystem {

    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    public EntityRenderSystem(Family family , OrthographicCamera camera) {
        super(family);
        this.camera = camera;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent p = Mappers.POSITION_M.get(entity);
        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.rect(p.pos.x , p.pos.y , 32, 32);
        shapeRenderer.end();
    }
}
