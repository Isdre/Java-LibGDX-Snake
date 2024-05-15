package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

public class Node {
    public Body body;
    private World _world;

    private Node next;
    static boolean addNode = false;

    public Node(World world, float x, float y) {
        _world = world;
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10, 10);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        this.body = world.createBody(bodyDef);
        this.body.createFixture(fixtureDef).setUserData(this);
    }

    public void setPosition(float x, float y) {
        float oldX = body.getPosition().x;
        float oldY = body.getPosition().y;

        body.setTransform(x,y,0);

        if (next != null) {
            next.setPosition(oldX, oldY);
        } else if (addNode) {
            next = new Node(_world, oldX, oldY);
            addNode = false;
        }
    }

    public void hit() {
        System.out.println("Hit!");
    }
}