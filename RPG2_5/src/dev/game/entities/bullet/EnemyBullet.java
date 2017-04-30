package dev.game.entities.bullet;

import dev.game.Handler;
import dev.game.entities.EntityManager;
import dev.game.entities.ID;
import dev.game.entities.battleentity.Trail;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class EnemyBullet extends Bullet {

    private int count = 0;
    private int Xmove, Ymove;
    private Random r;

    public EnemyBullet(EntityManager entitymanager, Handler handler, float x, float y, ID id, int atk, int speed) {
        super(entitymanager, handler, x, y, id, atk, speed);
        r = new Random();
        Ymove = r.nextInt(speed) + 3;
        Xmove = (r.nextInt(speed * 2) - 5);
    }

    @Override
    public void tick() {
        count++;
        x += Xmove;
        y += Ymove;
        if (y <= 0 || y >= handler.getHeight() - 50) {
            Ymove *= -1;
        }
        if (x <= 0 || x >= handler.getHeight() - 10) {
            Xmove *= -1;
        }

        if (count == 150) {
            active = false; //Đạn biến mất sau 1 time    
        }
        entitymanager.addEntity(new Trail(handler, x, y, width, height, ID.Trail, Color.red, 0.1f));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillOval((int) x, (int) y, width, height);
    }

    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
