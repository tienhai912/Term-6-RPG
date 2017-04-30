package dev.game.entities.combattroop;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.entities.EntityManager;
import dev.game.entities.ID;
import dev.game.entities.bullet.EnemyBullet;
import dev.game.entities.bullet.EnemySmartBullet;
import dev.game.entities.bullet.PlayerBullet;
import dev.game.gfx.Animation;
import dev.game.gfx.Asset;
import java.awt.Color;
import java.awt.Graphics;

public class EnemyTroop extends CombatTroop {

    private final float inital;
    private final float se;
    private int count = 0;
    private int[][] MonIndex;
    private int MonNumber;
    private Animation Anitemp;

    public EnemyTroop(int[][] MonIndex, int MonNumber, EntityManager entitymanager, Handler handler, float x, float y, ID id) {
        super(entitymanager, handler, x, y, id);
        this.MonIndex = MonIndex;
        this.MonNumber = MonNumber;
        inital = x;
        setHealth(MonIndex[MonNumber][3]);
        setAtk(MonIndex[MonNumber][4]);
        setDef((float) MonIndex[MonNumber][5] / 100);
        setSpeed(MonIndex[MonNumber][6]);
        se = 200.0f / health;

        if (MonIndex[MonNumber][2] == 1) {
            Anitemp = new Animation(500, Asset.enemy1);
        } else if (MonIndex[MonNumber][2] == 2) {
            Anitemp = new Animation(500, Asset.enemy2);
        } else if (MonIndex[MonNumber][2] == 3) {
            Anitemp = new Animation(500, Asset.enemy3);
        } else {
            Anitemp = new Animation(500, Asset.enemy4);
        }
    }

    @Override
    public void tick() {
        Anitemp.tick();
        x += speed;
        if (x <= inital - 150 || x >= inital + 150) {
            speed *= -1; //lock vị trí cách 80px   
        }

        count++;
        if (count % MonIndex[MonNumber][7] == 0) {
            entitymanager.addEntity(new EnemyBullet(entitymanager, handler, x + width / 2, y + height, ID.EnemyBullet, atk,MonIndex[MonNumber][9]));
        }
        if (count % MonIndex[MonNumber][8] == 0) {
            entitymanager.addEntity(new EnemySmartBullet(entitymanager, handler, x + width / 2, y + height, ID.SmartEnemyBullet, atk,MonIndex[MonNumber][9]));
        }

        Entity e = checkEntityCollisions(0f, 0f);
        if (e != null && e.getID() == ID.PlayerBullet) {
            PlayerBullet bullet = (PlayerBullet) e;
            hurt((int) (bullet.getAtk() * def));
            bullet.setActive(false);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Anitemp.getCurrentFrame(), (int) (x), (int) (y), width, height, null);
        //Enemy health bar
        g.setColor(Color.decode("#FF304F"));
        g.fillRect(650, 30, 200, 20);
        g.setColor(Color.black);
        g.drawRect(650, 30, 200, 20);
        g.setColor(Color.decode("#28C7FA"));
        g.fillRect(650, 30, (int) (health * se), 20);
        if (health <= 0) {
            g.drawString("Enemy's HP :" + 0, 650, 20);
        } else {
            g.drawString("Enemy's HP :" + health, 650, 20);
        }
    }

    @Override
    public void die() {
    }

}
