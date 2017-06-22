package systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import components.*;
import utils.Constants;

import java.awt.*;


public class PlayerInputSystem extends IteratingSystem implements InputProcessor {


    private final OrthographicCamera cam;
    private final SpriteBatch batch;
    private ShapeRenderer renderer;
    private Sprite playerSprite;

    public PlayerInputSystem(Family family , OrthographicCamera cam , SpriteBatch batch) {
        super(family);
        this.cam = cam;
        this.batch = batch;
        playerSprite = new Sprite(new Texture("images/player.png"));
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BodyComponent b = Mappers.BODY_M.get(entity);
        VelocityComponent v = Mappers.VELCOITY_M.get(entity);
        movePlayer(b , v , deltaTime);
        playerSprite.setPosition(b.body.getPosition().x * Constants.PPM - (32/2), b.body.getPosition().y * Constants.PPM - (32/2));
       // playerSprite.setRotation(b.body.getAngle());
        batch.begin();
        batch.setProjectionMatrix(cam.combined);
        playerSprite.draw(batch);
        batch.end();
        setPlayerAim(b);
    }

    @Override
    public boolean keyDown(int keycode) {

        return Gdx.input.isKeyPressed(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private void movePlayer(BodyComponent b , VelocityComponent v, float deltaTime){
        int forceX = 0;
        int forceY = 0;

        if(keyDown(Input.Keys.A)){
            forceX -= 1;
        }
        else if(keyDown(Input.Keys.D)){
            forceX += 1;
        }

        if(keyDown(Input.Keys.W)){
            forceY +=1;
        }
        else if(keyDown(Input.Keys.S)){
            forceY -= 1;
        }

        b.body.setLinearVelocity(forceX * v.velocity * deltaTime, forceY * v.velocity * deltaTime);
    }

    private void setPlayerAim(BodyComponent bodyComponent){
        final float mouseX = Gdx.input.getX();
        final float mouseY = Gdx.input.getY();
        final float playerX = bodyComponent.body.getPosition().x * Constants.PPM;
        final float playerY = bodyComponent.body.getPosition().y * Constants.PPM;
        final float angle = MathUtils.atan2(playerX - mouseX , playerY - mouseY) * MathUtils.radiansToDegrees;
        System.out.println(angle);
        playerSprite.setRotation(angle);
      //  bodyComponent.body.getTransform().setRotation(angle);
    }
}
