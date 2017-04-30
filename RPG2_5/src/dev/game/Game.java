package dev.game;

import dev.game.display.Display;
import dev.game.entities.EntityManager;
import dev.game.gfx.Asset;
import dev.game.gfx.GameCamera;
import dev.game.input.KeyManager;
import dev.game.input.MouseManager;
import dev.game.state.GameState;
import dev.game.state.MenuState;
import dev.game.state.State;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //STATE
    public State gameState;
    public State menuState;

    //INPUT
    private KeyManager keymanager;
    private MouseManager mousemanager;

    //CAMERA
    private GameCamera gamecamera;

    //Handler
    private Handler handler;
    private EntityManager entitymanager;
    //nhập thông số từ launcher

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
    }
    //Khởi tạo

    private void init() {
        display = new Display(title, width, height);

        keymanager = new KeyManager();
        display.getFrame().addKeyListener(keymanager);
        mousemanager = new MouseManager();
        display.getFrame().addMouseListener(mousemanager);
        display.getFrame().addMouseMotionListener(mousemanager);
        display.getCanvas().addMouseListener(mousemanager);
        display.getCanvas().addMouseMotionListener(mousemanager);

        Asset.intit();
        handler = new Handler(this);
        gamecamera = new GameCamera(handler, 0, 0);

        entitymanager = new EntityManager(handler);
        gameState = new GameState(handler, entitymanager);
        menuState = new MenuState(handler);

        State.setState(menuState);
    }
    //Tick là thay đổi liên quan tới chuyển động

    private void tick() {
        keymanager.tick(); //thay đổi mỗi khi ấn nút
        if (State.getState() != null) //thay đổi ở trong từng state
        {
            State.getState().tick();
        }
    }
    //render là thay đổi liên quan tới hình ảnh

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!
        if (State.getState() != null) //thay đổi ở trong từng state
        {
            State.getState().render(g);
        }
        //End Drawing!
        bs.show();
        g.dispose();
    }

    public void run() { //Game loop

        init();
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//SETTER AND GETTER

    public KeyManager getKeyManager() {
        return keymanager;
    }

    public MouseManager getMouseManager() {
        return mousemanager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameCamera getGameCamera() {
        return gamecamera;
    }
}
