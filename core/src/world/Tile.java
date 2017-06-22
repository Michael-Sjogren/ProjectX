package world;

import com.badlogic.gdx.maps.tiled.TiledMapTile;

public class Tile {
    private int id;
    private TileType type;
    private int colbox_width;
    private int colbox_height;

    public Tile(TiledMapTile tile , TileType type) {
        this.id = tile.getId();
        this.type = type;

        if(type == TileType.WALL){
            colbox_width = tile.getProperties().get("colbox_width",int.class);
            colbox_height = tile.getProperties().get("colbox_height",int.class);

        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public int getColbox_width() {
        return colbox_width;
    }

    public void setColbox_width(int colbox_width) {
        this.colbox_width = colbox_width;
    }

    public int getColbox_height() {
        return colbox_height;
    }

    public void setColbox_height(int colbox_height) {
        this.colbox_height = colbox_height;
    }

}
