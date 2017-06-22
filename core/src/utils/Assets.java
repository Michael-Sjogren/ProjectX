package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets{

    public AssetManager manager;
    public AssetDescriptor<TextureAtlas> atlas;
    private Skin skin;


    public Assets(){
        manager = new AssetManager(new InternalFileHandleResolver());
        atlas = new AssetDescriptor<TextureAtlas>("skin/uiskin.atlas", TextureAtlas.class);
    }
    public void loadAssets(){
        manager.load( atlas );
        manager.finishLoading();
        System.out.println(manager.getQueuedAssets());
    }

    public void loadSkin() {
        skin = new Skin(manager.get(atlas));
        skin.load(Gdx.files.internal("skin/uiskin.json"));
    }

    public Skin getSkin() {
        return skin;
    }

    public void dispose(){
        manager.dispose();
    }
}
