package dev.game.entities.bullet;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.entities.EntityManager;
import dev.game.entities.ID;

public abstract class Bullet extends Entity {

    public static final int DEFAULT_BULLET_WIDTH = 10, DEFAULT_BULLET_HEIGHT = 10;
    EntityManager entitymanager;
    protected int atk;
    protected int speed;

    public Bullet(EntityManager entitymanager, Handler handler, float x, float y, ID id, int atk, int speed) {
        super(handler, x, y, DEFAULT_BULLET_WIDTH, DEFAULT_BULLET_HEIGHT, id);
        this.entitymanager = entitymanager;
        this.atk = atk;
        this.speed = speed;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
