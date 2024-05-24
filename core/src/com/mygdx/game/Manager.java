package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.LinkedList;

public class Manager {
    private static Manager Instance = null;
    public static MyGdxGame game;
    public static World world;
    boolean death = false;
    public static LinkedList<Body> toDestroy = new LinkedList<>();
    public static float x;
    public static float y;

    private Manager() {

    }

    public static void update() {
        while (!Manager.toDestroy.isEmpty()) {
            game.scheduleDestroyBody(Manager.toDestroy.get(0));
            toDestroy.remove(0);
        }
    }

    public static void SpawnFruit() {
        Fruit fruit = new Fruit((int)(Math.random() * x),(int) (Math.random() * y));

        Body body = world.createBody(fruit.bodyDef);
        body.createFixture(fruit.fixtureDef).setUserData(fruit);
    }

    public static Manager getInstance() {
        if (Instance == null) Instance = new Manager();

        return Instance;
    }

    public static void addToDestroy(final Body body) {
        toDestroy.add(body);
    }
}
