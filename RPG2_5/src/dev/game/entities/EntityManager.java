package dev.game.entities;

import dev.game.Handler;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import dev.game.entities.ID;

public class EntityManager {

    private Handler handler;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){

		@Override
		public int compare(Entity a, Entity b) {
			if(a.getID() ==  ID.Menu &&(b.getID() ==ID.PlayerTroop || b.getID() ==ID.EnemyTroop))
				return -1;
			return 1;
		}
    	
    };

    public EntityManager(Handler handler) {
        this.handler = handler;
        entities = new ArrayList<Entity>();
    }

    public EntityManager() {
    }

    public void tick() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.tick();
            if (e.getActive() == false) {
                entities.remove(e);
            }
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g) {
        entities.forEach((Entity e) -> {
            e.render(g);
        });
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public void removeEntity(Entity e) {
        entities.remove(e);
    }
    //GETTERS SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
