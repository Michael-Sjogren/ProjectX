package components;


import com.badlogic.ashley.core.ComponentMapper;


public class Mappers {
    public static final ComponentMapper<PositionComponent> POSITION_M = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> VELCOITY_M = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<BodyComponent> BODY_M = ComponentMapper.getFor(BodyComponent.class);
    public static final ComponentMapper<MousePositionComponent> MOUSE_POS_M = ComponentMapper.getFor(MousePositionComponent.class);
    public static final ComponentMapper<AngleComponent> ANGLE_M = ComponentMapper.getFor(AngleComponent.class);
}
