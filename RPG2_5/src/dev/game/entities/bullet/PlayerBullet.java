package dev.game.entities.bullet;

import dev.game.Handler;
import dev.game.entities.EntityManager;
import dev.game.entities.ID;
import dev.game.entities.battleentity.Trail;
import java.awt.Color;
import java.awt.Graphics;

public class PlayerBullet extends Bullet {

    public PlayerBullet(EntityManager entitymanager, Handler handler, float x, float y, ID id, int atk, int speed) {
        super(entitymanager, handler, x, y, id, atk, speed);
    }

    @Override
    public void tick() {
        y -= 5;
        if (y <= 0) {
            active = false;
        }
        entitymanager.addEntity(new Trail(handler, x, y, 10, 10, ID.Trail, Color.blue, 0.1f));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval((int) x, (int) y, width, height);
    }

    @Override
    public void die() {
    }

}
