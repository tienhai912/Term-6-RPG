package dev.game.tiles;

import dev.game.gfx.Asset;

public class RockTile extends Tile {

    public RockTile(int id) {
        super(Asset.rock, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
