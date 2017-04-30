package dev.game.entities.bullet;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.entities.EntityManager;
import dev.game.entities.ID;
import dev.game.entities.battleentity.Trail;
import dev.game.entities.combattroop.PlayerTroop;
import java.awt.Color;
import java.awt.Graphics;

public class EnemySmartBullet extends Bullet {

    private float Xmove, Ymove;
    private int count = 0;

    public EnemySmartBullet(EntityManager entitymanager, Handler handler, float x, float y, ID id, int atk, int speed) {
        super(entitymanager, handler, x, y, id, atk, speed);
        Xmove = speed;
        Ymove = speed;
    }

    @Override
    public void tick() {
        count++;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getID() == ID.PlayerTroop) {
                PlayerTroop player = (PlayerTroop) e;
                x += Xmove;
                y += Ymove;
                float diffX = x - player.getX() - 10;
                float diffY = y - player.getY() - 10;
                float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
                Xmove = ((float) ((-1.0 / distance) * diffX)) * 2;
                Ymove = ((float) ((-1.0 / distance) * diffY)) * 2;
            }
        }

        if (y <= 0 || y >= handler.getHeight() - 50) {
            Ymove *= -1;
        }
        if (x <= 0 || x >= handler.getHeight() - 10) {
            Xmove *= -1;
        }

        if (count == 500) {
            active = false;
        }

        entitymanager.addEntity(new Trail(handler, x, y, width, height, ID.Trail, Color.ORANGE, 0.1f));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval((int) x, (int) y, width, height);
    }

    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
