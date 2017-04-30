package dev.game.entities.battleentity;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.entities.ID;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Trail extends Entity {

    private float alpha = 1;
    private Color color;
    private float life;

    public Trail(Handler handler, float x, float y, int width, int height, ID id, Color color, float life) {
        super(handler, x, y, width, height, id);
        this.color = color;
        this.life = life;
    }

    @Override
    public void tick() {
        if (alpha > life) {
            alpha -= life - 0.01f;
        } else {
            this.active = false;
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillOval((int) x, (int) y, 10, 10);

        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    @Override
    public void die() {
    }
}
