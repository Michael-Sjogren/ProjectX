package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import managers.LevelManager;
import utils.Constants;

/** This class defines a block in the world that is 30 x 30 tiles(32px w & h)**/
public class WorldQuadrant {

    private final MapLayers mapLayers;
   // private final TiledMapTileLayer wallLayer;
    private final MapLayer collisionLayer;
    private int mapTileHeight;
    private int mapTileWidth;
    private WorldQuadrantType quadrantType;
    private TiledMap map;
    private int mapWidth;
    private int mapHeight;
    private Tile[][] mapArray;
    private WorldGenerator worldGenerator;
    private OrthogonalTiledMapRenderer mapRenderer;

    public WorldQuadrant(WorldQuadrantType quadrantType, WorldGenerator worldGenerator) {
        this.quadrantType = quadrantType;
        this.worldGenerator = worldGenerator;
        // dispose
        map = LevelManager.loadMap(quadrantType);
        mapLayers = map.getLayers();
        collisionLayer =  mapLayers.get("Collision layer");
        mapWidth = map.getProperties().get("width",int.class) * 32;
        mapHeight = map.getProperties().get("height",int.class) * 32;
        mapTileWidth = map.getProperties().get("width",int.class);
        mapTileHeight = map.getProperties().get("height",int.class);

        // dispose
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        mapArray = new Tile[mapTileHeight][mapTileWidth];
        generateQudrant();
    }

    public void generateQudrant(){
        /*
        for (int row = 0; row < mapArray.length; row++ ){
            for (int col = 0; col < mapArray[0].length; col++ ){
                if(wallLayer.getCell(row , col) == null) continue;
                TiledMapTile t = wallLayer.getCell(row , col).getTile();

                Tile tile = new Tile(t , TileType.WALL);
                mapArray[row][col] = tile;
            }
        }
        */

        for (int i = 0; i < collisionLayer.getObjects().getCount(); i++){

            final float boxWidth = collisionLayer.getObjects().get(i).getProperties().get("width",float.class);
            final float boxHeight = collisionLayer.getObjects().get(i).getProperties().get("height",float.class);
            final float x = collisionLayer.getObjects().get(i).getProperties().get("x",float.class);
            final float y = collisionLayer.getObjects().get(i).getProperties().get("y",float.class);
            worldGenerator.getFactory().createStaticBox(boxWidth , boxHeight , x , y);
        }

    }

    public WorldQuadrantType getQuadrantType() {
        return quadrantType;
    }

    public void render(OrthographicCamera camera) {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    public Tile getTileAt(int row , int col){
     if(mapArray.length < row || mapArray[0].length < row) return null;
     return mapArray[row][col];
    }

}
