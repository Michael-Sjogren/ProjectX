package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import utils.EntityFactory;

/** This class generates all the quadrants that fill the world
 * , the world size will be 5 x 5 WorldQuadrants , 1 WorldQuadrant is 30 x 30 tiles
 * , 1 tile is w 32 x  h 32 **/
public class WorldGenerator {

    private EntityFactory factory;

    public WorldGenerator(EntityFactory factory) {
        this.factory = factory;
    }

    private WorldQuadrant[][] map;

    public void createWorldChunks(){
        map = new WorldQuadrant[20][20];
        for (int x = 0; x < map.length; x++){
            for (int y = 0; y < map[0].length; y++){
                WorldQuadrantType quadrantType = WorldQuadrantType.RANDOM;
                if(x == map.length / 2 && y == map[0].length / 2) {
                    quadrantType = WorldQuadrantType.BASE;
                }
                WorldQuadrant quadrant = new WorldQuadrant(quadrantType , this);
                map[x][y] = quadrant;
            }
        }
    }

    public WorldQuadrant[][] getMap(){
        return map;
    }

    public EntityFactory getFactory(){
        return factory;
    }

    public void render(OrthographicCamera camera){
        map[2][2].render(camera);
    }

    public WorldQuadrant getBase(){
        return map[map.length / 2][map[0].length / 2];
    }
}
