package systems;

import com.badlogic.ashley.core.Family;
import components.PlayerComponent;
import components.PositionComponent;

public class Families {
    public static final Family player = Family.all(PlayerComponent.class).get();
    public static final Family entity = Family.all(PositionComponent.class).get();
}
