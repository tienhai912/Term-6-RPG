package dev.game.state;

import dev.game.Handler;
import dev.game.entities.EntityManager;
import dev.game.worlds.World;
import java.awt.Graphics;

public class GameState extends State {

    private World world;
    EntityManager entitymanager;

    public GameState(Handler handler, EntityManager entitymanager) {
        super(handler);
        this.entitymanager = entitymanager;
        world = new World(handler, "res/worlds/world1.txt", entitymanager);
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
