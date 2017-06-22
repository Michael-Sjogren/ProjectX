package managers;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import world.WorldQuadrantType;

public abstract class LevelManager {

    private static TmxMapLoader loader = new TmxMapLoader();

    public LevelManager() {}

    public static TiledMap loadMap(WorldQuadrantType type){
        TiledMap map = null;
        switch (type){
            case BASE: map = loader.load("map/base.tmx"); break;
            case RANDOM: map = loader.load("map/base.tmx"); break;
        }
        return map;
    }

}
