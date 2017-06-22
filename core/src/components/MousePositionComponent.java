package components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class MousePositionComponent implements Component {
    public Vector2 position;

    public MousePositionComponent(Vector2 position) {
        this.position = position;
    }
}
