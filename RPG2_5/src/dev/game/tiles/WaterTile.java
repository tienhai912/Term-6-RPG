package dev.game.tiles;

import dev.game.gfx.Asset;

public class WaterTile extends Tile {

    public WaterTile(int id) {
        super(Asset.water, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
