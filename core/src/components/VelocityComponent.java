package components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class VelocityComponent implements Component {
    public float velocity;

    public VelocityComponent(float velocity) {
        this.velocity = velocity;
    }
}
